package com.esucri.projetox.adapters.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
  E000("E000", "Erro de validação de campos."),
  E001("E001", "Usuário não encontrado."),
  E002("E002", "Promoter não encontrado."),
  E003("E003", "Evento não encontrado."),
  E004("E004", "Nenhum evento encontrado para o promoter de código %d."),
  E005("E005", "Erro ao processar dados de entrada."),
  E006("E006", "Ticket não encontrado."),
  E007("E007", "Nenhum ticket encontrado para o usuário de código %d."),
  E008("E008", "Credenciais incorretas."),
  E009("E009", "Email já cadastrado."),
  E010("E010", "Tickets do evento esgotados.");

  private String code;
  private String message;
}
