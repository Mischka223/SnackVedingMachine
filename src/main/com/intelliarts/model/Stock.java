package com.intelliarts.model;

import java.util.Objects;

public class Stock implements Cloneable {
    private Snack snack;
    private int count;


    public Stock(Snack snack, int count) {
        this.snack = snack;
        this.count = count;
    }

    public Snack getSnack() {
        return snack;
    }

    public void setSnack(Snack snack) {
        this.snack = snack;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public Stock clone() {
        try {
            return (Stock) super.clone();
        } catch (CloneNotSupportedException e) {
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return count == stock.count &&
                snack.equals(stock.snack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(snack, count);
    }
}
