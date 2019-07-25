/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.api.utils;

import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ndn
 * Create by ndn
 * Create on 15.05.2018
 */

public class ServiceGenerator {

    private static final boolean release = false;

    public static final String HOST = "http://regx-test.entwurfsansicht.de";
    public static final String HOST_DEV = "http://regx-test.entwurfsansicht.de";

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(release ? HOST : HOST_DEV)
            .addCallAdapterFactory(new LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson));

    private static Retrofit retrofit;

    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.HEADERS);

    private ServiceGenerator() {

    }

    /**
     * Create default service
     *
     * @param serviceClass {@link Class} service class type
     * @param <S>          Service Class
     * @return Service class
     */
    public static <S> S createService(Class<S> serviceClass) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        });
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        return retrofit.create(serviceClass);
    }
}
