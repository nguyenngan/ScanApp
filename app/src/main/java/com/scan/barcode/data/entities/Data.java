/*
 * Copyright (c).
 * TT-Technologies IT-Service GmbH
 */

package com.scan.barcode.data.entities;

import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(primaryKeys = "username")
public class Data implements Parcelable {

    @NonNull
    private String username = "";
    private String barcode;
    private String qr1St;
    private String qr2nd;
    // Format time "EEE, d MMM yyyy HH:mm:ss"	Wed, 4 Jul 2001 12:08:56
    private String time;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getQr1St() {
        return qr1St;
    }

    public void setQr1St(String qr1St) {
        this.qr1St = qr1St;
    }

    public String getQr2nd() {
        return qr2nd;
    }

    public void setQr2nd(String qr2nd) {
        this.qr2nd = qr2nd;
    }

    public String getTime() {
        return time;
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
        dest.writeString(this.username);
        dest.writeString(this.barcode);
        dest.writeString(this.qr1St);
        dest.writeString(this.qr2nd);
        dest.writeString(this.time);
    }

    public Data() {
    }

    protected Data(Parcel in) {
        this.username = in.readString();
        this.barcode = in.readString();
        this.qr1St = in.readString();
        this.qr2nd = in.readString();
        this.time = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
