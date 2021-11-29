package com.esucri.projetox.domain.event.usecase;

import com.esucri.projetox.adapters.exceptions.ErrorWarningMessage;
import com.esucri.projetox.adapters.exceptions.ErrorWarningMessageException;
import com.esucri.projetox.adapters.exceptions.UnprocessableJsonException;
import com.esucri.projetox.domain.event.model.EventModel;
import com.esucri.projetox.domain.promoter.usecase.PromoterUseCase;
import com.esucri.projetox.domain.utils.mirror.Mirror;
import com.esucri.projetox.domain.utils.mirror.impl.NullIgnoreMirror;
import com.esucri.projetox.ports.event.EventPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

import static com.esucri.projetox.adapters.exceptions.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class EventUseCase {

  private final EventPort port;
  private final PromoterUseCase promoterUseCase;

  public EventModel salvar(String data, MultipartFile image) {
    var model = getModel(data, image);
    validateModel(model);
    var promoter = promoterUseCase.read(model.getPromoterId(), false);
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

  public EventModel update(Long id, String data, MultipartFile image) {
    var existingEvent = read(id);
    var model = getModel(data, image);
    Mirror nullIgnoreMirror = new NullIgnoreMirror();
    var promoter = promoterUseCase.read(existingEvent.getPromoter().getId(), false);
    var updatedEvent = nullIgnoreMirror.copy(model, existingEvent);
    updatedEvent.setPromoter(promoter);
    return port.update(updatedEvent);
  }

  @SneakyThrows
  private EventModel getModel(String data, MultipartFile image) {
    EventModel model;
    try {
      var mapper = new ObjectMapper();
      mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
      mapper.registerModule(new JSR310Module());
      model = mapper.readValue(data, EventModel.class);
      if (Objects.nonNull(image)) {
        model.setImage(image.getBytes());
      }
      return model;
    } catch (Exception e) {
      throw new UnprocessableJsonException(
          new ErrorWarningMessage(E005.getCode(), E005.getMessage()));
    }
  }

  public void deleteEventByPromoterId(Long promoterId) {
    port.deleteByPromoterId(promoterId);
  }

  private void validateModel(EventModel model) {
    Assert.notNull(model.getPromoterId(), "É necessário informar o código do promoter do evento.");
    Assert.notNull(model.getName(), "É necessário informar o nome do evento.");
    Assert.notNull(model.getDescription(), "É necessário informar a descrição do evento.");
    Assert.notNull(model.getTicketAmount(), "É necessário informar a quantidade de tickets do evento.");
    Assert.notNull(model.getTicketValue(), "É necessário informar o valor do ticket do evento.");
    Assert.notNull(model.getEventDate(), "É necessário informar a data do evento.");
  }
}
