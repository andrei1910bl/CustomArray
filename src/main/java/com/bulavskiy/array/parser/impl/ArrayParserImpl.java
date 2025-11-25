package com.bulavskiy.array.parser.impl;

import com.bulavskiy.array.parser.ArrayParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ArrayParserImpl implements ArrayParser {
  private static final Logger log = LogManager.getLogger();
  private static final String DELIMITER_REGEX = "[^\\d-]+";
  private static final String NUMBER_REGEX = "-?\\d+";

  @Override
  public int[] parse(String line) {
    if (line == null || line.strip().isEmpty()) {
      log.warn("Line is null or empty");
      return new int[0];
    }

    try {
      String[] parts = line.split(DELIMITER_REGEX);

      List<Integer> numbers = new ArrayList<>();
      for (String part : parts) {
        if (!part.isEmpty() && part.matches(NUMBER_REGEX)) {
          numbers.add(Integer.parseInt(part));
        }
      }

      int[] parsed = new int[numbers.size()];
      for (int i = 0; i < numbers.size(); i++) {
        parsed[i] = numbers.get(i);
      }

      log.info("Parsed {} numbers from '{}'", parsed.length, line);
      return parsed;
    } catch (NumberFormatException e) {
      log.error("Error parsing String to Integer {}", e.getMessage());
      return new int[0];
    }
  }
}

