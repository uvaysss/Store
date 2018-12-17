package com.uvaysss.store.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.uvaysss.store.ui.backend.BackEndFragment;
import com.uvaysss.store.ui.storefront.StoreFrontFragment;

public class MainBottomNavigationAdapter extends FragmentPagerAdapter {

    private static final int ITEM_COUNT = 2;
    public static final int POSITION_STORE_FRONT = 0;
    public static final int POSITION_BACK_END = 1;

    public MainBottomNavigationAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case POSITION_STORE_FRONT:
                return StoreFrontFragment.newInstance();
            case POSITION_BACK_END:
                return BackEndFragment.newInstance();
            default:
                throw new IllegalStateException("Unknown position");
        }
    }

    @Override
    public int getCount() {
        return ITEM_COUNT;
    }
}
