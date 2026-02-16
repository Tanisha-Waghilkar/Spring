package com.sprk.jwtdatabase.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "userlogin")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

}
//order id
//list of items
//list o prices
//total amount

//payment
//payment id
// payment amount



