package com.intelliarts.dao;


import com.intelliarts.model.Stock;

import java.util.List;
import java.util.Optional;

public interface StockDao {
    void create(Stock snack);

    void increaseStock(String name, int snackNumber);


    void reduceStock(String name, int count);

    void deleteAllEmpty();

    List<Stock> getAll();

    Optional<Stock> findByName(String name);
}
