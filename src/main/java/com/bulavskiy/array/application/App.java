package com.bulavskiy.array.application;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.service.ArrayCalculateService;
import com.bulavskiy.array.service.ArrayReplaceService;
import com.bulavskiy.array.service.ArraySearchService;
import com.bulavskiy.array.service.impl.ArrayCalculateServiceImpl;
import com.bulavskiy.array.service.impl.ArrayReplaceServiceImpl;
import com.bulavskiy.array.service.impl.ArraySearchServiceImpl;
import com.bulavskiy.array.validation.ArrayDataValidator;
import com.bulavskiy.array.validation.ArrayDataValidatorImpl;
import org.slf4j.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class App {
  public static final Logger log = LoggerFactory.getLogger(App.class);
  public static final String DATA = "data/array.txt";

  public static void main( String[] args ) {
    ArraySearchService arraySearchService = new ArraySearchServiceImpl();
    ArrayReplaceService arrayReplaceService = new ArrayReplaceServiceImpl();
    ArrayCalculateService arrayCalculateService = new ArrayCalculateServiceImpl();

    List<String> fileArrays = readAndValidateFile(DATA);
    for (int i = 0; i < fileArrays.size(); i++) {
      String arrayData = fileArrays.get(i);

      log.info(arrayData);

      NewArray newArray = NewArray.builder()
              .setId((long) i +1 )
              .setArrayFromString(arrayData)
              .build();

      log.info(newArray.toString());
      arraySearchService.findMinValue(newArray);
      arraySearchService.findMaxValue(newArray);
      arraySearchService.findNegative(newArray);
      arraySearchService.findPositive(newArray);

      NewArray newArray1 = arrayReplaceService.replaceElement(newArray, 2, 5);
      log.info(newArray1.toString());

      NewArray newArray2 = arrayReplaceService.replaceOldElement(newArray1, 4, 8);
      log.info(newArray2.toString());

      arrayCalculateService.calculateSum(newArray);
      arrayCalculateService.findAverage(newArray);
    }
  }

  public static List<String> readAndValidateFile(String filePath) {
    List<String>  allLines = new ArrayList<>();

    String line;
    try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
      ArrayDataValidator validator = new ArrayDataValidatorImpl();
      while ((line = br.readLine()) != null){
        if(validator.isValidArrayData(line)){
          allLines.add(line);
        }else {
          log.warn("Line {} invalid", line);
        }
      }
    } catch (IOException e) {
      //TODO: добавить trow new...
      return null;
    }

    log.info("Файл успешно прочитан и записан {}", filePath);
    return allLines;
  }
}
