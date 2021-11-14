package ru.configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class Configs {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("classpath:locale/messages");
        messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        return messageSource;
        /*ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("configure");
        messageSource.setBasename("log4j");
        messageSource.setBasename("sql");
        messageSource.setBasename("strings");
        return messageSource;*/
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localResolver=new SessionLocaleResolver();
        localResolver.setDefaultLocale(Locale.US);
        return localResolver;
    }


    @Bean
    public MessageSourceAccessor messageSourceAccessor() {
        return new MessageSourceAccessor(messageSource());
    }
}
