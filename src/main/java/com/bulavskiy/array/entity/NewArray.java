package com.bulavskiy.array.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bulavskiy.array.observer.ArrayObservable;
import com.bulavskiy.array.observer.ArrayObserver;
import com.bulavskiy.array.parser.ArrayParser;
import com.bulavskiy.array.parser.impl.ArrayParserImpl;

public class NewArray implements ArrayObservable {
  private Long id;
  private int[] array;
  List<ArrayObserver> observers = new ArrayList<>();

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

  public void setArray(int[] array) {
      this.array = Arrays.copyOf(array, array.length);
    notifyObserver();
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

  @Override
  public void addObserver(ArrayObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ArrayObserver observer) {
    observers.remove(observer);
  }

  @Override
  public void notifyObserver() {
    for(ArrayObserver observer : observers){
      observer.update(this);
    }

  }

  public static class NewArrayBuilder {
    private static final ArrayParser parser = new ArrayParserImpl();

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
      this.array = parser.parse(file);
      return this;
    }

    public NewArray build() {
      return new NewArray(id, array);
    }
  }
}