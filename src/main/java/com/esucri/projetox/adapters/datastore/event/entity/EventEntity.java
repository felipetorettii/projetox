package com.esucri.projetox.adapters.datastore.event.entity;

import com.esucri.projetox.adapters.datastore.promoter.entity.PromoterEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event")
public class EventEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "id_promoter", referencedColumnName = "id")
  private PromoterEntity promoter;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "ticket_amount")
  private Long ticketAmount;

  @Column(name = "ticket_value")
  private BigDecimal ticketValue;

  @Lob
  @Type(type = "org.hibernate.type.BinaryType")
  @Column(name = "image")
  private byte[] image;

  @Column(name = "event_date")
  private LocalDate eventDate;
}
