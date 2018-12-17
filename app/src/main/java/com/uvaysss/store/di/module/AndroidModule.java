package com.uvaysss.store.di.module;

import android.content.Context;
import android.content.res.Resources;

import com.uvaysss.store.StoreApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AndroidModule {

    private StoreApplication application;

    public AndroidModule(StoreApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    StoreApplication provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return application.getResources();
    }
}
