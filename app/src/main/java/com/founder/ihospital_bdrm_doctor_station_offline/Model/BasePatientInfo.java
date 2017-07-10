package com.founder.ihospital_bdrm_doctor_station_offline.Model;

/**
 * Created by hyyx on 16/8/26.
 */
public class BasePatientInfo {


    /**
     * Status : 1
     * Values : {"ID":"29716581","InPatienNo":"01856059","HID":"001633554500","CaseID":"001633554500","SubID":"1","ClinicID":"01856059","DeptCode":"1000654","DeptName":"重症医学科病房","DischargeDeptName":"重症医学科病房","WardName":null,"BedNo":"010","DoctorInCharge":"刘方","NurseInCharge":"龚芳","Diagnosis":"急性呼吸衰竭","Name":"毛元斌","NamePhonetic":null,"Nation":"汉族","Sex":"男","BirthDay":"1967-07-30 00:00:00","Age":"49岁","EnterDate":"2016-05-24 23:25:43","ExitDate":null,"MedicalInsuranceID":null,"IdentityType":"居民身份证","Company":"国资委","Phone":"13331002567","Address":"北京市西城区宣武门西大街26号","ZipCode":"100000","LinkManName":"王健","LinkManRelation":"配偶","LinkManPhone":"13331002567","IdentityNumber":"120104196707306835","Payment":"全公费","CurrentState":"急","CareLevel":"特级护理","PrePayments":null,"TotalCost":null,"TotalPayments":null,"BalanceMoney":null,"InHosDay":93,"IsWaiting":0,"IsCollection":0,"DiagnsisHistoryList":null}
     */

    private int Status;
    /**
     * ID : 29716581
     * InPatienNo : 01856059
     * HID : 001633554500
     * CaseID : 001633554500
     * SubID : 1
     * ClinicID : 01856059
     * DeptCode : 1000654
     * DeptName : 重症医学科病房
     * DischargeDeptName : 重症医学科病房
     * WardName : null
     * BedNo : 010
     * DoctorInCharge : 刘方
     * NurseInCharge : 龚芳
     * Diagnosis : 急性呼吸衰竭
     * Name : 毛元斌
     * NamePhonetic : null
     * Nation : 汉族
     * Sex : 男
     * BirthDay : 1967-07-30 00:00:00
     * Age : 49岁
     * EnterDate : 2016-05-24 23:25:43
     * ExitDate : null
     * MedicalInsuranceID : null
     * IdentityType : 居民身份证
     * Company : 国资委
     * Phone : 13331002567
     * Address : 北京市西城区宣武门西大街26号
     * ZipCode : 100000
     * LinkManName : 王健
     * LinkManRelation : 配偶
     * LinkManPhone : 13331002567
     * IdentityNumber : 120104196707306835
     * Payment : 全公费
     * CurrentState : 急
     * CareLevel : 特级护理
     * PrePayments : null
     * TotalCost : null
     * TotalPayments : null
     * BalanceMoney : null
     * InHosDay : 93
     * IsWaiting : 0
     * IsCollection : 0
     * DiagnsisHistoryList : null
     */

    private ValuesBean Values;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public ValuesBean getValues() {
        return Values;
    }

    public void setValues(ValuesBean Values) {
        this.Values = Values;
    }

    public static class ValuesBean {
        private String ID;
        private String InPatienNo;
        private String HID;
        private String CaseID;
        private String SubID;
        private String ClinicID;
        private String DeptCode;
        private String DeptName;
        private String DischargeDeptName;
        private Object WardName;
        private String BedNo;
        private String DoctorInCharge;
        private String NurseInCharge;
        private String Diagnosis;
        private String Name;
        private Object NamePhonetic;
        private String Nation;
        private String Sex;
        private String BirthDay;
        private String Age;
        private String EnterDate;
        private Object ExitDate;
        private Object MedicalInsuranceID;
        private String IdentityType;
        private String Company;
        private String Phone;
        private String Address;
        private String ZipCode;
        private String LinkManName;
        private String LinkManRelation;
        private String LinkManPhone;
        private String IdentityNumber;
        private String Payment;
        private String CurrentState;
        private String CareLevel;
        private Object PrePayments;
        private Object TotalCost;
        private Object TotalPayments;
        private Object BalanceMoney;
        private int InHosDay;
        private int IsWaiting;
        private int IsCollection;
        private Object DiagnsisHistoryList;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getInPatienNo() {
            return InPatienNo;
        }

        public void setInPatienNo(String InPatienNo) {
            this.InPatienNo = InPatienNo;
        }

        public String getHID() {
            return HID;
        }

        public void setHID(String HID) {
            this.HID = HID;
        }

        public String getCaseID() {
            return CaseID;
        }

        public void setCaseID(String CaseID) {
            this.CaseID = CaseID;
        }

        public String getSubID() {
            return SubID;
        }

        public void setSubID(String SubID) {
            this.SubID = SubID;
        }

        public String getClinicID() {
            return ClinicID;
        }

        public void setClinicID(String ClinicID) {
            this.ClinicID = ClinicID;
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

        public String getDischargeDeptName() {
            return DischargeDeptName;
        }

        public void setDischargeDeptName(String DischargeDeptName) {
            this.DischargeDeptName = DischargeDeptName;
        }

        public Object getWardName() {
            return WardName;
        }

        public void setWardName(Object WardName) {
            this.WardName = WardName;
        }

        public String getBedNo() {
            return BedNo;
        }

        public void setBedNo(String BedNo) {
            this.BedNo = BedNo;
        }

        public String getDoctorInCharge() {
            return DoctorInCharge;
        }

        public void setDoctorInCharge(String DoctorInCharge) {
            this.DoctorInCharge = DoctorInCharge;
        }

        public String getNurseInCharge() {
            return NurseInCharge;
        }

        public void setNurseInCharge(String NurseInCharge) {
            this.NurseInCharge = NurseInCharge;
        }

        public String getDiagnosis() {
            return Diagnosis;
        }

        public void setDiagnosis(String Diagnosis) {
            this.Diagnosis = Diagnosis;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public Object getNamePhonetic() {
            return NamePhonetic;
        }

        public void setNamePhonetic(Object NamePhonetic) {
            this.NamePhonetic = NamePhonetic;
        }

        public String getNation() {
            return Nation;
        }

        public void setNation(String Nation) {
            this.Nation = Nation;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public String getBirthDay() {
            return BirthDay;
        }

        public void setBirthDay(String BirthDay) {
            this.BirthDay = BirthDay;
        }

        public String getAge() {
            return Age;
        }

        public void setAge(String Age) {
            this.Age = Age;
        }

        public String getEnterDate() {
            return EnterDate;
        }

        public void setEnterDate(String EnterDate) {
            this.EnterDate = EnterDate;
        }

        public Object getExitDate() {
            return ExitDate;
        }

        public void setExitDate(Object ExitDate) {
            this.ExitDate = ExitDate;
        }

        public Object getMedicalInsuranceID() {
            return MedicalInsuranceID;
        }

        public void setMedicalInsuranceID(Object MedicalInsuranceID) {
            this.MedicalInsuranceID = MedicalInsuranceID;
        }

        public String getIdentityType() {
            return IdentityType;
        }

        public void setIdentityType(String IdentityType) {
            this.IdentityType = IdentityType;
        }

        public String getCompany() {
            return Company;
        }

        public void setCompany(String Company) {
            this.Company = Company;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getZipCode() {
            return ZipCode;
        }

        public void setZipCode(String ZipCode) {
            this.ZipCode = ZipCode;
        }

        public String getLinkManName() {
            return LinkManName;
        }

        public void setLinkManName(String LinkManName) {
            this.LinkManName = LinkManName;
        }

        public String getLinkManRelation() {
            return LinkManRelation;
        }

        public void setLinkManRelation(String LinkManRelation) {
            this.LinkManRelation = LinkManRelation;
        }

        public String getLinkManPhone() {
            return LinkManPhone;
        }

        public void setLinkManPhone(String LinkManPhone) {
            this.LinkManPhone = LinkManPhone;
        }

        public String getIdentityNumber() {
            return IdentityNumber;
        }

        public void setIdentityNumber(String IdentityNumber) {
            this.IdentityNumber = IdentityNumber;
        }

        public String getPayment() {
            return Payment;
        }

        public void setPayment(String Payment) {
            this.Payment = Payment;
        }

        public String getCurrentState() {
            return CurrentState;
        }

        public void setCurrentState(String CurrentState) {
            this.CurrentState = CurrentState;
        }

        public String getCareLevel() {
            return CareLevel;
        }

        public void setCareLevel(String CareLevel) {
            this.CareLevel = CareLevel;
        }

        public Object getPrePayments() {
            return PrePayments;
        }

        public void setPrePayments(Object PrePayments) {
            this.PrePayments = PrePayments;
        }

        public Object getTotalCost() {
            return TotalCost;
        }

        public void setTotalCost(Object TotalCost) {
            this.TotalCost = TotalCost;
        }

        public Object getTotalPayments() {
            return TotalPayments;
        }

        public void setTotalPayments(Object TotalPayments) {
            this.TotalPayments = TotalPayments;
        }

        public Object getBalanceMoney() {
            return BalanceMoney;
        }

        public void setBalanceMoney(Object BalanceMoney) {
            this.BalanceMoney = BalanceMoney;
        }

        public int getInHosDay() {
            return InHosDay;
        }

        public void setInHosDay(int InHosDay) {
            this.InHosDay = InHosDay;
        }

        public int getIsWaiting() {
            return IsWaiting;
        }

        public void setIsWaiting(int IsWaiting) {
            this.IsWaiting = IsWaiting;
        }

        public int getIsCollection() {
            return IsCollection;
        }

        public void setIsCollection(int IsCollection) {
            this.IsCollection = IsCollection;
        }

        public Object getDiagnsisHistoryList() {
            return DiagnsisHistoryList;
        }

        public void setDiagnsisHistoryList(Object DiagnsisHistoryList) {
            this.DiagnsisHistoryList = DiagnsisHistoryList;
        }
    }
}
