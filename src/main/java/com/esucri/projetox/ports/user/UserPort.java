package com.esucri.projetox.ports.user;

import com.esucri.projetox.domain.user.model.UserModel;

import java.util.Optional;

public interface UserPort {
  UserModel create(UserModel model);

  UserModel update(UserModel model);

  Optional<UserModel> read(Long id);
}
