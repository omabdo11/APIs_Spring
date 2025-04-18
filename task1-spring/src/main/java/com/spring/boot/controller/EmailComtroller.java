package com.spring.boot.controller;

import com.spring.boot.dto.EmailDto;
import com.spring.boot.dto.EmployeeDto;
import com.spring.boot.service.EmailService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailComtroller {

    @Autowired
    private EmailService emailService;

    @PostMapping("/add")
    public ResponseEntity<EmailDto>addEmail(@RequestBody @Valid EmailDto emailDto) throws SystemException {
        return ResponseEntity.created(URI.create("/add")).body(emailService.addEmail(emailDto));
    }

    @PutMapping("/updata")
    public ResponseEntity<EmailDto>updateEmail(@RequestBody @Valid EmailDto emailDto) throws SystemException {
        return ResponseEntity.ok(emailService.updateEmail(emailDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<EmailDto>deleteEmail(@RequestParam Long id) {
        emailService.deleteEmail(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getEmail")
    public ResponseEntity<List<EmailDto>> getEmail() {
        return ResponseEntity.ok(emailService.getAllEmailDto());
    }

    @GetMapping("/getByType")
    public ResponseEntity<List<EmailDto>> getByType(@RequestParam String type) throws SystemException {
        return ResponseEntity.ok(emailService.gitEmailDtoByType(type));
    }

    @GetMapping("/getByValue")
    public ResponseEntity<List<EmailDto>> getByValue(@RequestParam String value) throws SystemException {
        return ResponseEntity.ok(emailService.gitEmailDtoByValue(value));
    }
    @GetMapping("/getListOfType")
    public ResponseEntity<List<EmailDto>> getListOfType(@RequestParam  List<String> listOfType) throws SystemException {
        return ResponseEntity.ok(emailService.findEmailByListOfType(listOfType));
    }


}
