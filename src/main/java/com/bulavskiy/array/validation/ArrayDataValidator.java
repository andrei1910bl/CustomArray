package com.bulavskiy.array.validation;

import java.util.regex.Pattern;

public class ArrayDataValidator {

  private static final String ARRAY_DATA_REGEX =  "^\\s*(-?\\d+\\s*([, \\-]\\s*-?\\d+\\s*)*)?$";

  private static final Pattern ARRAY_DATA_PATTERN = Pattern.compile(ARRAY_DATA_REGEX);

  private ArrayDataValidator() {}

  public static boolean isValidArrayData(String data) {
    if (data == null) {
      return false;
    }
    return ARRAY_DATA_PATTERN.matcher(data).matches();
  }
}