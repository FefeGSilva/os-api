package com.felipe.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.felipe.os.services.DBService;


@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddl;
	
	@Bean //Cria e retorna um bean que pode ser usado como dependencia em outras classes do projeto
	public boolean instanciaDB() {
		
		if(ddl.equals("create")) {
			this.dbService.instanciaDB();
		}
		
		return false;
		
	}
}