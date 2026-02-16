package com.sprk.employee_management.controller;


import com.sprk.employee_management.config.JwtUtil;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LoginController {
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if (username.equals("admin") && password.equals("pass1234")) {
            return jwtUtil.generateToken(username);
        }
        return "Invalid Credentials";
    }
}