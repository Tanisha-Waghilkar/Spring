package com.sprk.employee_management.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity(name = "employee")
//@Table(name = "employee_sprk")
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    @Column( nullable = false)
    private String firstName;

    @Column( nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    private String gender;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private double salary;

    private String department;

    @Column(nullable = false)
    private String FilePath;

    @Column(nullable = false)
    private  String fileName;





}