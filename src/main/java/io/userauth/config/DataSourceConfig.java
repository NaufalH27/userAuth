package io.userauth.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class DataSourceConfig {
    
    @Primary
    @Bean(name = "mainDatabase")
    @ConfigurationProperties(prefix="spring.datasource.main")
    public DataSource mainDatabase() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "authSessionDatabase")
    @ConfigurationProperties(prefix="spring.datasource.session")
    public DataSource authSessionDatabase() {
        return DataSourceBuilder.create().build();
    }



}
