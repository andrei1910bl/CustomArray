package com.bulavskiy.array.filereader;

import com.bulavskiy.array.exception.ArrayException;
import com.bulavskiy.array.validation.ArrayDataValidator;
import com.bulavskiy.array.validation.impl.ArrayDataValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ArrayFileReader {
  public static final Logger log = LogManager.getLogger();

  public static List<String> readFile(String filePath) throws ArrayException {
    List<String> allLines = new ArrayList<>();

    String line;
    try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
      ArrayDataValidator validator = new ArrayDataValidatorImpl();
      while ((line = br.readLine()) != null) {
        if (validator.isValidArrayData(line)) {
          allLines.add(line);
        } else {
          log.warn("Line {} invalid", line);
        }
      }
    } catch (IOException e) {
      throw new ArrayException("Error reading file: " + filePath, e);
    }

    log.info("File read and written {}", filePath);
    return allLines;
  }
}