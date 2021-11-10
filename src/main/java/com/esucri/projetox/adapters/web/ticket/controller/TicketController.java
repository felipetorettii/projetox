package com.esucri.projetox.adapters.web.ticket.controller;

import com.esucri.projetox.adapters.web.GenericResponseDTO;
import com.esucri.projetox.adapters.web.PathEndpoints;
import com.esucri.projetox.adapters.web.ticket.controller.data.TicketRequestDTO;
import com.esucri.projetox.adapters.web.ticket.mapper.TicketDTOMapper;
import com.esucri.projetox.domain.ticket.usecase.TicketUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathEndpoints.ENDPOINT_TICKET)
public class TicketController {

  private final TicketUseCase useCase;
  private final TicketDTOMapper mapper;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GenericResponseDTO> salvar(@RequestBody @Valid TicketRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            GenericResponseDTO.builder()
                .data(mapper.toResponse(useCase.salvar(mapper.toModel(dto))))
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

  @GetMapping(value = PathEndpoints.ENDPOINT_USER + "/{id}")
  public ResponseEntity<GenericResponseDTO> readByPromoter(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(
        GenericResponseDTO.builder().data(mapper.toResponseList(useCase.readByUserId(id))).build());
  }
}
