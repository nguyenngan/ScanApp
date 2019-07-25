/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.scan.barcode.data.common.Resource;
import com.scan.barcode.data.entities.Data;
import com.scan.barcode.data.repository.data.DataRepository;
import com.scan.barcode.viewmodel.AbsentLiveData;

import java.util.List;

import javax.inject.Inject;

/**
 * @author ndn
 * Created by ndn
 * Created on 6/8/18.
 */
public class MainViewModel extends ViewModel {

    private DataRepository dataRepository;

    private MutableLiveData<Boolean> isGetData = new MutableLiveData<>();
    private LiveData<List<Data>> dataFromCache;
    private List<Data> dataList;

    private MutableLiveData<List<Data>> syncDataMutableLiveData = new MutableLiveData<>();
    private LiveData<Resource<Integer>> syncDataResponse;

    @Inject
    MainViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;

        dataFromCache = Transformations.switchMap(isGetData, input -> {
            if (input) {
                return dataRepository.getData();
            }
            return AbsentLiveData.create();
        });

        syncDataResponse = Transformations.switchMap(syncDataMutableLiveData, input -> {
            if (input == null || input.isEmpty()) {
                return AbsentLiveData.create();
            }
            return dataRepository.syncData(input);
        });
    }

    public void getDatas() {
        isGetData.setValue(true);
    }

    public LiveData<List<Data>> getDataFromCache() {
        return dataFromCache;
    }

    public void syncData() {
        syncDataMutableLiveData.setValue(dataList);
    }

    public LiveData<Resource<Integer>> getSyncDataResponse() {
        return syncDataResponse;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public void removeAllData() {
        dataRepository.emptyData();
    }
}
