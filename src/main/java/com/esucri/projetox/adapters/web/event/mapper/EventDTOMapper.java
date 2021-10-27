package com.esucri.projetox.adapters.web.event.mapper;

import com.esucri.projetox.adapters.web.event.controller.data.EventRequestDTO;
import com.esucri.projetox.adapters.web.event.controller.data.EventResponseDTO;
import com.esucri.projetox.domain.event.model.EventModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EventDTOMapper {

  @Mapping(target = "promoter.id", source = "promoterId")
  EventModel toModel(EventRequestDTO dto);

  EventResponseDTO toResponse(EventModel model);

  List<EventResponseDTO> toResponseList(List<EventModel> model);
}
