package com.esucri.projetox.adapters.datastore.event.dao;

import com.esucri.projetox.adapters.datastore.event.mapper.EventEntityMapper;
import com.esucri.projetox.adapters.datastore.event.repository.EventRepository;
import com.esucri.projetox.domain.event.model.EventModel;
import com.esucri.projetox.ports.event.EventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class EventPortImpl implements EventPort {

  private final EventRepository repository;
  private final EventEntityMapper mapper;

  @Override
  public EventModel create(EventModel model) {
    return mapper.toModel(repository.save(mapper.toEntity(model)));
  }

  @Override
  public EventModel update(EventModel model) {
    return mapper.toModel(repository.save(mapper.toEntity(model)));
  }

  @Override
  public Optional<EventModel> readById(Long id) {
    return repository.findById(id).map(mapper::toModel);
  }

  @Override
  public List<EventModel> readByPromoterId(Long id) {
    return repository.findEventEntitiesByPromoterId(id).stream()
        .filter(e -> !e.getEventDate().isBefore(LocalDate.now()))
        .map(mapper::toModel)
        .collect(Collectors.toList());
  }

  @Override
  public List<EventModel> readAll() {
    return repository.findAll().stream()
        .filter(e -> !e.getEventDate().isBefore(LocalDate.now()))
        .map(mapper::toModel)
        .collect(Collectors.toList());
  }
}
