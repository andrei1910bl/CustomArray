package com.bulavskiy.array.repository.specification.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.repository.specification.Specification;

public class ByIdIntervalSpecification implements Specification {
  private int minId;
  private int maxId;

  public ByIdIntervalSpecification(int minId, int maxId) {
    this.minId = minId;
    this.maxId = maxId;
  }

  @Override
  public boolean specify(NewArray array) {
    return (minId < array.getId() && array.getId() < maxId);
  }
}
