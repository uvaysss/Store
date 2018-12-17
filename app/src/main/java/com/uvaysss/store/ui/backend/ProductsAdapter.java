package com.uvaysss.store.ui.backend;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uvaysss.store.R;
import com.uvaysss.store.data.uimodel.ProductUiModel;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private LayoutInflater inflater;
    private List<ProductUiModel> items;
    private OnProductClickListener onProductClickListener;

    public ProductsAdapter(Context context, OnProductClickListener onProductClickListener) {
        this.inflater = LayoutInflater.from(context);
        this.items = new ArrayList<>();
        this.onProductClickListener = onProductClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view, onProductClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<ProductUiModel> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
