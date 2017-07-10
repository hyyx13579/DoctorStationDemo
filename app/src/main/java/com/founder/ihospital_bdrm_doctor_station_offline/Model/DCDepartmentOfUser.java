package com.founder.ihospital_bdrm_doctor_station_offline.Model;

import java.util.List;

/**
 * Created by 呼延 on 2016/3/25.
 * 用户登录,有医生每个科室信息的model
 */
public class DCDepartmentOfUser {


    /**
     * Status : 1
     * Values : [{"ID":"1000058","Name":"风湿免疫内科病房","ParentDeptID":null,"DeptCode":"1000058","WardCodes":null,"StaInfo":{"PatientInHos":47,"MyPatientInHos":0,"PatientEnterHos":0,"PatientExitHos":0,"PatientTransfer":0,"UseBedCount":0,"UnuseBedCount":0,"DoctorOrderCount":0,"DoctorOrderStateTitle":[],"DoctorOrderStateCount":[],"FinishDoctorOrderCount":0,"UnFinishDoctorOrderCount":0,"CareLevelAndStateTitle":["特级护理","一级护理","二级护理","其它"],"CareLevelAndStateCount":[0,6,41,0]},"IsVisual":false},{"ID":"1000066","Name":"肾内科病房","ParentDeptID":null,"DeptCode":"1000066","WardCodes":null,"StaInfo":{"PatientInHos":27,"MyPatientInHos":0,"PatientEnterHos":0,"PatientExitHos":0,"PatientTransfer":0,"UseBedCount":0,"UnuseBedCount":0,"DoctorOrderCount":0,"DoctorOrderStateTitle":[],"DoctorOrderStateCount":[],"FinishDoctorOrderCount":0,"UnFinishDoctorOrderCount":0,"CareLevelAndStateTitle":["特级护理","一级护理","二级护理","其它"],"CareLevelAndStateCount":[1,8,18,0]},"IsVisual":false},{"ID":"1000076","Name":"内分泌科病房","ParentDeptID":null,"DeptCode":"1000076","WardCodes":null,"StaInfo":{"PatientInHos":25,"MyPatientInHos":0,"PatientEnterHos":0,"PatientExitHos":0,"PatientTransfer":0,"UseBedCount":0,"UnuseBedCount":0,"DoctorOrderCount":0,"DoctorOrderStateTitle":[],"DoctorOrderStateCount":[],"FinishDoctorOrderCount":0,"UnFinishDoctorOrderCount":0,"CareLevelAndStateTitle":["特级护理","一级护理","二级护理","其它"],"CareLevelAndStateCount":[0,0,25,0]},"IsVisual":false},{"ID":"1000108","Name":"肝病科病房","ParentDeptID":null,"DeptCode":"1000108","WardCodes":null,"StaInfo":{"PatientInHos":20,"MyPatientInHos":0,"PatientEnterHos":0,"PatientExitHos":0,"PatientTransfer":0,"UseBedCount":0,"UnuseBedCount":0,"DoctorOrderCount":0,"DoctorOrderStateTitle":[],"DoctorOrderStateCount":[],"FinishDoctorOrderCount":0,"UnFinishDoctorOrderCount":0,"CareLevelAndStateTitle":["特级护理","一级护理","二级护理","其它"],"CareLevelAndStateCount":[0,3,17,0]},"IsVisual":false}]
     */

    private int Status;
    /**
     * ID : 1000058
     * Name : 风湿免疫内科病房
     * ParentDeptID : null
     * DeptCode : 1000058
     * WardCodes : null
     * StaInfo : {"PatientInHos":47,"MyPatientInHos":0,"PatientEnterHos":0,"PatientExitHos":0,"PatientTransfer":0,"UseBedCount":0,"UnuseBedCount":0,"DoctorOrderCount":0,"DoctorOrderStateTitle":[],"DoctorOrderStateCount":[],"FinishDoctorOrderCount":0,"UnFinishDoctorOrderCount":0,"CareLevelAndStateTitle":["特级护理","一级护理","二级护理","其它"],"CareLevelAndStateCount":[0,6,41,0]}
     * IsVisual : false
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
        private Object WardCodes;
        private boolean MySelfFlag;

        public boolean isVisual() {
            return IsVisual;
        }

        public void setVisual(boolean visual) {
            IsVisual = visual;
        }

        public boolean isMySelfFlag() {
            return MySelfFlag;
        }

        public void setMySelfFlag(boolean mySelfFlag) {
            MySelfFlag = mySelfFlag;
        }

        public ValuesBean(String name, boolean mySelfFlag) {
            Name = name;
            MySelfFlag = mySelfFlag;
        }

        /**
         * PatientInHos : 47
         * MyPatientInHos : 0
         * PatientEnterHos : 0
         * PatientExitHos : 0
         * PatientTransfer : 0
         * UseBedCount : 0
         * UnuseBedCount : 0
         * DoctorOrderCount : 0
         * DoctorOrderStateTitle : []
         * DoctorOrderStateCount : []
         * FinishDoctorOrderCount : 0
         * UnFinishDoctorOrderCount : 0
         * CareLevelAndStateTitle : ["特级护理","一级护理","二级护理","其它"]
         * CareLevelAndStateCount : [0,6,41,0]
         */

        private StaInfoBean StaInfo;
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

        public Object getWardCodes() {
            return WardCodes;
        }

        public void setWardCodes(Object WardCodes) {
            this.WardCodes = WardCodes;
        }

        public StaInfoBean getStaInfo() {
            return StaInfo;
        }

        public void setStaInfo(StaInfoBean StaInfo) {
            this.StaInfo = StaInfo;
        }

        public boolean isIsVisual() {
            return IsVisual;
        }

        public void setIsVisual(boolean IsVisual) {
            this.IsVisual = IsVisual;
        }

        public static class StaInfoBean {
            private int PatientInHos;
            private int MyPatientInHos;
            private int PatientEnterHos;
            private int PatientExitHos;
            private int PatientTransfer;
            private int UseBedCount;
            private int UnuseBedCount;
            private int DoctorOrderCount;
            private int FinishDoctorOrderCount;
            private int UnFinishDoctorOrderCount;
            private List<?> DoctorOrderStateTitle;
            private List<?> DoctorOrderStateCount;
            private List<String> CareLevelAndStateTitle;
            private List<Integer> CareLevelAndStateCount;

            public int getPatientInHos() {
                return PatientInHos;
            }

            public void setPatientInHos(int PatientInHos) {
                this.PatientInHos = PatientInHos;
            }

            public int getMyPatientInHos() {
                return MyPatientInHos;
            }

            public void setMyPatientInHos(int MyPatientInHos) {
                this.MyPatientInHos = MyPatientInHos;
            }

            public int getPatientEnterHos() {
                return PatientEnterHos;
            }

            public void setPatientEnterHos(int PatientEnterHos) {
                this.PatientEnterHos = PatientEnterHos;
            }

            public int getPatientExitHos() {
                return PatientExitHos;
            }

            public void setPatientExitHos(int PatientExitHos) {
                this.PatientExitHos = PatientExitHos;
            }

            public int getPatientTransfer() {
                return PatientTransfer;
            }

            public void setPatientTransfer(int PatientTransfer) {
                this.PatientTransfer = PatientTransfer;
            }

            public int getUseBedCount() {
                return UseBedCount;
            }

            public void setUseBedCount(int UseBedCount) {
                this.UseBedCount = UseBedCount;
            }

            public int getUnuseBedCount() {
                return UnuseBedCount;
            }

            public void setUnuseBedCount(int UnuseBedCount) {
                this.UnuseBedCount = UnuseBedCount;
            }

            public int getDoctorOrderCount() {
                return DoctorOrderCount;
            }

            public void setDoctorOrderCount(int DoctorOrderCount) {
                this.DoctorOrderCount = DoctorOrderCount;
            }

            public int getFinishDoctorOrderCount() {
                return FinishDoctorOrderCount;
            }

            public void setFinishDoctorOrderCount(int FinishDoctorOrderCount) {
                this.FinishDoctorOrderCount = FinishDoctorOrderCount;
            }

            public int getUnFinishDoctorOrderCount() {
                return UnFinishDoctorOrderCount;
            }

            public void setUnFinishDoctorOrderCount(int UnFinishDoctorOrderCount) {
                this.UnFinishDoctorOrderCount = UnFinishDoctorOrderCount;
            }

            public List<?> getDoctorOrderStateTitle() {
                return DoctorOrderStateTitle;
            }

            public void setDoctorOrderStateTitle(List<?> DoctorOrderStateTitle) {
                this.DoctorOrderStateTitle = DoctorOrderStateTitle;
            }

            public List<?> getDoctorOrderStateCount() {
                return DoctorOrderStateCount;
            }

            public void setDoctorOrderStateCount(List<?> DoctorOrderStateCount) {
                this.DoctorOrderStateCount = DoctorOrderStateCount;
            }

            public List<String> getCareLevelAndStateTitle() {
                return CareLevelAndStateTitle;
            }

            public void setCareLevelAndStateTitle(List<String> CareLevelAndStateTitle) {
                this.CareLevelAndStateTitle = CareLevelAndStateTitle;
            }

            public List<Integer> getCareLevelAndStateCount() {
                return CareLevelAndStateCount;
            }

            public void setCareLevelAndStateCount(List<Integer> CareLevelAndStateCount) {
                this.CareLevelAndStateCount = CareLevelAndStateCount;
            }
        }
    }
}
