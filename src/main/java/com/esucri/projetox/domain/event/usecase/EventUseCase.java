package com.esucri.projetox.domain.event.usecase;

import com.esucri.projetox.adapters.exceptions.ErrorWarningMessage;
import com.esucri.projetox.adapters.exceptions.ErrorWarningMessageException;
import com.esucri.projetox.adapters.exceptions.UnprocessableJsonException;
import com.esucri.projetox.domain.event.model.EventModel;
import com.esucri.projetox.domain.promoter.usecase.PromoterUseCase;
import com.esucri.projetox.domain.ticket.model.TicketModel;
import com.esucri.projetox.domain.utils.mirror.Mirror;
import com.esucri.projetox.domain.utils.mirror.impl.NullIgnoreMirror;
import com.esucri.projetox.ports.event.EventPort;
import com.esucri.projetox.ports.ticket.TicketPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static com.esucri.projetox.adapters.exceptions.ErrorMessage.*;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
@RequiredArgsConstructor
public class EventUseCase {

  private final EventPort port;
  private final PromoterUseCase promoterUseCase;
  private final TicketPort ticketPort;

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

  @SneakyThrows
  public List<EventModel> readByUser(Long userId) {
    var tickets =
        ticketPort.readByUserId(userId).stream()
            .filter(t -> (t.isShowedUp()) && (!t.isVoted()))
            .collect(Collectors.toList());
    var events = new ArrayList<EventModel>();
    for (TicketModel ticket : tickets) {
      var event = port.readById(ticket.getEvent().getId());
      event.ifPresent(events::add);
    }
    return events.stream()
        .collect(
            collectingAndThen(
                toCollection(() -> new TreeSet<>(comparingLong(EventModel::getId))),
                ArrayList::new));
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
    Assert.notNull(model.getPromoterId(), "?? necess??rio informar o c??digo do promoter do evento.");
    Assert.notNull(model.getName(), "?? necess??rio informar o nome do evento.");
    Assert.notNull(model.getDescription(), "?? necess??rio informar a descri????o do evento.");
    Assert.notNull(
        model.getTicketAmount(), "?? necess??rio informar a quantidade de tickets do evento.");
    Assert.notNull(model.getTicketValue(), "?? necess??rio informar o valor do ticket do evento.");
    Assert.notNull(model.getEventDate(), "?? necess??rio informar a data do evento.");
  }
}
