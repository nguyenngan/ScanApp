/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.repository;


import com.scan.barcode.data.repository.data.DataRepository;
import com.scan.barcode.data.repository.data.DataRepositoryImpl;
import com.scan.barcode.data.repository.user.UserRepository;
import com.scan.barcode.data.repository.user.UserRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * @author ndn
 * Created by ndn
 * Created on 5/16/18.
 */
@Module
public abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract DataRepository bindsDataRepository(DataRepositoryImpl impl);

    @Singleton
    @Binds
    abstract UserRepository bindsUserRepository(UserRepositoryImpl impl);
}