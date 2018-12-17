package com.uvaysss.store.data.mapper;

import com.uvaysss.store.data.database.entity.AddedProductEntity;
import com.uvaysss.store.data.database.entity.ProductEntity;

import javax.inject.Inject;

import io.reactivex.functions.Function;

public class AddedProductEntityToProductEntity
        implements Function<AddedProductEntity, ProductEntity> {

    @Inject
    public AddedProductEntityToProductEntity() {
    }

    @Override
    public ProductEntity apply(AddedProductEntity addedProductEntity) throws Exception {
        return new ProductEntity(
                addedProductEntity.getTitle(),
                addedProductEntity.getPrice(),
                addedProductEntity.getCount()
        );
    }
}
