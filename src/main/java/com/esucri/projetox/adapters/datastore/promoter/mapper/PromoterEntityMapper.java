package com.esucri.projetox.adapters.datastore.promoter.mapper;

import com.esucri.projetox.adapters.datastore.promoter.entity.PromoterEntity;
import com.esucri.projetox.domain.promoter.model.PromoterModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PromoterEntityMapper {

  @Mapping(target = "photo", ignore = true)
  PromoterEntity toEntity(PromoterModel model);

  @Mapping(target = "photo", ignore = true)
  PromoterModel toModel(PromoterEntity entity);
}
