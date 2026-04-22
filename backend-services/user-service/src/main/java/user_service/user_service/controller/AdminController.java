package user_service.user_service.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome Admin 🔥";
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "Admin deleted user with ID: " + id;
    }
}