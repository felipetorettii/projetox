package com.esucri.projetox.adapters.web.user.controller.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserRequestDTO {

  @NotNull(message = "O nome do usuário não pode nulo.")
  @NotEmpty(message = "O nome do usuário não pode ser vazio.")
  private String name;

  @NotNull(message = "O email do usuário não pode nulo.")
  @NotEmpty(message = "O email do usuário não pode ser vazio.")
  private String email;

  @NotNull(message = "A senha do usuário não pode nula.")
  @NotEmpty(message = "A senha do usuário não pode ser vazio.")
  private String pass;
}
