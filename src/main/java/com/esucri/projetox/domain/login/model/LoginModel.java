package com.esucri.projetox.domain.login.model;

import com.esucri.projetox.domain.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginModel {

  private String emailOrName;
  private String pass;
  private boolean admin;
  private UserModel user;
}
