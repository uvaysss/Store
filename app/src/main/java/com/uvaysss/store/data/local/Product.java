package com.uvaysss.store.data.local;

public class Product {

    private final String title;

    private final double price;

    private final int count;

    public Product(String title, double price, int count) {
        this.title = title;
        this.price = price;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }
}
