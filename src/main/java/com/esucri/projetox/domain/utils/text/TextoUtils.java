package com.esucri.projetox.domain.utils.text;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TextoUtils {

  private static final String SNAKE_CASE_PATTERN = "$1_$2";

  public static String getSufixAfterWord(String text, String word) {
    return text.substring(text.indexOf(word) + word.length());
  }

  public static String concatenatePrefixWithSufix(String prefix, String sufix) {
    try {
      return prefix.concat(sufix);
    } catch (NullPointerException e) {
      return "";
    }
  }

  public static String camelCaseToSnakeCase(String camel) {
    return camel.replaceAll("([a-z])([A-Z]+)", SNAKE_CASE_PATTERN).toLowerCase();
  }
}
