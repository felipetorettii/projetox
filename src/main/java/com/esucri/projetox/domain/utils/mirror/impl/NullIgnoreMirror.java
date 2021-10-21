package com.esucri.projetox.domain.utils.mirror.impl;

import com.esucri.projetox.domain.utils.text.TextoUtils;
import com.esucri.projetox.domain.utils.datastructure.object.ClassArgument;
import com.esucri.projetox.domain.utils.datastructure.object.ClassMethod;
import com.esucri.projetox.domain.utils.mirror.Mirror;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class NullIgnoreMirror implements Mirror {

  @Override
  public <T> T copy(T source, T destination) {
    Method[] metodos = source.getClass().getDeclaredMethods();

    Arrays.stream(metodos)
        .forEach(
            m -> {
              if (isGetMethod(m)) {
                var get = new ClassMethod(m.getName(), m.getReturnType());
                Object sourceValue = get.invoke(source);
                Object valueToPut =
                    Objects.isNull(sourceValue) ? get.invoke(destination) : sourceValue;

                ClassMethod set = captureSetByGet(get);
                set.addArgument(new ClassArgument(valueToPut, get.getReturnType()));
                set.invoke(destination);
              }
            });
    return destination;
  }

  private boolean isGetMethod(Method method) {
    return method.getName().contains("get");
  }

  private ClassMethod captureSetByGet(ClassMethod method) {
    String attributeName = TextoUtils.getSufixAfterWord(method.getName(), "get");
    String setName = TextoUtils.concatenatePrefixWithSufix("set", attributeName);
    return new ClassMethod(setName, null);
  }
}
