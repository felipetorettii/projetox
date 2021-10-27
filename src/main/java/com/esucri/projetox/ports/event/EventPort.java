package com.esucri.projetox.ports.event;

import com.esucri.projetox.domain.event.model.EventModel;

import java.util.List;
import java.util.Optional;

public interface EventPort {

  EventModel create(EventModel model);

  EventModel update(EventModel model);

  Optional<EventModel> readById(Long id);

  List<EventModel> readByPromoterId(Long id);
}
