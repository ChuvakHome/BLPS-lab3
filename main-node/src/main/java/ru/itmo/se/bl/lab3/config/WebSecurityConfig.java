package ru.itmo.se.bl.lab3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.itmo.se.bl.lab3.entity.UserRole;
import ru.itmo.se.bl.lab3.exception.UserNotFoundException;
import ru.itmo.se.bl.lab3.security.BCryptPassportEncoder;
import ru.itmo.se.bl.lab3.security.CustomBCryptPasswordEncoder;
import ru.itmo.se.bl.lab3.security.PassportEncoder;
import ru.itmo.se.bl.lab3.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and().csrf().disable()
                .httpBasic().and()
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.DELETE, "/api/hotel", "api/tour")
                            .hasAuthority(UserRole.ADMIN.getAsAuthority())
                        .requestMatchers("/api/admin/**")
                            .hasAuthority(UserRole.ADMIN.getAsAuthority())
                        .anyRequest().permitAll())
                .formLogin()
                    .permitAll()
                .and()
                .logout()
                    .permitAll()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().build();
    }

    @Bean
    public PassportEncoder passportEncoder() {
        return new BCryptPassportEncoder(20);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomBCryptPasswordEncoder(12);
    }

    @Bean
    protected UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Autowired
            private UserService service;

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                try {
                    ru.itmo.se.bl.lab3.entity.User user = service.getByLogin(username);

                    return User.builder()
                            .username(user.getLogin())
                            .password(user.getPasswordHash())
                            .roles(user.getRole().name())
                            .build();
                } catch (UserNotFoundException e) {
                    throw new UsernameNotFoundException(username);
                }
            }
        };
    }
}
