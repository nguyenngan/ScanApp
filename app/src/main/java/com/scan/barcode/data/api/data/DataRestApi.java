/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.api.data;

import android.arch.lifecycle.LiveData;

import com.scan.barcode.data.api.utils.ApiResponse;
import com.scan.barcode.data.common.Resource;
import com.scan.barcode.data.entities.Body;

import java.util.List;

/**
 * @author ndn
 * Created by ndn
 * Created on 2019-07-16.
 */
public interface DataRestApi {

    /**
     * Sync data to server
     *
     * @param bodyList {@link Body}
     * @return return 1 if post data success, otherwise
     */
    LiveData<ApiResponse<Integer>> syncData(List<Body> bodyList);
}
