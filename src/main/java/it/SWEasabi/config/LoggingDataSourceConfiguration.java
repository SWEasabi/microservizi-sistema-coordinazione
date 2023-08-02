package it.SWEasabi.config;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	    entityManagerFactoryRef = "loggingEntityManagerFactory",
	    transactionManagerRef = "loggingEntityTransactionManager",
	    basePackages = {"it.SWEasabi.repositories.logging"})
public class LoggingDataSourceConfiguration {
	
	@Bean(name="loggingProperties")
	@ConfigurationProperties("spring.datasource.logging")
	DataSourceProperties dataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean(name="loggingDatasource")
	@ConfigurationProperties(prefix = "spring.datasource.logging")
	public DataSource dataSource(@Qualifier("loggingProperties") DataSourceProperties properties) {
	    return properties.initializeDataSourceBuilder().build();
	}
	
	@Bean(name="loggingEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      @Qualifier("loggingDatasource") DataSource dataSource,
      EntityManagerFactoryBuilder builder) {
        return builder
          .dataSource(dataSource)
          .packages("it.SWEasabi.modelli.logging")
          .build();
    }

    @Bean(name="loggingEntityTransactionManager")
	@ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager transactionManager(
      @Qualifier("loggingEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
	
}
