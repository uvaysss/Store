package com.uvaysss.store.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.uvaysss.store.data.database.entity.AddedProductEntity;

import io.reactivex.Single;

@Dao
public interface AddedProductDao {

    @Insert
    long insert(AddedProductEntity addedProductEntity);

    @Query("DELETE FROM AddedProductEntity WHERE AddedProductEntity.id = :id")
    void delete(long id);

    @Query("DELETE FROM AddedProductEntity")
    void deleteAll();

    @Query("SELECT * FROM AddedProductEntity WHERE AddedProductEntity.id = :id")
    Single<AddedProductEntity> getSingle(long id);
}
