package com.esucri.projetox.domain.promoter.usecase;

import com.esucri.projetox.adapters.exceptions.ErrorWarningMessage;
import com.esucri.projetox.adapters.exceptions.ErrorWarningMessageException;
import com.esucri.projetox.adapters.exceptions.UnprocessableJsonException;
import com.esucri.projetox.domain.event.usecase.EventUseCase;
import com.esucri.projetox.domain.promoter.model.PromoterModel;
import com.esucri.projetox.domain.ticket.usecase.TicketUseCase;
import com.esucri.projetox.domain.user.usecase.UserUseCase;
import com.esucri.projetox.domain.utils.mirror.Mirror;
import com.esucri.projetox.domain.utils.mirror.impl.NullIgnoreMirror;
import com.esucri.projetox.ports.event.EventPort;
import com.esucri.projetox.ports.promoter.PromoterPort;
import com.esucri.projetox.ports.ticket.TicketPort;
import com.esucri.projetox.ports.user.UserPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

import static com.esucri.projetox.adapters.exceptions.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class PromoterUseCase {

  private final PromoterPort port;
  private final UserPort userPort;
  private final UserUseCase userUseCase;
  private final TicketPort ticketPort;
  private final EventPort eventPort;

  public PromoterModel save(String data, MultipartFile photo) {
    var model = getModel(data, photo);
    validatePromoter(model);
    verifyEmail(model.getUser().getEmail(), model.getUser().getId());
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
    verifyEmail(model.getUser().getEmail(), existingPromoter.getUser().getId());
    Mirror nullIgnoreMirror = new NullIgnoreMirror();
    var userId = existingPromoter.getUser().getId();
    var updatedPromoter = nullIgnoreMirror.copy(model, existingPromoter);
    updatedPromoter.setUser(userUseCase.update(userId, updatedPromoter.getUser()));
    return port.update(updatedPromoter);
  }

  public void deleteById(Long id) {
    var promoter = read(id, false);
    var userId = promoter.getUser().getId();
    ticketPort.deleteByUserId(userId);
    eventPort.deleteByPromoterId(id);
    port.deleteById(promoter.getId());
    userUseCase.deleteById(userId);
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
  private void verifyEmail(String email, Long id) {
    var existsEmail = userPort.readByEmail(email, id);
    if (existsEmail.isPresent())
      throw new ErrorWarningMessageException(
          new ErrorWarningMessage(E009.getCode(), E009.getMessage()));
  }

  private void validatePromoter(PromoterModel model) {
    Assert.notNull(model.getUser().getName(), "O nome do usuário não pode ser nulo ou vazio.");
    Assert.notNull(model.getUser().getEmail(), "O email do usuário não pode ser nulo ou vazio.");
    Assert.notNull(model.getUser().getPass(), "A senha do usuário não pode ser nulo ou vazio.");
  }
}
