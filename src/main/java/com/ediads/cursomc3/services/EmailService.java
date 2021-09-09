package com.ediads.cursomc3.services;

import org.springframework.mail.SimpleMailMessage;

import com.ediads.cursomc3.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
