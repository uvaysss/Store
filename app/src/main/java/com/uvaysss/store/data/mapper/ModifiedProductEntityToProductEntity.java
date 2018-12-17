package com.uvaysss.store.data.mapper;

import com.uvaysss.store.data.database.entity.ModifiedProductEntity;
import com.uvaysss.store.data.database.entity.ProductEntity;

import javax.inject.Inject;

import io.reactivex.functions.Function;

public class ModifiedProductEntityToProductEntity
        implements Function<ModifiedProductEntity, ProductEntity> {

    @Inject
    public ModifiedProductEntityToProductEntity() {
    }

    @Override
    public ProductEntity apply(ModifiedProductEntity modifiedProductEntity) throws Exception {
        return new ProductEntity(
                modifiedProductEntity.getProductId(),
                modifiedProductEntity.getTitle(),
                modifiedProductEntity.getPrice(),
                modifiedProductEntity.getCount()
        );
    }
}
