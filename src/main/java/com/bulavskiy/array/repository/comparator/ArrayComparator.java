package com.bulavskiy.array.repository.comparator;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.service.ArrayCalculateService;
import com.bulavskiy.array.service.impl.ArrayCalculateServiceImpl;

import java.util.Comparator;

public enum ArrayComparator implements Comparator<NewArray> {

  BY_ID {
    @Override
    public int compare(NewArray array1, NewArray array2) {
      return (int) (array1.getId()-array2.getId());
    }
  },
  BY_SUM{
    @Override
    public int compare(NewArray array1, NewArray array2) {
      ArrayCalculateService calculateService = new ArrayCalculateServiceImpl();
      return calculateService.calculateSum(array1) - calculateService.calculateSum(array2);
    }
  },
  BY_LENGTH{
    @Override
    public int compare(NewArray array1, NewArray array2) {
      return array1.getLength()-array2.getLength();
    }
  };

}
