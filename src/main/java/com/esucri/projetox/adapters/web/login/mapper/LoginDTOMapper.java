package com.esucri.projetox.adapters.web.login.mapper;

import com.esucri.projetox.adapters.web.login.controller.data.LoginRequestDTO;
import com.esucri.projetox.adapters.web.login.controller.data.LoginResponseDTO;
import com.esucri.projetox.domain.login.model.LoginModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface LoginDTOMapper {

    LoginModel toModel(LoginRequestDTO dto);

    LoginResponseDTO toResponseDTO(LoginModel model);

}
