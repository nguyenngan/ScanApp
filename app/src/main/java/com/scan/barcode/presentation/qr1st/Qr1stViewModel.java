/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.qr1st;

import android.arch.lifecycle.ViewModel;

import com.scan.barcode.data.entities.Data;
import com.scan.barcode.data.repository.data.DataRepository;

import javax.inject.Inject;

/**
 * @author ndn
 * Created by ndn
 * Created on 6/8/18.
 */
public class Qr1stViewModel extends ViewModel {

    private final DataRepository dataRepository;

    @Inject
    Qr1stViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void insertData(Data data) {
        dataRepository.insertData(data);
    }
}
