package com.bulavskiy.array.parser;

import com.bulavskiy.array.parser.impl.ArrayParserImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ArrayParserImplTest {

  private final ArrayParser parser = new ArrayParserImpl();

  @Test
  void parseShouldExtractNumbersIgnoringSeparators() {
    int[] result = parser.parse("4, -1, -2, 2");
    assertArrayEquals(new int[]{4, -1, -2, 2}, result);
  }
}

