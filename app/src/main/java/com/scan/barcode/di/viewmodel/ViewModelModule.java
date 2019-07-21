/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.di.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.scan.barcode.presentation.barcode.BarcodeViewModel;
import com.scan.barcode.presentation.login.LoginViewModel;
import com.scan.barcode.presentation.main.MainViewModel;
import com.scan.barcode.presentation.qr1st.Qr1stViewModel;
import com.scan.barcode.presentation.qr2nd.Qr2ndViewModel;
import com.scan.barcode.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * @author ndn
 * Created by ndn
 * Created on 6/1/18.
 */
@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(BarcodeViewModel.class)
    abstract ViewModel bindBarcodeViewModel(BarcodeViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(Qr1stViewModel.class)
    abstract ViewModel bindQr1stViewModel(Qr1stViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(Qr2ndViewModel.class)
    abstract ViewModel bindQr2ndViewModel(Qr2ndViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel viewModel);
}
