/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.viewmodel;

import android.arch.lifecycle.LiveData;

/**
 * A LiveData class that has {@code null} value.
 */
public class AbsentLiveData extends LiveData {

    @SuppressWarnings("unchecked")
    private AbsentLiveData() {
        postValue(null);
    }

    @SuppressWarnings("unchecked")
    public static <T> LiveData<T> create() {
        return new AbsentLiveData();
    }
}
