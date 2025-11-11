package com.bulavskiy.array.service.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.service.ArraySearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArraySearchServiceImpl implements ArraySearchService {
  public static final Logger log = LoggerFactory.getLogger(ArraySearchServiceImpl.class);

  @Override
  public void findMinValue(NewArray array){
    int min = array.getArray()[0];
    for(int i = 0; i < array.getLength(); i++){
      if(min > array.getArray()[i]){
        min = array.getArray()[i];
      }
    }
    log.info("Найдено минимальное значение массива {}", min);
  }

  @Override
  public void findMaxValue(NewArray array) {
    int max = array.getArray()[0];
    for(int i = 0; i < array.getLength(); i++){
      if(max < array.getArray()[i]){
        max = array.getArray()[i];
      }
    }
    log.info("Найдено максимальное значение массива {}", max);
  }

  @Override
  public void findNegative(NewArray array) {
    int count = 0;

    for (int i = 0; i < array.getLength(); i++){
      if (array.getArray()[i] < 0){
        count ++;
      }
    }

    int[] newWorkArray = new int[count];
    int j = 0;

    for (int i = 0; i < array.getLength(); i++){
      if (array.getArray()[i] < 0){
        newWorkArray[j] = array.getArray()[i];
        j++;
      }
    }
    log.info("Отрицательные числа массива {} равны {}", array, newWorkArray);
  }

  @Override
  public void findPositive(NewArray array) {
    int count = 0;

    for (int i = 0; i < array.getLength(); i++){
      if (array.getArray()[i] > 0){
        count ++;
      }
    }

    int[] newWorkArray = new int[count];
    int j = 0;

    for (int i = 0; i < array.getLength(); i++){
      if (array.getArray()[i] > 0){
        newWorkArray[j] = array.getArray()[i];
        j++;
      }
    }
    log.info("Положительные числа массива {} равны {}", array, newWorkArray);
  }
}
