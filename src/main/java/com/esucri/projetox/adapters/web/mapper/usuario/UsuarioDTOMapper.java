package com.esucri.projetox.adapters.web.mapper.usuario;

import com.esucri.projetox.adapters.web.controller.usuario.data.UsuarioRequestDTO;
import com.esucri.projetox.adapters.web.controller.usuario.data.UsuarioResponseDTO;
import com.esucri.projetox.domain.model.UsuarioModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UsuarioDTOMapper {

    UsuarioModel toModel(UsuarioRequestDTO dto);
    UsuarioResponseDTO toResponse(UsuarioModel model);
}
