package com.founder.ihospital_bdrm_doctor_station_offline.care_info.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCNursingRecordOfPatientHR;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCNursingRecordOfPatientHR.ValuesBean;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bin_li on 16/5/16.
 */
public class CareInfoDataHandler {
    private String mDataString;

    private List<ItemListBean> mItemBeanList = new ArrayList<>();

    private String mFirstTime;

    public CareInfoDataHandler() {
    }

    public CareInfoDataHandler(String jsonStr) {
        this.mDataString = jsonStr;
        initData();
        initShowDate();
        initDataInModel();
    }

    public void initData() {
        Gson gson = new Gson();
        DCNursingRecordOfPatientHR dcNursingRecordOfPatientHR = null;
        if (!TextUtils.isEmpty(mDataString)) {
            dcNursingRecordOfPatientHR = gson.fromJson(mDataString, DCNursingRecordOfPatientHR.class);
        }
        if (dcNursingRecordOfPatientHR == null || dcNursingRecordOfPatientHR.getValues() == null || dcNursingRecordOfPatientHR.getValues().size() <= 0) {
            return;
        }
        //获取ItemBean 集合，并设置时间
        List<ValuesBean> valueBeanList = dcNursingRecordOfPatientHR.getValues();
//        mFirstTime = (String) valueBeanList.get(valueBeanList.size() - 1).getStartDateTime();
        for (int i = 0; i < valueBeanList.size(); i++) {
            List<NurseingRecordListBean> recordBeanList = valueBeanList.get(i).getNurseingRecordList();
            if (recordBeanList == null)
                continue;
            for (int j = 0; j < recordBeanList.size(); j++) {
                List<ItemListBean> temp = recordBeanList.get(j).getItemList();
                for (int k = 0; k < temp.size(); k++) {
                    ItemListBean tempBean = temp.get(k);
                    tempBean.setTimeInMillis(TimeUtils.parseTimeString(tempBean.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                    mItemBeanList.add(tempBean);
                }
            }
        }
        Collections.sort(mItemBeanList, mComparator);
    }

    private long mShowFirstTime;
    private long mShowLastTime;
    private ArrayList<String> mShowDateStrs = new ArrayList<>();

    private void initShowDate() {
        final ItemListBean firstItem = mItemBeanList.get(0);
        final ItemListBean lastItem = mItemBeanList.get(mItemBeanList.size() - 1);
        mFirstTime = firstItem.getCreateTime();
        Calendar calendar = Calendar.getInstance();
        TimeUtils.getTargetDateStr(calendar.getTimeInMillis(), 0);
        calendar.setTimeInMillis(firstItem.getTimeInMillis());

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        mShowFirstTime = calendar.getTimeInMillis();
        calendar.setTimeInMillis(lastItem.getTimeInMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        mShowLastTime = calendar.getTimeInMillis();
        if (mShowLastTime - mShowFirstTime > 14 * 24 * 60 * 60 * 1000) {
            mShowFirstTime = mShowLastTime - 14 * 24 * 60 * 60 * 1000;
            for (int i = 0; i < 14; i++) {
                mShowDateStrs.add(TimeUtils.getTargetDateStr(mShowFirstTime, i + 1));
            }
        } else if (mShowLastTime - mShowFirstTime > 7 * 24 * 60 * 60 * 1000) {
            mShowLastTime = mShowFirstTime + 14 * 24 * 60 * 60 * 1000;
            for (int i = 0; i < 14; i++) {
                mShowDateStrs.add(TimeUtils.getTargetDateStr(mShowFirstTime, i));
            }
        } else {
            mShowLastTime = mShowFirstTime + 7 * 24 * 60 * 60 * 1000;
            for (int i = 0; i < 7; i++) {
                mShowDateStrs.add(TimeUtils.getTargetDateStr(mShowFirstTime, i));
            }
        }
    }

    private Map<String, List<ItemListBean>> mMap = new HashMap<>();

    private void initDataInModel() {
        List<ItemListBean> tempList = new ArrayList<>();
        int flag = 0;
        for (int i = 0; i < mItemBeanList.size(); i++) {
            if (mItemBeanList.get(i).getTimeInMillis() - mShowFirstTime > 0) {
                flag = i;
                break;
            }
        }
        tempList = mItemBeanList.subList(flag, mItemBeanList.size());
        for (int j = 0; j < tempList.size(); j++) {
            ItemListBean tempBean = tempList.get(j);
            if (mMap.containsKey(tempBean.getTypeName())) {
                mMap.get(tempBean.getTypeName()).add(tempBean);
            } else {
                List<ItemListBean> list = new ArrayList<>();
                list.add(tempBean);
                mMap.put(tempBean.getTypeName(), list);
            }

        }
    }

    public String getFirstTime() {
        return mFirstTime;
    }

    public ArrayList<String> getShowDateStrs() {
        return mShowDateStrs;
    }

    public Map<String, List<ItemListBean>> getMap() {
        return mMap;
    }

    private Comparator mComparator = new Comparator<ItemListBean>() {
        @Override
        public int compare(ItemListBean o, ItemListBean t1) {
            return (int) ((o.getTimeInMillis() - t1.getTimeInMillis()) / 1000);
        }
    };
}
