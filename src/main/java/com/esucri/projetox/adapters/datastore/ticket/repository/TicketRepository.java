package com.esucri.projetox.adapters.datastore.ticket.repository;

import com.esucri.projetox.adapters.datastore.ticket.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

  List<TicketEntity> findTicketEntitiesByUserId(Long id);
}
