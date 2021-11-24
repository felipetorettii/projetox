package com.esucri.projetox.domain.promoter.usecase;

import com.esucri.projetox.adapters.exceptions.ErrorWarningMessage;
import com.esucri.projetox.adapters.exceptions.ErrorWarningMessageException;
import com.esucri.projetox.adapters.exceptions.UnprocessableJsonException;
import com.esucri.projetox.domain.login.model.LoginModel;
import com.esucri.projetox.domain.promoter.model.PromoterModel;
import com.esucri.projetox.domain.user.usecase.UserUseCase;
import com.esucri.projetox.domain.utils.mirror.Mirror;
import com.esucri.projetox.domain.utils.mirror.impl.NullIgnoreMirror;
import com.esucri.projetox.ports.promoter.PromoterPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

import static com.esucri.projetox.adapters.exceptions.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class PromoterUseCase {

  private final PromoterPort port;
  private final UserUseCase userUseCase;

  public PromoterModel salvar(String data, MultipartFile photo) {
    var model = getModel(data, photo);
    return port.create(model);
  }

  public List<PromoterModel> readAll() {
    return port.readAll();
  }

  @SneakyThrows
  public PromoterModel read(Long id, boolean byUser) {
    if (byUser)
      return port.readByUserId(id)
          .orElseThrow(
              () ->
                  new ErrorWarningMessageException(
                      new ErrorWarningMessage(E002.getCode(), E002.getMessage())));
    return port.readById(id)
        .orElseThrow(
            () ->
                new ErrorWarningMessageException(
                    new ErrorWarningMessage(E002.getCode(), E002.getMessage())));
  }

  public PromoterModel update(Long id, String data, MultipartFile photo) {
    var model = getModel(data, photo);
    var existingPromoter = read(id, false);
    Mirror nullIgnoreMirror = new NullIgnoreMirror();
    var userId = existingPromoter.getUser().getId();
    var updatedPromoter = nullIgnoreMirror.copy(model, existingPromoter);
    updatedPromoter.setUser(userUseCase.update(userId, updatedPromoter.getUser()));
    return port.update(updatedPromoter);
  }

  @SneakyThrows
  private PromoterModel getModel(String data, MultipartFile photo) {
    PromoterModel model;
    try {
      model = new ObjectMapper().readValue(data, PromoterModel.class);
      if (Objects.nonNull(photo)) {
        model.setPhoto(photo.getBytes());
      }
      return model;
    } catch (Exception e) {
      throw new UnprocessableJsonException(
          new ErrorWarningMessage(E005.getCode(), E005.getMessage()));
    }
  }

  @SneakyThrows
  public void loginPromoter(LoginModel model) {
    var user =
        port.readToLogin(model.getEmailOrName(), model.getPass())
            .orElseThrow(
                () ->
                    new ErrorWarningMessageException(
                        new ErrorWarningMessage(E008.getCode(), E008.getMessage())));
  }
}
