package com.bulavskiy.array.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.exception.ArrayException;
import com.bulavskiy.array.observer.ArrayObserver;
import com.bulavskiy.array.observer.impl.ArrayObserverImpl;
import com.bulavskiy.array.repository.specification.Specification;
import com.bulavskiy.array.warehouse.Warehouse;

public class ArrayRepository {
  private static final Logger log = LogManager.getLogger();
  private static ArrayRepository instance;

  private final List<NewArray> arrays = new ArrayList<>();
  private final ArrayObserver observer = new ArrayObserverImpl();

  private ArrayRepository() {}

  public static ArrayRepository getInstance(){
    if(instance == null){
      instance = new ArrayRepository();
    }
    return instance;
  }

  public void add(NewArray array) throws ArrayException {
    if(array == null){
      throw new ArrayException("Array is null");
    }

    if(!arrays.contains(array)){
      arrays.add(array);
    } else {
      log.info("Array {} already present in repository. Updating state.", array.getId());
    }

    array.removeObserver(observer);
    array.addObserver(observer);
    observer.update(array);
    log.info("Array {} added to repository", array.getId());
  }

  public void remove(NewArray array) throws ArrayException{
    if(array == null){
      throw new ArrayException("Array is null");
    }
    arrays.remove(array);
    array.removeObserver(observer);
    Warehouse.getInstance().remove(array.getId());
    log.info("Array {} removed from repository", array.getId());
  }

  public List<NewArray> query(Specification specification) {
    List<NewArray> result = new ArrayList<>();
    for (NewArray array : arrays) {
      if (specification.specify(array)) {
        result.add(array);
      }
    }
    return result;
  }

  public List<NewArray> sort(Comparator<? super NewArray> comparator) {
    var sorted = new ArrayList<>(arrays);
    sorted.sort(comparator);
    return sorted;
  }
}
