package ru.analteam.gtracks.repository;

import liquibase.integration.spring.SpringLiquibase;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by dima-pc on 05.06.2016.
 */
@Configuration
public class TestContext {
    @Bean
    public javax.sql.DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");

//        ds.setUsername("root");
//        ds.setPassword("baikal11");

        ds.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
//        ds.setUsername(dbUsername);
//        ds.setPassword(dbPassword);

        return ds;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        emf.setPersistenceUnitName("persistentUnit1");
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaProperties(jpaProperties());

        emf.setPackagesToScan(
                "ru.analteam.gtracks.model.security",
                "ru.analteam.gtracks.model.test"
        );

        return emf;
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2ddl.auto", "create");

//        prop.put("hibernate.format_sql", "true");
//        prop.put("hibernate.show_sql", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory().getObject());
        return tm;
    }

    public RouteRepository routeRepository(){
        RouteRepository routeRepository = new RouteRepository();

        return routeRepository;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog("classpath:liquibase/db_changelog.xml");

        // Verbose logging
        Map<String, String> params = new HashMap<String, String>();
        params.put("verbose", "true");
        liquibase.setChangeLogParameters(params);

        return liquibase;
    }
}
