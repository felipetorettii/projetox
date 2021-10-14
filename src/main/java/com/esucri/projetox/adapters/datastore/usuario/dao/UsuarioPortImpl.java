package com.esucri.projetox.adapters.datastore.usuario.dao;

import com.esucri.projetox.adapters.datastore.usuario.mapper.UsuarioEntityMapper;
import com.esucri.projetox.adapters.datastore.usuario.repository.UsuarioRepository;
import com.esucri.projetox.domain.model.UsuarioModel;
import com.esucri.projetox.ports.UsuarioPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UsuarioPortImpl implements UsuarioPort {

    private final UsuarioRepository repository;
    private final UsuarioEntityMapper mapper;

    @Override
    public UsuarioModel create(UsuarioModel model) {
        return mapper.toModel(repository.save(mapper.toEntity(model)));
    }

    @Override
    public UsuarioModel update(UsuarioModel model) {
        return mapper.toModel(repository.save(mapper.toEntity(model)));
    }

    @Override
    public Optional<UsuarioModel> read(Long id) {
        var toReturn = repository.findById(id);
        return toReturn.map(mapper::toModel);
    }
}
