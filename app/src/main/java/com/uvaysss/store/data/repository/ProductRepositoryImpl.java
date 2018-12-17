package com.uvaysss.store.data.repository;

import com.uvaysss.store.data.database.dao.AddedProductDao;
import com.uvaysss.store.data.database.dao.ModifiedProductDao;
import com.uvaysss.store.data.database.dao.ProductDao;
import com.uvaysss.store.data.database.dao.PurchasedProductDao;
import com.uvaysss.store.data.database.entity.ProductEntity;
import com.uvaysss.store.data.mapper.AddedProductEntityToProductEntity;
import com.uvaysss.store.data.mapper.ModifiedProductEntityToProductEntity;
import com.uvaysss.store.data.mapper.ProductEntityToProductUiModel;
import com.uvaysss.store.data.mapper.ProductEntityToPurchasedProductEntity;
import com.uvaysss.store.data.mapper.ProductLocalToProductEntity;
import com.uvaysss.store.data.mapper.ProductUiModelToAddedProductEntity;
import com.uvaysss.store.data.mapper.ProductUiModelToModifiedProductEntity;
import com.uvaysss.store.data.mapper.PurchasedProductEntityToProductEntity;
import com.uvaysss.store.data.repository.datasource.ProductDataSource;
import com.uvaysss.store.data.uimodel.ProductId;
import com.uvaysss.store.data.uimodel.ProductUiModel;
import com.uvaysss.store.data.uimodel.purchase.PurchaseStatus;
import com.uvaysss.store.data.uimodel.purchase.PurchaseStatusFailure;
import com.uvaysss.store.data.uimodel.purchase.PurchaseStatusSuccess;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class ProductRepositoryImpl implements ProductRepository {

    private static final long REQUEST_ADD_EDIT_MILLIS = 5000;
    private static final long REQUEST_PURCHASE_MILLIS = 3000;

    private final ProductDao productDao;
    private final AddedProductDao addedProductDao;
    private final ModifiedProductDao modifiedProductDao;
    private final PurchasedProductDao purchasedProductDao;
    private final ProductDataSource productDataSource;
    private final ProductLocalToProductEntity productLocalToProductEntity;
    private final ProductEntityToProductUiModel productEntityToProductUiModel;
    private final ProductEntityToPurchasedProductEntity productEntityToPurchasedProductEntity;
    private final ProductUiModelToModifiedProductEntity productUiModelToModifiedProductEntity;
    private final ProductUiModelToAddedProductEntity productUiModelToAddedProductEntity;
    private final ModifiedProductEntityToProductEntity modifiedProductEntityToProductEntity;
    private final AddedProductEntityToProductEntity addedProductEntityToProductEntity;
    private final PurchasedProductEntityToProductEntity purchasedProductEntityToProductEntity;

    @Inject
    public ProductRepositoryImpl(
            ProductDao productDao,
            AddedProductDao addedProductDao,
            ModifiedProductDao modifiedProductDao,
            PurchasedProductDao purchasedProductDao,
            ProductDataSource productDataSource,
            ProductLocalToProductEntity productLocalToProductEntity,
            ProductEntityToProductUiModel productEntityToProductUiModel,
            ProductEntityToPurchasedProductEntity productEntityToPurchasedProductEntity,
            ProductUiModelToModifiedProductEntity productUiModelToModifiedProductEntity,
            ProductUiModelToAddedProductEntity productUiModelToAddedProductEntity,
            ModifiedProductEntityToProductEntity modifiedProductEntityToProductEntity,
            AddedProductEntityToProductEntity addedProductEntityToProductEntity,
            PurchasedProductEntityToProductEntity purchasedProductEntityToProductEntity
    ) {
        this.productDao = productDao;
        this.addedProductDao = addedProductDao;
        this.modifiedProductDao = modifiedProductDao;
        this.purchasedProductDao = purchasedProductDao;
        this.productDataSource = productDataSource;
        this.productLocalToProductEntity = productLocalToProductEntity;
        this.productEntityToProductUiModel = productEntityToProductUiModel;
        this.productEntityToPurchasedProductEntity = productEntityToPurchasedProductEntity;
        this.productUiModelToModifiedProductEntity = productUiModelToModifiedProductEntity;
        this.productUiModelToAddedProductEntity = productUiModelToAddedProductEntity;
        this.modifiedProductEntityToProductEntity = modifiedProductEntityToProductEntity;
        this.addedProductEntityToProductEntity = addedProductEntityToProductEntity;
        this.purchasedProductEntityToProductEntity = purchasedProductEntityToProductEntity;
    }

    @Override
    public Completable prefetchProducts() {
        return Observable
                .fromCallable(productDataSource::getProducts)
                .flatMap(Observable::fromIterable)
                .map(productLocalToProductEntity)
                .toList()
                // For demo purpose resetting all the data
                .doOnSuccess(products -> {
                    productDao.updateAll(products);
                    addedProductDao.deleteAll();
                    modifiedProductDao.deleteAll();
                    purchasedProductDao.deleteAll();
                })
                .toCompletable();
    }

    @Override
    public Flowable<List<ProductId>> getStoreFrontProductIds() {
        return productDao.getAllProductIdsWithActualPurchasedCount();
    }

    @Override
    public Flowable<List<ProductUiModel>> getBackEndProducts() {
        return productDao
                .getAllProductsWithActualAddedAndModifiedData()
                .map(products -> {
                    List<ProductUiModel> list = new ArrayList<>(products.size());

                    for (ProductEntity product : products) {
                        list.add(productEntityToProductUiModel.apply(product));
                    }

                    return list;
                });
    }

    @Override
    public Flowable<ProductUiModel> getStoreFrontProduct(long id) {
        return productDao
                .getProductWithActualPurchasedCount(id)
                .map(productEntityToProductUiModel);
    }

    @Override
    public Flowable<ProductUiModel> getBackEndProduct(long id) {
        return productDao
                .getProductWithActualModifiedData(id)
                .map(productEntityToProductUiModel);
    }

    @Override
    public Single<Long> edit(ProductUiModel productUiModel) {
        return Single
                .just(productUiModel)
                .map(productUiModelToModifiedProductEntity)
                .map(modifiedProductDao::insert);
    }

    @Override
    public Single<Integer> editApply(long modifiedProductId) {
        return modifiedProductDao
                .getSingle(modifiedProductId)
                // Emulating edit product http request
                .delay(REQUEST_ADD_EDIT_MILLIS, TimeUnit.MILLISECONDS)
                .map(modifiedProductEntityToProductEntity)
                .map(productDao::update)
                .doOnSuccess(i -> modifiedProductDao.delete(modifiedProductId));

    }

    @Override
    public Single<Long> add(ProductUiModel productUiModel) {
        return Single
                .just(productUiModel)
                .map(productUiModelToAddedProductEntity)
                .map(addedProductDao::insert);
    }

    @Override
    public Completable addApply(long addedProductId) {
        return addedProductDao
                .getSingle(addedProductId)
                // Emulating add product http request
                .delay(REQUEST_ADD_EDIT_MILLIS, TimeUnit.MILLISECONDS)
                .map(addedProductEntityToProductEntity)
                .map(productDao::insert)
                .doOnSuccess(l -> addedProductDao.delete(addedProductId))
                .toCompletable();
    }

    @Override
    public Single<Long> purchase(long id, long count) {
        return productDao
                .getSingle(id)
                .map(productEntity -> productEntityToPurchasedProductEntity.apply(productEntity, count))
                .map(purchasedProductDao::insert);
    }

    @Override
    public Single<PurchaseStatus> purchaseApply(long purchasedProductId) {
        return purchasedProductDao
                .getSingle(purchasedProductId)
                // Emulating purchase product http request
                .delay(REQUEST_PURCHASE_MILLIS, TimeUnit.MILLISECONDS)
                .map(purchasedProductEntity -> purchasedProductEntityToProductEntity.apply(
                        purchasedProductEntity,
                        productDao.get(purchasedProductEntity.getProductId())
                ))
                .map(productEntity -> {
                    if (productEntity.getCount() >= 0) {
                        productDao.update(productEntity);
                        return new PurchaseStatusSuccess();
                    } else {
                        return new PurchaseStatusFailure();
                    }
                })
                .doOnSuccess(status -> purchasedProductDao.delete(purchasedProductId));
    }
}
