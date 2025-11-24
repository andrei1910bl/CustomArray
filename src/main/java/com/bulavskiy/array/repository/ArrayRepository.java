package com.bulavskiy.array.repository;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.exception.ArrayException;
import com.bulavskiy.array.repository.specification.Specification;
import com.bulavskiy.array.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ArrayRepository {
  public static final Logger log = LogManager.getLogger();
  public static ArrayRepository instance;

  private final List<NewArray> arrays = new ArrayList<>();

  private ArrayRepository() {}

  public static ArrayRepository getInstance(){
    if(instance == null){
      log.info("Create new instance");
      instance = new ArrayRepository();
    }
    log.info("Return instance");
    return instance;
  }

  public void add(NewArray array) throws ArrayException {
    if(array == null){
      throw new ArrayException("Array is null");
    }
    array.notifyObserver();
    arrays.add(array);
  }

  public void remove(NewArray array) throws ArrayException{
    if(array == null){
      throw new ArrayException("Array is null");
    }
    Warehouse.getInstance().remove(array.getId());
    arrays.remove(array);
  }

  public List<NewArray> query(Specification specification) {
    List<NewArray> results = new ArrayList<>();
    for (NewArray array : arrays) {
      if (specification.specify(array)) {
        arrays.add(array);
      }
    }
    return results;
  }
}
