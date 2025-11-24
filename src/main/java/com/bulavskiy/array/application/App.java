package com.bulavskiy.array.application;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.exception.ArrayException;
import com.bulavskiy.array.filereader.ArrayFileReader;
import com.bulavskiy.array.service.ArrayCalculateService;
import com.bulavskiy.array.service.ArrayReplaceService;
import com.bulavskiy.array.service.ArraySearchService;
import com.bulavskiy.array.service.ArraySortService;
import com.bulavskiy.array.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class App {
  public static final Logger log = LogManager.getLogger();
  public static final String DATA = "data/array.txt";

  public static void main( String[] args ) throws ArrayException {
    ArraySearchService arraySearchService = new ArraySearchServiceImpl();
    ArrayReplaceService arrayReplaceService = new ArrayReplaceServiceImpl();
    ArrayCalculateService arrayCalculateService = new ArrayCalculateServiceImpl();

    ArraySortService bubbleSort = new BubbleSort();
    ArraySortService shuttleSort = new ShuttleSort();
    ArraySortService insertionSort = new InsertionSort();

    List<String> fileArrays = ArrayFileReader.readFile(DATA);
    for (int i = 0; i < fileArrays.size(); i++) {
      String arrayData = fileArrays.get(i);

      log.info(arrayData);

      NewArray newArray = NewArray.builder()
              .setId((long) i +1 )
              .setArrayFromString(arrayData)
              .build();

      bubbleSort.arraySort(newArray);
      shuttleSort.arraySort(newArray);
      insertionSort.arraySort(newArray);

      log.info(newArray.toString());
      int a = arraySearchService.findMinValue(newArray);
      arraySearchService.findMaxValue(newArray);
      arraySearchService.findNegative(newArray);
      arraySearchService.findPositive(newArray);

      newArray = arrayReplaceService.replaceElement(newArray, 2, 5);
      log.info(newArray.toString());

       newArray = arrayReplaceService.replaceOldElement(newArray, 4, 8);
      log.info(newArray.toString());

      arrayCalculateService.calculateSum(newArray);
      arrayCalculateService.findAverage(newArray);
    }
  }

}
