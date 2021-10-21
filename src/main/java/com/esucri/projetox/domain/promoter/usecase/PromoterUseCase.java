package com.esucri.projetox.domain.promoter.usecase;

import com.esucri.projetox.adapters.exceptions.ErrorWarningMessage;
import com.esucri.projetox.adapters.exceptions.ErrorWarningMessageException;
import com.esucri.projetox.domain.promoter.model.PromoterModel;
import com.esucri.projetox.domain.user.model.UserModel;
import com.esucri.projetox.domain.user.usecase.UserUseCase;
import com.esucri.projetox.domain.utils.mirror.Mirror;
import com.esucri.projetox.domain.utils.mirror.impl.NullIgnoreMirror;
import com.esucri.projetox.ports.promoter.PromoterPort;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import static com.esucri.projetox.adapters.exceptions.ErrorMessage.E002;

@Service
@RequiredArgsConstructor
public class PromoterUseCase {

  private final PromoterPort port;
  private final UserUseCase userUseCase;

  public PromoterModel salvar(PromoterModel model) {
    return port.create(model);
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

  public PromoterModel update(Long id, PromoterModel model) {
      var existingPromoter = read(id, false);
      Mirror nullIgnoreMirror = new NullIgnoreMirror();
      var userId = existingPromoter.getUser().getId();
      var updatedPromoter = nullIgnoreMirror.copy(model, existingPromoter);
      updatedPromoter.setUser(userUseCase.update(userId, updatedPromoter.getUser()));
      return port.update(updatedPromoter);

  }
}
