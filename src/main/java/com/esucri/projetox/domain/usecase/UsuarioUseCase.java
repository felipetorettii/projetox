package com.esucri.projetox.domain.usecase;

import com.esucri.projetox.domain.model.UsuarioModel;
import com.esucri.projetox.ports.UsuarioPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioUseCase {

    private final UsuarioPort port;

    public UsuarioModel salvar(UsuarioModel model) {
        return port.create(model);
    }
}
