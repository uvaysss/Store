package com.uvaysss.store.ui.storefront;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.uvaysss.store.data.uimodel.ProductId;
import com.uvaysss.store.ui.common.SmartFragmentStatePagerAdapter;
import com.uvaysss.store.ui.product.ProductFragment;

import java.util.ArrayList;
import java.util.List;

public class ProductsPagerAdapter extends SmartFragmentStatePagerAdapter<ProductFragment> {

    private List<ProductId> productIds;

    public ProductsPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        productIds = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return ProductFragment.newInstance(productIds.get(position));
    }

    @Override
    public int getCount() {
        return productIds.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    public void setProductIds(List<ProductId> productIds) {
        this.productIds.clear();
        this.productIds.addAll(productIds);
        notifyDataSetChanged();
    }
}
