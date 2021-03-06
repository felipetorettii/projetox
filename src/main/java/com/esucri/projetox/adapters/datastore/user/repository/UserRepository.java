package com.esucri.projetox.adapters.datastore.user.repository;

import com.esucri.projetox.adapters.datastore.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  @Query(
      value =
          "select u from UserEntity u where u.email = :emailOrName and u.pass = :pass")
  Optional<UserEntity> readToLogin(
      @Param("emailOrName") String emailOrName, @Param("pass") String pass);

  @Query(
      value = "select u from UserEntity u where u.email = :email and (:id is null or u.id != :id)")
  Optional<UserEntity> readByEmail(@Param("email") String email, @Param("id") Long id);
}
