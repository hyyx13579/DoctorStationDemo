package com.founder.ihospital_bdrm_doctor_station_offline.Model;

import java.util.List;

/**
 * Created by hyyx on 2016/12/22.
 */

public class NursingInforGruopBean {
    public NursingInforGruopBean(String gruopName, List<DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean> grouoInfo) {
        this.gruopName = gruopName;
        this.grouoInfo = grouoInfo;
    }

    private String gruopName;

    private List<DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean> grouoInfo;

    public String getGruopName() {
        return gruopName;
    }

    public void setGruopName(String gruopName) {
        this.gruopName = gruopName;
    }


    public List<DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean> getGrouoInfo() {
        return grouoInfo;
    }

    public void setGrouoInfo(List<DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean> grouoInfo) {
        this.grouoInfo = grouoInfo;
    }
}
