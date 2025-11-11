package com.bulavskiy.array.service.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.service.ArrayReplaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayReplaceServiceImpl implements ArrayReplaceService {
  public static final Logger log = LoggerFactory.getLogger(ArrayReplaceServiceImpl.class);

  @Override
  public NewArray replaceElement(NewArray array, int index, int value) {
    int[] workArray = array.getArray();
    workArray[index] = value;
    log.info("В массиве {} заменили элемент под номером {} на элемент {}", array.toString(), index,  value);
    return NewArray.NewArrayBuilder.builder()
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
    log.info("В массиве {} заменили все элементы равняющиеся {} на элементы {}", array.toString(), oldValue, newValue);
    return NewArray.NewArrayBuilder.builder()
            .setId(array.getId())
            .setArray(workArray)
            .build();
  }
}
