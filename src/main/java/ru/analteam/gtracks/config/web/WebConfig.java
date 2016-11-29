package ru.analteam.gtracks.config.web;

import freemarker.template.utility.XmlEscape;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.HashMap;
import java.util.List;
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

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(100000000l);
        return multipartResolver;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
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


   /* @Bean
    public FreeMarkerConfigurationFactory freeMarkerConfigurationFactory() {
        FreeMarkerConfigurationFactoryBean configurationFactoryBean = new FreeMarkerConfigurationFactoryBean();
        configurationFactoryBean.setTemplateLoaderPath("/WEB-INF/pages/ftl/");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("xml_escape", new XmlEscape());
        configurationFactoryBean.setFreemarkerVariables(map);

        Properties settings = new Properties();
        settings.setProperty("cache_storage", NullCacheStorage.class.getName());
        configurationFactoryBean.setFreemarkerSettings(settings);
        return configurationFactoryBean;
    }







    */

/*    @Bean(name ="freemarkerConfig")
    public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {
//        FreeMarkerConfigurationFactory configurationFactory = new FreeMarkerConfigurationFactoryBean();
//        configurationFactory.set



        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();

*//*
        configurer.setTemplateLoaderPath("/WEB-INF/pages/ftl/");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("xml_escape", new XmlEscape());

        configurer.setFreemarkerVariables(map);
*//*

//        freemarker.template.Configuration configuration = new freemarker.template.Configuration(new Version("2.3.23"));
//        configurer.setConfiguration(freeMarkerConfigurationFactory().createConfiguration());
//        configuration.setCacheStorage(new NullCacheStorage());
//        configuration.setLoad
//        configurer.setConfiguration(configuration);
//        configurer.getConfiguration().setTemplateUpdateDelayMilliseconds(1000l);

        return configurer;
    }*/

    @Bean(name ="freemarkerConfig")
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("xml_escape", new XmlEscape());
        configurer.setFreemarkerVariables(map);
        configurer.setDefaultEncoding("UTF-8");
        return configurer;
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver viewResolver
                = new FreeMarkerViewResolver();
        viewResolver.setViewClass(FreeMarkerView.class);
        viewResolver.setContentType("text/html;charset=UTF-8");

        viewResolver.setCache(false);
//        viewResolver.setPrefix("/WEB-INF/pages/ftl/");
        viewResolver.setPrefix("/pages/ftl/");
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

