/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.api.user;

import android.arch.lifecycle.LiveData;

import com.scan.barcode.data.api.utils.ApiResponse;
import com.scan.barcode.data.entities.User;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author ndn
 * Created by ndn
 * Created on 2019-07-16.
 */
public interface UserService {

    /**
     * Update list user from server
     * @param body {@link com.scan.barcode.data.entities.Body}
     * @return list {@link User}
     */
    @POST("api-get-users.php")
    LiveData<ApiResponse<List<User>>> updateUsers(@Body com.scan.barcode.data.entities.Body body);
}
