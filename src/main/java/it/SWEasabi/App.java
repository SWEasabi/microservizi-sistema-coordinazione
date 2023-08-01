package it.SWEasabi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}

/*
spring.datasource.hikari.connectionTimeout=20000
spring.datasource.hikari.maximumPoolSize=5

spring.datasource.url=jdbc:postgresql://localhost:5432/gestionedb
spring.datasource.username=postgres
spring.datasource.password=SWEasabi123!

spring.second-datasource.url=jdbc:postgresql://localhost:5432/loggingdb
spring.second-datasource.username=postgres
spring.second-datasource.password=SWEasabi123!

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8083
*/