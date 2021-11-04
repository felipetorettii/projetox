package com.esucri.projetox.domain.event.model;

import com.esucri.projetox.domain.promoter.model.PromoterModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EventModel {

  private Long id;
  private Long promoterId;
  private PromoterModel promoter;
  private String name;
  private String description;
  private Long ticketAmount;
  private BigDecimal ticketValue;
  private byte[] image;
  private LocalDate eventDate;
}
