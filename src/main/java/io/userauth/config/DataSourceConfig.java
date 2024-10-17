package io.userauth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;


@Configuration
public class DataSourceConfig {
    

    //primary db
    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("primaryDataSource") DataSource dataSource
        ) {
    return 
        builder
            .dataSource(dataSource)
            .packages("io.userauth.data.primary")
            .persistenceUnit("primary")
            .build();
    }
    
    @Primary
    @Bean(name = "primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(
        @Qualifier("primaryEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }


    //session db
    @Bean(name = "sessionDataSource")
    @ConfigurationProperties(prefix="spring.datasource.session")
    public DataSource sessionDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sessionEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean 
    sessionEntityManagerFactory(
      EntityManagerFactoryBuilder builder,
      @Qualifier("sessionDataSource") DataSource dataSource
    ) {
      return
        builder
            .dataSource(dataSource)
            .packages("io.userauth.data.session")
            .persistenceUnit("session")
            .build();
    }
    @Bean(name = "sessionTransactionManager")
    public PlatformTransactionManager sessionTransactionManager(
      @Qualifier("sessionEntityManagerFactory") EntityManagerFactory sessionEntityManagerFactory
    ) {
      return new JpaTransactionManager(sessionEntityManagerFactory);
    }

}
