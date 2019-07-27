/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.barcode;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.widget.Toast;

import com.google.zxing.ZXingScannerView;
import com.scan.barcode.R;
import com.scan.barcode.data.entities.Data;
import com.scan.barcode.databinding.BarcodeActBinding;
import com.scan.barcode.presentation.ScanApplication;
import com.scan.barcode.presentation.common.AbsXZingAct;
import com.scan.barcode.presentation.permission.RxPermissions;
import com.scan.barcode.presentation.qr1st.Qr1stAct;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class BarcodeAct extends AbsXZingAct {

    @Inject
    ViewModelProvider.Factory factory;

    BarcodeActBinding binding;
    BarcodeViewModel viewModel;

    private Data data;

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.barcode_act);
        viewModel = ViewModelProviders.of(BarcodeAct.this, factory).get(BarcodeViewModel.class);
    }

    @Override
    protected void initializeLayout() {
        super.initializeLayout();
        initData();

        binding.saveBt.setOnClickListener(v -> {
            if (data != null) {
                viewModel.insertData(data);
            }
            finish();
        });
        binding.nextBt.setOnClickListener(v -> handleResult(""));
        binding.abortBt.setOnClickListener(v -> finish());
    }

    private void initData() {
        data = new Data();
        if (ScanApplication.getInstance().getUser() != null) {
            data.setUsername(ScanApplication.getInstance().getUser().username);
        }
    }

    @Override
    public void addScannerView(ZXingScannerView mScannerView) {
        binding.contentFrame.addView(mScannerView);
    }

    @Override
    public void handleResult(String result) {
        if (data == null) {
            initData();
        }
        data.setBarcode(result);
        viewModel.insertData(data);

        navigateQr1st();
        finish();
    }

    @Override
    protected void initActionBar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void navigateQr1st() {
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
                            if (data != null) {
                                Intent intent = Qr1stAct.getIntent(BarcodeAct.this, data);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(BarcodeAct.this, R.string.permission_media_request_denied, Toast.LENGTH_LONG).show();
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
