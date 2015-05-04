package com.chrisbaileydeveloper.myapp.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import uk.co.gcwilliams.jodatime.thymeleaf.JodaTimeDialect;

@Configuration
public class LocaleConfiguration extends WebMvcConfigurerAdapter {

    /**
     *  Thymeleaf LocaleResolver
     */
    @Bean (name = "localeResolver")
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.CANADA);
        return sessionLocaleResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    	localeChangeInterceptor.setParamName("lang");
    	registry.addInterceptor(localeChangeInterceptor);
    }
    
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/i18n/messages", "classpath:/i18n/application");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    /**
     *  Joda Time to Thymeleaf converter<br>
     *  Spring Boot, in the ThymeleafAutoConfiguration class, 
     *  will automatically add any Beans that implement the IDialect interface.
     */
    @Bean
    public JodaTimeDialect jodaTimeDialect() {
    	return new JodaTimeDialect();
    }
    
    /**
     *  Thymeleaf - Spring Security Integration
     */
    @Bean
    public SpringSecurityDialect springSecurityDialect() {
    	return new SpringSecurityDialect();
    }
}
