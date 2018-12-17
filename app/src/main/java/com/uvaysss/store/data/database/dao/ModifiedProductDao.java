package com.uvaysss.store.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.uvaysss.store.data.database.entity.ModifiedProductEntity;

import io.reactivex.Single;

@Dao
public interface ModifiedProductDao {

    @Insert
    long insert(ModifiedProductEntity modifiedProduct);

    @Query("DELETE FROM ModifiedProductEntity WHERE ModifiedProductEntity.id = :id")
    void delete(long id);

    @Query("DELETE FROM ModifiedProductEntity")
    void deleteAll();

    @Query("SELECT * FROM ModifiedProductEntity WHERE ModifiedProductEntity.id = :id")
    Single<ModifiedProductEntity> getSingle(long id);
}
