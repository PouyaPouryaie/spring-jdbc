package ir.bigz.springboot.springjdbc.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepositoryExtendCrudRepo extends CrudRepository<User, Long> {
}
