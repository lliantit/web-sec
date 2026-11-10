package chnu.edu.websec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        // публічний доступ
                        .requestMatchers("/", "/index.html").permitAll()

                        // тестові маршрути (визначені вище загального патерну /api/v1/cars/**)
                        .requestMatchers("/api/v1/cars/hello/admin").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers("/api/v1/cars/hello/user").hasRole("USER")
                        .requestMatchers("/api/v1/cars/hello/man").hasAnyRole("USER", "ADMIN", "SUPERADMIN")

                        // REST CRUD операції
                        .requestMatchers(HttpMethod.GET, "/api/v1/cars", "/api/v1/cars/**").hasAnyRole("USER", "ADMIN", "SUPERADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/cars").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/cars", "/api/v1/cars/**").hasAnyRole("ADMIN", "SUPERADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/cars/**").hasRole("SUPERADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}user")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();

        UserDetails superadmin = User.builder()
                .username("superadmin")
                .password("{noop}superadmin")
                .roles("SUPERADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin, superadmin);
    }
}