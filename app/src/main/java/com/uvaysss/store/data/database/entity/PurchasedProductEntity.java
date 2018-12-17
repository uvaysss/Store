package com.uvaysss.store.data.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class PurchasedProductEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true)
    private long id;

    @ColumnInfo(index = true)
    private final long productId;

    private final long count;

    public PurchasedProductEntity(long id, long productId, long count) {
        this.id = id;
        this.productId = productId;
        this.count = count;
    }

    @Ignore
    public PurchasedProductEntity(long productId, long count) {
        this.productId = productId;
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public long getProductId() {
        return productId;
    }

    public long getCount() {
        return count;
    }
}
