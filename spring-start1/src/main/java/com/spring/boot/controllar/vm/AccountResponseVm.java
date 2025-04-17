package com.spring.boot.controllar.vm;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccountResponseVm {
    private Long id;
    private String userName;
    private String phoneNumber;
    private String length;
}
