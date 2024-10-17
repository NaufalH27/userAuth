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
    @Bean(name = "primaryDatabase")
    @ConfigurationProperties(prefix="spring.datasource.primary")
    public DataSource mainDatabase() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "sessionDatabase")
    @ConfigurationProperties(prefix="spring.datasource.session")
    public DataSource sessionDatabase() {
        return DataSourceBuilder.create().build();
    }



}
