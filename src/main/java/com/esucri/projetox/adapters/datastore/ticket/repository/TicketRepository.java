package com.esucri.projetox.adapters.datastore.ticket.repository;

import com.esucri.projetox.adapters.datastore.ticket.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

  List<TicketEntity> findTicketEntitiesByUserId(Long id);

  @Query(value = "delete from TicketEntity t where t.user.id = :userId")
  @Transactional
  @Modifying
  void deleteTicketByUserId(@Param("userId") Long userId);
}
