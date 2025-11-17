package com.bulavskiy.array.service.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.service.ArrayReplaceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ArrayReplaceServiceImpl implements ArrayReplaceService {
  public static final Logger log = LogManager.getLogger();

  @Override
  public NewArray replaceElement(NewArray array, int index, int value) {
    int[] workArray = array.getArray();
    workArray[index] = value;
    log.info("In array {} replace element number {} with element {}", array.toString(), index,  value);
    return NewArray.builder()
            .setId(array.getId())
            .setArray(workArray)
            .build();
  }

  @Override
  public NewArray replaceOldElement(NewArray array, int oldValue, int newValue) {
    int[] workArray = array.getArray();
    for(int i = 0; i < workArray.length; i++){
      if(workArray[i] == oldValue){
        workArray[i] = newValue;
      }
    }
    log.info("In array {} replace all elements equal {} with {}", array.toString(), oldValue, newValue);
    return NewArray.builder()
            .setId(array.getId())
            .setArray(workArray)
            .build();
  }

  @Override
  public NewArray replaceOldElementStream(NewArray array, int oldValue, int newValue) {
    int[] workArray = Arrays.stream(array.getArray())
            .map(element -> element == oldValue ? newValue : element)
            .toArray();

    log.info("In array {} replace all elements equal {} with {}", array.toString(), oldValue, newValue);
    return NewArray.builder()
            .setId(array.getId())
            .setArray(workArray)
            .build();
  }
}
