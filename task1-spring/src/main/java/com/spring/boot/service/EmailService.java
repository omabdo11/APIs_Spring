package com.spring.boot.service;

import com.spring.boot.dto.EmailDto;
import jakarta.transaction.SystemException;


import java.util.List;

public interface EmailService {

     EmailDto addEmail(EmailDto emailDto) throws SystemException;
     EmailDto updateEmail(EmailDto emailDto) throws SystemException;
     boolean deleteEmail(Long id);
     List<EmailDto> getAllEmailDto();

    List<EmailDto>  gitEmailDtoByType(String type) throws SystemException;
    List<EmailDto>  gitEmailDtoByValue(String value) throws SystemException;
    List<EmailDto> findEmailByListOfType(List<String> emailDtosvalue) throws SystemException;

}
