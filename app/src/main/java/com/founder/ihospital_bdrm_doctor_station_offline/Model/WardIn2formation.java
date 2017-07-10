package com.founder.ihospital_bdrm_doctor_station_offline.Model;

import java.io.Serializable;

/**
 * 作者：呼延 on 2016/4/29 10:48
 * 邮箱：huyanyx@founder.com.cn
 */
public class WardIn2formation  implements Serializable{

    private String DeptName;
    private String DeptCode;
    private String WardName;
    private String WardCode;


    public WardIn2formation() {

    }

    public WardIn2formation(String deptName, String deptCode, String wardCode, String wardName) {
        DeptName = deptName;
        DeptCode = deptCode;
        WardCode = wardCode;
        WardName = wardName;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    public String getDeptCode() {
        return DeptCode;
    }

    public void setDeptCode(String deptCode) {
        DeptCode = deptCode;
    }

    public String getWardName() {
        return WardName;
    }

    public void setWardName(String wardName) {
        WardName = wardName;
    }

    public String getWardCode() {
        return WardCode;
    }

    public void setWardCode(String wardCode) {
        WardCode = wardCode;
    }
}
