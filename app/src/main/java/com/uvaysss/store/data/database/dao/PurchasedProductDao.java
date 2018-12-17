package com.uvaysss.store.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.uvaysss.store.data.database.entity.PurchasedProductEntity;

import io.reactivex.Single;

@Dao
public interface PurchasedProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(PurchasedProductEntity purchasedProductEntity);

    @Query("DELETE FROM PurchasedProductEntity WHERE PurchasedProductEntity.id = :id")
    void delete(long id);

    @Query("DELETE FROM PurchasedProductEntity")
    void deleteAll();

    @Query("SELECT * FROM PurchasedProductEntity WHERE PurchasedProductEntity.id = :id")
    Single<PurchasedProductEntity> getSingle(long id);
}
