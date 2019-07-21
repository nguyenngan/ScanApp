/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.api.data;

import com.scan.barcode.data.api.utils.ServiceGenerator;

import javax.inject.Inject;
import javax.inject.Singleton;

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
}
