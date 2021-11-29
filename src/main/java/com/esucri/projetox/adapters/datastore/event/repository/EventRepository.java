package com.esucri.projetox.adapters.datastore.event.repository;

import com.esucri.projetox.adapters.datastore.event.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

  List<EventEntity> findEventEntitiesByPromoterId(Long id);

  @Query(value = "delete from EventEntity e where e.promoter.id = :promoterId")
  @Transactional
  @Modifying
  void deleteEventByPromoterId(@Param("promoterId") Long promoterId);
}
