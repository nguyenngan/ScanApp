/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.login;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.scan.barcode.data.common.Resource;
import com.scan.barcode.data.entities.User;
import com.scan.barcode.data.repository.user.UserRepository;
import com.scan.barcode.viewmodel.AbsentLiveData;

import javax.inject.Inject;

/**
 * @author ndn
 * Created by ndn
 * Created on 6/8/18.
 */
public class LoginViewModel extends ViewModel {

    private MutableLiveData<User> user = new MutableLiveData<>();
    private LiveData<Resource<User>> userResponse;

    private LiveData<User> cacheUser;

    @Inject
    LoginViewModel(UserRepository userRepository) {
        userResponse = Transformations.switchMap(user, input -> {
            if (input == null) {
                return AbsentLiveData.create();
            }
            return userRepository.login(input);
        });

        cacheUser = userRepository.getUser();
    }

    public LiveData<Resource<User>> getUserResponse() {
        return userResponse;
    }

    public void setUser(User user) {
        this.user.setValue(user);
    }

    public LiveData<User> getCacheUser() {
        return cacheUser;
    }
}
