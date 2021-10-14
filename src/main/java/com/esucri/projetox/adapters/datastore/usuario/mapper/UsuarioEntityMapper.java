package com.esucri.projetox.adapters.datastore.usuario.mapper;

import com.esucri.projetox.adapters.datastore.usuario.entity.UsuarioEntity;
import com.esucri.projetox.domain.model.UsuarioModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UsuarioEntityMapper {

    UsuarioEntity toEntity(UsuarioModel model);

    UsuarioModel toModel(UsuarioEntity entity);
}
