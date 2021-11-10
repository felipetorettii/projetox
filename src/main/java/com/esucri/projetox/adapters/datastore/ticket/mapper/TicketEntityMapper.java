package com.esucri.projetox.adapters.datastore.ticket.mapper;

import com.esucri.projetox.adapters.datastore.ticket.entity.TicketEntity;
import com.esucri.projetox.domain.ticket.model.TicketModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TicketEntityMapper {

  TicketEntity toEntity(TicketModel model);

  TicketModel toModel(TicketEntity entity);
}
