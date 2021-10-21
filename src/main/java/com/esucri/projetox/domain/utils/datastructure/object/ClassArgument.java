package com.esucri.projetox.domain.utils.datastructure.object;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClassArgument {

    private final Object value;
    private final Class<?> type;
}
