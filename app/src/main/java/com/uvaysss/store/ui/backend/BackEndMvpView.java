package com.uvaysss.store.ui.backend;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.uvaysss.store.data.uimodel.ProductUiModel;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface BackEndMvpView extends MvpView {

    void setProducts(List<ProductUiModel> products);

    @StateStrategyType(SkipStrategy.class)
    void showMessage(@StringRes int message);

    @StateStrategyType(SkipStrategy.class)
    void navigateToAddProductScreen();

    @StateStrategyType(SkipStrategy.class)
    void navigateToProductEditScreen(ProductUiModel product);
}
