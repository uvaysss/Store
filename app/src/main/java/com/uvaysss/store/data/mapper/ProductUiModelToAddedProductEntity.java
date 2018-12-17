package com.uvaysss.store.data.mapper;

import com.uvaysss.store.data.database.entity.AddedProductEntity;
import com.uvaysss.store.data.uimodel.ProductUiModel;

import javax.inject.Inject;

import io.reactivex.functions.Function;

public class ProductUiModelToAddedProductEntity
        implements Function<ProductUiModel, AddedProductEntity> {

    @Inject
    public ProductUiModelToAddedProductEntity() {
    }

    @Override
    public AddedProductEntity apply(ProductUiModel productUiModel) throws Exception {
        return new AddedProductEntity(
                productUiModel.getTitle(),
                productUiModel.getPrice(),
                productUiModel.getCount()
        );
    }
}
