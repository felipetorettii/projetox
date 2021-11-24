package com.esucri.projetox.adapters.datastore.promoter.repository;

import com.esucri.projetox.adapters.datastore.promoter.entity.PromoterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PromoterRepository extends JpaRepository<PromoterEntity, Long> {

  Optional<PromoterEntity> findPromoterEntityByUserId(Long id);

  @Query(
      value =
          "select p from PromoterEntity p where (p.user.email = :emailOrName or p.user.name = :emailOrName) and p.user.pass = :pass")
  Optional<PromoterEntity> readToLogin(
      @Param("emailOrName") String emailOrName, @Param("pass") String pass);
}
