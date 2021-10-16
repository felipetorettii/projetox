package com.esucri.projetox.adapters.web.promoter.controller;

import com.esucri.projetox.adapters.web.PathEndpoints;
import com.esucri.projetox.adapters.web.promoter.controller.data.PromoterRequestDTO;
import com.esucri.projetox.adapters.web.promoter.controller.data.PromoterResponseDTO;
import com.esucri.projetox.adapters.web.promoter.mapper.PromoterDTOMapper;
import com.esucri.projetox.domain.promoter.usecase.PromoterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathEndpoints.ENDPOINT_PROMOTER)
public class PromoterController {

  private final PromoterUseCase useCase;
  private final PromoterDTOMapper mapper;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PromoterResponseDTO> salvar(
      @RequestBody @Validated PromoterRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(mapper.toResponse(useCase.salvar(mapper.toModel(dto))));
  }
}
