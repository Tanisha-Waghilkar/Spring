package com.sprk.student_file_upload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
        http.csrf(csrf-> csrf.disable()).authorizeHttpRequests(auth->auth.requestMatchers("/api/v1/student/**").hasRole("STUDENT")
                .requestMatchers("/api/v1/teacher/**").hasRole("TEACHER")
                .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults())
                .formLogin(form->form.disable());
        return http.build();
    }
    @Bean
    public UserDetailsManager users() {

        UserDetails student1 = User.builder()
                .username("Manisha")
                .password(passwordEncoder().encode("manisha@123"))
                .roles("STUDENT")
                .build();

        UserDetails student2 = User.builder()
                .username("Rahul")
                .password(passwordEncoder().encode("rahul@123"))
                .roles("STUDENT")
                .build();

        UserDetails student3 = User.builder()
                .username("priya")
                .password(passwordEncoder().encode("priya@123"))
                .roles("STUDENT")
                .build();

        UserDetails teacher = User.builder()
                .username("teacher1")
                .password(passwordEncoder().encode("teacher@123"))
                .roles("TEACHER")
                .build();

        return new InMemoryUserDetailsManager(student1,student2,student3, teacher);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
