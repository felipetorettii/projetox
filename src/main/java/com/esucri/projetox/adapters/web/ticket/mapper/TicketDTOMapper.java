package com.esucri.projetox.adapters.web.ticket.mapper;

import com.esucri.projetox.adapters.web.ticket.controller.data.TicketRequestDTO;
import com.esucri.projetox.adapters.web.ticket.controller.data.TicketResponseDTO;
import com.esucri.projetox.domain.ticket.model.TicketModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TicketDTOMapper {

  TicketResponseDTO toResponse(TicketModel model);

  TicketModel toModel(TicketRequestDTO dto);
}
