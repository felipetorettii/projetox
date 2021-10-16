package com.esucri.projetox.adapters.datastore.user.repository;

import com.esucri.projetox.adapters.datastore.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {}
