package com.uvaysss.store.ui.splash;

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
public class SplashMvpPresenter extends BaseMvpPresenter<SplashMvpView> {

    @Inject ProductRepository productRepository;

    private Disposable disposable;

    public SplashMvpPresenter() {
        StoreApplication.applicationComponent.inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dispose(disposable);
    }

    public void onCreate() {
        dispose(disposable);
        disposable = productRepository.prefetchProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getViewState().navigateToMainScreen(), Timber::e);
    }
}
