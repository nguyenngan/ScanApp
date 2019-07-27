/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.barcode;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.scan.barcode.data.entities.Data;
import com.scan.barcode.data.repository.data.DataRepository;
import com.scan.barcode.data.repository.user.UserRepository;

import javax.inject.Inject;

/**
 * @author ndn
 * Created by ndn
 * Created on 6/8/18.
 */
public class BarcodeViewModel extends ViewModel {

    private final DataRepository dataRepository;

    @Inject
    BarcodeViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void insertData(Data data) {
        dataRepository.insertData(data);
    }
}