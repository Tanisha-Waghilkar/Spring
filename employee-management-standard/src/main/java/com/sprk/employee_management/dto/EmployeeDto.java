package com.sprk.employee_management.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDto {

    private Long empId;

    @NotBlank(message = "First Name cannot be empty")
    private String firstName;

    @NotBlank(message = "Last Name cannot be empty")
    private String lastName;


    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Enter valid email")
    private String email;

    @NotBlank(message = "Phone cannot be empty")
    @Pattern(regexp = "^\\d{8,15}$",message = "Enter valid phone number minimum 8 num")
    private String phone;

    private String gender;

    @NotNull(message = "Age cannot be empty")
    @Min(value = 1,message = "Enter age greater than 0")
    private Integer age;


    @NotNull(message = "Salary cannot be empty")
    private Double salary;

    @NotBlank(message = "Department cannot be empty")
    private String department;

    private String fileName;

    private String filePath;



}