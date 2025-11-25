package com.bulavskiy.array.application;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bulavskiy.array.entity.ArrayStatistics;
import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.exception.ArrayException;
import com.bulavskiy.array.filereader.ArrayFileReader;
import com.bulavskiy.array.filereader.impl.ArrayFileReaderImpl;
import com.bulavskiy.array.repository.ArrayRepository;
import com.bulavskiy.array.repository.comparator.ArrayComparator;
import com.bulavskiy.array.repository.specification.impl.BySumSpecification;
import com.bulavskiy.array.service.ArrayCalculateService;
import com.bulavskiy.array.service.ArrayReplaceService;
import com.bulavskiy.array.service.ArraySearchService;
import com.bulavskiy.array.service.ArraySortService;
import com.bulavskiy.array.service.impl.ArrayCalculateServiceImpl;
import com.bulavskiy.array.service.impl.ArrayReplaceServiceImpl;
import com.bulavskiy.array.service.impl.ArraySearchServiceImpl;
import com.bulavskiy.array.service.impl.BubbleSort;
import com.bulavskiy.array.service.impl.InsertionSort;
import com.bulavskiy.array.service.impl.ShuttleSort;
import com.bulavskiy.array.warehouse.Warehouse;


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

    ArrayFileReader fileReader = new ArrayFileReaderImpl();
    List<String> fileArrays = fileReader.readFile(DATA);
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
      arraySearchService.findMinValue(newArray);
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

    demonstrateRepositoryBehaviour();
  }

  private static void demonstrateRepositoryBehaviour() {
    Warehouse warehouse = Warehouse.getInstance();
    ArrayRepository repository = ArrayRepository.getInstance();

    try {
      NewArray first = NewArray.builder()
              .setId(52L)
              .setArray(new int[]{1, 2, 3})
              .build();

      NewArray second = NewArray.builder()
              .setId(53L)
              .setArray(new int[]{4, 5, 6, 7})
              .build();

      NewArray third = NewArray.builder()
              .setId(54L)
              .setArray(new int[]{10, -5, 0})
              .build();

      repository.add(first);
      repository.add(second);
      repository.add(third);

      var sumSpecification = new BySumSpecification(6);
      log.info("Arrays with sum == 6 -> {}", repository.query(sumSpecification));

      log.info("Arrays sorted by length -> {}", repository.sort(ArrayComparator.BY_LENGTH));

      first.setArray(new int[]{10, 20, 30});
      ArrayStatistics stats = warehouse.getStatistics(first.getId());
      log.info("Updated Warehouse statistics for {} -> {}", first.getId(), stats);

      repository.remove(third);
      log.info("Repository size after removal -> {}", repository.sort(ArrayComparator.BY_ID).size());
    } catch (ArrayException e) {
      log.error("Repository validation failed", e);
    }
  }
}
