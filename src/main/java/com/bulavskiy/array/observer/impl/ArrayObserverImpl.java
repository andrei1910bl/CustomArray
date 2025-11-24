package com.bulavskiy.array.observer.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.observer.ArrayObserver;
import com.bulavskiy.array.service.ArrayCalculateService;
import com.bulavskiy.array.service.ArraySearchService;
import com.bulavskiy.array.service.impl.ArrayCalculateServiceImpl;
import com.bulavskiy.array.service.impl.ArraySearchServiceImpl;
import com.bulavskiy.array.entity.ArrayStatistics;
import com.bulavskiy.array.warehouse.Warehouse;

public class ArrayObserverImpl implements ArrayObserver {
  private static final Logger log = LogManager.getLogger();

  private final Warehouse warehouse = Warehouse.getInstance();
  private final ArrayCalculateService calculateService = new ArrayCalculateServiceImpl();
  private final ArraySearchService searchService = new ArraySearchServiceImpl();

  @Override
  public void update(NewArray array) {
    int sum = calculateService.calculateSum(array);
    double average = calculateService.findAverage(array);
    int min = searchService.findMinValue(array);
    int max = searchService.findMaxValue(array);

    warehouse.put(array.getId(), new ArrayStatistics(sum, average, min, max));
    log.info("Statistics updated for array {}", array.toString());
  }
}
