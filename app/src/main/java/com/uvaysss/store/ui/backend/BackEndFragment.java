package com.uvaysss.store.ui.backend;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.uvaysss.store.R;
import com.uvaysss.store.data.uimodel.ProductUiModel;
import com.uvaysss.store.ui.productadd.ProductAddActivity;
import com.uvaysss.store.ui.productedit.ProductEditActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BackEndFragment extends MvpAppCompatFragment implements BackEndMvpView {

    @BindView(R.id.coordinatorLayout) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @InjectPresenter BackEndMvpPresenter backEndMvpPresenter;

    private Unbinder unbinder;
    private ProductsAdapter productsAdapter;
    private LinearLayoutManager layoutManager;
    private DividerItemDecoration itemDecoration;

    public static BackEndFragment newInstance() {
        return new BackEndFragment();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_back_end, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        backEndMvpPresenter.onViewCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initViews() {
        toolbar.setTitle(R.string.back_end_title);
        toolbar.inflateMenu(R.menu.back_end);
        toolbar.setOnMenuItemClickListener(menuItem -> backEndMvpPresenter.onMenuItemClick(menuItem));

        layoutManager = new LinearLayoutManager(getActivity());
        itemDecoration = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
        productsAdapter = new ProductsAdapter(getActivity(),
                product -> backEndMvpPresenter.onProductClick(product));

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(productsAdapter);
        recyclerView.setHasFixedSize(true);
    }

    // BackEndMvpView

    @Override
    public void setProducts(List<ProductUiModel> products) {
        productsAdapter.setItems(products);
    }

    @Override
    public void showMessage(int message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void navigateToAddProductScreen() {
        startActivity(ProductAddActivity.createStartIntent(getActivity()));
    }

    @Override
    public void navigateToProductEditScreen(ProductUiModel product) {
        startActivity(ProductEditActivity.createStartIntent(getActivity(), product));
    }
}
