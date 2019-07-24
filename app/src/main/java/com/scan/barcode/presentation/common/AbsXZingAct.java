/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.common;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.ZXingScannerView;
import com.scan.barcode.R;
import com.scan.barcode.databinding.BarcodeActBinding;
import com.scan.barcode.presentation.barcode.BarcodeViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public abstract class AbsXZingAct extends AbsBackActivity implements ZXingScannerView.ResultHandler {

    public static final String ARGS = "Data-Args";

    private ZXingScannerView mScannerView;
    private ArrayList<Integer> mSelectedIndices;

    @Override
    public void onResume() {
        super.onResume();
        if (mScannerView != null) {
            mScannerView.setResultHandler(this);
            mScannerView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mScannerView != null) {
            mScannerView.stopCamera();
        }
    }

    @Override
    protected void initializeLayout() {
        mScannerView = new ZXingScannerView(this);
        setupFormats();
        addScannerView(mScannerView);
    }

    public abstract void addScannerView(ZXingScannerView mScannerView);

    @Override
    public void handleResult(Result rawResult) {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this,
                "Contents = " + rawResult.getText() + ", Format = "
                        + rawResult.getBarcodeFormat().toString(),
                Toast.LENGTH_LONG).show();
        handleResult(rawResult.getText());
    }

    public abstract void handleResult(String result);

    public void setupFormats() {
        List<BarcodeFormat> formats = new ArrayList<>();
        if (mSelectedIndices == null || mSelectedIndices.isEmpty()) {
            mSelectedIndices = new ArrayList<>();
            for (int i = 0; i < ZXingScannerView.ALL_FORMATS.size(); i++) {
                mSelectedIndices.add(i);
            }
        }

        for (int index : mSelectedIndices) {
            formats.add(ZXingScannerView.ALL_FORMATS.get(index));
        }
        if (mScannerView != null) {
            mScannerView.setFormats(formats);
        }
    }
}
