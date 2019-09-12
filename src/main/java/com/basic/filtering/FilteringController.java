package com.basic.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

  @Autowired
  private CreateFilter createFilter;

  @GetMapping(path = "/filtering")
  public MappingJacksonValue retrieveSomeBean() {
    SomeBean someBeam = new SomeBean("v1", "v2", "v3", "v4", "v5", "v6", "v7");

    FilterProvider filter = createFilter.filteringUsingFields("field5", "field6");
    MappingJacksonValue mapping = new MappingJacksonValue(someBeam);
    mapping.setFilters(filter);
    return mapping;
  }

  @GetMapping(path = "/filtering-list")
  public MappingJacksonValue retrieveSomeBeanList() {
    List<SomeBean> listOfSomeBean = Arrays.asList(
        new SomeBean("v1", "v2", "v3", "v4", "v5", "v6", "v7"),
        new SomeBean("v2.1", "v2.2", "v2.3", "v2.4", "v2.5", "v2.6", "v2.7"));

    FilterProvider filter = createFilter.filteringUsingFields("field3", "field5");
    MappingJacksonValue mapping = new MappingJacksonValue(listOfSomeBean);
    mapping.setFilters(filter);
    return mapping;

  }
}
