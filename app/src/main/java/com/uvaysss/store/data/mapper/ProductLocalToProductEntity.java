package com.uvaysss.store.data.mapper;

import com.uvaysss.store.data.database.entity.ProductEntity;
import com.uvaysss.store.data.local.Product;

import javax.inject.Inject;

import io.reactivex.functions.Function;

public class ProductLocalToProductEntity implements Function<Product, ProductEntity> {

    @Inject
    public ProductLocalToProductEntity() {
    }

    @Override
    public ProductEntity apply(Product product) throws Exception {
        return new ProductEntity(
                product.getTitle(),
                product.getPrice(),
                product.getCount()
        );
    }
}
