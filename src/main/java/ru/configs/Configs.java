package ru.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Configs {

    @Bean
    public MessageSource messageSource() {
       /* ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        return messageSource;*/
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("configure");
        messageSource.setBasename("log4j");
        messageSource.setBasename("sql");
        messageSource.setBasename("strings");
        return messageSource;
    }


    @Bean
    public MessageSourceAccessor messageSourceAccessor() {
        return new MessageSourceAccessor(messageSource());
    }
}
