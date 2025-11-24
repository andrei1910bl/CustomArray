package com.bulavskiy.array.observer;

public interface ArrayObservable {
  void addObserver(ArrayObserver observer);
  void removeObserver(ArrayObserver observer);
  void notifyObserver();
}
