/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.cache;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author ndn
 * Created by ndn
 * Created on 5/16/18.
 */
@Module
public abstract class CacheModule {

    private static final String DB_NAME = "scan.db";

    @Singleton
    @Provides
    static AppDb provideDb(Application app) {
        return Room.databaseBuilder(app, AppDb.class, DB_NAME)
                // Xoa het data cache tren device
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    static DataDao provideDataDao(AppDb db) {
        return db.dataDao();
    }

    @Singleton
    @Provides
    static UserDao provideUserDao(AppDb db) {
        return db.userDao();
    }
}
