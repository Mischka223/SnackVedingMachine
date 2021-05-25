package com.intelliarts.dao;


import com.intelliarts.model.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao {
    void createOrder(Order order);

    List<Order> getOrdersByDate(LocalDate localDate);


    List<Order> getOrdersByDates(LocalDate startDate, LocalDate endDate);
}
