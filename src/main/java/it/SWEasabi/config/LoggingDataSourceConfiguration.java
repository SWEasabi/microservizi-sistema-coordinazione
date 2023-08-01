package it.SWEasabi.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingDataSourceConfiguration {
	
	@Bean
	@ConfigurationProperties("spring.datasource.logging")
	DataSourceProperties loggingDataSourceProperties() {
		return new DataSourceProperties();
	}
}
