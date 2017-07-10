package com.founder.ihospital_bdrm_doctor_station_offline.Model;

import java.util.List;

/**
 * Created by 呼延 on 2016/3/25.
 * 医生登录以后获得数据的model
 */
public class DCUser {


    /**
     * Status : 1
     * Values : {"ModulePermission":[10000,10001,10002,10003,10004,10005,10006,10007,10008],"ID":"03267","Certification":null,"CertificationRequired":false,"Name":"测试","UserName":"09264","Password":"00","IdentityCard":null,"Sex":null,"Nation":null,"Title":"主任医师","LevelCode":null,"ImageUri":null,"UserRole":0,"SursurgeryClass":null,"DeptName":"测试科室","DeptCode":"4080000","Dept_IDS":null}
     */

    private int Status;
    /**
     * ModulePermission : [10000,10001,10002,10003,10004,10005,10006,10007,10008]
     * ID : 03267
     * Certification : null
     * CertificationRequired : false
     * Name : 测试
     * UserName : 09264
     * Password : 00
     * IdentityCard : null
     * Sex : null
     * Nation : null
     * Title : 主任医师
     * LevelCode : null
     * ImageUri : null
     * UserRole : 0
     * SursurgeryClass : null
     * DeptName : 测试科室
     * DeptCode : 4080000
     * Dept_IDS : null
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
        private Object Certification;
        private boolean CertificationRequired;
        private String Name;
        private String UserName;
        private String Password;
        private Object IdentityCard;
        private Object Sex;
        private Object Nation;
        private String Title;
        private Object LevelCode;
        private Object ImageUri;
        private int UserRole;
        private Object SursurgeryClass;
        private String DeptName;
        private String DeptCode;
        private Object Dept_IDS;
        private List<Integer> ModulePermission;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public Object getCertification() {
            return Certification;
        }

        public void setCertification(Object Certification) {
            this.Certification = Certification;
        }

        public boolean isCertificationRequired() {
            return CertificationRequired;
        }

        public void setCertificationRequired(boolean CertificationRequired) {
            this.CertificationRequired = CertificationRequired;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public Object getIdentityCard() {
            return IdentityCard;
        }

        public void setIdentityCard(Object IdentityCard) {
            this.IdentityCard = IdentityCard;
        }

        public Object getSex() {
            return Sex;
        }

        public void setSex(Object Sex) {
            this.Sex = Sex;
        }

        public Object getNation() {
            return Nation;
        }

        public void setNation(Object Nation) {
            this.Nation = Nation;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public Object getLevelCode() {
            return LevelCode;
        }

        public void setLevelCode(Object LevelCode) {
            this.LevelCode = LevelCode;
        }

        public Object getImageUri() {
            return ImageUri;
        }

        public void setImageUri(Object ImageUri) {
            this.ImageUri = ImageUri;
        }

        public int getUserRole() {
            return UserRole;
        }

        public void setUserRole(int UserRole) {
            this.UserRole = UserRole;
        }

        public Object getSursurgeryClass() {
            return SursurgeryClass;
        }

        public void setSursurgeryClass(Object SursurgeryClass) {
            this.SursurgeryClass = SursurgeryClass;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public String getDeptCode() {
            return DeptCode;
        }

        public void setDeptCode(String DeptCode) {
            this.DeptCode = DeptCode;
        }

        public Object getDept_IDS() {
            return Dept_IDS;
        }

        public void setDept_IDS(Object Dept_IDS) {
            this.Dept_IDS = Dept_IDS;
        }

        public List<Integer> getModulePermission() {
            return ModulePermission;
        }

        public void setModulePermission(List<Integer> ModulePermission) {
            this.ModulePermission = ModulePermission;
        }
    }
}
