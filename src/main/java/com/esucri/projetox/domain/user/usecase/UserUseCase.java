package com.esucri.projetox.domain.user.usecase;

import com.esucri.projetox.adapters.exceptions.ErrorWarningMessage;
import com.esucri.projetox.adapters.exceptions.ErrorWarningMessageException;
import com.esucri.projetox.domain.login.model.LoginModel;
import com.esucri.projetox.domain.user.model.UserModel;
import com.esucri.projetox.domain.utils.mirror.Mirror;
import com.esucri.projetox.domain.utils.mirror.impl.NullIgnoreMirror;
import com.esucri.projetox.ports.promoter.PromoterPort;
import com.esucri.projetox.ports.user.UserPort;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.esucri.projetox.adapters.exceptions.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class UserUseCase {

  private final UserPort port;
  private final PromoterPort promoterPort;

  public UserModel save(UserModel model) {
    verifyEmail(model.getEmail(), null);
    return port.create(model);
  }

  @SneakyThrows
  private void verifyEmail(String email, Long id) {
    var existsEmail = port.readByEmail(email, id);
    if (existsEmail.isPresent())
      throw new ErrorWarningMessageException(
          new ErrorWarningMessage(E009.getCode(), E009.getMessage()));
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
    verifyEmail(model.getEmail(), existingUser.getId());
    Mirror nullIgnoreMirror = new NullIgnoreMirror();
    var updatedUser = nullIgnoreMirror.copy(model, existingUser);
    return port.update(updatedUser);
  }

  @SneakyThrows
  public LoginModel login(LoginModel model) {
    var user =
        port.readToLogin(model.getEmailOrName(), model.getPass())
            .orElseThrow(
                () ->
                    new ErrorWarningMessageException(
                        new ErrorWarningMessage(E008.getCode(), E008.getMessage())));
    model.setAdmin(promoterPort.existsByIdUserId(user.getId()));
    model.setUser(user);
    return model;
  }
}
