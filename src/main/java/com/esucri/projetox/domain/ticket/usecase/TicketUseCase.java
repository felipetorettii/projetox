package com.esucri.projetox.domain.ticket.usecase;

import com.esucri.projetox.adapters.exceptions.ErrorWarningMessage;
import com.esucri.projetox.adapters.exceptions.ErrorWarningMessageException;
import com.esucri.projetox.domain.event.usecase.EventUseCase;
import com.esucri.projetox.domain.ticket.model.CheckinModel;
import com.esucri.projetox.domain.ticket.model.TicketModel;
import com.esucri.projetox.domain.user.model.UserModel;
import com.esucri.projetox.domain.user.usecase.UserUseCase;
import com.esucri.projetox.ports.event.EventPort;
import com.esucri.projetox.ports.ticket.TicketPort;
import com.esucri.projetox.ports.user.UserPort;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.esucri.projetox.adapters.exceptions.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class TicketUseCase {

  private final UserUseCase userUseCase;
  private final EventUseCase eventUseCase;
  private final TicketPort port;
  private final EventPort eventPort;
  private final UserPort userPort;

  @SneakyThrows
  public TicketModel salvar(TicketModel model) {
    var user = userUseCase.read(model.getUserId());
    var event = eventUseCase.read(model.getEventId());
    if (event.getTicketAmount() == 0L)
      throw new ErrorWarningMessageException(
          new ErrorWarningMessage(E010.getCode(), E010.getMessage()));
    event.setTicketAmount(event.getTicketAmount() - 1);
    model.setUser(user);
    model.setEvent(event);
    eventPort.update(event);
    return port.create(model);
  }

  @SneakyThrows
  public void checkin(CheckinModel model) {
    var user = getUserModel(model);
    var ticket = getTicketList(model, user);
    ticket.forEach(t-> {
      t.setShowedUp(true);
      port.update(t);
    });
  }

  @SneakyThrows
  private List<TicketModel> getTicketList(CheckinModel model, UserModel user) {
    return port.readByUserIdAndEventId(user.getId(), model.getEventId());
  }

  @SneakyThrows
  private UserModel getUserModel(CheckinModel model) {
    var userOpt = userPort.readByEmail(model.getUserEmail(), null);
    if (userOpt.isEmpty()) {
      throw new ErrorWarningMessageException(
          new ErrorWarningMessage(E001.getCode(), E001.getMessage()));
    }
    return userOpt.get();
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

  public void deleteTicketByUserId(Long userId) {
    port.deleteByUserId(userId);
  }
}
