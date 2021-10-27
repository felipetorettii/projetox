package com.esucri.projetox.adapters.web.event.controller.data;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class EventRequestDTO {

  @NotNull(message = "É necessário informar o código do promoter do evento.")
  private Long promoterId;

  @NotEmpty(message = "É necessário informar o nome do evento.")
  private String name;

  @NotEmpty(message = "É necessário informar a descrição do evento.")
  private String description;

  @NotNull(message = "É necessário informar a quantidade de tickets do evento.")
  private Long ticketAmount;

  @NotNull(message = "É necessário informar o valor do ticket do evento.")
  private BigDecimal ticketValue;

  private MultipartFile image;

  @NotNull(message = "É necessário informar a data do evento.")
  private LocalDate eventDate;
}
