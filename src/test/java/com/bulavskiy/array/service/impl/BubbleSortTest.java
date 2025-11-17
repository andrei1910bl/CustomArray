package com.bulavskiy.array.service.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.service.ArraySortService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {

  private static final ArraySortService bubbleSort = new BubbleSort();

  static Stream<Arguments> provideArrayForBubbleSort() {
    return Stream.of(
            Arguments.of(1L, new int[]{4, -2, 3}, new int[]{-2, 3, 4}),
            Arguments.of(2L, new int[]{3, 2, 1}, new int[]{1, 2, 3}),
            Arguments.of(3L, new int[]{3, 3, 3, 3}, new int[]{3, 3, 3, 3}),
            Arguments.of(4L, new int[]{}, new int[]{}),
            Arguments.of(5L, new int[]{5, 3, 8, 1, 2}, new int[]{1, 2, 3, 5, 8}),
            Arguments.of(6L, new int[]{-1, -1, -2, -2}, new int[]{-2, -2, -1, -1})
    );
  }

  @ParameterizedTest
  @MethodSource("provideArrayForBubbleSort")
  void arraySort(Long id, int[] testArray, int[] expectedArray) {
    NewArray newArray = NewArray.builder()
            .setId(id)
            .setArray(testArray)
            .build();

    NewArray result = bubbleSort.arraySort(newArray);

    assertArrayEquals(expectedArray, result.getArray());
    assertEquals(id, result.getId());
  }

}