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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathEndpoints.ENDPOINT_USER)
public class UserController {

  private final UserUseCase useCase;
  private final UserDTOMapper mapper;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponseDTO> save(@RequestBody @Valid UserRequestDTO dto) {
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

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponseDTO> readAll() {
    return ResponseEntity.ok(
        GenericResponseDTO.builder().data(mapper.toResponseList(useCase.readAll())).build());
  }

  @PatchMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponseDTO> update(
      @PathVariable(name = "id") Long id, @RequestBody UserRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            GenericResponseDTO.builder()
                .data(mapper.toResponse(useCase.update(id, mapper.toModel(dto))))
                .build());
  }
}
