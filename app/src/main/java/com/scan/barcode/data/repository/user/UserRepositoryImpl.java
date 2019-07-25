/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.repository.user;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.gson.Gson;
import com.scan.barcode.data.api.user.UserRestApi;
import com.scan.barcode.data.api.utils.ApiResponse;
import com.scan.barcode.data.cache.AppDb;
import com.scan.barcode.data.common.Resource;
import com.scan.barcode.data.entities.Body;
import com.scan.barcode.data.entities.User;
import com.scan.barcode.data.executor.AppExecutors;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.scan.barcode.data.common.StatusNetwork.ERROR;

@Singleton
public class UserRepositoryImpl implements UserRepository {

    private final AppDb appDb;
    private final UserRestApi restApi;
    private final AppExecutors appExecutors;

    private Body body = new Body("regx-app", "8ecVZORdfkjt");

    @Inject
    UserRepositoryImpl(AppDb appDb, UserRestApi restApi, AppExecutors appExecutors) {
        this.appDb = appDb;
        this.restApi = restApi;
        this.appExecutors = appExecutors;
    }

    @Override
    public LiveData<Resource<User>> login(User user) {
        MediatorLiveData<Resource<User>> result = new MediatorLiveData<>();
        result.setValue(Resource.loading(null));
        LiveData<ApiResponse<List<User>>> response = restApi.updateUsers(body);
        result.addSource(response, resource -> {
            if (resource == null) {
                result.setValue(Resource.error(ERROR, "Response null", null));
            } else if (resource.isSuccessful() && resource.body != null) {
                List<User> userList = resource.body;
                if (isExits(userList, input -> Objects.equal(user, input))) {
                    result.setValue(Resource.success(user));
                } else {
                    result.setValue(Resource.error(ERROR, "Response null", null));
                }
            } else {
                result.setValue(Resource.error(resource.code, resource.errorMessage, null));
            }
        });
        return result;
    }

    private static <E> boolean isExits(@NonNull List<E> eList, @NonNull Predicate<E> ePredicate) {
        int index = getPosition(eList, ePredicate);
        return index >= 0 && index < eList.size();
    }

    private static <E> int getPosition(@NonNull List<E> eList, @NonNull Predicate<E> ePredicate) {
        if (!eList.isEmpty()) {
            return Iterators.indexOf(eList.iterator(), ePredicate);
        }
        return -1;
    }
}
