package com.esucri.projetox.adapters.web.ticket.controller.data;

import com.esucri.projetox.adapters.web.event.controller.data.EventResponseDTO;
import com.esucri.projetox.adapters.web.user.controller.data.UserResponseDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class TicketResponseDTO {

  private Long id;
  private UserResponseDTO user;
  private EventResponseDTO event;
  private boolean showedUp;
  private boolean voted;
}
