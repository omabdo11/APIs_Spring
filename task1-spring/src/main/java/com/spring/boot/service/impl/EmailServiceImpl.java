package com.spring.boot.service.impl;

import com.spring.boot.ModelMapper.EmailMapper;
import com.spring.boot.dto.EmailDto;
import com.spring.boot.exception.CustomSystemException;
import com.spring.boot.model.Email;
import com.spring.boot.model.Employee;
import com.spring.boot.repo.EmailRepo;
import com.spring.boot.repo.EmployeeRepo;
import com.spring.boot.service.EmailService;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailRepo emailRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public EmailDto addEmail(EmailDto emailDto) throws SystemException {
        if(Objects.nonNull(emailDto.getId())){
            throw new CustomSystemException("error.id.invalid");
        }
        if (emailDto.getEmployeeId() == null) {
            throw new CustomSystemException("error.employee.id.invalid");
        }
        Optional<Email> emailValue = emailRepo.findByValue(emailDto.getValue());
        if(emailValue.isPresent()){
            throw new CustomSystemException("error.email.exist");
        }
        Optional<Employee> employeeId = employeeRepo.findById(emailDto.getEmployeeId());
        if(employeeId.isEmpty()){
            throw new CustomSystemException("error.id.found");
        }
     Email email= EmailMapper.EMAIL_MAPPER.fromEmailDto(emailDto);
     emailRepo.save(email);
        return emailDto;
    }

    @Override
    public EmailDto updateEmail(EmailDto emailDto) throws SystemException {
        if(Objects.isNull(emailDto.getId())){
            throw new CustomSystemException("error.id.valid");
        }
        Optional<Email> emailValue = emailRepo.findByValue(emailDto.getValue());
        if(emailValue.isPresent()){
            throw new CustomSystemException( "error.email.exist");
        }
        Email email= EmailMapper.EMAIL_MAPPER.fromEmailDto(emailDto);
        emailRepo.save(email);
        return emailDto;
    }

    @Override
    public boolean deleteEmail(Long id) {
        Optional<Email> emailValue = emailRepo.findById(id);
        if(emailValue.isEmpty()){
            return false;
        }
        emailRepo.delete(emailValue.get());
        return true;
    }

    @Override
    public List<EmailDto> getAllEmailDto() {
        List<Email> email = emailRepo.findAll();
        return extractEmail(email);
    }

    @Override
    public List<EmailDto> gitEmailDtoByType(String type) throws SystemException {
        if(Objects.isNull(type)){
            throw new CustomSystemException("error.type.invalid");
        }
        Optional<Email>email =emailRepo.findByType(type);
        if(email.isEmpty()){
            throw new CustomSystemException("error.email.type");
        }
        List<Email> emails = emailRepo.findByTypeEmails(type);
        return extractEmail(emails);
    }

    @Override
    public List<EmailDto> gitEmailDtoByValue(String value) throws SystemException {
        if(Objects.isNull(value)){
            throw new CustomSystemException("error.type.invalid");
        }
        Optional<Email>email =emailRepo.findByValue(value);
        if(email.isEmpty()){
            throw new CustomSystemException("error.email.type");
        }
        List<Email> emails = emailRepo.findByValueEmails(value);
        return extractEmail(emails);
    }

    @Override
    public List<EmailDto> findEmailByListOfType(List<String> emailDtosvalue) throws SystemException {
        if(emailDtosvalue == null || emailDtosvalue.isEmpty()){
            throw new CustomSystemException("error.typeList.null");//List of types must not be null or empty !
        }
         List<Email> email = emailRepo.findByTypeEmails(emailDtosvalue.get(0));
        if(email.isEmpty()){
            throw new CustomSystemException("error.email.value");
        }
        List<Email> emails=emailRepo.findByListOfTypeEmails(emailDtosvalue);
        return extractEmail(emails);
    }

    private static List<EmailDto> extractEmail(List<Email> emails) {
        List<EmailDto> emailDtos = new ArrayList<>();
        for (Email emailDto : emails) {
            emailDtos.add(EmailMapper.EMAIL_MAPPER.toEmailDto(emailDto));
        }
        return emailDtos;
    }


}
