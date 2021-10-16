package com.esucri.projetox.adapters.datastore.promoter.repository;

import com.esucri.projetox.adapters.datastore.promoter.entity.PromoterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromoterRepository extends JpaRepository<PromoterEntity, Long> {

  Optional<PromoterEntity> findPromoterEntityByUserId(Long id);
}
