package com.felixweb.projeto.config;

import com.felixweb.projeto.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;


    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;



    @Bean
    public Boolean instantiationDatabase() throws ParseException {
        if("!create".equals(strategy)) {
            return false;
        }
        dbService.instantiateTestDatabase();
        return true;
    }
}
