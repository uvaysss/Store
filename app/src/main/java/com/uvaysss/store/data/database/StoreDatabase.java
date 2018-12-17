package com.uvaysss.store.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.uvaysss.store.data.database.dao.AddedProductDao;
import com.uvaysss.store.data.database.dao.ModifiedProductDao;
import com.uvaysss.store.data.database.dao.ProductDao;
import com.uvaysss.store.data.database.dao.PurchasedProductDao;
import com.uvaysss.store.data.database.entity.AddedProductEntity;
import com.uvaysss.store.data.database.entity.ModifiedProductEntity;
import com.uvaysss.store.data.database.entity.ProductEntity;
import com.uvaysss.store.data.database.entity.PurchasedProductEntity;

@Database(
        entities = {
                ProductEntity.class,
                AddedProductEntity.class,
                ModifiedProductEntity.class,
                PurchasedProductEntity.class
        },
        version = 1,
        exportSchema = false
)
public abstract class StoreDatabase extends RoomDatabase {

    public static final String NAME = "store_database";

    public abstract ProductDao productDao();
    public abstract AddedProductDao addedProductDao();
    public abstract ModifiedProductDao modifiedProductDao();
    public abstract PurchasedProductDao purchasedProductDao();
}
