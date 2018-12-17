package com.uvaysss.store.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.uvaysss.store.R;
import com.uvaysss.store.ui.base.BaseActivity;
import com.uvaysss.store.ui.common.NonSwipeableViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.nonSwipeableViewPager) NonSwipeableViewPager nonSwipeableViewPager;
    @BindView(R.id.tabLayout) TabLayout tabLayout;

    private MainBottomNavigationAdapter mainBottomNavigationAdapter;

    public static Intent createStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        mainBottomNavigationAdapter = new MainBottomNavigationAdapter(getSupportFragmentManager());
        nonSwipeableViewPager.setAdapter(mainBottomNavigationAdapter);

        tabLayout.setupWithViewPager(nonSwipeableViewPager);

        tabLayout.getTabAt(MainBottomNavigationAdapter.POSITION_STORE_FRONT)
                .setIcon(R.drawable.ic_store_front);
        tabLayout.getTabAt(MainBottomNavigationAdapter.POSITION_BACK_END)
                .setIcon(R.drawable.ic_back_end);
    }
}
