package com.uvaysss.store.ui.backend;

import android.view.MenuItem;

import com.arellomobile.mvp.InjectViewState;
import com.uvaysss.store.R;
import com.uvaysss.store.StoreApplication;
import com.uvaysss.store.data.repository.ProductRepository;
import com.uvaysss.store.data.uimodel.ProductUiModel;
import com.uvaysss.store.ui.base.BaseMvpPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class BackEndMvpPresenter extends BaseMvpPresenter<BackEndMvpView> {

    @Inject ProductRepository productRepository;

    private Disposable getProductsDisposable;

    public BackEndMvpPresenter() {
        StoreApplication.applicationComponent.inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dispose(getProductsDisposable);
    }

    public void onViewCreated() {
        dispose(getProductsDisposable);
        getProductsDisposable = productRepository.getBackEndProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(products -> getViewState().setProducts(products), Timber::e);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_add:
                getViewState().navigateToAddProductScreen();
                return true;
            default:
                return false;
        }
    }

    public void onProductClick(ProductUiModel product) {
        if (product.getId() == 0) {
            getViewState().showMessage(R.string.back_end_product_not_added_error);
            return;
        }

        getViewState().navigateToProductEditScreen(product);
    }
}
