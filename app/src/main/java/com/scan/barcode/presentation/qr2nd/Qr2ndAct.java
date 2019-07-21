/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.qr2nd;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.zxing.ZXingScannerView;
import com.scan.barcode.R;
import com.scan.barcode.databinding.LoginActBinding;
import com.scan.barcode.databinding.QrCode1stActBinding;
import com.scan.barcode.databinding.QrCode2ndActBinding;
import com.scan.barcode.presentation.common.AbsActivity;
import com.scan.barcode.presentation.common.AbsXZingAct;
import com.scan.barcode.presentation.login.LoginViewModel;
import com.scan.barcode.presentation.qr1st.Qr1stViewModel;

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

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.qr_code_2nd_act);
        viewModel = ViewModelProviders.of(this, factory).get(Qr2ndViewModel.class);
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
