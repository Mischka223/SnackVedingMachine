package com.intelliarts.model;

import java.time.LocalDate;
import java.util.Objects;

public class Order {
    private Snack snack;
    private LocalDate localDate;


    public Order(Snack snack, LocalDate localDate) {
        this.snack = snack;
        this.localDate = localDate;
    }

    public Snack getSnack() {
        return snack;
    }

    public void setSnack(Snack snack) {
        this.snack = snack;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return snack.equals(order.snack) &&
                localDate.equals(order.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(snack, localDate);
    }
}
