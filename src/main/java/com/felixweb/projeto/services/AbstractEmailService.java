package com.felixweb.projeto.services;

import com.felixweb.projeto.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private  String sender;
    @Override
    public void sendOrderConfirmationEmail(Pedido obj) {
        SimpleMailMessage msg = prepareSimpleMailMessage(obj);
        sendEmail(msg);
    }

    protected  SimpleMailMessage prepareSimpleMailMessage(Pedido obj) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(obj.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject(" Pedido confirmado " + obj.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(obj.toString());

        return sm;
    }


}
