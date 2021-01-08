package com.example.cyberycon.smartmeter;

import com.example.cyberycon.smartmeter.event.MeterReadingSender;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestConfig {
    @Bean
    @Primary
    public MeterReadingSender getSender () {
        return new MockSender() ;
    }
}