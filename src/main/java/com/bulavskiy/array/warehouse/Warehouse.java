package com.bulavskiy.array.warehouse;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bulavskiy.array.entity.ArrayStatistics;

public class Warehouse {
  private static final Logger log = LogManager.getLogger();
  private static Warehouse instance;
  private final Map<Long, ArrayStatistics> statistics = new HashMap<>();

  private Warehouse(){}

  public static Warehouse getInstance(){
    if(instance == null){
      instance = new Warehouse();
    }
    return instance;
  }

  public ArrayStatistics put(Long id, ArrayStatistics stats) {
    log.info("Array statistics with id={} added to warehouse", id);
    return statistics.put(id, stats);
  }

  public ArrayStatistics remove(Long id){
    log.info("Array statistics with id={} remove from warehouse", id);
    return statistics.remove(id);
  }

  public ArrayStatistics getStatistics(Long id) {
    return statistics.get(id);
  }
}
