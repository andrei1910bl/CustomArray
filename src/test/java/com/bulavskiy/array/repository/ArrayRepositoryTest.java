package com.bulavskiy.array.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bulavskiy.array.entity.NewArray;
import com.bulavskiy.array.exception.ArrayException;

class ArrayRepositoryTest {

  private ArrayRepository repository;

  @BeforeEach
  @SuppressWarnings("unused")
  void setUp() {
    repository = ArrayRepository.getInstance();
    repository.sort((a, b) -> 0).forEach(array -> {
      try {
        repository.remove(array);
      } catch (ArrayException e) {
        throw new RuntimeException(e);
      }
    });
  }

  @Test
  void addShouldStoreArray() throws ArrayException {
    NewArray array = NewArray.builder()
            .setId(1L)
            .setArray(new int[]{1, 2, 3})
            .build();

    repository.add(array);

    assertEquals(1, repository.sort((a, b) -> 0).size());
  }

  @Test
  void removeShouldDeleteArray() throws ArrayException {
    NewArray array = NewArray.builder()
            .setId(2L)
            .setArray(new int[]{4, 5, 6})
            .build();

    repository.add(array);
    repository.remove(array);

    assertTrue(repository.sort((a, b) -> 0).isEmpty());
  }
}

