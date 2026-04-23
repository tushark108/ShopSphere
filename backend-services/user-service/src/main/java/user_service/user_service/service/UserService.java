package user_service.user_service.service;

import user_service.user_service.entity.User;
import user_service.user_service.repository.UserRepository;
import user_service.user_service.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final EmailService emailService; // ✅ NEW

    public UserService(UserRepository userRepository,
                       JwtUtil jwtUtil,
                       EmailService emailService) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.emailService = emailService;
    }

    // ✅ REGISTER
    public User registerUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        if (user.getRole() == null) {
            user.setRole("USER");
        }

        return userRepository.save(user);
    }

    // ✅ LOGIN (JWT)
    public String login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email does not exist"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }

    // ✅ GET ALL
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ GET BY ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ✅ DELETE
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // ✅ UPDATE
    public User updateUser(Long id, User updatedUser) {
        User user = getUserById(id);

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());

        return userRepository.save(user);
    }

    // ✅ FORGOT PASSWORD (UPDATED WITH EMAIL)
    public String forgotPassword(String email) {

        User user = (User) userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();

        user.setResetToken(token);
        user.setTokenExpiry(System.currentTimeMillis() + 1000 * 60 * 10); // 10 min

        userRepository.save(user);

        // 🔥 SEND EMAIL
        emailService.sendResetEmail(user.getEmail(), token);

        return "Reset link sent to email";
    }

    // ✅ RESET PASSWORD
    public String resetPassword(String token, String newPassword) {

        User user = userRepository.findByResetToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (user.getTokenExpiry() < System.currentTimeMillis()) {
            throw new RuntimeException("Token expired");
        }

        user.setPassword(newPassword);

        user.setResetToken(null);
        user.setTokenExpiry(null);

        userRepository.save(user);

        return "Password reset successful";
    }
}