/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.di;

import android.app.Application;

import com.scan.barcode.data.api.ApiModule;
import com.scan.barcode.data.cache.CacheModule;
import com.scan.barcode.data.repository.RepositoryModule;
import com.scan.barcode.di.activity.ActivityBindingModule;
import com.scan.barcode.di.viewmodel.ViewModelModule;
import com.scan.barcode.presentation.ScanApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by ndngan
 * Created on 4/6/18.
 */
@Singleton
@Component(modules = {
        ApiModule.class,
        CacheModule.class,
        RepositoryModule.class,
        ViewModelModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<ScanApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}