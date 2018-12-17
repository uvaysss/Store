package com.uvaysss.store.data.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.uvaysss.store.data.database.entity.ProductEntity;
import com.uvaysss.store.data.uimodel.ProductId;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ProductEntity product);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ProductEntity> products);

    @Update()
    int update(ProductEntity product);

    @Query("DELETE FROM ProductEntity")
    void deleteAll();

    @Query("SELECT * FROM ProductEntity")
    Flowable<List<ProductEntity>> getAll();

    @Query("SELECT * FROM ProductEntity WHERE ProductEntity.id = :id")
    ProductEntity get(long id);

    @Query("SELECT * FROM ProductEntity WHERE ProductEntity.id = :id")
    Single<ProductEntity> getSingle(long id);

    /**
     * This method returns products with count > 0, taking into account the products that are
     * currently in the process of buying
     */
    @Query("SELECT ProductEntity.id " +
            "FROM ProductEntity " +
            "LEFT JOIN PurchasedProductEntity " +
            "ON PurchasedProductEntity.productId = ProductEntity.id " +
            "GROUP BY ProductEntity.id " +
            "HAVING (ProductEntity.count - COUNT(PurchasedProductEntity.id)) > 0")
    Flowable<List<ProductId>> getAllProductIdsWithActualPurchasedCount();

    /**
     * This method returns product with actual count value, taking into account the products that
     * are currently in the process of buying
     */
    @Query("SELECT ProductEntity.id, ProductEntity.title, ProductEntity.price, " +
            "(ProductEntity.count - COUNT(PurchasedProductEntity.id)) AS count " +
            "FROM ProductEntity " +
            "LEFT JOIN PurchasedProductEntity " +
            "ON PurchasedProductEntity.productId = ProductEntity.id " +
            "GROUP BY ProductEntity.id " +
            "HAVING ProductEntity.id = :id")
    Flowable<ProductEntity> getProductWithActualPurchasedCount(long id);

    /**
     * This method returns products with actual data, taking into account the products that are
     * currently in the process of modifying and adding
     */
    @Query("SELECT " +
            "ProductEntity.id, " +
            "CASE " +
            "WHEN ModifiedProductEntity.id IS NULL THEN ProductEntity.title " +
            "ELSE ModifiedProductEntity.title " +
            "END AS title, " +
            "CASE " +
            "WHEN ModifiedProductEntity.id IS NULL THEN ProductEntity.price " +
            "ELSE ModifiedProductEntity.price " +
            "END AS price, " +
            "CASE " +
            "WHEN ModifiedProductEntity.id IS NULL THEN ProductEntity.count " +
            "ELSE ModifiedProductEntity.count " +
            "END AS count " +
            "FROM ProductEntity " +
            "LEFT JOIN (" +
            "SELECT * " +
            "FROM ModifiedProductEntity " +
            "GROUP BY ModifiedProductEntity.productId" +
            ") AS ModifiedProductEntity " +
            "ON ModifiedProductEntity.productId = ProductEntity.id " +
            "UNION ALL " +
            "SELECT NULL, " +
            "AddedProductEntity.title, AddedProductEntity.price, AddedProductEntity.count  " +
            "FROM AddedProductEntity")
    Flowable<List<ProductEntity>> getAllProductsWithActualAddedAndModifiedData();

    /**
     * This method returns product with actual data, taking into account the products that
     * are currently in the process of modifying
     */
    @Query("SELECT " +
            "ProductEntity.id, " +
            "CASE " +
            "WHEN ModifiedProductEntity.id IS NULL THEN ProductEntity.title " +
            "ELSE ModifiedProductEntity.title " +
            "END AS title, " +
            "CASE " +
            "WHEN ModifiedProductEntity.id IS NULL THEN ProductEntity.price " +
            "ELSE ModifiedProductEntity.price " +
            "END AS price, " +
            "CASE " +
            "WHEN ModifiedProductEntity.id IS NULL THEN ProductEntity.count " +
            "ELSE ModifiedProductEntity.count " +
            "END AS count " +
            "FROM ProductEntity " +
            "LEFT JOIN ModifiedProductEntity " +
            "ON ModifiedProductEntity.productId = ProductEntity.id " +
            "WHERE ProductEntity.id = :id " +
            "GROUP BY ModifiedProductEntity.productId")
    Flowable<ProductEntity> getProductWithActualModifiedData(long id);

    @Transaction
    default void updateAll(List<ProductEntity> products) {
        deleteAll();
        insertAll(products);
    }
}
