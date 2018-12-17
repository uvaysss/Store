package com.uvaysss.store.data.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ModifiedProductEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true)
    private long id;

    @ColumnInfo(index = true)
    private final long productId;

    private final String title;

    private final double price;

    private final long count;

    public ModifiedProductEntity(long id, long productId, String title, double price, long count) {
        this.id = id;
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.count = count;
    }

    @Ignore
    public ModifiedProductEntity(long productId, String title, double price, long count) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public long getProductId() {
        return productId;
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
