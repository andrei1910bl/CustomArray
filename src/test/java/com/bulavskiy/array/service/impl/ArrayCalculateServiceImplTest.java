package com.bulavskiy.array.service.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.service.ArrayCalculateService;
import com.bulavskiy.array.service.ArraySortService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ArrayCalculateServiceImplTest {

  private static final ArrayCalculateService calc = new ArrayCalculateServiceImpl();

  static Stream<Arguments> provideArrayForSum(){
    return Stream.of(
            Arguments.of(1L, new int[]{1, 2, 3}, 6),
            Arguments.of(2L, new int[]{5, 5, 5}, 15),
            Arguments.of(3L, new int[]{-1, -2, -3}, -6)
    );
  }

  static Stream<Arguments> provideArrayForAverage(){
    return Stream.of(
            Arguments.of(1L, new int[]{1, 2, 3}, 2),
            Arguments.of(2L, new int[]{5, 5, 5}, 5),
            Arguments.of(3L, new int[]{-1, -2, -3}, -2)
    );
  }

  @ParameterizedTest
  @MethodSource("provideArrayForSum")
  void calculateSum(Long id, int[] testArray, int expected) {
    NewArray newArray = NewArray.builder()
            .setId(id)
            .setArray(testArray)
            .build();

    int result = calc.calculateSum(newArray);

    assertEquals(expected, result);
  }

  @ParameterizedTest
  @MethodSource("provideArrayForAverage")
  void findAverage(Long id, int[] testArray, int expected) {
    NewArray newArray = NewArray.builder()
            .setId(id)
            .setArray(testArray)
            .build();

    double result = calc.findAverage(newArray);

    assertEquals(expected, result);
  }

  @ParameterizedTest
  @MethodSource("provideArrayForSum")
  void calculateSumStream(Long id, int[] testArray, int expected) {
    NewArray newArray = NewArray.builder()
            .setId(id)
            .setArray(testArray)
            .build();

    int result = calc.calculateSumStream(newArray);

    assertEquals(expected, result);
  }

  @ParameterizedTest
  @MethodSource("provideArrayForAverage")
  void findAverageStream(Long id, int[] testArray, int expected) {
    NewArray newArray = NewArray.builder()
            .setId(id)
            .setArray(testArray)
            .build();

    double result = calc.findAverageStream(newArray);

    assertEquals(expected, result);
  }
  //TODO: добавить для исключительных ситуаций
}