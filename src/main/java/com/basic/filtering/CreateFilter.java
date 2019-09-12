package com.basic.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Component;

@Component
class CreateFilter {
  FilterProvider filteringUsingFields(String... args) {
    SimpleBeanPropertyFilter beanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept(args);
    return new SimpleFilterProvider().addFilter("BeanFilter", beanPropertyFilter);
  }
}
