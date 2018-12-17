package com.uvaysss.store.di.module;

import com.uvaysss.store.data.repository.ProductRepository;
import com.uvaysss.store.data.repository.ProductRepositoryImpl;
import com.uvaysss.store.data.repository.datasource.ProductDataSource;
import com.uvaysss.store.data.repository.datasource.ProductDataSourceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    ProductRepository provideProductRepository(ProductRepositoryImpl productRepository) {
        return productRepository;
    }

    @Provides
    @Singleton
    ProductDataSource provideProductDataSource(ProductDataSourceImpl productDataSource) {
        return productDataSource;
    }
}
