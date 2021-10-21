package com.esucri.projetox.domain.utils.datastructure.object;

import com.esucri.projetox.adapters.exceptions.ReflectionException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
@Getter
public class ClassMethod {

  private final String name;
  private final List<ClassArgument> arguments;
  private final Class<?> returnType;

  public ClassMethod(String name, Class<?> returnType) {
    this.name = name;
    this.arguments = new ArrayList<>();
    this.returnType = returnType;
  }

  public <T> Object invoke(T object) {
    var argTypes = arguments.stream().map(ClassArgument::getType).toArray(Class<?>[]::new);
    var argValues = arguments.stream().map(ClassArgument::getValue).toArray(Object[]::new);

    try {
      return arguments.isEmpty()
          ? object.getClass().getDeclaredMethod(name).invoke(object)
          : object.getClass().getDeclaredMethod(name, argTypes).invoke(object, argValues);
    } catch (ReflectiveOperationException e) {
      throw new ReflectionException(e.toString());
    }
  }

  public void addArgument(ClassArgument argument) {
    arguments.add(argument);
  }
}
