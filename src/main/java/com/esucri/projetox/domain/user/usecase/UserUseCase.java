package com.esucri.projetox.domain.user.usecase;

import com.esucri.projetox.adapters.exceptions.ErrorWarningMessage;
import com.esucri.projetox.adapters.exceptions.ErrorWarningMessageException;
import com.esucri.projetox.domain.login.model.LoginModel;
import com.esucri.projetox.domain.user.model.UserModel;
import com.esucri.projetox.domain.utils.mirror.Mirror;
import com.esucri.projetox.domain.utils.mirror.impl.NullIgnoreMirror;
import com.esucri.projetox.ports.user.UserPort;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.esucri.projetox.adapters.exceptions.ErrorMessage.E001;
import static com.esucri.projetox.adapters.exceptions.ErrorMessage.E008;

@Service
@RequiredArgsConstructor
public class UserUseCase {

  private final UserPort port;

  public UserModel save(UserModel model) {
    return port.create(model);
  }

  @SneakyThrows
  public UserModel read(Long id) {
    return port.read(id)
        .orElseThrow(
            () ->
                new ErrorWarningMessageException(
                    new ErrorWarningMessage(E001.getCode(), E001.getMessage())));
  }

  public List<UserModel> readAll() {
    return port.readAll();
  }

  public UserModel update(Long id, UserModel model) {
    var existingUser = read(id);
    Mirror nullIgnoreMirror = new NullIgnoreMirror();
    var updatedUser = nullIgnoreMirror.copy(model, existingUser);
    return port.update(updatedUser);
  }

  @SneakyThrows
  public void loginUser(LoginModel model) {
    var user =
        port.readToLogin(model.getEmailOrName(), model.getPass())
            .orElseThrow(
                () ->
                    new ErrorWarningMessageException(
                        new ErrorWarningMessage(E008.getCode(), E008.getMessage())));
  }
}
