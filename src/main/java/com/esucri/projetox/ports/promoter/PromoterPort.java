package com.esucri.projetox.ports.promoter;

import com.esucri.projetox.domain.promoter.model.PromoterModel;

import java.util.List;
import java.util.Optional;

public interface PromoterPort {
  PromoterModel create(PromoterModel model);

  PromoterModel update(PromoterModel model);

  Optional<PromoterModel> readById(Long id);

  Optional<PromoterModel> readByUserId(Long id);

  List<PromoterModel> readAll();

  boolean existsByIdUserId(Long id);
}
