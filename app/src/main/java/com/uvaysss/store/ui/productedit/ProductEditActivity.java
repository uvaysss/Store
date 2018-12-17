package com.uvaysss.store.ui.productedit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.uvaysss.store.R;
import com.uvaysss.store.data.uimodel.ProductUiModel;
import com.uvaysss.store.ui.base.BaseActivity;
import com.uvaysss.store.ui.service.ActionService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductEditActivity extends BaseActivity implements ProductEditMvpView {

    private static final String EXTRA_PRODUCT_ID = "product_id";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.textInputEditTextTitle) TextInputEditText textInputEditTextTitle;
    @BindView(R.id.textInputEditTextPrice) TextInputEditText textInputEditTextPrice;
    @BindView(R.id.textInputEditTextCount) TextInputEditText textInputEditTextCount;

    @InjectPresenter ProductEditMvpPresenter productEditMvpPresenter;

    public static Intent createStartIntent(Context context, ProductUiModel product) {
        return new Intent(context, ProductEditActivity.class)
                .putExtra(EXTRA_PRODUCT_ID, product.getId());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add_edit);
        ButterKnife.bind(this);
        initViews();

        long productId = getIntent().getLongExtra(EXTRA_PRODUCT_ID, -1);
        productEditMvpPresenter.onCreate(productId);
    }

    private void initViews() {
        toolbar.setTitle(R.string.product_edit_title);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        toolbar.setNavigationOnClickListener(v -> finish());
        toolbar.inflateMenu(R.menu.product_add_edit);
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_save:
                    productEditMvpPresenter.onSaveClick(
                            textInputEditTextTitle.getText().toString(),
                            textInputEditTextPrice.getText().toString(),
                            textInputEditTextCount.getText().toString()
                    );
                    return true;
                default:
                    return false;
            }
        });
    }

    // ProductEditMvpView

    @Override
    public void setProduct(ProductUiModel product) {
        textInputEditTextTitle.setText(product.getTitle());
        textInputEditTextPrice.setText(product.getPriceFormatted());
        textInputEditTextCount.setText(product.getCountFormatted());
    }

    @Override
    public void showTitleError(int error) {
        textInputEditTextTitle.setError(getString(error));
    }

    @Override
    public void removeTitleError() {
        textInputEditTextTitle.setError(null);
    }

    @Override
    public void showPriceError(int error) {
        textInputEditTextPrice.setError(getString(error));
    }

    @Override
    public void removePriceError() {
        textInputEditTextPrice.setError(null);
    }

    @Override
    public void showCountError(int error) {
        textInputEditTextCount.setError(getString(error));
    }

    @Override
    public void removeCountError() {
        textInputEditTextCount.setError(null);
    }

    @Override
    public void startEditService(long id) {
        startService(ActionService.createStartIntentActionEdit(this, id));
    }

    @Override
    public void navigateBack() {
        finish();
    }
}
