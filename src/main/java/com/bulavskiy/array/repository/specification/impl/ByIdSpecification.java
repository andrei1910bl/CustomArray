package com.bulavskiy.array.repository.specification.impl;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.repository.specification.Specification;

public class ByIdSpecification implements Specification {
  private final Long id;

  public ByIdSpecification(Long id) {
    this.id = id;
  }

  @Override
  public boolean specify(NewArray array) {
    return array.getId().equals(id);
  }
}
