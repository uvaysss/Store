package com.uvaysss.store.data.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class AddedProductEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private final String title;

    private final double price;

    private final long count;

    public AddedProductEntity(long id, String title, double price, long count) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.count = count;
    }

    @Ignore
    public AddedProductEntity(String title, double price, long count) {
        this.title = title;
        this.price = price;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public long getCount() {
        return count;
    }
}
