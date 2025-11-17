package com.bulavskiy.array.exception;

public class ArrayException extends Exception {

  public ArrayException() {
    super();
  }

  public ArrayException(String message) {
    super(message);
  }

  public ArrayException(Throwable reason) {
    super(reason);
  }

  public ArrayException(String message, Throwable reason) {
    super(message, reason);
  }
}
