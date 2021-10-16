package com.esucri.projetox.domain.promoter.usecase;

import com.esucri.projetox.domain.promoter.model.PromoterModel;
import com.esucri.projetox.ports.promoter.PromoterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromoterUseCase {

  private final PromoterPort port;

  public PromoterModel salvar(PromoterModel model) {
    return port.create(model);
  }
}
