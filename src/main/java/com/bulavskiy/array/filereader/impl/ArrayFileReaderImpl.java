package com.bulavskiy.array.filereader.impl;

import com.bulavskiy.array.exception.ArrayException;
import com.bulavskiy.array.filereader.ArrayFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ArrayFileReaderImpl implements ArrayFileReader {
  private static final Logger log = LogManager.getLogger();

  @Override
  public List<String> readFile(String path) throws ArrayException {
    log.info("Attempting to read file: {}", path);
    try {
      URL resource = getClass().getClassLoader().getResource(path);
      Path filePath;
      
      if (resource != null) {
        filePath = Path.of(resource.toURI());
        log.info("File found in classpath: {}", path);
      } else {
        filePath = Paths.get(path);
        if (!Files.exists(filePath)) {
          throw new ArrayException("File not found: " + path);
        }
        log.info("File found in file system: {}", path);
      }

      List<String> lines = Files.readAllLines(filePath);
      log.info("File read successfully: {}", path);
      return lines;
    } catch (IOException e) {
      throw new ArrayException("IO problem while reading file " + path, e);
    } catch (URISyntaxException e) {
      throw new ArrayException("URI problem for file " + path, e);
    }
  }
}