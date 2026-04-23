package user_service.user_service.controller;

import user_service.user_service.entity.User;
import user_service.user_service.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // REGISTER
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

    // GET ALL
    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted";
    }

    // UPDATE
    @PutMapping("/{id}")
    public User update(@PathVariable Long id,
                       @RequestBody User user) {
        return userService.updateUser(id, user);
    }
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) {
        return userService.forgotPassword(email);
    }
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String newPassword) {
        return userService.resetPassword(token, newPassword);
    }

    @PostMapping("/logout")
    public String logout() {
        return "Logged out successfully";
    }
}