package com.uvaysss.store.ui.storefront;

import com.arellomobile.mvp.InjectViewState;
import com.uvaysss.store.StoreApplication;
import com.uvaysss.store.data.repository.ProductRepository;
import com.uvaysss.store.ui.base.BaseMvpPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class StoreFrontMvpPresenter extends BaseMvpPresenter<StoreFrontMvpView> {

    @Inject ProductRepository productRepository;

    private Disposable getProductsDisposable;

    public StoreFrontMvpPresenter() {
        StoreApplication.applicationComponent.inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dispose(getProductsDisposable);
    }

    public void onViewCreated() {
        dispose(getProductsDisposable);
        getProductsDisposable = productRepository.getStoreFrontProductIds()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productIds -> getViewState().setProductIds(productIds), Timber::e);
    }
}
