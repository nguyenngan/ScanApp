/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.api;

import com.scan.barcode.data.api.data.DataRestApi;
import com.scan.barcode.data.api.data.DataRestApiImpl;
import com.scan.barcode.data.api.user.UserRestApi;
import com.scan.barcode.data.api.user.UserRestApiImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * @author ndn
 * Created by ndn
 * Created on 2019-07-17.
 */
@Module
public abstract class ApiModule {

    @Singleton
    @Binds
    abstract DataRestApi bindDataRestApi(DataRestApiImpl impl);

    @Singleton
    @Binds
    abstract UserRestApi bindUserRestApi(UserRestApiImpl impl);
}
