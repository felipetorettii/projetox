package com.esucri.projetox.adapters.datastore.event.repository;

import com.esucri.projetox.adapters.datastore.event.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Long> {

  List<EventEntity> findEventEntityByPromoterId(Long id);
}
