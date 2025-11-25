package com.bulavskiy.array.validation.impl;

import java.util.regex.Pattern;

import com.bulavskiy.array.validation.ArrayDataValidator;

public class ArrayDataValidatorImpl implements ArrayDataValidator {

  private static final String ARRAY_DATA_REGEX =  "^\\s*-?\\d+(\\s*[, \\-]\\s*-?\\d+)*\\s*$";

  public ArrayDataValidatorImpl() {}

  @Override
  public boolean isValidArrayData(String data) {
    if (data == null) {
      return false;
    }
    Pattern arrayDataPattern = Pattern.compile(ARRAY_DATA_REGEX);
    return arrayDataPattern.matcher(data).matches();
  }
}