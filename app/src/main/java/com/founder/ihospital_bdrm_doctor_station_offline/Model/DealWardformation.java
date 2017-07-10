package com.founder.ihospital_bdrm_doctor_station_offline.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：呼延 on 2016/4/29 10:48
 * 邮箱：huyanyx@founder.com.cn
 */
public class DealWardformation implements Parcelable {


    private  String deptName;
    private String deptCode;
    private String wardName;
    private String wardCode;

    public DealWardformation(String deptName, String deptCode, String wardName, String wardCode) {
        this.deptName = deptName;
        this.deptCode = deptCode;
        this.wardName = wardName;
        this.wardCode = wardCode;
    }

    protected DealWardformation(Parcel in) {
        deptName = in.readString();
        deptCode = in.readString();
        wardName = in.readString();
        wardCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(deptName);
        dest.writeString(deptCode);
        dest.writeString(wardName);
        dest.writeString(wardCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DealWardformation> CREATOR = new Creator<DealWardformation>() {
        @Override
        public DealWardformation createFromParcel(Parcel in) {
            return new DealWardformation(in);
        }

        @Override
        public DealWardformation[] newArray(int size) {
            return new DealWardformation[size];
        }
    };

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }



}
