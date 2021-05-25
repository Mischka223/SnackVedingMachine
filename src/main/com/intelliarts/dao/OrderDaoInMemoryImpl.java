package com.intelliarts.dao;



import com.intelliarts.model.Order;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDaoInMemoryImpl implements OrderDao {
    private final List<Order> orderList = new LinkedList<>();

    @Override
    public void createOrder(Order order) {
        orderList.add(order);
    }
    @Override
    public List<Order> getOrdersByDate(LocalDate localDate){
        return orderList.stream().filter(snack -> snack.getLocalDate().equals(localDate)).collect(Collectors.toList());
    }
    @Override
    public List<Order> getOrdersByDates(LocalDate startDate,LocalDate endDate){
        return orderList.stream()
                .filter(snack -> snack.getLocalDate().isBefore(endDate) || snack.getLocalDate().equals(endDate))
                .filter(snack -> snack.getLocalDate().isAfter(startDate) || snack.getLocalDate().equals(startDate))
                .collect(Collectors.toList());
    }
}
