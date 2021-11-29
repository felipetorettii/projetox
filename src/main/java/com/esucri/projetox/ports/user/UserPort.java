package com.esucri.projetox.ports.user;

import com.esucri.projetox.domain.user.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserPort {
  UserModel create(UserModel model);

  UserModel update(UserModel model);

  Optional<UserModel> read(Long id);

  List<UserModel> readAll();

  Optional<UserModel> readToLogin(String emailOrName, String pass);

  Optional<UserModel> readByEmail(String email, Long id);

  void deleteById(Long id);
}
