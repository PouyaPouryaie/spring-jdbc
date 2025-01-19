package ir.bigz.springboot.springjdbc.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private static final int MAX_RETRIES = 3;

    private final static Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly=true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public User createUser(User user) {
        return userRepository.create(user);
    }

    @Transactional
    public void updateUserWithRetryAndOptimisticLocking(long id, User user) {

        int attempts = 0;
        while (attempts < MAX_RETRIES) {
            try {
                User latestUser = findUserById(id);
                if (latestUser == null) {
                    throw new RuntimeException("User with id " + user.getId() + " not found");
                }

                latestUser.setName(user.getName());
                latestUser.setEmail(user.getEmail());
                userRepository.updateUser(id, latestUser);
                return;
            } catch (OptimisticLockingFailureException ex) {
                attempts++;
                log.info("Optimistic locking conflict (attempt #{}: {}", attempts, ex.getMessage());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Retry interrupted", e);
                }
            }
        }
        throw new RuntimeException("Failed to update user after " + MAX_RETRIES + " retries.");
    }
}
