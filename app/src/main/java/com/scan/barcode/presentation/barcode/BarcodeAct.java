/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.barcode;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;

import com.google.zxing.ZXingScannerView;
import com.scan.barcode.R;
import com.scan.barcode.databinding.BarcodeActBinding;
import com.scan.barcode.presentation.common.AbsXZingAct;

import javax.inject.Inject;


public class BarcodeAct extends AbsXZingAct {

    @Inject
    ViewModelProvider.Factory factory;

    BarcodeActBinding binding;
    BarcodeViewModel viewModel;

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.barcode_act);
        viewModel = ViewModelProviders.of(BarcodeAct.this, factory).get(BarcodeViewModel.class);
    }

    @Override
    protected void initializeLayout() {
        super.initializeLayout();
    }

    @Override
    public void addScannerView(ZXingScannerView mScannerView) {
        binding.contentFrame.addView(mScannerView);
    }

    @Override
    protected void initActionBar() {
        setSupportActionBar(binding.toolbar);
    }
}
