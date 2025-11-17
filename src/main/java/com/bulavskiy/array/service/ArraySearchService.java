package com.bulavskiy.array.service;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.exception.ArrayException;

public interface ArraySearchService {
  int findMinValue(NewArray array);

  int findMaxValue(NewArray array);

  int[] findNegative(NewArray array);

  int[] findPositive(NewArray array);

  int findMinValueStream(NewArray array) throws ArrayException;

  int[] findNegativeStream(NewArray array);
}
