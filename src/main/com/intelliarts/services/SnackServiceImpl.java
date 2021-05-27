package com.intelliarts.services;

import com.intelliarts.dao.OrderDao;
import com.intelliarts.dao.OrderDaoInMemoryImpl;
import com.intelliarts.dao.StockDao;
import com.intelliarts.dao.StockDaoInMemoryImpl;
import com.intelliarts.model.Order;
import com.intelliarts.model.Snack;
import com.intelliarts.model.Stock;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


public class SnackServiceImpl implements SnackService {
    private final StockDao stockDao = new StockDaoInMemoryImpl();
    private final OrderDao orderDao = new OrderDaoInMemoryImpl();


    private static final DateTimeFormatter DATE_TIME_FOR_DAY = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void addCategory(String name, double price, int quantity) {
        validateName(name);
        validatePrice(price);
        if (quantity < 0) {
            quantity = 0;
        }
        stockDao.create(new Stock(new Snack(name, price), quantity));
    }

    private void validatePrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("price can not be 0 or negative");
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can not be empty");
        }
    }

    @Override
    public void purchase(String name, LocalDate localDate) {
        Optional<Stock> stockOptional = stockDao.findByName(name);
        if (stockOptional.isPresent()) {
            if (stockOptional.get().getCount() < 1) {
                System.out.println("this snack already be over");
                stockDao.getAll();
            }
            stockDao.reduceStock(name, 1);
            System.out.println(localDate);
            System.out.println(stockOptional.get().getSnack().getName() + " " + stockOptional.get().getSnack().getPrice());
            orderDao.createOrder(new Order(stockOptional.get().getSnack(), localDate));
        }
        else System.out.println("this snack is not exist");
    }

    @Override
    public void reportForMonth(String s) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM").parseDefaulting(ChronoField.DAY_OF_MONTH, 1).toFormatter();
        LocalDate startDate = LocalDate.parse(s, formatter);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        countTotalPrice(orderDao.getOrdersByDates(startDate, endDate));
    }

    @Override
    public void reportForDay(String s) {
        LocalDate parseDate = LocalDate.parse(s, DATE_TIME_FOR_DAY);
        countTotalPrice(orderDao.getOrdersByDate(parseDate));
    }

    @Override
    public void updateSnack(String snackName, int snackNumber) {
        stockDao.increaseStock(snackName, snackNumber);
    }

    @Override
    public void deleteAllEmpty() {
        stockDao.deleteAllEmpty();
    }

    @Override
    public void getAll() {
        stockDao.getAll();
    }

    double total = 0;

    public void countTotalPrice(List<Order> orderList) {
        Map<String, List<Order>> byDate = orderList.stream()
                .collect(Collectors.groupingBy(order -> order.getSnack().getName()));
        byDate.forEach((name, orders) -> {
            double price = orders.iterator().next().getSnack().getPrice();
            int count = orders.size();
            total += count * price;
            price = price * count;
            System.out.println(String.format("%s %,.2f %d", name, price, count));

        });
        System.out.println(">Total " + total);
    }
}