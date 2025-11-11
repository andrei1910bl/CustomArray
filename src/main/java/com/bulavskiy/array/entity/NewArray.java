package com.bulavskiy.array.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.regex.Pattern;

public class NewArray {
  private final Long id;
  private final int[] array;

  NewArray(Long id, int[] array) {
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

  // Interface for Builder pattern
  public interface NewArrayBuilder {
    NewArrayBuilder setId(Long id);
    NewArrayBuilder setArray(int[] array);
    NewArrayBuilder setArrayFromString(String file, String delimiter);
    NewArray build();

    static NewArrayBuilder builder() {
      return new NewArrayBuilderImpl();
    }
  }

  // Implementation of Builder pattern
  public static class NewArrayBuilderImpl implements NewArrayBuilder {
    private static final Logger log = LoggerFactory.getLogger(NewArrayBuilderImpl.class);

    private Long id;
    private int[] array;

    @Override
    public NewArrayBuilder setId(Long id) {
      this.id = id;
      return this;
    }

    @Override
    public NewArrayBuilder setArray(int[] array) {
      if (array != null) {
        this.array = array.clone();
      } else {
        this.array = new int[0];
      }
      return this;
    }

    @Override
    public NewArrayBuilder setArrayFromString(String file, String delimiter) {
      if (file == null || file.trim().isEmpty()) {
        this.array = new int[0];
        log.warn("Файл равен null или пустой");
        return this;
      }
      try {
        String[] pars = file.split(Pattern.quote(delimiter));
        this.array = new int[pars.length];
        for (int i = 0; i < pars.length; i++) {
          this.array[i] = Integer.parseInt(pars[i].trim());
        }
      } catch (NumberFormatException e) {
        this.array = new int[0];
        log.error("Ошибка преобразования строк в массив чисел");
      }
      return this;
    }

    @Override
    public NewArray build() {
      return new NewArray(id, array);
    }
  }
}