package com.bulavskiy.array.service.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.service.ArraySortService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BubbleSort implements ArraySortService {
  private static final Logger log = LogManager.getLogger();
  @Override
  public NewArray arraySort(NewArray array) {
    int[] workArray = array.getArray();
    boolean isSorted = false;

    while (!isSorted){
      isSorted = true;
      for(int i = 1; i < workArray.length; i++){
        if(workArray[i] < workArray[i - 1]) {
          int temp = workArray[i];
          workArray[i] = workArray[i - 1];
          workArray[i - 1] = temp;
          isSorted = false;
        }
      }
    }
    log.info("Bubble sort array {} equal {}", array.getArray(), workArray);
    return NewArray.builder()
            .setId(array.getId())
            .setArray(workArray)
            .build();
  }
}
