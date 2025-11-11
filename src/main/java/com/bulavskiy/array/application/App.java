package com.bulavskiy.array.application;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.service.ArrayCalculateService;
import com.bulavskiy.array.service.ArrayReplaceService;
import com.bulavskiy.array.service.ArraySearchService;
import com.bulavskiy.array.service.impl.ArrayCalculateServiceImpl;
import com.bulavskiy.array.service.impl.ArrayReplaceServiceImpl;
import com.bulavskiy.array.service.impl.ArraySearchServiceImpl;
import com.bulavskiy.array.validation.ArrayDataValidator;
import org.slf4j.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class App {
  public static final Logger log = LoggerFactory.getLogger(App.class);
  public static final String DATA = "data/array.txt";

  public static void main( String[] args ) {
    ArraySearchService arraySearchService = new ArraySearchServiceImpl();
    ArrayReplaceService arrayReplaceService = new ArrayReplaceServiceImpl();
    ArrayCalculateService arrayCalculateService = new ArrayCalculateServiceImpl();

    String file = readAndValidateFile(DATA);

    NewArray newArray = NewArray.NewArrayBuilder.builder()
            .setId(1L)
            .setArrayFromString(file, ",")
            .build();

    log.info(newArray.toString());
    arraySearchService.findMinValue(newArray);
    arraySearchService.findMaxValue(newArray);
    arraySearchService.findNegative(newArray);
    arraySearchService.findPositive(newArray);

    NewArray newArray1 = arrayReplaceService.replaceElement(newArray, 2, 5);
    log.info(newArray1.toString());

    NewArray newArray2 = arrayReplaceService.replaceOldElement(newArray1,4, 8);
    log.info(newArray2.toString());

    arrayCalculateService.calculateSum(newArray);
    arrayCalculateService.findAverage(newArray);
  }

  public static String readAndValidateFile(String filePath) {
    String file;

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      file = br.readLine();
    } catch (IOException e) {
      log.error("Ошибка чтения файла", e);
      return null;
    }

    if (file == null){
      log.warn("Файл пустой");
      return null;
    }

    file = file.trim();

    if(!ArrayDataValidator.isValidArrayData(file)){
      log.warn("Невалидный формат данных в файле {}", file);
      return null;
    }

    log.info("Файл успешно прочитан и записан {}", file);
    return file;
  }
}
