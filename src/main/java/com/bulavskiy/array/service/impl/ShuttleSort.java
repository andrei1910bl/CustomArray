package com.bulavskiy.array.service.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.service.ArraySortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShuttleSort implements ArraySortService {
  private static final Logger log = LogManager.getLogger();

  @Override
  public NewArray arraySort(NewArray array) {
    int[] workArray = array.getArray();

    for (int i = 1; i < workArray.length; i++) {
      if (workArray[i] < workArray[i - 1]) {
        swap(workArray, i, i - 1);

        for (int j = i - 1; j >= 1; j--) {
          if (workArray[j] < workArray[j - 1]) {
            swap(workArray, j, j - 1);
          } else {
            break;
          }
        }
      }
    }

    log.info("Shuttle sort array {} equal {}", array.getArray(), workArray);
    return NewArray.builder()
            .setId(array.getId())
            .setArray(workArray)
            .build();
  }

  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}