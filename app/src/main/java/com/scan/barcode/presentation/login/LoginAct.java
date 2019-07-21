/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.login;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.scan.barcode.R;
import com.scan.barcode.databinding.LoginActBinding;
import com.scan.barcode.presentation.common.AbsActivity;

import javax.inject.Inject;

/**
 * @author ndn
 * Created by ndn
 * Created on 2019-07-17.
 */
public class LoginAct extends AbsActivity {

    @Inject
    ViewModelProvider.Factory factory;

    LoginActBinding binding;
    LoginViewModel viewModel;

    @Override
    protected void initializeBindingViewModel() {
        binding = DataBindingUtil.setContentView(this, R.layout.login_act);
        viewModel = ViewModelProviders.of(LoginAct.this, factory).get(LoginViewModel.class);
    }

    @Override
    protected void initializeLayout(@Nullable Bundle savedInstanceState) {

    }
}
