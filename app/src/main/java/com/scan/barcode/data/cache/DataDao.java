/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.cache;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.scan.barcode.data.entities.Data;

import java.util.List;

/**
 * @author ndn
 * Created by ndn
 * Created on 7/26/18
 */
@Dao
public interface DataDao {

    /**
     * Insert all Data to database cache when load Data from api
     *
     * @param data {@link Data}
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Data... data);

    /**
     * Insert all Data to database cache when load Data from api
     *
     * @param dataEntities {@link Data}
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Data> dataEntities);

    /**
     * Get all Data from database cache
     *
     * @return List {@link Data}
     */
    @Query("SELECT * FROM data")
    LiveData<List<Data>> getData();

    /**
     * Remove all Data from database cache
     */
    @Query("DELETE FROM data")
    void emptyData();
}
