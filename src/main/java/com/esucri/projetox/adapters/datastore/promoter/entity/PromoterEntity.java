package com.esucri.projetox.adapters.datastore.promoter.entity;

import com.esucri.projetox.adapters.datastore.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "promoter")
public class PromoterEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @OneToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "id_user", referencedColumnName = "id")
  private UserEntity user;

  @Lob
  @Type(type = "org.hibernate.type.BinaryType")
  @Column(name = "photo")
  private byte[] photo;
}
