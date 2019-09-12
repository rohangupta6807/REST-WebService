package com.basic.exception;

import java.util.Date;
import lombok.Data;

@Data
class ExceptionResponse {

  private Date timestamp;
  private String mssg;
  private String details;

  ExceptionResponse(Date timestamp, String mssg, String details) {
    super();
    this.timestamp = timestamp;
    this.mssg = mssg;
    this.details = details;
  }
}
