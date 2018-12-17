package com.uvaysss.store;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;
import com.uvaysss.store.di.ApplicationComponent;
import com.uvaysss.store.di.DaggerApplicationComponent;
import com.uvaysss.store.di.module.AndroidModule;
import com.uvaysss.store.di.module.DatabaseModule;
import com.uvaysss.store.di.module.RepositoryModule;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import timber.log.Timber;

public class StoreApplication extends MultiDexApplication {

    public static ApplicationComponent applicationComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());

            Stetho.initializeWithDefaults(this);
        }

        if (applicationComponent == null) {
            applicationComponent = createApplicationComponent();
        }

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath(getString(R.string.font_product_sans_regular))
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }

    protected ApplicationComponent createApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .databaseModule(new DatabaseModule())
                .repositoryModule(new RepositoryModule())
                .build();
    }
}
