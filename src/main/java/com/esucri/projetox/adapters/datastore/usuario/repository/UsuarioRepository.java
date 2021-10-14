package com.esucri.projetox.adapters.datastore.usuario.repository;

import com.esucri.projetox.adapters.datastore.usuario.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

}
