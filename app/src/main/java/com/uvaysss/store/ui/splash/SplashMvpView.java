package com.uvaysss.store.ui.splash;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface SplashMvpView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void navigateToMainScreen();
}
