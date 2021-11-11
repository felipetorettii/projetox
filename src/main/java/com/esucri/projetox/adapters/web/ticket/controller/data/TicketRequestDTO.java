package com.esucri.projetox.adapters.web.ticket.controller.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TicketRequestDTO {

  @NotNull(message = "É necessário informar o código do evento.")
  private Long eventId;

  @NotNull(message = "É necessário informar o código do usuário do ticket.")
  private Long userId;

  @JsonProperty(defaultValue = "false")
  private boolean showedUp;

  @JsonProperty(defaultValue = "false")
  private boolean voted;
}
