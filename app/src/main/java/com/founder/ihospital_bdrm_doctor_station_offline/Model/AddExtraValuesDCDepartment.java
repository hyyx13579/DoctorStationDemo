package com.founder.ihospital_bdrm_doctor_station_offline.Model;

/**
 * 作者：呼延 on 2016/5/11 15:51
 * 邮箱：huyanyx@founder.com.cn
 */
public class AddExtraValuesDCDepartment {


    public AddExtraValuesDCDepartment(String ID, String name, Object parentDeptID, String deptCode, Object staInfo, boolean isVisual, String titleFlag) {
        this.ID = ID;
        Name = name;
        ParentDeptID = parentDeptID;
        DeptCode = deptCode;
        StaInfo = staInfo;
        IsVisual = isVisual;
        this.titleFlag = titleFlag;
    }

    public AddExtraValuesDCDepartment(String ID, String name, Object parentDeptID, String deptCode) {
        this.ID = ID;
        Name = name;
        ParentDeptID = parentDeptID;
        DeptCode = deptCode;
    }



    private String ID;
    private String Name;
    private Object ParentDeptID;
    private String DeptCode;
    private Object StaInfo;
    private boolean IsVisual;
    private String titleFlag;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Object getParentDeptID() {
        return ParentDeptID;
    }

    public void setParentDeptID(Object parentDeptID) {
        ParentDeptID = parentDeptID;
    }

    public String getDeptCode() {
        return DeptCode;
    }

    public void setDeptCode(String deptCode) {
        DeptCode = deptCode;
    }

    public Object getStaInfo() {
        return StaInfo;
    }

    public void setStaInfo(Object staInfo) {
        StaInfo = staInfo;
    }

    public boolean isVisual() {
        return IsVisual;
    }

    public void setVisual(boolean visual) {
        IsVisual = visual;
    }

    public String getTitleFlag() {
        return titleFlag;
    }

    public void setTitleFlag(String titleFlag) {
        this.titleFlag = titleFlag;
    }


}
