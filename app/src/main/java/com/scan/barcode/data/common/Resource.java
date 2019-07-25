/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.common;

import android.support.annotation.Nullable;

import static com.scan.barcode.data.common.StatusNetwork.LOADING;
import static com.scan.barcode.data.common.StatusNetwork.SUCCESS;


/**
 * @param <T>
 * @author ndn
 * Created by ndn
 * Created on 6/7/18.
 * A generic class that holds a value with its loading statusNetwork.
 */
public class Resource<T> {

    public final int statusNetwork;

    @Nullable
    public final String message;

    @Nullable
    public final T data;

    private Resource(int status, @Nullable T data, @Nullable String message) {
        this.statusNetwork = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(int error, String msg, @Nullable T data) {
        return new Resource<>(error, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    public boolean isSuccessful() {
        return statusNetwork >= 200 && statusNetwork < 300;
    }

    public boolean isUnauthorized() {
        return statusNetwork == 401;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "statusNetwork=" + statusNetwork +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
