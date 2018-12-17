package com.uvaysss.store.ui.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

@SuppressWarnings("unused")
public class BottomNavigationBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {

    public BottomNavigationBehavior() {
    }

    public BottomNavigationBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent,
                                   @NonNull V child,
                                   @NonNull View dependency) {
        if (dependency instanceof Snackbar.SnackbarLayout) {
            updateSnackbar(child, (Snackbar.SnackbarLayout) dependency);
        }

        return super.layoutDependsOn(parent, child, dependency);
    }

    private void updateSnackbar(View child, Snackbar.SnackbarLayout snackbarLayout) {
        if (snackbarLayout.getLayoutParams() instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.LayoutParams layoutParams =
                    (CoordinatorLayout.LayoutParams) snackbarLayout.getLayoutParams();
            layoutParams.setAnchorId(child.getId());
            layoutParams.anchorGravity = Gravity.TOP;
            layoutParams.gravity = Gravity.TOP;
            snackbarLayout.setLayoutParams(layoutParams);
        }
    }
}
