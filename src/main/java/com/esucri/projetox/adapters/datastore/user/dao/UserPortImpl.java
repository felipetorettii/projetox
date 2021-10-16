package com.esucri.projetox.adapters.datastore.user.dao;

import com.esucri.projetox.adapters.datastore.user.mapper.UserEntityMapper;
import com.esucri.projetox.adapters.datastore.user.repository.UserRepository;
import com.esucri.projetox.domain.user.model.UserModel;
import com.esucri.projetox.ports.user.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UserPortImpl implements UserPort {

  private final UserRepository repository;
  private final UserEntityMapper mapper;

  @Override
  public UserModel create(UserModel model) {
    return mapper.toModel(repository.save(mapper.toEntity(model)));
  }

  @Override
  public UserModel update(UserModel model) {
    return mapper.toModel(repository.save(mapper.toEntity(model)));
  }

  @Override
  public Optional<UserModel> read(Long id) {
    return repository.findById(id).map(mapper::toModel);
  }
}
