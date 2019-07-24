/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.qr2nd;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;

import com.google.zxing.ZXingScannerView;
import com.scan.barcode.R;
import com.scan.barcode.data.entities.Data;
import com.scan.barcode.databinding.QrCode2ndActBinding;
import com.scan.barcode.presentation.common.AbsXZingAct;

import javax.inject.Inject;

/**
 * @author ndn
 * Created by ndn
 * Created on 2019-07-17.
 */
public class Qr2ndAct extends AbsXZingAct {

    @Inject
    ViewModelProvider.Factory factory;

    QrCode2ndActBinding binding;
    Qr2ndViewModel viewModel;

    private Data data;

    public static Intent getIntent(Context context, Data data) {
        Intent intent = new Intent(context, Qr2ndAct.class);
        intent.putExtra(ARGS, data);
        return intent;
    }

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.qr_code_2nd_act);
        viewModel = ViewModelProviders.of(this, factory).get(Qr2ndViewModel.class);
    }

    @Override
    protected void initializeLayout() {
        super.initializeLayout();
        if (getIntent() != null) {
            data = getIntent().getParcelableExtra(ARGS);
        }
        binding.abortBt.setOnClickListener(v -> finish());
        binding.saveBt.setOnClickListener(v -> {
            if (data != null) {
                viewModel.insertData(data);
                finish();
            }
        });
    }

    @Override
    public void addScannerView(ZXingScannerView mScannerView) {
        binding.contentFrame.addView(mScannerView);
    }

    @Override
    public void handleResult(String result) {
        if (data != null) {
            data.setQr2nd(result);
            viewModel.insertData(data);
        }
        finish();
    }

    @Override
    protected void initActionBar() {
        setSupportActionBar(binding.toolbar);
    }
}