package com.basic.Persons;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

  @GetMapping(path = "/v1/person")
  public PersonV1 getPersonV1() {
    return new PersonV1("hello world");
  }

  @GetMapping(path = "/v2/person")
  public PersonV2 getPersonV2() {
    return new PersonV2(new Name("hello", "world"));
  }

  @GetMapping(path = "/person/param", params = "version=1")
  public PersonV1 getPersonVersion1() {
    return new PersonV1("hello world");
  }

  @GetMapping(path = "/person/param", params = "version=2")
  public PersonV2 getPersonVersion2() {
    return new PersonV2(new Name("hello", "world"));
  }

  @GetMapping(path = "/person/header", headers = "API-VERSION=1")
  public PersonV1 getPersonHeaderVersion1() {
    return new PersonV1("hello world");
  }

  @GetMapping(path = "/person/header", headers = "API-VERSION=2")
  public PersonV2 getPersonHeaderVersion2() {
    return new PersonV2(new Name("hello", "world"));
  }

  @GetMapping(path = "/person/produces", produces = "application/vdn.company.app-v1+json")
  public PersonV1 getPersonProduceVersion1() {
    return new PersonV1("hello world");
  }

  @GetMapping(path = "/person/produces", produces = "application/vdn.company.app-v2+json")
  public PersonV2 getPersonProduceVersion2() {
    return new PersonV2(new Name("hello", "world"));
  }
}
