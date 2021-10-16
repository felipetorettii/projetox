package com.esucri.projetox.adapters.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
  E001("E001", "Usuário não encontrado.");

  private String code;
  private String message;
}
