package com.uvaysss.store.ui.productedit;

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
public class ProductEditMvpPresenter extends BaseMvpPresenter<ProductEditMvpView> {

    @Inject ProductRepository productRepository;

    private long productId;
    private Disposable getProductDisposable;
    private Disposable updateProductDisposable;

    public ProductEditMvpPresenter() {
        StoreApplication.applicationComponent.inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dispose(getProductDisposable);
        dispose(updateProductDisposable);
    }

    public void onCreate(long productId) {
        this.productId = productId;

        dispose(getProductDisposable);
        getProductDisposable = productRepository.getBackEndProduct(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(product -> getViewState().setProduct(product), Timber::e);
    }

    public void onSaveClick(String title, String price, String count) {
        if (!areValuesValid(title, price, count)) {
            return;
        }

        ProductUiModel product = new ProductUiModel(
                productId,
                title,
                Double.parseDouble(price),
                Long.valueOf(count)
        );

        dispose(updateProductDisposable);
        updateProductDisposable = productRepository.edit(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(id -> {
                    getViewState().startEditService(id);
                    getViewState().navigateBack();
                }, Timber::e);
    }

    private boolean areValuesValid(String title, String price, String count) {
        if (title.trim().isEmpty()) {
            getViewState().showTitleError(R.string.error_field_cannot_be_empty);
            return false;
        } else {
            getViewState().removeTitleError();
        }

        if (price.trim().isEmpty()) {
            getViewState().showPriceError(R.string.error_field_cannot_be_empty);
            return false;
        } else {
            getViewState().removePriceError();
        }

        if (count.trim().isEmpty()) {
            getViewState().showCountError(R.string.error_field_cannot_be_empty);
            return false;
        } else {
            getViewState().removeCountError();
        }

        return true;
    }
}
