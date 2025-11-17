package com.bulavskiy.array.service.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.service.ArrayCalculateService;
import com.bulavskiy.array.service.ArrayReplaceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArrayReplaceServiceImplTest {

  private static final ArrayReplaceService replace = new ArrayReplaceServiceImpl();

  static Stream<Arguments> provideArrayForReplaceElement(){
    return Stream.of(
            Arguments.of(1L, 1, -4, new int[]{4, -2, 3}, new int[]{4, -4, 3}),
            Arguments.of(2L, 0, -4, new int[]{4, -2, 3}, new int[]{-4, -2, 3}),
            Arguments.of(3L, 2, -4, new int[]{4, -2, 3}, new int[]{4, -2, -4})
    );
  }
  static Stream<Arguments> provideArraysForReplaceOldElement() {
    return Stream.of(
            Arguments.of(1L, new int[]{1, 2, 3, 4}, 2, 99, new int[]{1, 99, 3, 4}),

            Arguments.of(2L, new int[]{1, 2, 2, 3, 2}, 2, 99, new int[]{1, 99, 99, 3, 99}),

            // Замена отрицательных чисел
            Arguments.of(3L, new int[]{-1, -2, -3}, -2, 99, new int[]{-1, 99, -3}),

            Arguments.of(4L, new int[]{0, 1, 0, 2}, 0, 99, new int[]{99, 1, 99, 2})
    );
  }

  @ParameterizedTest
  @MethodSource("provideArrayForReplaceElement")
  void replaceElement(Long id, int index, int value, int[] testArray, int[] expectedArray) {

    NewArray newTestArray = NewArray.builder()
            .setId(id)
            .setArray(testArray)
            .build();

    NewArray newExpectedArray = NewArray.builder()
            .setId(id)
            .setArray(expectedArray)
            .build();

    NewArray resultArray = replace.replaceElement(newTestArray, index, value);

    assertArrayEquals(expectedArray, resultArray.getArray());
  }

  @ParameterizedTest
  @MethodSource("provideArraysForReplaceOldElement")
  void replaceOldElement(Long id, int[] testArray, int oldValue, int newValue, int[] expectedArray) {

    NewArray newArray = NewArray.builder()
            .setId(id)
            .setArray(testArray)
            .build();

    NewArray result = replace.replaceOldElement(newArray, oldValue, newValue);

    assertArrayEquals(expectedArray, result.getArray());
  }

  @ParameterizedTest
  @MethodSource("provideArraysForReplaceOldElement")
  void replaceOldElementStream(Long id, int[] testArray, int oldValue, int newValue, int[] expectedArray) {

    NewArray newArray = NewArray.builder()
            .setId(id)
            .setArray(testArray)
            .build();

    NewArray result = replace.replaceOldElementStream(newArray, oldValue, newValue);

    assertArrayEquals(expectedArray, result.getArray());
  }
}