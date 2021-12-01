package com.esucri.projetox.ports.ticket;

import com.esucri.projetox.domain.ticket.model.TicketModel;

import java.util.List;
import java.util.Optional;

public interface TicketPort {

  TicketModel create(TicketModel model);

  TicketModel update(TicketModel model);

  Optional<TicketModel> readById(Long id);

  List<TicketModel> readByUserId(Long id);

  List<TicketModel> readAll();

  List<TicketModel> readByUserIdAndEventId(Long userId, Long eventId);

  void deleteByUserId(Long userId);
}
