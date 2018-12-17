package com.uvaysss.store.ui.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.uvaysss.store.R;
import com.uvaysss.store.StoreApplication;
import com.uvaysss.store.data.repository.ProductRepository;
import com.uvaysss.store.data.uimodel.purchase.PurchaseStatusFailure;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@SuppressLint("CheckResult")
public class ActionService extends Service {

    private static final int ACTION_ADD = 111;
    private static final int ACTION_EDIT = 112;
    private static final int ACTION_PURCHASE = 113;
    private static final String EXTRA_ACTION = "action";
    private static final String EXTRA_ID = "id";

    @Inject ProductRepository productRepository;

    public static Intent createStartIntentActionAdd(Context context, long id) {
        return new Intent(context, ActionService.class)
                .putExtra(EXTRA_ACTION, ACTION_ADD)
                .putExtra(EXTRA_ID, id);
    }

    public static Intent createStartIntentActionEdit(Context context, long id) {
        return new Intent(context, ActionService.class)
                .putExtra(EXTRA_ACTION, ACTION_EDIT)
                .putExtra(EXTRA_ID, id);
    }

    public static Intent createStartIntentActionPurchase(Context context, long id) {
        return new Intent(context, ActionService.class)
                .putExtra(EXTRA_ACTION, ACTION_PURCHASE)
                .putExtra(EXTRA_ID, id);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        init(intent, startId);
        return super.onStartCommand(intent, flags, startId);
    }

    private void init(Intent intent, int startId) {
        int action = intent.getIntExtra(EXTRA_ACTION, 0);
        long id = intent.getLongExtra(EXTRA_ID, 0);

        StoreApplication.applicationComponent.inject(this);

        if (action == 0 || id == 0) {
            stopSelf(startId);
            return;
        }

        switch (action) {
            case ACTION_ADD:
                addProduct(id, startId);
                return;
            case ACTION_EDIT:
                editProduct(id, startId);
                return;
            case ACTION_PURCHASE:
                purchaseProduct(id, startId);
                return;
            default:
                stopSelf(startId);
        }
    }


    private void addProduct(long id, int startId) {
        productRepository.addApply(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> stopSelf(startId), Timber::e);
    }

    private void editProduct(long id, int startId) {
        productRepository.editApply(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(i -> stopSelf(startId), Timber::e);
    }

    private void purchaseProduct(long id, int startId) {
        productRepository.purchaseApply(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(purchaseStatus -> {
                    if (purchaseStatus instanceof PurchaseStatusFailure) {
                        Toast.makeText(this, R.string.store_front_purchase_failure,
                                Toast.LENGTH_SHORT).show();
                    }
                    stopSelf(startId);
                }, Timber::e);
    }
}
