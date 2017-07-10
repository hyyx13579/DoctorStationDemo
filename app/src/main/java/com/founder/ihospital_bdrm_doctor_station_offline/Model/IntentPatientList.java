package com.founder.ihospital_bdrm_doctor_station_offline.Model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：呼延 on 2016/5/3 17:38
 * 邮箱：huyanyx@founder.com.cn
 */
public class IntentPatientList {


    /**
     * Status : 1
     * Values : [{"PatientID":"000000152300","Series":"228","InpatientPermitID":"","OutpatientID":"00001523","Name":"贾晓君","PlanDeptCode":"1000003","PlanDeptName":"心血管内科病房","PlanWardCode":"1000632","PlanWardName":"19A病区","Sex":"女","Age":"47岁","Identity":"110108196902022844","InpatientPermitDate":"2015-08-19T18:28:41","OutDiagnosis":"手术","Deposit":10,"AdtDeptNo":"1"},{"PatientID":"001539177600","Series":"8","InpatientPermitID":"","OutpatientID":"0015391776","Name":"徐颖","PlanDeptCode":"1000003","PlanDeptName":"心血管内科病房","PlanWardCode":"1000665","PlanWardName":"5B病区","Sex":"男","Age":"36岁","Identity":"110101198001010096","InpatientPermitDate":"2015-06-24T13:22:43","OutDiagnosis":"wwwwwwww","Deposit":0,"AdtDeptNo":"1"},{"PatientID":"001539205900","Series":"229","InpatientPermitID":"","OutpatientID":"0015392059","Name":"换号1","PlanDeptCode":"1000003","PlanDeptName":"心血管内科病房","PlanWardCode":"1000005","PlanWardName":"4A病区","Sex":"男","Age":"27岁","Identity":"110106198901011111","InpatientPermitDate":"2015-11-10T10:30:49","OutDiagnosis":"检查","Deposit":10,"AdtDeptNo":"1"},{"PatientID":"001546212800","Series":"3","InpatientPermitID":"","OutpatientID":"02497619","Name":"韩岗","PlanDeptCode":"1000003","PlanDeptName":"心血管内科病房","PlanWardCode":"1000005","PlanWardName":"4A病区","Sex":"男","Age":"22岁","Identity":"110228199402153215","InpatientPermitDate":"2015-10-28T09:13:19","OutDiagnosis":"11个月","Deposit":12000,"AdtDeptNo":"1"}]
     */

    private int Status;
    /**
     * PatientID : 000000152300
     * Series : 228
     * InpatientPermitID :
     * OutpatientID : 00001523
     * Name : 贾晓君
     * PlanDeptCode : 1000003
     * PlanDeptName : 心血管内科病房
     * PlanWardCode : 1000632
     * PlanWardName : 19A病区
     * Sex : 女
     * Age : 47岁
     * Identity : 110108196902022844
     * InpatientPermitDate : 2015-08-19T18:28:41
     * OutDiagnosis : 手术
     * Deposit : 10
     * AdtDeptNo : 1
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

    public static class ValuesBean implements Serializable {
        private String PatientID;
        private String Series;
        private String InpatientPermitID;
        private String OutpatientID;
        private String Name;
        private String PlanDeptCode;
        private String PlanDeptName;
        private String PlanWardCode;
        private String PlanWardName;
        private String Sex;
        private String Age;
        private String Identity;
        private String InpatientPermitDate;
        private String OutDiagnosis;
        private int Deposit;
        private String AdtDeptNo;

        public String getPatientID() {
            return PatientID;
        }

        public void setPatientID(String PatientID) {
            this.PatientID = PatientID;
        }

        public String getSeries() {
            return Series;
        }

        public void setSeries(String Series) {
            this.Series = Series;
        }

        public String getInpatientPermitID() {
            return InpatientPermitID;
        }

        public void setInpatientPermitID(String InpatientPermitID) {
            this.InpatientPermitID = InpatientPermitID;
        }

        public String getOutpatientID() {
            return OutpatientID;
        }

        public void setOutpatientID(String OutpatientID) {
            this.OutpatientID = OutpatientID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPlanDeptCode() {
            return PlanDeptCode;
        }

        public void setPlanDeptCode(String PlanDeptCode) {
            this.PlanDeptCode = PlanDeptCode;
        }

        public String getPlanDeptName() {
            return PlanDeptName;
        }

        public void setPlanDeptName(String PlanDeptName) {
            this.PlanDeptName = PlanDeptName;
        }

        public String getPlanWardCode() {
            return PlanWardCode;
        }

        public void setPlanWardCode(String PlanWardCode) {
            this.PlanWardCode = PlanWardCode;
        }

        public String getPlanWardName() {
            return PlanWardName;
        }

        public void setPlanWardName(String PlanWardName) {
            this.PlanWardName = PlanWardName;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public String getAge() {
            return Age;
        }

        public void setAge(String Age) {
            this.Age = Age;
        }

        public String getIdentity() {
            return Identity;
        }

        public void setIdentity(String Identity) {
            this.Identity = Identity;
        }

        public String getInpatientPermitDate() {
            return InpatientPermitDate;
        }

        public void setInpatientPermitDate(String InpatientPermitDate) {
            this.InpatientPermitDate = InpatientPermitDate;
        }

        public String getOutDiagnosis() {
            return OutDiagnosis;
        }

        public void setOutDiagnosis(String OutDiagnosis) {
            this.OutDiagnosis = OutDiagnosis;
        }

        public int getDeposit() {
            return Deposit;
        }

        public void setDeposit(int Deposit) {
            this.Deposit = Deposit;
        }

        public String getAdtDeptNo() {
            return AdtDeptNo;
        }

        public void setAdtDeptNo(String AdtDeptNo) {
            this.AdtDeptNo = AdtDeptNo;
        }
    }
}
