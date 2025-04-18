package com.spring.boot.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.boot.model.Email;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto {

    private Long id;
    @NotEmpty(message = "invalid.user.name")
    private String name;
    @Min(value = 15, message = "invalid.min.age")
    @Max(value = 40, message = "invalid.max.age")
    private int age;
    @DecimalMin(value = "5000.0", message = "invalid.min.salary")
    @DecimalMax(value = "10000.0", message = "invalid.max.salary")
    private double salary;

    private List<Email> emails;



}
