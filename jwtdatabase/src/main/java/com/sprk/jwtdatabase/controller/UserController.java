package com.sprk.jwtdatabase.controller;

import com.sprk.jwtdatabase.dto.UserDto;
import com.sprk.jwtdatabase.entity.UserEntity;
import com.sprk.jwtdatabase.repository.UserRepository;
import com.sprk.jwtdatabase.service.UserService;
import com.sprk.jwtdatabase.util.JwtUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.startup.Tomcat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.autoconfigure.SecurityProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {
    @Autowired
   private UserService userService;
    @Autowired
    private JwtUtil jwtutil;
    @Autowired
    private UserRepository userRepository;
    //login
    @PostMapping("/login")

    public String login(@RequestParam String username,@RequestParam String password){

        UserEntity user=userService.authenticate(username,password);
        return jwtutil.generateToken(user.getUsername(),String.valueOf(user.getRole()));
    }
  //register
    @PostMapping("/register")

    public String register(@RequestBody UserDto userDto) {

        UserEntity saveUser = userService.register(userDto);
        return "User register";

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user")
    public List<UserEntity> getUser() {
        List<UserEntity> user=userService.getAllUsers();
        return user;
    }

@PreAuthorize("hasRole('USER')")
    @GetMapping("/user/showdetails")
    public UserEntity getById(HttpServletRequest request,HttpServletRequest response){
        UserEntity user=userService.showdetails(request,response);
        return user;
    }
}
