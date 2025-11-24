package com.bulavskiy.array.repository.specification.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.repository.specification.Specification;
import com.bulavskiy.array.service.ArrayCalculateService;
import com.bulavskiy.array.service.impl.ArrayCalculateServiceImpl;

public class BySumSpecification implements Specification {
  final int sum;

  public BySumSpecification(int sum) {
    this.sum = sum;
  }

  @Override
  public boolean specify(NewArray array) {
    ArrayCalculateService calculateService = new ArrayCalculateServiceImpl();
    return calculateService.calculateSum(array) == sum;
  }
}
