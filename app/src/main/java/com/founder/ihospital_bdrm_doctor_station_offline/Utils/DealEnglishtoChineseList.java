package com.founder.ihospital_bdrm_doctor_station_offline.Utils;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.AddExtraValuesDCDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCDepartmentOfUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 作者：呼延 on 2016/5/11 15:16
 * 邮箱：huyanyx@founder.com.cn
 * 处理数据源列表从英文变为中文
 */
public class DealEnglishtoChineseList {


    public static List<AddExtraValuesDCDepartment> AddExtraValues(List<DCDepartmentOfUser.ValuesBean> valuesBeen) {
        List<AddExtraValuesDCDepartment> data = new ArrayList<>();

        for (int i = 0; i < valuesBeen.size(); i++) {
            if (valuesBeen.get(i).isMySelfFlag()) {
                data.add(new AddExtraValuesDCDepartment(valuesBeen.get(i).getID(),
                        valuesBeen.get(i).getName(), valuesBeen.get(i).getParentDeptID(),
                        valuesBeen.get(i).getDeptCode(), valuesBeen.get(i).getStaInfo(),
                        valuesBeen.get(i).isIsVisual(),
                        "#"
                ));
            }else {
                data.add(new AddExtraValuesDCDepartment(valuesBeen.get(i).getID(),
                        valuesBeen.get(i).getName(), valuesBeen.get(i).getParentDeptID(),
                        valuesBeen.get(i).getDeptCode(), valuesBeen.get(i).getStaInfo(),
                        valuesBeen.get(i).isIsVisual(),
                        ChineseToEnglish.getPingYin(valuesBeen.get(i).getName())
                ));
            }


        }
        Collections.sort(data, new Comparator<AddExtraValuesDCDepartment>() {
            @Override
            public int compare(AddExtraValuesDCDepartment lhs, AddExtraValuesDCDepartment rhs) {
                if (lhs.getTitleFlag().charAt(0) < rhs.getTitleFlag().charAt(0)) {
                    return -1;
                }
                if (lhs.getTitleFlag().charAt(0) == rhs.getTitleFlag().charAt(0)) {
                    return 0;
                }


                return 1;
            }
        });


        return data;
    }

    public static List<HashMap<String, List<AddExtraValuesDCDepartment>>> DealAddExtraValues(List<AddExtraValuesDCDepartment> values) {

        List<HashMap<String, List<AddExtraValuesDCDepartment>>> data = new ArrayList<>();
        HashMap<String, List<AddExtraValuesDCDepartment>> map;
        List<AddExtraValuesDCDepartment> list;

        map = new HashMap<>();
        list = new ArrayList<>();
        list.add(new AddExtraValuesDCDepartment(values.get(0).getID(), values.get(0).getName(),
                values.get(0).getParentDeptID(), values.get(0).getDeptCode()));
        map.put(values.get(0).getTitleFlag(), list);
        data.add(map);


        for (int i = 1; i < values.size(); i++) {
            if (!values.get(i - 1).getTitleFlag().equals(values.get(i).getTitleFlag())) {
                map = new HashMap<>();
                list = new ArrayList<>();
                list.add(new AddExtraValuesDCDepartment(values.get(i).getID(), values.get(i).getName(),
                        values.get(i).getParentDeptID(), values.get(i).getDeptCode()));
                map.put(values.get(i).getTitleFlag(), list);
                data.add(map);


            } else {
                HashMap<String, List<AddExtraValuesDCDepartment>> stringListHashMap = data.get(data.size() - 1);
                List<AddExtraValuesDCDepartment> addExtraValuesDCDepartments = stringListHashMap.get(values.get(i - 1).getTitleFlag());
                addExtraValuesDCDepartments.add(new AddExtraValuesDCDepartment(values.get(i).getID(), values.get(i).getName(),
                        values.get(i).getParentDeptID(), values.get(i).getDeptCode()));
                stringListHashMap.put(values.get(i - 1).getTitleFlag(), addExtraValuesDCDepartments);
            }

        }

        return data;

    }


}
