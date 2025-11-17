package com.bulavskiy.array.service;

import com.bulavskiy.array.entity.NewArray;

public interface ArrayCalculateService {

  int calculateSum(NewArray array);
  int calculateSumStream(NewArray array);
  double findAverage(NewArray array);
  double findAverageStream(NewArray array);

}
