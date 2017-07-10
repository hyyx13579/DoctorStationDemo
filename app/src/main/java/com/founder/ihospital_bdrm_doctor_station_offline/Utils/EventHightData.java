package com.founder.ihospital_bdrm_doctor_station_offline.Utils;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;

import java.util.List;

/**
 * Created by 呼延 on 2016/3/29.
 */
public class EventHightData {


    public List<DCPatientHROfDepartment.ValuesBean> getData() {
        return data;
    }

    public void setData(List<DCPatientHROfDepartment.ValuesBean> data) {
        this.data = data;
    }

    private List<DCPatientHROfDepartment.ValuesBean> data;

    public EventHightData(List<DCPatientHROfDepartment.ValuesBean> data) {
        this.data = data;
    }


}
