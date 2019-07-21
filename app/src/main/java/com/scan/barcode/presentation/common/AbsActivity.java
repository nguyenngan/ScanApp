/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.common;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * @author ndn
 * Created by ndn
 * Created on 5/15/18.
 */
public abstract class AbsActivity extends DaggerAppCompatActivity {

    public ProgressDialog indicatorDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Enable extended screen features. This must be called before setContentView().
        // May be called as many times as desired as long as it is before setContentView().
        // If not called, no extended features will be available.
        // You can not turn off a feature once it is requested.
        // You cannot use other title features with FEATURE_CUSTOM_TITLE.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }

        initIndicatorProgress();

        initializeBindingViewModel();
        initializeLayout();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dismissIndicator();
    }

    @Override
    protected void onStop() {
        dismissIndicator();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        try {
            indicatorDialog = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    protected abstract void initializeBindingViewModel();

    protected abstract void initializeLayout();

    public void initIndicatorProgress() {
        indicatorDialog = new ProgressDialog(this);
        indicatorDialog.setCancelable(true);
    }

    public void showIndicator() {
        if (indicatorDialog != null && !indicatorDialog.isShowing()) {
            indicatorDialog.show();
        }
    }

    public void dismissIndicator() {
        if (indicatorDialog != null && indicatorDialog.isShowing()) {
            indicatorDialog.dismiss();
        }
    }
}
