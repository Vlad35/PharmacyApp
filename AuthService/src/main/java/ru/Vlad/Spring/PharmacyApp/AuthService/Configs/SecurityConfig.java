package ru.Vlad.Spring.PharmacyApp.AuthService.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.Vlad.Spring.PharmacyApp.AuthService.Service.CustomerDetailsService;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomerDetailsService myUserDetailsService;

    @Autowired
    public SecurityConfig(CustomerDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        //authenticationManagerBuilder.authenticationProvider(authProvider);
        authenticationManagerBuilder.userDetailsService(myUserDetailsService)
                .passwordEncoder(getPasswordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth-> {
                    auth.requestMatchers(antMatcher("/api/auth/registration")).permitAll();
                    auth.requestMatchers(antMatcher("/api/auth/login")).permitAll();
                    auth.anyRequest().hasAnyRole("ADMIN","USER");
                })
                .formLogin(form -> form
                        .loginPage("/api/auth/login")
                        .loginProcessingUrl("/processing_login")
                        .defaultSuccessUrl("/api/auth/ShowUserInfo",true)
                        .failureUrl("/auth/login?error")
                        .permitAll()
                )
                .logout(logout -> {logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/login");
                })
                .exceptionHandling(exc->{
                    exc.accessDeniedPage("/auth/denied");
                })
        ;


        return http.build();
    }
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
