/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.cache;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.scan.barcode.data.entities.Data;
import com.scan.barcode.data.entities.User;

/**
 * Main database description.
 */
@Database(entities = {
        Data.class,
        User.class},
        version = 1,
        exportSchema = true)
public abstract class AppDb extends RoomDatabase {

    abstract public UserDao userDao();

    abstract public DataDao dataDao();
}