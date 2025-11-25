package com.bulavskiy.array.filereader;

import com.bulavskiy.array.exception.ArrayException;

import java.util.List;

public interface ArrayFileReader {
  List<String> readFile(String path) throws ArrayException;
}
