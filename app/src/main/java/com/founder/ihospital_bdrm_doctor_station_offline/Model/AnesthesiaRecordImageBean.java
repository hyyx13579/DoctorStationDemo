package com.founder.ihospital_bdrm_doctor_station_offline.Model;

import java.util.List;

/**
 * Created by bin_li on 16/5/6.
 */
public class AnesthesiaRecordImageBean {

    /**
     * Status : 1
     * Values :
     */

    private int Status;
    private List<String> Values;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public List<String> getValues() {
        return Values;
    }

    public void setValues(List<String> Values) {
        this.Values = Values;
    }
}
