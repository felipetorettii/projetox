package com.esucri.projetox.adapters.web.login.controller.data;

import com.esucri.projetox.adapters.web.user.controller.data.UserResponseDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LoginResponseDTO {

    private boolean admin;
    private UserResponseDTO user;
}
