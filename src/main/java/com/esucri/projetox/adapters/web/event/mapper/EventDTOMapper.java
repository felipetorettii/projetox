package com.esucri.projetox.adapters.web.event.mapper;

import com.esucri.projetox.adapters.web.event.controller.data.EventResponseDTO;
import com.esucri.projetox.domain.event.model.EventModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EventDTOMapper {

  EventResponseDTO toResponse(EventModel model);

  List<EventResponseDTO> toResponseList(List<EventModel> model);
}
