package com.esucri.projetox.adapters.web.ticket.mapper;

import com.esucri.projetox.adapters.web.ticket.controller.data.CheckinRequestDTO;
import com.esucri.projetox.adapters.web.ticket.controller.data.RatingRequestDTO;
import com.esucri.projetox.adapters.web.ticket.controller.data.TicketRequestDTO;
import com.esucri.projetox.adapters.web.ticket.controller.data.TicketResponseDTO;
import com.esucri.projetox.domain.ticket.model.CheckinModel;
import com.esucri.projetox.domain.ticket.model.RatingModel;
import com.esucri.projetox.domain.ticket.model.TicketModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TicketDTOMapper {

  TicketResponseDTO toResponse(TicketModel model);

  List<TicketResponseDTO> toResponseList(List<TicketModel> model);

  TicketModel toModel(TicketRequestDTO dto);

  CheckinModel toCheckinModel(CheckinRequestDTO dto);

  RatingModel toRatingModel(RatingRequestDTO dto);
}
