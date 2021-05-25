package com.intelliarts.dao;

import com.intelliarts.model.Snack;
import com.intelliarts.model.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StockDaoInMemoryImplTest {
    private  static StockDaoInMemoryImpl stockDaoInMemory;

    @BeforeAll
    public static void before(){
        stockDaoInMemory = new StockDaoInMemoryImpl();
    }
    @Test
    void create() {
        Stock bar = new Stock(new Snack("Bar", 35.75), 5);
        stockDaoInMemory.create(bar);
        Assertions.assertEquals(stockDaoInMemory.findByName("Bar").get(),bar);
    }

    @Test
    void increaseStock() {
    }

    @Test
    void reduceStock() {
    }

    @Test
    void deleteAllEmpty() {
    }

    @Test
    void getAll() {
    }

    @Test
    void findByName() {
    }
}