package com.uvaysss.store.ui.base;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.Disposable;

public abstract class BaseMvpPresenter<View extends MvpView> extends MvpPresenter<View> {

    protected void dispose(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
