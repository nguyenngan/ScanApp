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
import android.arch.persistence.room.Update;

import com.scan.barcode.data.entities.User;

/**
 * @author ndn
 * Created by ndn
 * Created on 6/11/18.
 */
@Dao
public interface UserDao {

    /**
     * Insert user into database
     *
     * @param user user sign in
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    /**
     * Update new data for user
     *
     * @param user new user update
     */
    @Update
    void update(User user);

    /**
     * Get all cache user
     *
     * @return user
     */
    @Query("SELECT * FROM user")
    LiveData<User> findUser();

    /**
     * Remove all user cache
     */
    @Query("DELETE FROM user")
    void emptyUser();
}
