package com.uvaysss.store.ui.storefront;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.uvaysss.store.data.uimodel.ProductId;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface StoreFrontMvpView extends MvpView {

    void setProductIds(List<ProductId> productIds);
}
