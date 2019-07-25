/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.api.user;

import android.arch.lifecycle.LiveData;

import com.scan.barcode.data.api.utils.ApiResponse;
import com.scan.barcode.data.api.utils.ServiceGenerator;
import com.scan.barcode.data.common.Resource;
import com.scan.barcode.data.entities.Body;
import com.scan.barcode.data.entities.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author ndn
 * Created by ndn
 * Created on 2019-07-16.
 */
@Singleton
public class UserRestApiImpl implements UserRestApi {

    private UserService service;

    @Inject
    UserRestApiImpl() {
        service = ServiceGenerator.createService(UserService.class);
    }

    @Override
    public LiveData<ApiResponse<List<User>>> updateUsers(Body body) {
        return service.updateUsers(body);
    }
}
