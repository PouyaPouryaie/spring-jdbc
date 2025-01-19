package ir.bigz.springboot.springjdbc.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Repository
public class UserRepository {

    Logger logger = LogManager.getLogger(UserRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly=true)
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users", new UserRowMapper());
    }

    @Transactional(readOnly=true)
    public User findUserById(long id) {
        try {
            return jdbcTemplate.queryForObject("select * from users where id=?", new UserRowMapper(), new Object[]{id});
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User create(final User user) {
        final String sql = "insert into users(name,email, created_on) values(?,?, NOW())";

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            return ps;
        }, holder);

        //holder.getKeys().keySet().stream().forEach(System.out::println);
        Integer newUserId = (Integer) holder.getKeys().get("id");
        logger.debug("id is : " + newUserId);
        user.setId(newUserId);

        Timestamp createdOn = (Timestamp) holder.getKeys().get("created_on");
        LocalDateTime localDateTime = createdOn.toLocalDateTime().truncatedTo(ChronoUnit.SECONDS);
        user.setCreatedOn(localDateTime);

        return user;
    }
}

class UserRowMapper implements RowMapper<User>
{

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        Timestamp createdOn = rs.getTimestamp("created_on");

        if(createdOn != null) {
            user.setCreatedOn(createdOn.toLocalDateTime().truncatedTo(ChronoUnit.SECONDS));
        }

        return user;
    }
}
