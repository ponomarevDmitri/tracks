package ru.analteam.gtracks.config.web;

import freemarker.template.utility.XmlEscape;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dima-pc on 07.12.2015.
 */
@EnableWebMvc
@Configuration
@ComponentScan({"ru.analteam.gtracks.controller"})
public class WebConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // relatively root of .war archive
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }

    /*@Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver
                = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }*/

    @Bean(name ="freemarkerConfig")
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/pages/ftl/");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("xml_escape", new XmlEscape());
        configurer.setFreemarkerVariables(map);
        return configurer;
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver viewResolver
                = new FreeMarkerViewResolver();
        viewResolver.setViewClass(FreeMarkerView.class);

//        viewResolver.setPrefix("/WEB-INF/pages/ftl/");
        viewResolver.setSuffix(".ftl");
        return viewResolver;
    }


    /*@Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }*/

}

