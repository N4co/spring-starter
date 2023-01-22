package com.felixweb.projeto.config;

import com.felixweb.projeto.services.DBService;
import com.felixweb.projeto.services.EmailService;
import com.felixweb.projeto.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;
    @Bean
    public Boolean instantiationDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }
    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
