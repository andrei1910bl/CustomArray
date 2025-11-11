package com.bulavskiy.array.service;

import com.bulavskiy.array.entity.NewArray;

public interface ArrayReplaceService {

  NewArray replaceElement(NewArray array, int index, int value);

  NewArray replaceOldElement(NewArray array, int oldValue, int newValue);
}
