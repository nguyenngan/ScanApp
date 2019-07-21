/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.api.user;

import com.scan.barcode.data.api.utils.ServiceGenerator;

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
}
