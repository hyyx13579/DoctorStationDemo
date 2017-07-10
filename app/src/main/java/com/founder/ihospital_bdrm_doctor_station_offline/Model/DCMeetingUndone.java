package com.founder.ihospital_bdrm_doctor_station_offline.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangqun on 16/4/26.
 */
public class DCMeetingUndone {
    /**
     * Status : 1
     * Values : [{"TotalCount":"","PageSize":"","PageNo":"","ConsultationID":"2904","ConsultationCode":"102906","PatientID":"001539207600","Series":"1","InpatientNo":"4237819","Name":"苏苏","WardCode":"10C病区","WardName":"10C病区","DeptCode":"120000000","DeptName":"消化内科","BedNO":"002","ConRequestDate":"2016-04-25 11:09:14.0","ConRequestDoc":"984","ConRequestDocName":"本院医生","SignDocCode":"","SignDoc":"","SignDate":"","SignStatus":"","SignType":"","SignNotes":"","ReservPlace":"10C病区002床","Priority":"0","ConsultationType":"1","PatientDesc":"敬悉病史：\r\n\r\n\r\n\r\n\r\n           敬邀！","Place":"","Purpose":"","ConsultationOpinion":""},{"TotalCount":"","PageSize":"","PageNo":"","ConsultationID":"2903","ConsultationCode":"102905","PatientID":"001521383000","Series":"5","InpatientNo":"4232682","Name":"李亚珍","WardCode":"10C病区","WardName":"10C病区","DeptCode":"120000000","DeptName":"消化内科","BedNO":"001","ConRequestDate":"2016-04-25 11:08:03.0","ConRequestDoc":"984","ConRequestDocName":"本院医生","SignDocCode":"","SignDoc":"","SignDate":"","SignStatus":"","SignType":"","SignNotes":"","ReservPlace":"10C病区001床","Priority":"0","ConsultationType":"1","PatientDesc":"敬悉病史：\r\n\r\n\r\n\r\n\r\n           敬邀！","Place":"","Purpose":"","ConsultationOpinion":""}]
     */

    private int Status;
    /**
     * TotalCount :
     * PageSize :
     * PageNo :
     * ConsultationID : 2904
     * ConsultationCode : 102906
     * PatientID : 001539207600
     * Series : 1
     * InpatientNo : 4237819
     * Name : 苏苏
     * WardCode : 10C病区
     * WardName : 10C病区
     * DeptCode : 120000000
     * DeptName : 消化内科
     * BedNO : 002
     * ConRequestDate : 2016-04-25 11:09:14.0
     * ConRequestDoc : 984
     * ConRequestDocName : 本院医生
     * SignDocCode :
     * SignDoc :
     * SignDate :
     * SignStatus :
     * SignType :
     * SignNotes :
     * ReservPlace : 10C病区002床
     * Priority : 0
     * ConsultationType : 1
     * PatientDesc : 敬悉病史：




     敬邀！
     * Place :
     * Purpose :
     * ConsultationOpinion :
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
        private String TotalCount;
        private String PageSize;
        private String PageNo;
        private String ConsultationID;
        private String ConsultationCode;
        private String PatientID;
        private String Series;
        private String InpatientNo;
        private String Name;
        private String WardCode;
        private String WardName;
        private String DeptCode;
        private String DeptName;
        private String BedNO;
        private String ConRequestDate;
        private String ConRequestDoc;
        private String ConRequestDocName;
        private String SignDocCode;
        private String SignDoc;
        private String SignDate;
        private String SignStatus;
        private String SignType;
        private String SignNotes;
        private String ReservPlace;
        private String Priority;
        private String ConsultationType;
        private String PatientDesc;
        private String Place;
        private String Purpose;
        private String ConsultationOpinion;
        private String VisitSn;



        public String getVisitSn() {
            return VisitSn;
        }

        public void setVisitSn(String VisitSn) {
            this.VisitSn = VisitSn;
        }


        public String getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(String TotalCount) {
            this.TotalCount = TotalCount;
        }

        public String getPageSize() {
            return PageSize;
        }

        public void setPageSize(String PageSize) {
            this.PageSize = PageSize;
        }

        public String getPageNo() {
            return PageNo;
        }

        public void setPageNo(String PageNo) {
            this.PageNo = PageNo;
        }

        public String getConsultationID() {
            return ConsultationID;
        }

        public void setConsultationID(String ConsultationID) {
            this.ConsultationID = ConsultationID;
        }

        public String getConsultationCode() {
            return ConsultationCode;
        }

        public void setConsultationCode(String ConsultationCode) {
            this.ConsultationCode = ConsultationCode;
        }

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

        public String getInpatientNo() {
            return InpatientNo;
        }

        public void setInpatientNo(String InpatientNo) {
            this.InpatientNo = InpatientNo;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
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

        public String getBedNO() {
            return BedNO;
        }

        public void setBedNO(String BedNO) {
            this.BedNO = BedNO;
        }

        public String getConRequestDate() {
            return ConRequestDate;
        }

        public void setConRequestDate(String ConRequestDate) {
            this.ConRequestDate = ConRequestDate;
        }

        public String getConRequestDoc() {
            return ConRequestDoc;
        }

        public void setConRequestDoc(String ConRequestDoc) {
            this.ConRequestDoc = ConRequestDoc;
        }

        public String getConRequestDocName() {
            return ConRequestDocName;
        }

        public void setConRequestDocName(String ConRequestDocName) {
            this.ConRequestDocName = ConRequestDocName;
        }

        public String getSignDocCode() {
            return SignDocCode;
        }

        public void setSignDocCode(String SignDocCode) {
            this.SignDocCode = SignDocCode;
        }

        public String getSignDoc() {
            return SignDoc;
        }

        public void setSignDoc(String SignDoc) {
            this.SignDoc = SignDoc;
        }

        public String getSignDate() {
            return SignDate;
        }

        public void setSignDate(String SignDate) {
            this.SignDate = SignDate;
        }

        public String getSignStatus() {
            return SignStatus;
        }

        public void setSignStatus(String SignStatus) {
            this.SignStatus = SignStatus;
        }

        public String getSignType() {
            return SignType;
        }

        public void setSignType(String SignType) {
            this.SignType = SignType;
        }

        public String getSignNotes() {
            return SignNotes;
        }

        public void setSignNotes(String SignNotes) {
            this.SignNotes = SignNotes;
        }

        public String getReservPlace() {
            return ReservPlace;
        }

        public void setReservPlace(String ReservPlace) {
            this.ReservPlace = ReservPlace;
        }

        public String getPriority() {
            return Priority;
        }

        public void setPriority(String Priority) {
            this.Priority = Priority;
        }

        public String getConsultationType() {
            return ConsultationType;
        }

        public void setConsultationType(String ConsultationType) {
            this.ConsultationType = ConsultationType;
        }

        public String getPatientDesc() {
            return PatientDesc;
        }

        public void setPatientDesc(String PatientDesc) {
            this.PatientDesc = PatientDesc;
        }

        public String getPlace() {
            return Place;
        }

        public void setPlace(String Place) {
            this.Place = Place;
        }

        public String getPurpose() {
            return Purpose;
        }

        public void setPurpose(String Purpose) {
            this.Purpose = Purpose;
        }

        public String getConsultationOpinion() {
            return ConsultationOpinion;
        }

        public void setConsultationOpinion(String ConsultationOpinion) {
            this.ConsultationOpinion = ConsultationOpinion;
        }
    }
}
