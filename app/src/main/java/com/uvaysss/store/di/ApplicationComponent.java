package com.uvaysss.store.di;

import com.uvaysss.store.di.module.AndroidModule;
import com.uvaysss.store.di.module.DatabaseModule;
import com.uvaysss.store.di.module.RepositoryModule;
import com.uvaysss.store.ui.backend.BackEndMvpPresenter;
import com.uvaysss.store.ui.product.ProductMvpPresenter;
import com.uvaysss.store.ui.productadd.ProductAddMvpPresenter;
import com.uvaysss.store.ui.productedit.ProductEditMvpPresenter;
import com.uvaysss.store.ui.service.ActionService;
import com.uvaysss.store.ui.splash.SplashMvpPresenter;
import com.uvaysss.store.ui.storefront.StoreFrontMvpPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AndroidModule.class,
        DatabaseModule.class,
        RepositoryModule.class
})
public interface ApplicationComponent {
    void inject(SplashMvpPresenter splashMvpPresenter);
    void inject(StoreFrontMvpPresenter storeFrontMvpPresenter);
    void inject(BackEndMvpPresenter backEndMvpPresenter);
    void inject(ProductMvpPresenter productMvpPresenter);
    void inject(ProductEditMvpPresenter productEditMvpPresenter);
    void inject(ProductAddMvpPresenter productAddMvpPresenter);
    void inject(ActionService actionService);
}
