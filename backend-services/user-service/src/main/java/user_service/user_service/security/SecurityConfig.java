package user_service.user_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // ✅ PUBLIC APIs (NO TOKEN REQUIRED)
                        .requestMatchers(
                                "/user/login",
                                "/user/register",
                                "/user/forgot-password",
                                "/user/reset-password"
                        ).permitAll()

                        // ✅ ADMIN ONLY
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")

                        // ✅ USER + ADMIN
                        .requestMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")

                        // ✅ EVERYTHING ELSE
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}