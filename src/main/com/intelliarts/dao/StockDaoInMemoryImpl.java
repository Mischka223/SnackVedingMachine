package com.intelliarts.dao;


import com.intelliarts.exeptions.NotFoundException;
import com.intelliarts.model.Stock;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class StockDaoInMemoryImpl implements StockDao {
    private final List<Stock> stockList = new LinkedList<>();

    @Override
    public void create(Stock stock) {
        if (findByName(stock.getSnack().getName()).isEmpty()) {
            stockList.add(stock);
            System.out.println(String.format("%s %,.2f %d", stock.getSnack().getName(), stock.getSnack().getPrice(), stock.getCount()));
        } else
            System.out.println("this snack already exists");
    }

    @Override
    public void increaseStock(String name, int count) {
        try {
            Stock stock1 =stockList.stream().filter(s -> s.getSnack().getName().equals(name)).findAny().orElseThrow(NotFoundException::new);
            stock1.setCount(stock1.getCount() + count);
            System.out.println(String.format("%s %,.2f %d", stock1.getSnack().getName(), stock1.getSnack().getPrice(), stock1.getCount()));
        }catch (NotFoundException e){
            System.out.println("Exception:  " + e.toString());
        }

    }

    @Override
    public void reduceStock(String name, int count) {
        try {
            Stock stock1 = stockList.stream().filter(s -> s.getSnack().getName().equals(name)).findAny().orElseThrow(NotFoundException::new);
            stock1.setCount(stock1.getCount() - count);
        }catch (NotFoundException e){
            System.out.println("Exception e" + e.toString());
        }

    }

    @Override
    public void deleteAllEmpty() {
        stockList.stream().filter(stock -> stock.getCount() <= 0).forEach(stock -> System.out.println(String.format("%s %,.2f", stock.getSnack().getName(), stock.getSnack().getPrice())));
        stockList.removeIf(stock -> stock.getCount() <= 0);
    }

    @Override
    public List<Stock> getAll() {
        for (Stock stock : stockList) {
            System.out.println(String.format("%s %,.2f %d", stock.getSnack().getName(), stock.getSnack().getPrice(), stock.getCount()));
        }
        return stockList;
    }

    @Override
    public Optional<Stock> findByName(String name) {
        return stockList.stream()
                .filter(stock -> stock.getSnack().getName().equals(name))
                .map(Stock::clone)
                .findFirst();
    }


    @Override
    public String toString() {
        return "StockDaoInMemoryImpl{" +
                "snackList=" + stockList +
                '}';
    }
}
