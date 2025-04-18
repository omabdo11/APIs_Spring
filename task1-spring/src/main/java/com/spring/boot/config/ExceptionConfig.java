package com.spring.boot.config;

import com.spring.boot.controller.vm.ExceptionResponseVm;
import com.spring.boot.dto.bandleMessage.BandleMessage;
import com.spring.boot.exception.CustomSystemException;
import com.spring.boot.service.bandleService.BandleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionConfig {

    @Autowired
    private BandleServiceImpl bundleService;


    @ExceptionHandler( CustomSystemException.class)
    public ResponseEntity<ExceptionResponseVm> handleException(CustomSystemException e) {
        BandleMessage  bundleMessage = bundleService.getBundleMessageInEnglishAndArabic(e.getMessage());
        return ResponseEntity.ok(new ExceptionResponseVm(List.of(bundleMessage),HttpStatus.NOT_ACCEPTABLE));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseVm> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ExceptionResponseVm exceptions = new ExceptionResponseVm(new ArrayList<>(),HttpStatus.BAD_REQUEST);
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            exceptions.getErrormessage().add(
                    bundleService.getBundleMessageInEnglishAndArabic(error.getDefaultMessage())
            );
        }

        return ResponseEntity.ok(exceptions);

    }
}
