/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.common;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author ndn
 * Created by ndn
 * Created on 6/7/18.
 * Status of a resource that is provided to the UI.
 * <p>
 * These are usually created by the Repository classes where they return
 * {@code LiveData<Resource<T>>} to pass back the latest data to the UI with its fetch statusNetwork.
 */
@IntDef({StatusNetwork.SUCCESS, StatusNetwork.ERROR, StatusNetwork.LOADING})
@Retention(RetentionPolicy.SOURCE)
public @interface StatusNetwork {
    int SUCCESS = 0;
    int ERROR = 1;
    int LOADING = 2;
}
