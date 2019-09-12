package com.basic.helloworld;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {


  @Autowired
  private ResourceBundleMessageSource resourceBundleMessageSource;

  @GetMapping(path = "/hello-world")
  public String helloWorld() {
    return "HelloWorld";
  }

  @GetMapping(path = "/hello-world-bean")
  public HelloWorldBean helloWorldBean() {
    return new HelloWorldBean("jhbjh");
  }

  @GetMapping(path = "/hello-world/path-variable/{name}")
  public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
    return new HelloWorldBean(name);
  }

  @GetMapping(path = "/hello-world-internationalized")
  public String helloWorldInternationalized(
      @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
    return resourceBundleMessageSource.getMessage("good.morning.message", null, locale);
  }

  @GetMapping(path = "/hello-world-internationalized-secondMethod")
  public String helloWorldInternationalized2() {
    return resourceBundleMessageSource.getMessage("good.morning.message",
                                                  null,
                                                  LocaleContextHolder.getLocale());
  }
}
