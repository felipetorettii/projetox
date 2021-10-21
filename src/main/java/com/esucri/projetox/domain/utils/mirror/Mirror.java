package com.esucri.projetox.domain.utils.mirror;

public interface Mirror {

    <T> T copy(T origem, T destino);
}
