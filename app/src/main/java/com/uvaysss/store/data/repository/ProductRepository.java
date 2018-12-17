package com.uvaysss.store.data.repository;

import com.uvaysss.store.data.uimodel.ProductId;
import com.uvaysss.store.data.uimodel.ProductUiModel;
import com.uvaysss.store.data.uimodel.purchase.PurchaseStatus;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface ProductRepository {

    Completable prefetchProducts();

    Flowable<List<ProductId>> getStoreFrontProductIds();

    Flowable<List<ProductUiModel>> getBackEndProducts();

    Flowable<ProductUiModel> getStoreFrontProduct(long id);

    Flowable<ProductUiModel> getBackEndProduct(long id);

    Single<Long> edit(ProductUiModel productUiModel);

    Single<Integer> editApply(long modifiedProductId);

    Single<Long> add(ProductUiModel productUiModel);

    Completable addApply(long addedProductId);

    Single<Long> purchase(long id, long count);

    Single<PurchaseStatus> purchaseApply(long purchasedProductId);
}
