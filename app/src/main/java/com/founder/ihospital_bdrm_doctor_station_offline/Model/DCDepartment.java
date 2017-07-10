package com.founder.ihospital_bdrm_doctor_station_offline.Model;

import java.util.List;


/**
 * 各个科室的model
 * */

public class DCDepartment {


    /**
     * Status : 1
     * Values : [{"ID":"3103","Name":"骨科","ParentDeptID":null,"DeptCode":"3103","StaInfo":null,"IsVisual":true},{"ID":"310303","Name":"骨科病区","ParentDeptID":null,"DeptCode":"310303","StaInfo":null,"IsVisual":true},{"ID":"3105","Name":"普外科","ParentDeptID":null,"DeptCode":"3105","StaInfo":null,"IsVisual":true},{"ID":"310503","Name":"普外科病区","ParentDeptID":null,"DeptCode":"310503","StaInfo":null,"IsVisual":true},{"ID":"410301","Name":"眼科病区","ParentDeptID":null,"DeptCode":"410301","StaInfo":null,"IsVisual":true},{"ID":"410401","Name":"耳鼻喉科病区","ParentDeptID":null,"DeptCode":"410401","StaInfo":null,"IsVisual":true},{"ID":"410501","Name":"口腔科病区","ParentDeptID":null,"DeptCode":"410501","StaInfo":null,"IsVisual":true},{"ID":"410601","Name":"妇科病区","ParentDeptID":null,"DeptCode":"410601","StaInfo":null,"IsVisual":true},{"ID":"410701","Name":"皮肤科病区","ParentDeptID":null,"DeptCode":"410701","StaInfo":null,"IsVisual":true},{"ID":"2125","Name":"血液净化中心","ParentDeptID":null,"DeptCode":"2125","StaInfo":null,"IsVisual":true},{"ID":"212501","Name":"肾脏内科病区","ParentDeptID":null,"DeptCode":"212501","StaInfo":null,"IsVisual":true},{"ID":"21250101","Name":"血液净化中心干部病区","ParentDeptID":null,"DeptCode":"21250101","StaInfo":null,"IsVisual":true},{"ID":"21250102","Name":"血液净化中心普通病区","ParentDeptID":null,"DeptCode":"21250102","StaInfo":null,"IsVisual":true},{"ID":"41103","Name":"急诊科病区","ParentDeptID":null,"DeptCode":"41103","StaInfo":null,"IsVisual":true},{"ID":"3108","Name":"重症监护病区","ParentDeptID":null,"DeptCode":"3108","StaInfo":null,"IsVisual":true},{"ID":"310801","Name":"重症监护内科病区","ParentDeptID":null,"DeptCode":"310801","StaInfo":null,"IsVisual":true},{"ID":"310803","Name":"重症监护外科病区 ","ParentDeptID":null,"DeptCode":"310803","StaInfo":null,"IsVisual":true},{"ID":"212306","Name":"心脏中心三病区","ParentDeptID":null,"DeptCode":"212306","StaInfo":null,"IsVisual":true},{"ID":"2103","Name":"呼吸内科","ParentDeptID":null,"DeptCode":"2103","StaInfo":null,"IsVisual":true},{"ID":"210304","Name":"呼吸内科病区","ParentDeptID":null,"DeptCode":"210304","StaInfo":null,"IsVisual":true},{"ID":"2104","Name":"内分泌科","ParentDeptID":null,"DeptCode":"2104","StaInfo":null,"IsVisual":true},{"ID":"210404","Name":"内分泌科病区","ParentDeptID":null,"DeptCode":"210404","StaInfo":null,"IsVisual":true},{"ID":"2105","Name":"心脏中心一","ParentDeptID":null,"DeptCode":"2105","StaInfo":null,"IsVisual":true},{"ID":"210504","Name":"心脏中心一病区","ParentDeptID":null,"DeptCode":"210504","StaInfo":null,"IsVisual":true},{"ID":"210607","Name":"神经内科南病区","ParentDeptID":null,"DeptCode":"210607","StaInfo":null,"IsVisual":true},{"ID":"2106","Name":"神经内科","ParentDeptID":null,"DeptCode":"2106","StaInfo":null,"IsVisual":true},{"ID":"210604","Name":"神经内科病区","ParentDeptID":null,"DeptCode":"210604","StaInfo":null,"IsVisual":true},{"ID":"211904","Name":"干部病房","ParentDeptID":null,"DeptCode":"211904","StaInfo":null,"IsVisual":true},{"ID":"2122","Name":"消化内科","ParentDeptID":null,"DeptCode":"2122","StaInfo":null,"IsVisual":true},{"ID":"212204","Name":"消化内科病区","ParentDeptID":null,"DeptCode":"212204","StaInfo":null,"IsVisual":true},{"ID":"2123","Name":"心脏中心二","ParentDeptID":null,"DeptCode":"2123","StaInfo":null,"IsVisual":true},{"ID":"212304","Name":"心脏中心二病区","ParentDeptID":null,"DeptCode":"212304","StaInfo":null,"IsVisual":true},{"ID":"3107","Name":"心胸外科","ParentDeptID":null,"DeptCode":"3107","StaInfo":null,"IsVisual":true},{"ID":"310703","Name":"心胸外科病区","ParentDeptID":null,"DeptCode":"310703","StaInfo":null,"IsVisual":true}]
     */

    private int Status;
    /**
     * ID : 3103
     * Name : 骨科
     * ParentDeptID : null
     * DeptCode : 3103
     * StaInfo : null
     * IsVisual : true
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
        private String ID;
        private String Name;
        private Object ParentDeptID;
        private String DeptCode;
        private Object StaInfo;
        private boolean IsVisual;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public Object getParentDeptID() {
            return ParentDeptID;
        }

        public void setParentDeptID(Object ParentDeptID) {
            this.ParentDeptID = ParentDeptID;
        }

        public String getDeptCode() {
            return DeptCode;
        }

        public void setDeptCode(String DeptCode) {
            this.DeptCode = DeptCode;
        }

        public Object getStaInfo() {
            return StaInfo;
        }

        public void setStaInfo(Object StaInfo) {
            this.StaInfo = StaInfo;
        }

        public boolean isIsVisual() {
            return IsVisual;
        }

        public void setIsVisual(boolean IsVisual) {
            this.IsVisual = IsVisual;
        }
    }
}