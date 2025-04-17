package com.spring.boot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {


    private Long id;
    @NotEmpty(message = "invalid user name")
    private String userName;

    @NotEmpty(message = "invalid password")
    private String password;

    @Size(max = 11,min = 11,message = "invalid phone number")
    @NotEmpty(message = "invalid phone number")
    private String phoneNumber;
    private String length;

}
