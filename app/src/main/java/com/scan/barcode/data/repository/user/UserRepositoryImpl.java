/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.repository.user;

import com.scan.barcode.data.api.data.DataRestApi;
import com.scan.barcode.data.cache.AppDb;
import com.scan.barcode.data.executor.AppExecutors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserRepositoryImpl implements UserRepository {

    private final AppDb appDb;
    private final DataRestApi restApi;
    private final AppExecutors appExecutors;

    @Inject
    UserRepositoryImpl(AppDb appDb, DataRestApi restApi, AppExecutors appExecutors) {
        this.appDb = appDb;
        this.restApi = restApi;
        this.appExecutors = appExecutors;
    }
}
