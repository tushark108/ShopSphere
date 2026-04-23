package user_service.user_service.repository;

import user_service.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String token);

    Optional<Object> findByEmailIgnoreCase(String email);
}