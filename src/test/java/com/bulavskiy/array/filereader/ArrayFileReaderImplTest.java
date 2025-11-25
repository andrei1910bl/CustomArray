package com.bulavskiy.array.filereader;

import com.bulavskiy.array.exception.ArrayException;
import com.bulavskiy.array.filereader.impl.ArrayFileReaderImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArrayFileReaderImplTest {

  private final ArrayFileReader reader = new ArrayFileReaderImpl();

  @Test
  void readFileShouldLoadLinesFromClasspathOrFs() throws ArrayException {
    List<String> lines = reader.readFile("data/array.txt");
    assertEquals(3, lines.size());
  }

  @Test
  void readFileShouldThrowForMissingFile() {
    assertThrows(ArrayException.class, () -> reader.readFile("missing.txt"));
  }
}

