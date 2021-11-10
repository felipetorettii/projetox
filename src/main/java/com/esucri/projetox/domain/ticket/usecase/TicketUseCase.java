package com.esucri.projetox.domain.ticket.usecase;

import com.esucri.projetox.domain.event.usecase.EventUseCase;
import com.esucri.projetox.domain.ticket.model.TicketModel;
import com.esucri.projetox.domain.user.usecase.UserUseCase;
import com.esucri.projetox.ports.ticket.TicketPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketUseCase {

  private final UserUseCase userUseCase;
  private final EventUseCase eventUseCase;
  private final TicketPort port;

  public TicketModel salvar(TicketModel model) {
    var user = userUseCase.read(model.getUserId());
    var event = eventUseCase.read(model.getEventId());
    model.setUser(user);
    model.setEvent(event);
    return port.create(model);
  }
}
