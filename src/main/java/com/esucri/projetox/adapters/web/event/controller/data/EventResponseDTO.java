package com.esucri.projetox.adapters.web.event.controller.data;

import com.esucri.projetox.adapters.web.promoter.controller.data.PromoterResponseDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EventResponseDTO {

  private Long id;
  private String name;
  private String description;
  private Long ticketAmount;
  private BigDecimal ticketValue;
  private LocalDate eventDate;
  private byte[] image;
  private PromoterResponseDTO promoter;
}
