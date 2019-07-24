/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.repository.data;

import android.arch.lifecycle.LiveData;

import com.scan.barcode.data.api.data.DataRestApi;
import com.scan.barcode.data.cache.AppDb;
import com.scan.barcode.data.entities.Data;
import com.scan.barcode.data.executor.AppExecutors;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataRepositoryImpl implements DataRepository {

    private final AppDb appDb;
    private final DataRestApi restApi;
    private final AppExecutors appExecutors;

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
}
