package com.example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.repository",
        entityManagerFactoryRef = "pcmEntityManagerFactory",
        transactionManagerRef = "pcmTransactionManager"
)
@EntityScan(basePackages = "com.example.entity")
public class PcmDatabaseConfig {

    @Bean
    @ConfigurationProperties("pcm.datasource")
    public DataSourceProperties pcmDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "pcmDataSource")
    public DataSource pcmDataSource(@Qualifier("pcmDataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "pcmEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean pcmEntityManagerFactory(@Qualifier("pcmDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        em.setJpaProperties(properties);

        return em;
    }

    @Bean(name = "pcmTransactionManager")
    public JpaTransactionManager pcmTransactionManager(@Qualifier("pcmEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
