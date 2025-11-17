package com.bulavskiy.array.service.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.exception.ArrayException;
import com.bulavskiy.array.service.ArraySearchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class ArraySearchServiceImpl implements ArraySearchService{
  public static final Logger log = LogManager.getLogger();

  @Override
  public int findMinValue(NewArray array){
    int min = array.getArray()[0];
    for(int i = 0; i < array.getLength(); i++){
      if(min > array.getArray()[i]){
        min = array.getArray()[i];
      }
    }
    log.info("Find min value {}", min);
    return min;
  }


  @Override
  public int findMaxValue(NewArray array) {
    int max = array.getArray()[0];
    for(int i = 0; i < array.getLength(); i++){
      if(max < array.getArray()[i]){
        max = array.getArray()[i];
      }
    }
    log.info("Find max value {}", max);
    return max;
  }

  @Override
  public int[] findNegative(NewArray array) {
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
    log.info("Negative elements of array {} is equal {}", array, newWorkArray);
    return newWorkArray;
  }

  @Override
  public int[] findPositive(NewArray array) {
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
    log.info("Positive elements of array {} is equal {}", array, newWorkArray);
    return newWorkArray;
  }

  @Override
  public int findMinValueStream(NewArray array) throws ArrayException {
    int min = Arrays.stream(array.getArray())
            .min()
            .orElseThrow(() -> new ArrayException("Array cannot be empty"));

    log.info("Find min value {}", min);
    return min;


  }

  @Override
  public int[] findNegativeStream(NewArray array) {
    int[] newWorkArray = Arrays.stream(array.getArray())
            .filter(element -> element<0)
            .toArray();
    log.info("Negative elements of array {} is equal {}", array, newWorkArray);

    return newWorkArray;
  }
}
