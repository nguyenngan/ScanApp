/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.qr1st;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.widget.Toast;

import com.google.zxing.ZXingScannerView;
import com.scan.barcode.R;
import com.scan.barcode.data.entities.Data;
import com.scan.barcode.databinding.QrCode1stActBinding;
import com.scan.barcode.presentation.common.AbsXZingAct;
import com.scan.barcode.presentation.permission.RxPermissions;
import com.scan.barcode.presentation.qr2nd.Qr2ndAct;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author ndn
 * Created by ndn
 * Created on 2019-07-17.
 */
public class Qr1stAct extends AbsXZingAct {

    @Inject
    ViewModelProvider.Factory factory;

    QrCode1stActBinding binding;
    Qr1stViewModel viewModel;

    private Data data;

    public static Intent getIntent(Context context, Data data) {
        Intent intent = new Intent(context, Qr1stAct.class);
        intent.putExtra(ARGS, data);
        return intent;
    }

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.qr_code_1st_act);
        viewModel = ViewModelProviders.of(this, factory).get(Qr1stViewModel.class);
    }

    @Override
    protected void initializeLayout() {
        super.initializeLayout();
        if (getIntent() != null) {
            data = getIntent().getParcelableExtra(ARGS);
        }
        binding.saveBt.setOnClickListener(v -> {
            if (data != null) {
                viewModel.insertData(data);
                finish();
            }
        });

        binding.saveBt.setOnClickListener(v -> {
            if (data != null) {
                viewModel.insertData(data);
            }
            finish();
        });
        binding.nextBt.setOnClickListener(v -> handleResult(""));
        binding.abortBt.setOnClickListener(v -> finish());
    }

    @Override
    public void addScannerView(ZXingScannerView mScannerView) {
        binding.contentFrame.addView(mScannerView);
    }

    @Override
    public void handleResult(String result) {
        if (data != null) {
            data.setQr1St(result);
            viewModel.insertData(data);
        }
        navigateQr2nd();
        finish();
    }

    @Override
    protected void initActionBar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void navigateQr2nd() {
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
                                Intent intent = Qr2ndAct.getIntent(Qr1stAct.this, data);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(Qr1stAct.this, R.string.permission_media_request_denied, Toast.LENGTH_LONG).show();
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
