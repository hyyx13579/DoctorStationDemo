package com.founder.ihospital_bdrm_doctor_station_offline.Model;

/**
 * Created by 呼延 on 2016/3/25.
 * 医生登录以后获得数据的model
 */
public class CommonBean {


    /**
     * Status : 0
     * Values : 用户名或密码错误
     */

    private int Status;
    private String Values;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getValues() {
        return Values;
    }

    public void setValues(String Values) {
        this.Values = Values;
    }
}
