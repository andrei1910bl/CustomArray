package com.bulavskiy.array.repository.specification;

import com.bulavskiy.array.entity.NewArray;

public interface Specification {
  boolean specify(NewArray array);
}
