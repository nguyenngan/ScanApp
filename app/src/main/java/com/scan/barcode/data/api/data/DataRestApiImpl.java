/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.api.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.google.gson.Gson;
import com.scan.barcode.BuildConfig;
import com.scan.barcode.data.api.utils.ApiResponse;
import com.scan.barcode.data.api.utils.ServiceGenerator;
import com.scan.barcode.data.common.Resource;
import com.scan.barcode.data.entities.Body;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author ndn
 * Created by ndn
 * Created on 2019-07-16.
 */
@Singleton
public class DataRestApiImpl implements DataRestApi {

    private final DataService service;

    @Inject
    DataRestApiImpl() {
        service = ServiceGenerator.createService(DataService.class);
    }

    @Override
    public LiveData<ApiResponse<Integer>> syncData(List<Body> bodyList) {
        checkNotNull(bodyList);
        if (BuildConfig.DEBUG) {
            Log.i(getClass().getName(), new Gson().toJson(bodyList));
        }
        return service.syncData(bodyList);
    }
}
