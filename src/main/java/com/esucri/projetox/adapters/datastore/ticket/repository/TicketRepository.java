package com.esucri.projetox.adapters.datastore.ticket.repository;

import com.esucri.projetox.adapters.datastore.ticket.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

  List<TicketEntity> findTicketEntitiesByUserId(Long id);

  @Query(value = "delete from TicketEntity t where t.user.id = :userId")
  @Transactional
  @Modifying
  void deleteTicketByUserId(@Param("userId") Long userId);

  @Query(value = "select t from TicketEntity t where t.user.id = :userId and t.event.id = :eventId")
  List<TicketEntity> findByUserIdAndEventId(@Param("userId") Long userId, @Param("eventId") Long eventId);
}
