package com.bulavskiy.array.service.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.service.ArrayCalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayCalculateServiceImpl implements ArrayCalculateService {
  public static final Logger log = LoggerFactory.getLogger(ArrayCalculateServiceImpl.class);

  @Override
  public void calculateSum(NewArray array) {
    int sum = 0;
    for(int i = 0; i < array.getLength(); i++){
      sum += array.getArray()[i];
    }
    log.info("Сумма всех элементов массива {}, равна {}", array, sum);
  }

  @Override
  public void findAverage(NewArray array) {
    int sum = 0;
    double average = 0.0;
    for(int i = 0; i < array.getLength(); i++){
      sum += array.getArray()[i];
    }
    average = (double) sum / array.getLength();
    log.info("Среднее значение элементов массива {} равно {}",array, average);
  }
}
