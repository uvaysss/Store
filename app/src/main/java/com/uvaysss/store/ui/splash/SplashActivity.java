package com.uvaysss.store.ui.splash;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.uvaysss.store.ui.base.BaseActivity;
import com.uvaysss.store.ui.main.MainActivity;

public class SplashActivity extends BaseActivity implements SplashMvpView {

    @InjectPresenter SplashMvpPresenter splashMvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashMvpPresenter.onCreate();
    }

    @Override
    public void navigateToMainScreen() {
        startActivity(MainActivity.createStartIntent(this));
    }
}
