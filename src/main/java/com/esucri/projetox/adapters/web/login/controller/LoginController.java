package com.esucri.projetox.adapters.web.login.controller;

import com.esucri.projetox.adapters.web.GenericResponseDTO;
import com.esucri.projetox.adapters.web.PathEndpoints;
import com.esucri.projetox.adapters.web.login.controller.data.LoginRequestDTO;
import com.esucri.projetox.adapters.web.login.mapper.LoginDTOMapper;
import com.esucri.projetox.domain.user.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathEndpoints.ENDPOINT_LOGIN)
public class LoginController {

  private final UserUseCase userUseCase;
  private final LoginDTOMapper mapper;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponseDTO> readUser(@RequestBody LoginRequestDTO dto) {
    return ResponseEntity.ok(
        GenericResponseDTO.builder()
            .data(mapper.toResponseDTO(userUseCase.login(mapper.toModel(dto))))
            .build());
  }
}
