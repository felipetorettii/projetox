package com.esucri.projetox.domain.ticket.model;

import com.esucri.projetox.domain.event.model.EventModel;
import com.esucri.projetox.domain.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TicketModel {

  private Long id;
  private EventModel event;
  private UserModel user;
  private Long eventId;
  private Long userId;
  private boolean showedUp;
  private boolean voted;
}
