package com.esucri.projetox.domain.ticket.usecase;

import com.esucri.projetox.adapters.exceptions.ErrorWarningMessage;
import com.esucri.projetox.adapters.exceptions.ErrorWarningMessageException;
import com.esucri.projetox.domain.event.usecase.EventUseCase;
import com.esucri.projetox.domain.ticket.model.TicketModel;
import com.esucri.projetox.domain.user.usecase.UserUseCase;
import com.esucri.projetox.ports.ticket.TicketPort;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.esucri.projetox.adapters.exceptions.ErrorMessage.E006;
import static com.esucri.projetox.adapters.exceptions.ErrorMessage.E007;

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

  @SneakyThrows
  public TicketModel read(Long id) {
    return port.readById(id)
        .orElseThrow(
            () ->
                new ErrorWarningMessageException(
                    new ErrorWarningMessage(E006.getCode(), E006.getMessage())));
  }

  @SneakyThrows
  public List<TicketModel> readByUserId(Long userId) {
    var ticketList = port.readByUserId(userId);
    if (ticketList.isEmpty())
      throw new ErrorWarningMessageException(
          new ErrorWarningMessage(E007.getCode(), String.format(E007.getMessage(), userId)));
    return ticketList;
  }

  @SneakyThrows
  public List<TicketModel> readAll() {
    return port.readAll();
  }
}
