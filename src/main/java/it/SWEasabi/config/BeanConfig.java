package it.SWEasabi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.SWEasabi.core.CoreIlluminazione;

@Configuration
public class BeanConfig {
	
	@Bean
	CoreIlluminazione getCoreIlluminazione() {
		return new CoreIlluminazione();
	}
}
