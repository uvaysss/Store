package com.uvaysss.store.ui.productedit;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.uvaysss.store.data.uimodel.ProductUiModel;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ProductEditMvpView extends MvpView {

    void setProduct(ProductUiModel product);

    void showTitleError(@StringRes int error);

    void removeTitleError();

    void showPriceError(@StringRes int error);

    void removePriceError();

    void showCountError(@StringRes int error);

    void removeCountError();

    @StateStrategyType(SkipStrategy.class)
    void startEditService(long id);

    @StateStrategyType(SkipStrategy.class)
    void navigateBack();
}
