package com.sprk.jwtdatabase.service;

import com.sprk.jwtdatabase.dto.UserDto;
import com.sprk.jwtdatabase.entity.Role;
import com.sprk.jwtdatabase.entity.UserEntity;
import com.sprk.jwtdatabase.repository.UserRepository;
import com.sprk.jwtdatabase.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;




    @Autowired
    private final UserRepository userRepository;


    public UserEntity authenticate(String username,String password) {
        UserEntity user= userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password,user.getPassword())){
            throw new RuntimeException("Invalid user");
        }
        return user;

    }



    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }

    public UserEntity register(UserDto userDto) {
        UserEntity user=new UserEntity();

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUsername(userDto.getUsername());
        if (userDto.getRole().equals("ADMIN") || userDto.getRole().equals("admin")||userDto.getRole().equals("Admin")) {
            user.setRole(Role.ADMIN);
        }else if (userDto.getRole().equals("USER")||userDto.getRole().equals("user")||userDto.getRole().equals("User")) {
            user.setRole((Role.USER));

        }else {
            throw new RuntimeException("Invalid User");
        }

        return userRepository.save(user);

    }


    public UserEntity showdetails(HttpServletRequest request, HttpServletRequest response) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        UserEntity user=userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
        return user;


    }
}

