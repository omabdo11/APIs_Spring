package com.spring.boot.dto.bandleMessage;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BandleMessage {

    @JsonProperty("messages_en")
    private String messagesEn;
    @JsonProperty("messages_ar")
    private String massagesAr;

    public BandleMessage(String messagesEn, String massagesAr ) {
        this.messagesEn = messagesEn;
        this.massagesAr = massagesAr;
    }
}
