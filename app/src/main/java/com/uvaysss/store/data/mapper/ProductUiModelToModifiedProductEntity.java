package com.uvaysss.store.data.mapper;

import com.uvaysss.store.data.database.entity.ModifiedProductEntity;
import com.uvaysss.store.data.uimodel.ProductUiModel;

import javax.inject.Inject;

import io.reactivex.functions.Function;

public class ProductUiModelToModifiedProductEntity
        implements Function<ProductUiModel, ModifiedProductEntity> {

    @Inject
    public ProductUiModelToModifiedProductEntity() {
    }

    @Override
    public ModifiedProductEntity apply(ProductUiModel productUiModel) throws Exception {
        return new ModifiedProductEntity(
                productUiModel.getId(),
                productUiModel.getTitle(),
                productUiModel.getPrice(),
                productUiModel.getCount()
        );
    }
}
