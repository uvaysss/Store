package com.uvaysss.store.ui.productadd;

import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ProductAddMvpView extends MvpView {

    void showTitleError(@StringRes int error);

    void removeTitleError();

    void showPriceError(@StringRes int error);

    void removePriceError();

    void showCountError(@StringRes int error);

    void removeCountError();

    @StateStrategyType(SkipStrategy.class)
    void startAddService(long id);

    @StateStrategyType(SkipStrategy.class)
    void navigateBack();
}
