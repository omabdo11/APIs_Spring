package com.spring.boot.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.boot.model.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmailDto {

    private Long id;
    @NotEmpty(message = "invalid.email.type")
    private String type;
    @Email(message = "invalid.email.value")
    private String value;
    private Long employeeId;

}
