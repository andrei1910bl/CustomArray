package com.bulavskiy.array.service.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.service.ArrayCalculateService;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;

public class ArrayCalculateServiceImpl implements ArrayCalculateService {
  public static final org.apache.logging.log4j.Logger log = LogManager.getLogger();

  @Override
  public int calculateSum(NewArray array) {
    int sum = 0;
    for(int i = 0; i < array.getLength(); i++){
      sum += array.getArray()[i];
    }
    log.info("Sum of all elements of array {}, is equal {}", array, sum);
    return sum;
  }

  @Override
  public int calculateSumStream(NewArray array) {
    int sum = Arrays.stream(array.getArray())
            .sum();

    log.info("Sum of all elements of array {}, is equal {}", array, sum);
    return sum;

  }

  @Override
  public double findAverage(NewArray array) {
    int sum = 0;
    double average;
    for(int i = 0; i < array.getLength(); i++){
      sum += array.getArray()[i];
    }
    average = (double) sum / array.getLength();
    log.info("The average of array elements {} is equal {}",array, average);
    return average;
  }

  @Override
  public double findAverageStream(NewArray array) {
    double average = Arrays.stream(array.getArray())
            .average()
            .orElse(0.0);
    log.info("The average of array elements {} is equal {}",array, average);
    return average;
  }
}
