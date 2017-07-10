package com.founder.ihospital_bdrm_doctor_station_offline.Model;

/**
 * 作者：呼延 on 2016/5/9 15:05
 * 邮箱：huyanyx@founder.com.cn
 */
public class AllocationPatientBed {


    /**
     * Status : 0
     * Values : Server was unable to process request. ---> 不存在您录入的“计划入院的院区、病区、科室对应”，请修改！
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
