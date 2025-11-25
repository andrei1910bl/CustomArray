package com.bulavskiy.array.repository.specification;

import com.bulavskiy.array.entity.NewArray;

@FunctionalInterface
public interface Specification {
  boolean specify(NewArray array);
}
