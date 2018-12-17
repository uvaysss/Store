package com.uvaysss.store.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.uvaysss.store.R;
import com.uvaysss.store.data.uimodel.ProductId;
import com.uvaysss.store.data.uimodel.ProductUiModel;
import com.uvaysss.store.ui.service.ActionService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProductFragment extends MvpAppCompatFragment implements ProductMvpView {

    private static final String ARG_PRODUCT_ID = "product_id";

    @BindView(R.id.textViewTitleValue) AppCompatTextView textViewTitleValue;
    @BindView(R.id.textViewPriceValue) AppCompatTextView textViewPriceValue;
    @BindView(R.id.textViewCountValue) AppCompatTextView textViewCountValue;
    @BindView(R.id.buttonBuy) AppCompatButton buttonBuy;

    @InjectPresenter ProductMvpPresenter productMvpPresenter;

    private long productId;
    private Unbinder unbinder;

    public static ProductFragment newInstance(ProductId productId) {
        Bundle args = new Bundle();
        args.putLong(ARG_PRODUCT_ID, productId.getId());

        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            productId = getArguments().getLong(ARG_PRODUCT_ID);
            productMvpPresenter.onCreated(productId);
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        productMvpPresenter.onViewCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initViews() {
        buttonBuy.setOnClickListener(v -> productMvpPresenter.onPurchaseClick());
    }

    // ProductMvpView

    @Override
    public void setProduct(ProductUiModel product) {
        textViewTitleValue.setText(product.getTitle());
        textViewPriceValue.setText(product.getPriceFormatted());
        textViewCountValue.setText(product.getCountFormatted());
    }

    @Override
    public void startPurchaseService(long id) {
        Intent intent = ActionService.createStartIntentActionPurchase(getActivity(), id);
        getActivity().startService(intent);
    }
}
