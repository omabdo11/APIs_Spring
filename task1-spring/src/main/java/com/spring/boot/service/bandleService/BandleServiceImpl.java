package com.spring.boot.service.bandleService;

import com.spring.boot.dto.bandleMessage.BandleMessage;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class BandleServiceImpl {

    private final ResourceBundleMessageSource resourceBundleMessageSource;

    public BandleServiceImpl(ResourceBundleMessageSource resourceBundleMessageSource) {
        this.resourceBundleMessageSource = resourceBundleMessageSource;
    }

    public BandleMessage getBundleMessageInEnglishAndArabic(String key) {
        return new BandleMessage(
                resourceBundleMessageSource.getMessage(key, null, new Locale("en")),
                resourceBundleMessageSource.getMessage(key, null, new Locale("ar"))
        );
    }
}
