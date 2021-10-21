package com.esucri.projetox.adapters.exceptions;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ErrorWarningMessage {
  private String code;
  private String message;
  private List<ErrorWarningField> fields = new ArrayList<>();

  public ErrorWarningMessage(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
