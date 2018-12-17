package com.uvaysss.store.ui.base;

import android.content.Context;

import com.arellomobile.mvp.MvpAppCompatActivity;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public abstract class BaseActivity extends MvpAppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
