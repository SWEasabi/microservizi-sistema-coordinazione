package it.SWEasabi.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnagrafeDataSourceConfiguration {
	
	@Bean
	@ConfigurationProperties("spring.datasource.anagrafe")
	DataSourceProperties anagrafeDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConfigurationProperties("spring.datasource.todos.hikari")
	public DataSource anagrafeDataSource() {
	    return anagrafeDataSourceProperties()
	      .initializeDataSourceBuilder()
	      .build();
	}
}
