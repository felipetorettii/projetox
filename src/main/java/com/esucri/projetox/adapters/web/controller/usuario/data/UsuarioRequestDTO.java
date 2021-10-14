package com.esucri.projetox.adapters.web.controller.usuario.data;

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
public class UsuarioRequestDTO{

    @NotNull(message = "O nome do usuário não pode nulo.")
    @NotEmpty(message = "O nome do usuário não pode ser vazio.")
    private String nome;
}
