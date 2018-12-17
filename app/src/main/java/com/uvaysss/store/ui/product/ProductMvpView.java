package com.uvaysss.store.ui.product;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.uvaysss.store.data.uimodel.ProductUiModel;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ProductMvpView extends MvpView {

    void setProduct(ProductUiModel product);

    @StateStrategyType(SkipStrategy.class)
    void startPurchaseService(long id);
}
