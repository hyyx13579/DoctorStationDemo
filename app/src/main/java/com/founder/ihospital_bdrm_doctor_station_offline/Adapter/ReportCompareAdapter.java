package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCTestOfPatientHR;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.St;
import com.founder.ihospital_bdrm_doctor_station_offline.View.CompareView.CompareBean;
import com.founder.ihospital_bdrm_doctor_station_offline.View.CompareView.CompareView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by bin_li on 16/5/11.
 */
public class ReportCompareAdapter extends BaseAdapter {
    private Map<String, List<DCTestOfPatientHR.ValuesBean.TestItemListBean>> mSouceMapList;
    private Context mContext;
    private Map<String, List<CompareBean>> mDataMapList;
    private List<String> mDataNameList;

    public ReportCompareAdapter(Context context) {
        mContext = context;
        mDataNameList = new ArrayList<>();
    }

    public void updataList(String name, Map<String, List<DCTestOfPatientHR.ValuesBean.TestItemListBean>> list) {
        mSouceMapList = list;
        mDataMapList = handlerDataList(mSouceMapList);

        boolean hasName = false;
        for (int i = 0; i < mDataNameList.size(); i++) {
            if (TextUtils.equals(name, mDataNameList.get(i))) {
                hasName = true;
            }
        }
        if (hasName) {
            mDataNameList.remove(name);
        } else {
            if (!mDataMapList.containsKey(name)) {//是否有该项目
                return;
            }
            // 匹配该项中的value是否都是数字
            List<CompareBean> tempList = mDataMapList.get(name);
            for (int i = 0; i < tempList.size(); i++) {

                if (!TextUtils.isEmpty(tempList.get(i).value) && !St.isDigits(tempList.get(i).value)) {
                    return;
                }
            }
            mDataNameList.add(name);
        }

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mDataNameList != null)
            return mDataNameList.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_report_compare, null);
            holder.nameText = (TextView) view.findViewById(R.id.report_compare_name_txt);
            holder.compareView = (CompareView) view.findViewById(R.id.compare_view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.nameText.setText(mDataNameList.get(i));
        List<CompareBean> list = mDataMapList.get(mDataNameList.get(i));
        holder.compareView.setData(list);
        return view;
    }

    class ViewHolder {
        public TextView nameText;
        public CompareView compareView;
    }

    private Map<String, List<CompareBean>> handlerDataList(Map<String, List<DCTestOfPatientHR.ValuesBean.TestItemListBean>> mapList) {
        Map<String, List<CompareBean>> retMap = new HashMap<>();
        Iterator<Map.Entry<String, List<DCTestOfPatientHR.ValuesBean.TestItemListBean>>> it = mapList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<DCTestOfPatientHR.ValuesBean.TestItemListBean>> entry = it.next();
            String key = entry.getKey();
            List<DCTestOfPatientHR.ValuesBean.TestItemListBean> testlist = entry.getValue();
            List<CompareBean> list = new ArrayList<>();
            for (int i = 0; i < testlist.size(); i++) {
                DCTestOfPatientHR.ValuesBean.TestItemListBean testBean = testlist.get(i);
                CompareBean bean = new CompareBean();
                bean.date = ((String) testBean.getReportDate()).substring(5, ((String) testBean.getReportDate()).length());
                bean.result = testBean.getResult();
                bean.value = testBean.getReusltValue();
                list.add(bean);
            }
            retMap.put(key, list);
        }
        return retMap;
    }
}
