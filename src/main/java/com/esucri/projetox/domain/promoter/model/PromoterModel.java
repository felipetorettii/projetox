package com.esucri.projetox.domain.promoter.model;

import com.esucri.projetox.domain.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PromoterModel {

  private Long id;
  private UserModel user;
  private MultipartFile photo;
}
