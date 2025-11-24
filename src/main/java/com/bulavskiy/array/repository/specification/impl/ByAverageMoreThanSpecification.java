package com.bulavskiy.array.repository.specification.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.repository.specification.Specification;
import com.bulavskiy.array.service.ArrayCalculateService;
import com.bulavskiy.array.service.impl.ArrayCalculateServiceImpl;

public class ByAverageMoreThanSpecification implements Specification {
  private final double average;

  public ByAverageMoreThanSpecification(double average) {
    this.average = average;
  }

  @Override
  public boolean specify(NewArray array) {
    ArrayCalculateService calculateService = new ArrayCalculateServiceImpl();
    return calculateService.findAverage(array) > average;
  }
}
