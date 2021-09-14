package com.ediads.cursomc3.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService{
	
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando Envio de Email ...");
		LOG.info(msg.toString());
		LOG.info("EmailEnviado");
		
		
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulando Envio de Email HTML...");
		LOG.info(msg.toString());
		LOG.info("EmailEnviado");
		
	}

}
