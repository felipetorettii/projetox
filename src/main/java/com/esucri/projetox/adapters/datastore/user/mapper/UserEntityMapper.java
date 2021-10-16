package com.esucri.projetox.adapters.datastore.user.mapper;

import com.esucri.projetox.adapters.datastore.user.entity.UserEntity;
import com.esucri.projetox.domain.user.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

  UserEntity toEntity(UserModel model);

  UserModel toModel(UserEntity entity);
}
