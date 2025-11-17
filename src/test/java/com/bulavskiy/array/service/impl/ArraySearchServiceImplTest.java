package com.bulavskiy.array.service.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.exception.ArrayException;
import com.bulavskiy.array.service.ArraySearchService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArraySearchServiceImplTest {

  private static final ArraySearchService search = new ArraySearchServiceImpl();

  static Stream<Arguments> provideArrayForFindMinValue() {
    return Stream.of(
            Arguments.of(1L, new int[]{4, -2, 3}, -2),
            Arguments.of(2L, new int[]{1, 2, 3}, 1),
            Arguments.of(3L, new int[]{-1, -2, -3}, -3),
            Arguments.of(4L, new int[]{5}, 5),
            Arguments.of(5L, new int[]{0, 0, 0}, 0),
            Arguments.of(6L, new int[]{10, 5, 8, 1}, 1)
    );
  }

  static Stream<Arguments> provideArrayForFindMaxValue() {
    return Stream.of(
            Arguments.of(1L, new int[]{4, -2, 3}, 4),
            Arguments.of(2L, new int[]{1, 2, 3}, 3),
            Arguments.of(3L, new int[]{-1, -2, -3}, -1),
            Arguments.of(4L, new int[]{5}, 5),
            Arguments.of(5L, new int[]{0, 0, 0}, 0),
            Arguments.of(6L, new int[]{10, 5, 8, 1}, 10)
    );
  }

  static Stream<Arguments> provideArrayForFindNegative() {
    return Stream.of(
            Arguments.of(1L, new int[]{4, -2, 3}, new int[]{-2}),
            Arguments.of(2L, new int[]{-1, -2, -3}, new int[]{-1, -2, -3}),
            Arguments.of(3L, new int[]{1, 2, 3}, new int[]{}),
            Arguments.of(4L, new int[]{0, -5, 10}, new int[]{-5}),
            Arguments.of(5L, new int[]{-1, 0, 1}, new int[]{-1}),
            Arguments.of(6L, new int[]{}, new int[]{})
    );
  }

  static Stream<Arguments> provideArrayForFindPositive() {
    return Stream.of(
            Arguments.of(1L, new int[]{4, -2, 3}, new int[]{4, 3}),
            Arguments.of(2L, new int[]{-1, -2, -3}, new int[]{}),
            Arguments.of(3L, new int[]{1, 2, 3}, new int[]{1, 2, 3}),
            Arguments.of(4L, new int[]{0, -5, 10}, new int[]{10}),
            Arguments.of(5L, new int[]{-1, 0, 1}, new int[]{1}),
            Arguments.of(6L, new int[]{}, new int[]{})
    );
  }

  @ParameterizedTest
  @MethodSource("provideArrayForFindMinValue")
  void findMinValue(Long id, int[] testArray, int expected) {
    NewArray newArray = NewArray.builder()
            .setId(id)
            .setArray(testArray)
            .build();

    int result = search.findMinValue(newArray);

    assertEquals(expected, result);
  }

  @ParameterizedTest
  @MethodSource("provideArrayForFindMaxValue")
  void findMaxValue(Long id, int[] testArray, int expected) {
    NewArray newArray = NewArray.builder()
            .setId(id)
            .setArray(testArray)
            .build();

    int result = search.findMaxValue(newArray);

    assertEquals(expected, result);
  }

  @ParameterizedTest
  @MethodSource("provideArrayForFindNegative")
  void findNegative(Long id, int[] testArray, int[] expected) {
    NewArray newArray = NewArray.builder()
            .setId(id)
            .setArray(testArray)
            .build();

    int[] result = search.findNegative(newArray);

    assertArrayEquals(expected, result);
  }

  @ParameterizedTest
  @MethodSource("provideArrayForFindPositive")
  void findPositive(Long id, int[] testArray, int[] expected) {
    NewArray newArray = NewArray.builder()
            .setId(id)
            .setArray(testArray)
            .build();

    int[] result = search.findPositive(newArray);

    assertArrayEquals(expected, result);
  }

  @ParameterizedTest
  @MethodSource("provideArrayForFindMinValue")
  void findMinValueStream(Long id, int[] testArray, int expected) throws ArrayException {
    NewArray newArray = NewArray.builder()
            .setId(id)
            .setArray(testArray)
            .build();

    int result = search.findMinValueStream(newArray);

    assertEquals(expected, result);
  }

  @ParameterizedTest
  @MethodSource("provideArrayForFindNegative")
  void findNegativeStream(Long id, int[] testArray, int[] expected) {
    NewArray newArray = NewArray.builder()
            .setId(id)
            .setArray(testArray)
            .build();

    int[] result = search.findNegativeStream(newArray);

    assertArrayEquals(expected, result);
  }
}