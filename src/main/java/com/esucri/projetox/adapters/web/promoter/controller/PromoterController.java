package com.esucri.projetox.adapters.web.promoter.controller;

import com.esucri.projetox.adapters.web.GenericResponseDTO;
import com.esucri.projetox.adapters.web.PathEndpoints;
import com.esucri.projetox.adapters.web.promoter.mapper.PromoterDTOMapper;
import com.esucri.projetox.domain.promoter.usecase.PromoterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathEndpoints.ENDPOINT_PROMOTER)
public class PromoterController {

  private final PromoterUseCase useCase;
  private final PromoterDTOMapper mapper;

  @PostMapping(
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponseDTO> salvar(
      @RequestPart("data") String data, @RequestPart("photo") MultipartFile photo) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            GenericResponseDTO.builder()
                .data(mapper.toResponse(useCase.salvar(data, photo)))
                .build());
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponseDTO> readAll() {
    return ResponseEntity.ok(
        GenericResponseDTO.builder().data(mapper.toResponseList(useCase.readAll())).build());
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponseDTO> read(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(
        GenericResponseDTO.builder().data(mapper.toResponse(useCase.read(id, false))).build());
  }

  @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponseDTO> readByUser(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(
        GenericResponseDTO.builder().data(mapper.toResponse(useCase.read(id, true))).build());
  }

  @PatchMapping(
      value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponseDTO> update(
      @PathVariable(name = "id") Long id,
      @RequestPart("data") String data,
      @RequestPart("photo") MultipartFile photo) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            GenericResponseDTO.builder()
                .data(mapper.toResponse(useCase.update(id, data, photo)))
                .build());
  }
}
