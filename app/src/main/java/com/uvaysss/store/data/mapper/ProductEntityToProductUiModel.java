package com.uvaysss.store.data.mapper;

import com.uvaysss.store.data.database.entity.ProductEntity;
import com.uvaysss.store.data.uimodel.ProductUiModel;

import java.util.Locale;

import javax.inject.Inject;

import io.reactivex.functions.Function;

public class ProductEntityToProductUiModel implements Function<ProductEntity, ProductUiModel> {

    @Inject
    public ProductEntityToProductUiModel() {
    }

    @Override
    public ProductUiModel apply(ProductEntity productEntity) throws Exception {
        return new ProductUiModel(
                productEntity.getId(),
                productEntity.getTitle(),
                productEntity.getPrice(),
                formatPrice(productEntity.getPrice()),
                productEntity.getCount(),
                formatCount(productEntity.getCount())
        );
    }

    private String formatPrice(double price) {
        return String.format(Locale.US, "%.2f", price);
    }

    private String formatCount(long count) {
        return String.valueOf(count);
    }
}
