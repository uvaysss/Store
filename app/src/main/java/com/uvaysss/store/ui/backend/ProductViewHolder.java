package com.uvaysss.store.ui.backend;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.uvaysss.store.R;
import com.uvaysss.store.data.uimodel.ProductUiModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.rootView) View rootView;
    @BindView(R.id.textViewTitle) AppCompatTextView textViewTitle;
    @BindView(R.id.textViewCount) AppCompatTextView textViewCount;

    private ProductUiModel product;

    public ProductViewHolder(@NonNull View itemView, OnProductClickListener onProductClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        rootView.setOnClickListener(v -> {
            if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                onProductClickListener.onProductClick(product);
            }
        });
    }

    public void bind(ProductUiModel product) {
        this.product = product;

        textViewTitle.setText(product.getTitle());
        textViewCount.setText(String.valueOf(product.getCount()));
    }
}
