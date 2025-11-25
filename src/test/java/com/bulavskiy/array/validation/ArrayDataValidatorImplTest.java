package com.bulavskiy.array.validation;

import com.bulavskiy.array.validation.impl.ArrayDataValidatorImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayDataValidatorImplTest {

  private final ArrayDataValidator validator = new ArrayDataValidatorImpl();

  @Test
  void isValidArrayDataShouldAllowDigitsWithSeparators() {
    assertTrue(validator.isValidArrayData("1 2 3 -4"));
  }

  @Test
  void isValidArrayDataShouldRejectEmptyString() {
    assertFalse(validator.isValidArrayData(""));
  }
}

