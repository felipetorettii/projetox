package com.esucri.projetox.adapters.web.user.mapper;

import com.esucri.projetox.adapters.web.user.controller.data.UserRequestDTO;
import com.esucri.projetox.adapters.web.user.controller.data.UserResponseDTO;
import com.esucri.projetox.domain.user.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserDTOMapper {

  UserModel toModel(UserRequestDTO dto);

  List<UserResponseDTO> toResponseList(List<UserModel> modelList);

  UserResponseDTO toResponse(UserModel model);
}
