package com.bulavskiy.array.service.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.service.ArraySortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InsertionSort implements ArraySortService {
  private static final Logger log = LogManager.getLogger();

  @Override
  public NewArray arraySort(NewArray array) {
    int[] workArray = array.getArray();

    for (int i = 1; i < workArray.length; i++) {
      int current = workArray[i];
      int j = i - 1;

      while (j >= 0 && workArray[j] > current) {
        workArray[j + 1] = workArray[j];
        j--;
      }

      workArray[j + 1] = current;
    }

    log.info("Insertion sort array {} equal {}", array.getArray(), workArray);
    return NewArray.builder()
            .setId(array.getId())
            .setArray(workArray)
            .build();
  }
}