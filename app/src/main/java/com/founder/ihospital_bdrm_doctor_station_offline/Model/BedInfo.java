package com.founder.ihospital_bdrm_doctor_station_offline.Model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：呼延 on 2016/5/5 15:02
 * 邮箱：huyanyx@founder.com.cn
 */
public class BedInfo {


    /**
     * Status : 1
     * Values : [{"CaseNO":"","BedNO":"001","PatientNO":"001521383000","Series":"5","MRN":"","Name":"","BedStatus":"有人","PlanWardName":"","BedPrice":"35","DeptCode":"1000066","DeptName":"肾内科病房","WardCode":"1000068","WardName":"10C病区","BedType":"1","BedTypeName":"开放","Pipe":"","Monitor":"","Window":""},{"CaseNO":"","BedNO":"004","PatientNO":"001537942200","Series":"1","MRN":"","Name":"","BedStatus":"有人","PlanWardName":"","BedPrice":"70","DeptCode":"1000066","DeptName":"肾内科病房","WardCode":"1000068","WardName":"10C病区","BedType":"1","BedTypeName":"开放","Pipe":"","Monitor":"","Window":""},{"CaseNO":"","BedNO":"010","PatientNO":"001535160600","Series":"1","MRN":"","Name":"","BedStatus":"有人","PlanWardName":"","BedPrice":"35","DeptCode":"1000066","DeptName":"肾内科病房","WardCode":"1000068","WardName":"10C病区","BedType":"1","BedTypeName":"开放","Pipe":"","Monitor":"","Window":""},{"CaseNO":"","BedNO":"015","PatientNO":"000011052300","Series":"5","MRN":"","Name":"","BedStatus":"有人","PlanWardName":"","BedPrice":"70","DeptCode":"1000066","DeptName":"肾内科病房","WardCode":"1000068","WardName":"10C病区","BedType":"1","BedTypeName":"开放","Pipe":"","Monitor":"","Window":""},{"CaseNO":"","BedNO":"019","PatientNO":"","Series":"0","MRN":"","Name":"","BedStatus":"空闲","PlanWardName":"","BedPrice":"35","DeptCode":"1000066","DeptName":"肾内科病房","WardCode":"1000068","WardName":"10C病区","BedType":"1","BedTypeName":"开放","Pipe":"","Monitor":"","Window":""},{"CaseNO":"","BedNO":"020","PatientNO":"","Series":"","MRN":"","Name":"","BedStatus":"空闲","PlanWardName":"","BedPrice":"35","DeptCode":"1000066","DeptName":"肾内科病房","WardCode":"1000068","WardName":"10C病区","BedType":"1","BedTypeName":"开放","Pipe":"","Monitor":"","Window":""},{"CaseNO":"","BedNO":"023","PatientNO":"001409506500","Series":"2","MRN":"","Name":"","BedStatus":"有人","PlanWardName":"","BedPrice":"35","DeptCode":"1000066","DeptName":"肾内科病房","WardCode":"1000068","WardName":"10C病区","BedType":"1","BedTypeName":"开放","Pipe":"","Monitor":"","Window":""}]
     */

    private int Status;
    /**
     * CaseNO :
     * BedNO : 001
     * PatientNO : 001521383000
     * Series : 5
     * MRN :
     * Name :
     * BedStatus : 有人
     * PlanWardName :
     * BedPrice : 35
     * DeptCode : 1000066
     * DeptName : 肾内科病房
     * WardCode : 1000068
     * WardName : 10C病区
     * BedType : 1
     * BedTypeName : 开放
     * Pipe :
     * Monitor :
     * Window :
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

    public static class ValuesBean implements Serializable{
        private String CaseNO;
        private String BedNO;
        private String PatientNO;
        private String Series;
        private String MRN;
        private String Name;
        private String BedStatus;
        private String PlanWardName;
        private String BedPrice;
        private String DeptCode;
        private String DeptName;
        private String WardCode;
        private String WardName;
        private String BedType;
        private String BedTypeName;
        private String Pipe;
        private String Monitor;
        private String Window;

        public String getCaseNO() {
            return CaseNO;
        }

        public void setCaseNO(String CaseNO) {
            this.CaseNO = CaseNO;
        }

        public String getBedNO() {
            return BedNO;
        }

        public void setBedNO(String BedNO) {
            this.BedNO = BedNO;
        }

        public String getPatientNO() {
            return PatientNO;
        }

        public void setPatientNO(String PatientNO) {
            this.PatientNO = PatientNO;
        }

        public String getSeries() {
            return Series;
        }

        public void setSeries(String Series) {
            this.Series = Series;
        }

        public String getMRN() {
            return MRN;
        }

        public void setMRN(String MRN) {
            this.MRN = MRN;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getBedStatus() {
            return BedStatus;
        }

        public void setBedStatus(String BedStatus) {
            this.BedStatus = BedStatus;
        }

        public String getPlanWardName() {
            return PlanWardName;
        }

        public void setPlanWardName(String PlanWardName) {
            this.PlanWardName = PlanWardName;
        }

        public String getBedPrice() {
            return BedPrice;
        }

        public void setBedPrice(String BedPrice) {
            this.BedPrice = BedPrice;
        }

        public String getDeptCode() {
            return DeptCode;
        }

        public void setDeptCode(String DeptCode) {
            this.DeptCode = DeptCode;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public String getWardCode() {
            return WardCode;
        }

        public void setWardCode(String WardCode) {
            this.WardCode = WardCode;
        }

        public String getWardName() {
            return WardName;
        }

        public void setWardName(String WardName) {
            this.WardName = WardName;
        }

        public String getBedType() {
            return BedType;
        }

        public void setBedType(String BedType) {
            this.BedType = BedType;
        }

        public String getBedTypeName() {
            return BedTypeName;
        }

        public void setBedTypeName(String BedTypeName) {
            this.BedTypeName = BedTypeName;
        }

        public String getPipe() {
            return Pipe;
        }

        public void setPipe(String Pipe) {
            this.Pipe = Pipe;
        }

        public String getMonitor() {
            return Monitor;
        }

        public void setMonitor(String Monitor) {
            this.Monitor = Monitor;
        }

        public String getWindow() {
            return Window;
        }

        public void setWindow(String Window) {
            this.Window = Window;
        }
    }
}
