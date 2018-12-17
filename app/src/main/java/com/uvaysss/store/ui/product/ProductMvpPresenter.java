package com.uvaysss.store.ui.product;

import com.arellomobile.mvp.InjectViewState;
import com.uvaysss.store.StoreApplication;
import com.uvaysss.store.data.repository.ProductRepository;
import com.uvaysss.store.ui.base.BaseMvpPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class ProductMvpPresenter extends BaseMvpPresenter<ProductMvpView> {

    // For possible future change
    private static final long PRODUCT_COUNT_TO_PURCHASE = 1;

    @Inject ProductRepository productRepository;

    private long productId;
    private Disposable getProductDisposable;
    private CompositeDisposable compositeDisposable;

    public ProductMvpPresenter() {
        StoreApplication.applicationComponent.inject(this);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dispose(getProductDisposable);
        compositeDisposable.clear();
    }

    public void onCreated(long productId) {
        this.productId = productId;
    }

    public void onViewCreated() {
        dispose(getProductDisposable);
        getProductDisposable = productRepository.getStoreFrontProduct(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(product -> getViewState().setProduct(product), Timber::e);
    }

    public void onPurchaseClick() {
        Disposable purchase = productRepository.purchase(productId, PRODUCT_COUNT_TO_PURCHASE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(id -> getViewState().startPurchaseService(id), Timber::e);

        compositeDisposable.add(purchase);
    }
}
