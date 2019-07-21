/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.presentation.qr2nd;

import android.arch.lifecycle.ViewModel;

import com.scan.barcode.data.repository.user.UserRepository;

import javax.inject.Inject;

/**
 * @author ndn
 * Created by ndn
 * Created on 6/8/18.
 */
public class Qr2ndViewModel extends ViewModel {

    private final UserRepository userRepository;

    @Inject
    Qr2ndViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
