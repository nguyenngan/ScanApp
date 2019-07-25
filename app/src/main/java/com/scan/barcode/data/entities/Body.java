/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author ndn
 * Created by ndn
 * Created on 2019-07-25.
 */
public class Body implements Parcelable {

    @Expose
    @SerializedName("auth_name")
    private String authName;

    @Expose
    @SerializedName("password")
    private String password;

    @Expose
    @SerializedName("username")
    private String username;

    @Expose
    @SerializedName("barcode")
    private String barcode;

    @Expose
    @SerializedName("qr1St")
    private String qr1St;

    @Expose
    @SerializedName("qr2nd")
    private String qr2nd;

    @Expose
    @SerializedName("time")
    private String time;

    public Body() {
    }

    public Body(String authName, String password) {
        this.authName = authName;
        this.password = password;
    }

    public Body(String username, String barcode, String qr1St, String qr2nd, String time) {
        this.username = username;
        this.barcode = barcode;
        this.qr1St = qr1St;
        this.qr2nd = qr2nd;
        this.time = time;
    }

    public Body(@NonNull Data data) {
        this.username = data.getUsername();
        this.barcode = data.getBarcode();
        this.qr1St = data.getQr1St();
        this.qr2nd = data.getQr2nd();
        this.time = data.getTime();
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setQr1St(String qr1St) {
        this.qr1St = qr1St;
    }

    public void setQr2nd(String qr2nd) {
        this.qr2nd = qr2nd;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.authName);
        dest.writeString(this.password);
        dest.writeString(this.username);
        dest.writeString(this.barcode);
        dest.writeString(this.qr1St);
        dest.writeString(this.qr2nd);
        dest.writeString(this.time);
    }

    public Body(Parcel in) {
        this.authName = in.readString();
        this.password = in.readString();
        this.username = in.readString();
        this.barcode = in.readString();
        this.qr1St = in.readString();
        this.qr2nd = in.readString();
        this.time = in.readString();
    }

    public static final Creator<Body> CREATOR = new Creator<Body>() {
        @Override
        public Body createFromParcel(Parcel source) {
            return new Body(source);
        }

        @Override
        public Body[] newArray(int size) {
            return new Body[size];
        }
    };
}
