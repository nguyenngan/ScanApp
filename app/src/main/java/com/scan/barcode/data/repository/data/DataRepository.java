/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.repository.data;

import android.arch.lifecycle.LiveData;

import com.scan.barcode.data.common.Resource;
import com.scan.barcode.data.entities.Data;

import java.util.List;

public interface DataRepository {

    void insertData(Data data);

    void emptyData();

    LiveData<List<Data>> getData();

    LiveData<Resource<Integer>> syncData(List<Data> dataList);
}