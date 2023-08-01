package it.SWEasabi.config;

import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import it.SWEasabi.modelli.anagrafica.LampAnagrafica;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackageClasses = LampAnagrafica.class,
		entityManagerFactoryRef = "anagrafeEntityManagerFactory",
		transactionManagerRef = "anagrafeTransactionManager")
public class AnagrafeJpaConfiguration {
	
	@Bean(name="anagrafeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean anagrafeEntityManagerFactory(
      @Qualifier("anagrafeDataSource") DataSource dataSource,
      EntityManagerFactoryBuilder builder) {
        return builder
          .dataSource(dataSource)
          .packages(LampAnagrafica.class)
          .build();
    }

    @Bean(name="anagrafeEntityTransactionManager")
    public PlatformTransactionManager anagrafeTransactionManager(
      @Qualifier("anagrafeEntityManagerFactory") LocalContainerEntityManagerFactoryBean anagrafeEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(anagrafeEntityManagerFactory.getObject()));
    }
}
