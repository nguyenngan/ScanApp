/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.repository.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.scan.barcode.data.api.data.DataRestApi;
import com.scan.barcode.data.api.utils.ApiResponse;
import com.scan.barcode.data.cache.AppDb;
import com.scan.barcode.data.common.Resource;
import com.scan.barcode.data.entities.Body;
import com.scan.barcode.data.entities.Data;
import com.scan.barcode.data.executor.AppExecutors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.scan.barcode.data.common.StatusNetwork.ERROR;

@Singleton
public class DataRepositoryImpl implements DataRepository {

    private final AppDb appDb;
    private final DataRestApi restApi;
    private final AppExecutors appExecutors;

    private Body body = new Body("regx-app", "8ecVZORdfkjt");

    @Inject
    DataRepositoryImpl(AppDb appDb, DataRestApi restApi, AppExecutors appExecutors) {
        this.appDb = appDb;
        this.restApi = restApi;
        this.appExecutors = appExecutors;
    }

    @Override
    public void insertData(Data data) {
        appExecutors.diskIO().execute(() -> appDb.dataDao().insert(data));
    }

    @Override
    public void emptyData() {
        appExecutors.diskIO().execute(() -> appDb.dataDao().emptyData());
    }

    @Override
    public LiveData<List<Data>> getData() {
        return appDb.dataDao().getData();
    }

    @Override
    public LiveData<Resource<Integer>> syncData(List<Data> data) {
        MediatorLiveData<Resource<Integer>> result = new MediatorLiveData<>();
        result.setValue(Resource.loading(null));
        LiveData<ApiResponse<Integer>> response = restApi.syncData(bodyList(data));
        result.addSource(response, resource -> {
            if (resource == null) {
                result.setValue(Resource.error(ERROR, "Response null", null));
            } else if (resource.isSuccessful() && resource.body != null) {
                result.setValue(Resource.success(resource.body));
            } else {
                result.setValue(Resource.error(resource.code, resource.errorMessage, null));
            }
        });
        return result;
    }

    private List<Body> bodyList(List<Data> dataList) {
        if (dataList != null && !dataList.isEmpty()) {
            List<Body> bodies = new ArrayList<>();
            bodies.add(body);
            for (Data d : dataList) {
                if (d != null) {
                    bodies.add(transform(d));
                }
            }
            return bodies;
        }
        return Collections.emptyList();
    }

    private Body transform(Data data) {
        return new Body(data);
    }
}
