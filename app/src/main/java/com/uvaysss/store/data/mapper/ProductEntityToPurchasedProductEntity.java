package com.uvaysss.store.data.mapper;

import com.uvaysss.store.data.database.entity.ProductEntity;
import com.uvaysss.store.data.database.entity.PurchasedProductEntity;

import javax.inject.Inject;

public class ProductEntityToPurchasedProductEntity {

    @Inject
    public ProductEntityToPurchasedProductEntity() {
    }

    public PurchasedProductEntity apply(ProductEntity productEntity, long count) throws Exception {
        return new PurchasedProductEntity(productEntity.getId(), count);
    }
}
