package com.esucri.projetox.adapters.datastore.promoter.dao;

import com.esucri.projetox.adapters.datastore.promoter.mapper.PromoterEntityMapper;
import com.esucri.projetox.adapters.datastore.promoter.repository.PromoterRepository;
import com.esucri.projetox.domain.promoter.model.PromoterModel;
import com.esucri.projetox.ports.promoter.PromoterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PromoterPortImpl implements PromoterPort {

  private final PromoterRepository repository;
  private final PromoterEntityMapper mapper;

  @Override
  public PromoterModel create(PromoterModel model) {
    return mapper.toModel(repository.save(mapper.toEntity(model)));
  }

  @Override
  public PromoterModel update(PromoterModel model) {
    return mapper.toModel(repository.save(mapper.toEntity(model)));
  }

  @Override
  public Optional<PromoterModel> readById(Long id) {
    return repository.findById(id).map(mapper::toModel);
  }

  @Override
  public Optional<PromoterModel> readByUserId(Long id) {
    return repository.findPromoterEntityByUserId(id).map(mapper::toModel);
  }

  @Override
  public List<PromoterModel> readAll() {
    return repository.findAll().stream().map(mapper::toModel).collect(Collectors.toList());
  }
}
