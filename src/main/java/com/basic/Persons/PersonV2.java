package com.basic.Persons;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@Data
public class PersonV2 {
  @Autowired
  private Name name;
}
