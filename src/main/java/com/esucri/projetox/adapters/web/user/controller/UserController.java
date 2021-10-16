package com.esucri.projetox.adapters.web.user.controller;

import com.esucri.projetox.adapters.web.GenericResponseDTO;
import com.esucri.projetox.adapters.web.PathEndpoints;
import com.esucri.projetox.adapters.web.user.controller.data.UserRequestDTO;
import com.esucri.projetox.adapters.web.user.mapper.UserDTOMapper;
import com.esucri.projetox.domain.user.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathEndpoints.ENDPOINT_USER)
public class UserController {

  private final UserUseCase useCase;
  private final UserDTOMapper mapper;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponseDTO> save(@RequestBody @Validated UserRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            GenericResponseDTO.builder()
                .data(mapper.toResponse(useCase.save(mapper.toModel(dto))))
                .build());
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponseDTO> read(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(
        GenericResponseDTO.builder().data(mapper.toResponse(useCase.read(id))).build());
  }
}
