package com.esucri.projetox.domain.user.usecase;

import com.esucri.projetox.adapters.exceptions.ErrorWarningMessage;
import com.esucri.projetox.adapters.exceptions.ErrorWarningMessageException;
import com.esucri.projetox.domain.user.model.UserModel;
import com.esucri.projetox.domain.utils.mirror.Mirror;
import com.esucri.projetox.domain.utils.mirror.impl.NullIgnoreMirror;
import com.esucri.projetox.ports.user.UserPort;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import static com.esucri.projetox.adapters.exceptions.ErrorMessage.E001;

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

  public UserModel update(Long id, UserModel model) {
    var existingUser = read(id);
    Mirror nullIgnoreMirror = new NullIgnoreMirror();
    var updatedUser = nullIgnoreMirror.copy(model, existingUser);
    return port.update(updatedUser);
  }
}
