/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.main;

import android.Manifest;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.widget.Toast;

import com.scan.barcode.R;
import com.scan.barcode.data.entities.Data;
import com.scan.barcode.databinding.MainActBinding;
import com.scan.barcode.presentation.barcode.BarcodeAct;
import com.scan.barcode.presentation.common.AbsActivity;
import com.scan.barcode.presentation.permission.RxPermissions;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.scan.barcode.data.common.StatusNetwork.LOADING;
import static com.scan.barcode.data.common.StatusNetwork.SUCCESS;

public class MainAct extends AbsActivity {

    @Inject
    ViewModelProvider.Factory factory;

    MainActBinding binding;
    MainViewModel viewModel;

    private ProgressDialog progressDialog;

    public static Intent getIntent(Context context) {
        return new Intent(context, MainAct.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getDatas();
    }

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.main_act);
        viewModel = ViewModelProviders.of(MainAct.this, factory).get(MainViewModel.class);
    }

    @Override
    protected void initializeLayout() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        binding.scanBt.setOnClickListener(v -> navigateScanBarcode());
        binding.updateBt.setOnClickListener(v -> viewModel.syncData());

        initGetDataFromCache();
        initSyncResponse();
    }

    private void initGetDataFromCache() {
        stateSyncData(false);
        viewModel.getDataFromCache().observe(this, data -> {
            if (data == null || data.isEmpty()) {
                stateSyncData(false);
            } else {
                boolean canSync = false;
                for (Data d : data) {
                    if (d.canSync()) {
                        canSync = true;
                    }
                }
                if (canSync) {
                    viewModel.setDataList(data);
                }
                stateSyncData(canSync);
            }
        });
    }

    private void initSyncResponse() {
        viewModel.getSyncDataResponse().observe(this, resource -> {
            if (resource != null) {
                if (resource.statusNetwork == LOADING) {
                    progressDialog.show();
                    return;
                }
                if (resource.statusNetwork == SUCCESS) {
                    if (resource.data != null && resource.data == 1) {
                        viewModel.removeAllData();
                        Toast.makeText(this, "Update data success", Toast.LENGTH_LONG).show();
                        stateSyncData(false);
                    }
                }
            }
        });
    }

    private void stateSyncData(boolean enable) {
        binding.updateBt.setActivated(enable);
        binding.updateBt.setEnabled(enable);
        binding.updateBt.setSelected(enable);
    }

    private void navigateScanBarcode() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // Do nothing
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            Intent intent = new Intent(MainAct.this, BarcodeAct.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainAct.this, R.string.permission_media_request_denied, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Do nothing
                    }

                    @Override
                    public void onComplete() {
                        // Do nothing
                    }
                });
    }
}