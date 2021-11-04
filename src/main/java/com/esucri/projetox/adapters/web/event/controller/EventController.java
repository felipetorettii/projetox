package com.esucri.projetox.adapters.web.event.controller;

import com.esucri.projetox.adapters.web.GenericResponseDTO;
import com.esucri.projetox.adapters.web.PathEndpoints;
import com.esucri.projetox.adapters.web.event.mapper.EventDTOMapper;
import com.esucri.projetox.domain.event.usecase.EventUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathEndpoints.ENDPOINT_EVENT)
public class EventController {

  private final EventUseCase useCase;
  private final EventDTOMapper mapper;

  @PostMapping(
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponseDTO> salvar(
      @RequestPart("data") String data, @RequestPart("image") MultipartFile image) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            GenericResponseDTO.builder()
                .data(mapper.toResponse(useCase.salvar(data, image)))
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
        GenericResponseDTO.builder().data(mapper.toResponse(useCase.read(id))).build());
  }

  @GetMapping(value = PathEndpoints.ENDPOINT_PROMOTER + "/{id}")
  public ResponseEntity<GenericResponseDTO> readByPromoter(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(
        GenericResponseDTO.builder()
            .data(mapper.toResponseList(useCase.readByPromoter(id)))
            .build());
  }

  @PatchMapping(
      value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE},
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponseDTO> update(
      @PathVariable(name = "id") Long id,
      @RequestPart("data") String data,
      @RequestPart("image") MultipartFile image) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(
            GenericResponseDTO.builder()
                .data(mapper.toResponse(useCase.update(id, data, image)))
                .build());
  }
}
