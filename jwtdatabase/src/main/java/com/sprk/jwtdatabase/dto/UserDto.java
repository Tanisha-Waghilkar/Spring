package com.sprk.jwtdatabase.dto;

import com.sprk.jwtdatabase.entity.Role;
import lombok.Data;

@Data
    public class UserDto {
        private String username;
        private String password;
        private String role;
    }

