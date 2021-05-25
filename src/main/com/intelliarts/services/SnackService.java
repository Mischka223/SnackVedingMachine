package com.intelliarts.services;

import java.text.ParseException;
import java.time.LocalDate;

public interface SnackService {
    void addCategory(String name, double price, int quantity);

    void purchase(String s, LocalDate localDate);

    void reportForMonth(String date) throws ParseException;

    void reportForDay(String date);

    void updateSnack(String snackName, int snackNumber);

    void deleteAllEmpty();

    void getAll();
}
