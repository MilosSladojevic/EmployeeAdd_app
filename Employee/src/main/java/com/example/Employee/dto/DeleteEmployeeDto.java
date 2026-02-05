package com.example.Employee.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteEmployeeDto {

    @NotNull
    @Size(min = 2 ,max = 25)
    private String firstName;

    @NotNull
    @Size(min = 2 ,max = 25)
    private String lastName;

    @NotNull
    private Long employeeId;
}
