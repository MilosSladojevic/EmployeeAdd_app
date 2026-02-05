package com.example.Employee.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    @NotNull
    @Size(min = 2 ,max = 25)
    private String firstName;

    @NotNull
    @Size(min = 2 ,max = 25)
    private String lastName;


    @Email
    private String email;

    @NotNull
    @Size (min = 2, max = 20)
    private String password;

    private String username;

    private String role;

    private LocalDate admissionDate;

    @Size(min = 2,max = 20)
    private String confirmPassword;
}
