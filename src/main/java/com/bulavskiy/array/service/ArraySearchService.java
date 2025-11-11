package com.bulavskiy.array.service;

import com.bulavskiy.array.entity.NewArray;

public interface ArraySearchService {
  void findMinValue(NewArray array);

  void findMaxValue(NewArray array);

  void findNegative(NewArray array);

  void findPositive(NewArray array);
}
