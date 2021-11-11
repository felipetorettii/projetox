package com.esucri.projetox.adapters.datastore.ticket.entity;

import com.esucri.projetox.adapters.datastore.event.entity.EventEntity;
import com.esucri.projetox.adapters.datastore.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket")
public class TicketEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "id_user", referencedColumnName = "id")
  private UserEntity user;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "id_event", referencedColumnName = "id")
  private EventEntity event;

  @Column(name = "showed_up")
  private boolean showedUp;

  @Column(name = "voted")
  private boolean voted;
}
