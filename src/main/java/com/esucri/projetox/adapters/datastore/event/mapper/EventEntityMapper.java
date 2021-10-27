package com.esucri.projetox.adapters.datastore.event.mapper;

import com.esucri.projetox.adapters.datastore.event.entity.EventEntity;
import com.esucri.projetox.domain.event.model.EventModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EventEntityMapper {

  @Mapping(target = "image", ignore = true)
  @Mapping(target = "promoter.photo", ignore = true)
  EventEntity toEntity(EventModel model);

  @Mapping(target = "image", ignore = true)
  @Mapping(target = "promoter.photo", ignore = true)
  EventModel toModel(EventEntity entity);
}
