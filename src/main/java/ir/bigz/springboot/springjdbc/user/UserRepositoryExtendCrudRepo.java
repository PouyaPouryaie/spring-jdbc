package ir.bigz.springboot.springjdbc.user;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepositoryExtendCrudRepo extends CrudRepository<User, Long> {


    @Modifying
    @Query("UPDATE users set name = :name, email = :email, updated_on = NOW(), version = version + 1 where id = :id and version = :version")
    boolean updateUser(@Param("id") long id, @Param("name") String name, @Param("email") String email, @Param("version") int version);
}
