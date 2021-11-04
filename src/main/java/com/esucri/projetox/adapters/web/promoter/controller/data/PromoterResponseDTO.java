package com.esucri.projetox.adapters.web.promoter.controller.data;

import com.esucri.projetox.adapters.web.user.controller.data.UserResponseDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PromoterResponseDTO {

  private Long id;
  private UserResponseDTO user;
  private byte[] photo;
}
