/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.binding;

import android.databinding.BindingAdapter;
import android.view.View;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * @author ndn
 * Created by ndn
 * Created on 6/1/18.
 * Data Binding adapters specific to the app.
 */
public class BindingAdapters {

    @BindingAdapter("visibility")
    public static void visibility(View view, boolean visibility) {
        view.setVisibility(visibility ? VISIBLE : GONE);
    }
}