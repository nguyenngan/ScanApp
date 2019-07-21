/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.main;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.scan.barcode.R;
import com.scan.barcode.databinding.MainActBinding;
import com.scan.barcode.presentation.barcode.BarcodeAct;
import com.scan.barcode.presentation.common.AbsActivity;
import com.scan.barcode.presentation.permission.RxPermissions;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainAct extends AbsActivity {

    @Inject
    ViewModelProvider.Factory factory;

    MainActBinding binding;
    MainViewModel viewModel;

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.main_act);
        viewModel = ViewModelProviders.of(MainAct.this, factory).get(MainViewModel.class);
    }

    @Override
    protected void initializeLayout() {
        binding.scanBt.setOnClickListener(v -> navigateScanBarcode());
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