package it.SWEasabi.config;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import it.SWEasabi.modelli.anagrafica.LampAnagrafica;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	    entityManagerFactoryRef = "anagrafeEntityManagerFactory",
	    transactionManagerRef = "anagrafeEntityTransactionManager",
	    basePackages = {"it.SWEasabi.repositories.illuminazione"})
public class AnagrafeDataSourceConfiguration {
	
	@Primary
	@Bean(name="anagrafeProperties")
	@ConfigurationProperties("spring.datasource.anagrafe")
	DataSourceProperties anagrafeDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Primary
	@Bean(name="anagrafeDatasource")
	@ConfigurationProperties(prefix = "spring.datasource.anagrafe")
	public DataSource anagrafeDataSource(@Qualifier("anagrafeProperties") DataSourceProperties properties) {
	    return properties.initializeDataSourceBuilder().build();
	}
	
	@Primary
	@Bean(name="anagrafeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      @Qualifier("anagrafeDatasource") DataSource dataSource,
      EntityManagerFactoryBuilder builder) {
        return builder
          .dataSource(dataSource)
          .packages("it.SWEasabi.modelli.anagrafica")
          .build();
    }

	@Primary
    @Bean(name="anagrafeEntityTransactionManager")
	@ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager transactionManager(
      @Qualifier("anagrafeEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
	
}
