package com.bulavskiy.array.service.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.service.ArraySortService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ShuttleSortTest {

  private static final ArraySortService shuttleSort = new ShuttleSort();

  static Stream<Arguments> provideArrayForShuttleSort() {
    return Stream.of(
            Arguments.of(1L, new int[]{4, -2, 3}, new int[]{-2, 3, 4}),
            Arguments.of(2L, new int[]{3, 2, 1}, new int[]{1, 2, 3}),
            Arguments.of(3L, new int[]{5, 2, 5, 2, 1}, new int[]{1, 2, 2, 5, 5}),
            Arguments.of(4L, new int[]{-1, -2, -3}, new int[]{-3, -2, -1}),
            Arguments.of(5L, new int[]{5}, new int[]{5}),
            Arguments.of(6L, new int[]{0, 0, 0}, new int[]{0, 0, 0}),
            Arguments.of(7L, new int[]{}, new int[]{}),
            Arguments.of(8L, new int[]{-1, -1, -2, -2}, new int[]{-2, -2, -1, -1})
    );
  }

  @ParameterizedTest
  @MethodSource("provideArrayForShuttleSort")
  void arraySort(Long id, int[] testArray, int[] expectedArray) {
    NewArray newArray = NewArray.builder()
            .setId(id)
            .setArray(testArray)
            .build();

    NewArray result = shuttleSort.arraySort(newArray);

    assertArrayEquals(expectedArray, result.getArray());
    assertEquals(id, result.getId());
  }

}