package com.esucri.projetox.adapters.datastore.ticket.dao;

import com.esucri.projetox.adapters.datastore.ticket.mapper.TicketEntityMapper;
import com.esucri.projetox.adapters.datastore.ticket.repository.TicketRepository;
import com.esucri.projetox.domain.ticket.model.TicketModel;
import com.esucri.projetox.ports.ticket.TicketPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class TicketPortImpl implements TicketPort {

  private final TicketRepository repository;
  private final TicketEntityMapper mapper;

  @Override
  public TicketModel create(TicketModel model) {
    return mapper.toModel(repository.save(mapper.toEntity(model)));
  }

  @Override
  public TicketModel update(TicketModel model) {
    return mapper.toModel(repository.save(mapper.toEntity(model)));
  }

  @Override
  public Optional<TicketModel> readById(Long id) {
    return repository.findById(id).map(mapper::toModel);
  }

  @Override
  public List<TicketModel> readByUserId(Long id) {
    return repository.findTicketEntitiesByUserId(id).stream()
        .map(mapper::toModel)
        .collect(Collectors.toList());
  }

  @Override
  public List<TicketModel> readAll() {
    return repository.findAll().stream().map(mapper::toModel).collect(Collectors.toList());
  }
}
