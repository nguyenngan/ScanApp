/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.di.activity;

import com.scan.barcode.di.ActivityScoped;
import com.scan.barcode.presentation.barcode.BarcodeAct;
import com.scan.barcode.presentation.login.LoginAct;
import com.scan.barcode.presentation.main.MainAct;
import com.scan.barcode.presentation.qr1st.Qr1stAct;
import com.scan.barcode.presentation.qr2nd.Qr2ndAct;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by ndngan
 * Created on 4/6/18.
 */
@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract LoginAct loginAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract Qr1stAct qr1stAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract BarcodeAct barcodeAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract Qr2ndAct qr2ndAct();

    @ActivityScoped
    @ContributesAndroidInjector
    abstract MainAct mainAct();
}