package com.esucri.projetox.adapters.datastore.event.mapper;

import com.esucri.projetox.adapters.datastore.event.entity.EventEntity;
import com.esucri.projetox.domain.event.model.EventModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EventEntityMapper {

  EventEntity toEntity(EventModel model);

  EventModel toModel(EventEntity entity);
}
