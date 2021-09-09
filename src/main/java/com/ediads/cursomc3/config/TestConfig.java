package com.ediads.cursomc3.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ediads.cursomc3.services.DBService;
import com.ediads.cursomc3.services.EmailService;
import com.ediads.cursomc3.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
    private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true ;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
