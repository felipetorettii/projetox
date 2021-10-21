package com.esucri.projetox.adapters.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
  E000("E000", "Erro de validação de campos."),
  E001("E001", "Usuário não encontrado."),
  E002("E002", "Promoter não encontrado.");

  private String code;
  private String message;
}
