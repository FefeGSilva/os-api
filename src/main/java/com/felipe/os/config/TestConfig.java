package com.felipe.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.felipe.os.services.DBService;

@Component
@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;

	@Bean // Cria e retorna um bean que pode ser usado como dependencia em outras classes
			// do projeto
	public boolean instanciaDB() {
		this.dbService.instanciaDB();
		return false;
	}
}