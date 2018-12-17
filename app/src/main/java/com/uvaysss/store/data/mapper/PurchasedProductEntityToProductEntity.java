package com.uvaysss.store.data.mapper;

import com.uvaysss.store.data.database.entity.ProductEntity;
import com.uvaysss.store.data.database.entity.PurchasedProductEntity;

import javax.inject.Inject;

public class PurchasedProductEntityToProductEntity {

    @Inject
    public PurchasedProductEntityToProductEntity() {
    }

    public ProductEntity apply(
            PurchasedProductEntity purchasedProductEntity,
            ProductEntity productEntity
    ) {
        return new ProductEntity(
                productEntity.getId(),
                productEntity.getTitle(),
                productEntity.getPrice(),
                productEntity.getCount() - purchasedProductEntity.getCount()
        );
    }
}
