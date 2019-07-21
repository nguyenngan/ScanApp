/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.qr1st;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;

import com.google.zxing.ZXingScannerView;
import com.scan.barcode.R;
import com.scan.barcode.databinding.QrCode1stActBinding;
import com.scan.barcode.presentation.common.AbsXZingAct;

import javax.inject.Inject;

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

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.qr_code_1st_act);
        viewModel = ViewModelProviders.of(this, factory).get(Qr1stViewModel.class);
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
