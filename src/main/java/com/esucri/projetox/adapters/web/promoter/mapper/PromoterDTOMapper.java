package com.esucri.projetox.adapters.web.promoter.mapper;

import com.esucri.projetox.adapters.web.promoter.controller.data.PromoterResponseDTO;
import com.esucri.projetox.domain.promoter.model.PromoterModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PromoterDTOMapper {

  PromoterResponseDTO toResponse(PromoterModel model);
}
