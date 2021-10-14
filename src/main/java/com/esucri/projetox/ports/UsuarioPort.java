package com.esucri.projetox.ports;

import com.esucri.projetox.domain.model.UsuarioModel;

import java.util.Optional;

public interface UsuarioPort {
    UsuarioModel create(UsuarioModel model);
    UsuarioModel update(UsuarioModel model);
    Optional<UsuarioModel> read(Long id);
}
