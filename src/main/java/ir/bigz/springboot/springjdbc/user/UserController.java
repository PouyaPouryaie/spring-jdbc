package ir.bigz.springboot.springjdbc.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends User> findUserById(@PathVariable long id) {
        User userById = userService.findUserById(id);

        if (userById == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userById);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {

        try {
            userService.createUser(user);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody User user) {

        try {
            userService.updateUserWithRetryAndOptimisticLocking(id, user);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
