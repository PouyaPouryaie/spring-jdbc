package ir.bigz.springboot.springjdbc.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends User> findCustomerById(@PathVariable long id) {
        User customerById = userRepository.findUserById(id);

        if (customerById == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerById);
    }

}
