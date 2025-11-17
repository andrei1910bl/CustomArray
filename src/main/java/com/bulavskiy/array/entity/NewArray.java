package com.bulavskiy.array.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewArray {
  private final Long id;
  private final int[] array;

  private NewArray(Long id, int[] array) {
    this.id = id;

    if (array != null) {
      this.array = Arrays.copyOf(array, array.length);
    } else {
      this.array = new int[0];
    }
  }

  public Long getId() {
    return id;
  }

  public int[] getArray() {
    return Arrays.copyOf(array, array.length);
  }

  public int getLength() {
    return array.length;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("NewArray{");
    sb.append("id=").append(id);
    sb.append(", array=").append(Arrays.toString(array));
    sb.append('}');
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    NewArray newArray = (NewArray) o;
    return id.equals(newArray.id) && Arrays.equals(array, newArray.array);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + Arrays.hashCode(array);
    return result;
  }

    public static NewArrayBuilder builder() {
      return new NewArrayBuilder();
    }

  public static class NewArrayBuilder {
    public static final Logger log = LogManager.getLogger();
    private static final String DELIMETER_REGEX = "[^\\d-]+";
    private static final String NUMBER_REGEX = "-?\\d+";

    private Long id;
    private int[] array;

    private NewArrayBuilder(){}

    public NewArrayBuilder setId(Long id) {
      this.id = id;
      return this;
    }

    public NewArrayBuilder setArray(int[] array) {
      if (array != null) {
        this.array = array.clone();
      } else {
        this.array = new int[0];
      }
      return this;
    }

    public NewArrayBuilder setArrayFromString(String file) {
      if (file == null || file.trim().isEmpty()) { // метод .strip выдает у меня ошибку file.strip().isEmpty()
        this.array = new int[0];
        log.warn("Line is null or empty");
        return this;
      }
      try {
        String[] parts = file.split(DELIMETER_REGEX);

        List<Integer> numbers = new ArrayList<>();
        for (String part : parts){
          if(!part.isEmpty() && part.matches(NUMBER_REGEX)){
            numbers.add(Integer.parseInt(part));
          }
        }

        this.array = new int[numbers.size()];
        for (int i =0; i < numbers.size(); i++){
          this.array[i] = numbers.get(i);
        }

        log.info("Parsed {} numbers from '{}' ", this.array, file);

      } catch (NumberFormatException e) {
        this.array = new int[0];
        log.error("Error parsing String to Integer {}", e.getMessage());
      }
      return this;
    }

    public NewArray build() {
      return new NewArray(id, array);
    }
  }
}