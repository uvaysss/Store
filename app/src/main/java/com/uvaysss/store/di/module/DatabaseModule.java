package com.uvaysss.store.di.module;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.uvaysss.store.data.database.StoreDatabase;
import com.uvaysss.store.data.database.dao.AddedProductDao;
import com.uvaysss.store.data.database.dao.ModifiedProductDao;
import com.uvaysss.store.data.database.dao.ProductDao;
import com.uvaysss.store.data.database.dao.PurchasedProductDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    StoreDatabase provideDatabase(Context context) {
        return Room
                .databaseBuilder(context, StoreDatabase.class, StoreDatabase.NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    ProductDao provideProductDao(StoreDatabase database) {
        return database.productDao();
    }

    @Provides
    @Singleton
    ModifiedProductDao provideModifiedProductDao(StoreDatabase database) {
        return database.modifiedProductDao();
    }

    @Provides
    @Singleton
    AddedProductDao provideAddedProductDao(StoreDatabase database) {
        return database.addedProductDao();
    }

    @Provides
    @Singleton
    PurchasedProductDao providePurchasedProductDao(StoreDatabase database) {
        return database.purchasedProductDao();
    }
}
