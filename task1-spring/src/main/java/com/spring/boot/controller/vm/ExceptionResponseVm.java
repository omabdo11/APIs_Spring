package com.spring.boot.controller.vm;

import com.spring.boot.dto.bandleMessage.BandleMessage;

import lombok.Getter;

import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter

public class ExceptionResponseVm {
    private List<BandleMessage> errormessage;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;

    public ExceptionResponseVm(List<BandleMessage> errormessage, HttpStatus httpStatus ) {
        this.errormessage = errormessage;
        this.httpStatus = httpStatus;
        this.timestamp = LocalDateTime.now();
    }
}
