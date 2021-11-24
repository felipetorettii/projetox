package com.esucri.projetox.adapters.web.login.controller;

import com.esucri.projetox.adapters.web.PathEndpoints;
import com.esucri.projetox.adapters.web.login.controller.data.LoginRequestDTO;
import com.esucri.projetox.adapters.web.login.mapper.LoginDTOMapper;
import com.esucri.projetox.domain.promoter.usecase.PromoterUseCase;
import com.esucri.projetox.domain.user.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathEndpoints.ENDPOINT_LOGIN)
public class LoginController {

  private final UserUseCase userUseCase;
  private final PromoterUseCase promoterUseCase;
  private final LoginDTOMapper mapper;

  @PostMapping(
      value = "/users",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void readUser(@RequestBody LoginRequestDTO dto) {
    userUseCase.loginUser(mapper.toModel(dto));
  }

  @PostMapping(
      value = "/promoters",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void readPromoter(@RequestBody LoginRequestDTO dto) {
    promoterUseCase.loginPromoter(mapper.toModel(dto));
  }
}
