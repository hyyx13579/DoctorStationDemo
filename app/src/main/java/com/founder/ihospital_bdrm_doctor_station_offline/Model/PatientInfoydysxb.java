package com.founder.ihospital_bdrm_doctor_station_offline.Model;

import java.util.List;

/**
 * Created by hyyx on 16/8/15.
 */
public class PatientInfoydysxb {


    /**
     * Status : 1
     * Values : [{"Inpatient_No":"123","Patient_Sn":"123"},{"Inpatient_No":"456","Patient_Sn":"456"}]
     */

    private int Status;
    /**
     * Inpatient_No : 123
     * Patient_Sn : 123
     */

    private List<ValuesBean> Values;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public List<ValuesBean> getValues() {
        return Values;
    }

    public void setValues(List<ValuesBean> Values) {
        this.Values = Values;
    }

    public static class ValuesBean {
        private String Inpatient_No;
        private String Patient_Sn;

        public String getInpatient_No() {
            return Inpatient_No;
        }

        public void setInpatient_No(String Inpatient_No) {
            this.Inpatient_No = Inpatient_No;
        }

        public String getPatient_Sn() {
            return Patient_Sn;
        }

        public void setPatient_Sn(String Patient_Sn) {
            this.Patient_Sn = Patient_Sn;
        }
    }
}
