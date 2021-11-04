package com.esucri.projetox.adapters.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UnprocessableJsonException extends Exception {
  private ErrorWarningMessage errorWarningMessage;
}
