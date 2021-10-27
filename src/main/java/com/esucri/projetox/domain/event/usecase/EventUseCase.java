package com.esucri.projetox.domain.event.usecase;

import com.esucri.projetox.adapters.exceptions.ErrorWarningMessage;
import com.esucri.projetox.adapters.exceptions.ErrorWarningMessageException;
import com.esucri.projetox.domain.event.model.EventModel;
import com.esucri.projetox.domain.promoter.usecase.PromoterUseCase;
import com.esucri.projetox.domain.utils.mirror.Mirror;
import com.esucri.projetox.domain.utils.mirror.impl.NullIgnoreMirror;
import com.esucri.projetox.ports.event.EventPort;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.esucri.projetox.adapters.exceptions.ErrorMessage.E003;
import static com.esucri.projetox.adapters.exceptions.ErrorMessage.E004;

@Service
@RequiredArgsConstructor
public class EventUseCase {

  private final EventPort port;
  private final PromoterUseCase promoterUseCase;

  public EventModel salvar(EventModel model) {
    var promoter = promoterUseCase.read(model.getPromoter().getId(), false);
    model.setPromoter(promoter);
    return port.create(model);
  }

  @SneakyThrows
  public List<EventModel> readAll() {
    return port.readAll();
  }

  @SneakyThrows
  public EventModel read(Long id) {
    return port.readById(id)
        .orElseThrow(
            () ->
                new ErrorWarningMessageException(
                    new ErrorWarningMessage(E003.getCode(), E003.getMessage())));
  }

  @SneakyThrows
  public List<EventModel> readByPromoter(Long promoterId) {
    var eventList = port.readByPromoterId(promoterId);
    if (eventList.isEmpty())
      throw new ErrorWarningMessageException(
          new ErrorWarningMessage(E004.getCode(), String.format(E004.getMessage(), promoterId)));
    return eventList;
  }

  public EventModel update(Long id, EventModel model) {
    var existingEvent = read(id);
    Mirror nullIgnoreMirror = new NullIgnoreMirror();
    var promoterId = existingEvent.getPromoter().getId();
    var updatedEvent = nullIgnoreMirror.copy(model, existingEvent);
    updatedEvent.setPromoter(promoterUseCase.update(promoterId, updatedEvent.getPromoter()));
    return port.update(updatedEvent);
  }
}
