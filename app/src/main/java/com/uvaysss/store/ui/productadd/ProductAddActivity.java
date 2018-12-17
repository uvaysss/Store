package com.uvaysss.store.ui.productadd;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.uvaysss.store.R;
import com.uvaysss.store.ui.base.BaseActivity;
import com.uvaysss.store.ui.service.ActionService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAddActivity extends BaseActivity implements ProductAddMvpView {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.textInputEditTextTitle) TextInputEditText textInputEditTextTitle;
    @BindView(R.id.textInputEditTextPrice) TextInputEditText textInputEditTextPrice;
    @BindView(R.id.textInputEditTextCount) TextInputEditText textInputEditTextCount;

    @InjectPresenter ProductAddMvpPresenter productAddMvpPresenter;

    public static Intent createStartIntent(Context context) {
        return new Intent(context, ProductAddActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add_edit);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        toolbar.setTitle(R.string.product_add_title);
        toolbar.setNavigationIcon(R.drawable.ic_close);
        toolbar.setNavigationOnClickListener(v -> finish());
        toolbar.inflateMenu(R.menu.product_add_edit);
        toolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_save:
                    productAddMvpPresenter.onSaveClick(
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

    // ProductAddMvpView

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
    public void startAddService(long id) {
        startService(ActionService.createStartIntentActionAdd(this, id));
    }

    @Override
    public void navigateBack() {
        finish();
    }
}
