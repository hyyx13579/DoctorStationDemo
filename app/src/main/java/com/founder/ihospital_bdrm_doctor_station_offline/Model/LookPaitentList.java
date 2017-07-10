package com.founder.ihospital_bdrm_doctor_station_offline.Model;

import java.util.List;

/**
 * Created by hyyx on 16/8/25.
 */
public class LookPaitentList {


    /**
     * Status : 1
     * Values : [{"DeptCode":"","BedNo":"001","PatientName":"周平生","Diagnosis":"肝硬化 ","Sex":"男","DoctorID":"09264","VisitSn":"30551731","DeptName":"肝病科病房"},{"DeptCode":"1000076","BedNo":"028","PatientName":"肖汉林","Diagnosis":"低钠血症","Sex":"男","DoctorID":"09264","VisitSn":"30526147","DeptName":"内分泌科病房"},{"DeptCode":"1000076","BedNo":"026","PatientName":"耿岩","Diagnosis":"2型糖尿病","Sex":"女","DoctorID":"09264","VisitSn":"30566476","DeptName":"内分泌科病房"},{"DeptCode":"1000076","BedNo":"019","PatientName":"张瑛","Diagnosis":"糖尿病","Sex":"男","DoctorID":"09264","VisitSn":"30552761","DeptName":"内分泌科病房"},{"DeptCode":"1000058","BedNo":"023","PatientName":"梁妍","Diagnosis":"系统性红斑狼疮","Sex":"女","DoctorID":"09264","VisitSn":"30607084","DeptName":"风湿免疫内科病房"},{"DeptCode":"1000058","BedNo":"019","PatientName":"卢淑兰","Diagnosis":"类风湿关节炎","Sex":"女","DoctorID":"09264","VisitSn":"30507279","DeptName":"风湿免疫内科病房"},{"DeptCode":"1000058","BedNo":"003","PatientName":"宋秀丽","Diagnosis":"系统性红斑狼疮","Sex":"女","DoctorID":"09264","VisitSn":"30620489","DeptName":"风湿免疫内科病房"},{"DeptCode":"1000058","BedNo":"002","PatientName":"郝玉琴","Diagnosis":"血清阴性类风湿","Sex":"女","DoctorID":"09264","VisitSn":"30542514","DeptName":"风湿免疫内科病房"}]
     */

    private int Status;
    /**
     * DeptCode :
     * BedNo : 001
     * PatientName : 周平生
     * Diagnosis : 肝硬化
     * Sex : 男
     * DoctorID : 09264
     * VisitSn : 30551731
     * DeptName : 肝病科病房
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
        private String DeptCode;
        private String BedNo;
        private String PatientName;
        private String Diagnosis;
        private String Sex;
        private String DoctorID;
        private String VisitSn;
        private String DeptName;
        private int IsCollection;

        public int getIsCollection() {
            return IsCollection;
        }

        public void setIsCollection(int isCollection) {
            IsCollection = isCollection;
        }

        public ValuesBean(String deptCode, String bedNo, String patientName, String sex, String doctorID, String visitSn, String deptName
                          ) {
            DeptCode = deptCode;
            BedNo = bedNo;
            PatientName = patientName;
            Sex = sex;
            DoctorID = doctorID;
            VisitSn = visitSn;
            DeptName = deptName;
        }

        public String getDeptCode() {
            return DeptCode;
        }

        public void setDeptCode(String DeptCode) {
            this.DeptCode = DeptCode;
        }

        public String getBedNo() {
            return BedNo;
        }

        public void setBedNo(String BedNo) {
            this.BedNo = BedNo;
        }

        public String getPatientName() {
            return PatientName;
        }

        public void setPatientName(String PatientName) {
            this.PatientName = PatientName;
        }

        public String getDiagnosis() {
            return Diagnosis;
        }

        public void setDiagnosis(String Diagnosis) {
            this.Diagnosis = Diagnosis;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public String getDoctorID() {
            return DoctorID;
        }

        public void setDoctorID(String DoctorID) {
            this.DoctorID = DoctorID;
        }

        public String getVisitSn() {
            return VisitSn;
        }

        public void setVisitSn(String VisitSn) {
            this.VisitSn = VisitSn;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }
    }
}
