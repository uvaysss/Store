package com.uvaysss.store.ui.storefront;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.uvaysss.store.R;
import com.uvaysss.store.data.uimodel.ProductId;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StoreFrontFragment extends MvpAppCompatFragment implements StoreFrontMvpView {

    @BindView(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewPager) ViewPager viewPager;

    @InjectPresenter StoreFrontMvpPresenter storeFrontMvpPresenter;

    private Unbinder unbinder;
    private ProductsPagerAdapter productsPagerAdapter;

    public static StoreFrontFragment newInstance() {
        return new StoreFrontFragment();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store_front, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        storeFrontMvpPresenter.onViewCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initViews() {
        toolbar.setTitle(R.string.store_front_title);

        productsPagerAdapter = new ProductsPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(productsPagerAdapter);
    }

    // StoreFrontMvpView

    @Override
    public void setProductIds(List<ProductId> productIds) {
        productsPagerAdapter.setProductIds(productIds);
    }
}
