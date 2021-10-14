package com.esucri.projetox.adapters.web.controller.usuario;

import com.esucri.projetox.adapters.web.controller.usuario.data.UsuarioRequestDTO;
import com.esucri.projetox.adapters.web.controller.usuario.data.UsuarioResponseDTO;
import com.esucri.projetox.adapters.web.mapper.usuario.UsuarioDTOMapper;
import com.esucri.projetox.adapters.web.path.PathEndpoints;
import com.esucri.projetox.domain.usecase.UsuarioUseCase;
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
@RequestMapping(PathEndpoints.ENDPOINT_USUARIO)
public class UsuarioController {

  private final UsuarioUseCase useCase;
  private final UsuarioDTOMapper mapper;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UsuarioResponseDTO> salvar(@RequestBody @Validated UsuarioRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(mapper.toResponse(useCase.salvar(mapper.toModel(dto))));
  }
}
