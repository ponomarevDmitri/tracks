package ru.analteam.gtracks.config.app;

import liquibase.integration.spring.SpringLiquibase;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by dima-pc on 30.11.2015.
 */
@Configuration
//@Import(WebConfig.class)
@EnableTransactionManagement
@ComponentScan({"ru.analteam.gtracks.service", "ru.analteam.gtracks.repository"})
public class AppConfig {

    private static Logger log = LoggerFactory.getLogger(AppConfig.class);

    @Value("${db.connection.url}")
    private String dbUrl;

    @Value("${db.connection.username}")
    private String dbUsername;

    @Value("${db.connection.password}")
    private String dbPassword;


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();

        log.debug("Try to find specific app.properties file.");
        Resource liq = new FileSystemResource("./app.properties");
        if (liq.exists()) {
            configurer.setLocations(liq);
        } else {
            log.debug("Specific properties file was not found. Using source app.properties file");
            configurer.setLocation(new ClassPathResource("app.properties"));
        }

        return configurer;
    }

    /*@Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder =
                new LocalSessionFactoryBuilder(dataSource());
        builder.scanPackages("com.mkyong.users.model")
                .addProperties(getHibernateProperties());

        return builder.buildSessionFactory();
    }*/

    /*private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect",
                "org.hibernate.dialect.MySQL5Dialect");
        return prop;
    }*/

    @Bean
    public javax.sql.DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");

//        ds.setUrl("jdbc:mysql://localhost:3306/gps_tracks_db");
//        ds.setUsername("root");
//        ds.setPassword("baikal11");

        ds.setUrl(dbUrl);
        ds.setUsername(dbUsername);
        ds.setPassword(dbPassword);

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

//    todo make COnditional
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

