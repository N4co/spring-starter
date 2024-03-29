package com.felixweb.projeto.services;

import com.mysql.cj.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService{

    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
    @Override
    public void sendEmail(SimpleMailMessage msg) {

        LOG.info(" Simulando o envio de Email...");
        LOG.info(msg.toString());
        LOG.info(" Email Enviado");
    }
}
