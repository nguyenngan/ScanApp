/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.repository.user;

import android.arch.lifecycle.LiveData;

import com.scan.barcode.data.common.Resource;
import com.scan.barcode.data.entities.User;

public interface UserRepository {

    LiveData<Resource<User>> login(User user);

    void saveUser(User user);

    LiveData<User> getUser();

    void emptyUser();
}
