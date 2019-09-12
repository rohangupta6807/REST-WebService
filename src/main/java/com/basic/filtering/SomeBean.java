package com.basic.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@JsonFilter("BeanFilter")
@JsonIgnoreProperties(value = {"field2","field3"})
class SomeBean {
  private String field1;
  private String field2;
  @JsonIgnore
  private String field3;
  private String field4;
  private String field5;
  private String field6;
  private String field7;
}
