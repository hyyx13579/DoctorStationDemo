package com.founder.ihospital_bdrm_doctor_station_offline.Model;

import java.util.List;

/**
 * Created by hyyx on 16/8/25.
 */
public class DepartmentCareLevelCountOfDept {

    /**
     * Status : 1
     * Values : {"ID":null,"Name":null,"ParentDeptID":null,"DeptCode":null,"WardCodes":null,"StaInfo":{"PatientInHos":12,"MyPatientInHos":0,"PatientEnterHos":0,"PatientExitHos":0,"PatientTransfer":0,"UseBedCount":0,"UnuseBedCount":0,"DoctorOrderCount":0,"DoctorOrderStateTitle":[],"DoctorOrderStateCount":[],"FinishDoctorOrderCount":0,"UnFinishDoctorOrderCount":0,"CareLevelAndStateTitle":["特级护理","一级护理","二级护理","其它"],"CareLevelAndStateCount":[1,3,8,0]},"IsVisual":false}
     */

    private int Status;
    /**
     * ID : null
     * Name : null
     * ParentDeptID : null
     * DeptCode : null
     * WardCodes : null
     * StaInfo : {"PatientInHos":12,"MyPatientInHos":0,"PatientEnterHos":0,"PatientExitHos":0,"PatientTransfer":0,"UseBedCount":0,"UnuseBedCount":0,"DoctorOrderCount":0,"DoctorOrderStateTitle":[],"DoctorOrderStateCount":[],"FinishDoctorOrderCount":0,"UnFinishDoctorOrderCount":0,"CareLevelAndStateTitle":["特级护理","一级护理","二级护理","其它"],"CareLevelAndStateCount":[1,3,8,0]}
     * IsVisual : false
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
        private Object ID;
        private Object Name;
        private Object ParentDeptID;
        private Object DeptCode;
        private Object WardCodes;
        /**
         * PatientInHos : 12
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
         * CareLevelAndStateCount : [1,3,8,0]
         */

        private StaInfoBean StaInfo;
        private boolean IsVisual;

        public Object getID() {
            return ID;
        }

        public void setID(Object ID) {
            this.ID = ID;
        }

        public Object getName() {
            return Name;
        }

        public void setName(Object Name) {
            this.Name = Name;
        }

        public Object getParentDeptID() {
            return ParentDeptID;
        }

        public void setParentDeptID(Object ParentDeptID) {
            this.ParentDeptID = ParentDeptID;
        }

        public Object getDeptCode() {
            return DeptCode;
        }

        public void setDeptCode(Object DeptCode) {
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
