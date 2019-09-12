package com.basic;

import java.util.Locale;
import javax.websocket.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


@SpringBootApplication
@EnableRetry
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public LocaleResolver localeResolver()
  {
    /* if you want custom accept locale in header
    SessionLocaleResolver sessionLocaleResolver=new SessionLocaleResolver();
    sessionLocaleResolver.setDefaultLocale(Locale.US);
    return sessionLocaleResolver;
    */

    AcceptHeaderLocaleResolver acceptHeaderLocaleResolver=new AcceptHeaderLocaleResolver();
    acceptHeaderLocaleResolver.setDefaultLocale(Locale.US);
    return acceptHeaderLocaleResolver;
  }

  /*
  "spring.messages.basename=messages" line in application.properties works same

  @Bean
  public ResourceBundleMessageSource resourceBundleMessageSource()
  {
    ResourceBundleMessageSource resourceBundleMessageSource=new ResourceBundleMessageSource();
    resourceBundleMessageSource.setBasename("message");
    return  resourceBundleMessageSource;
  }*/
}