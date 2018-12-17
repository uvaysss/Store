package com.uvaysss.store.data.repository.datasource;

import com.uvaysss.store.data.local.Product;

import java.util.List;

public interface ProductDataSource {

    List<Product> getProducts();
}
