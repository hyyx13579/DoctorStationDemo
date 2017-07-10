package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCClinicalRecord;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCNursingRecordOfPatientHR;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;

import java.util.ArrayList;
import java.util.List;


public class ExpanableListView_NurseInfo_ListView_Adapter extends BaseAdapter {

    private List<DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean> data;
    private Context context;
    private LayoutInflater inflater;
    private ViewHolder holder;

    public ExpanableListView_NurseInfo_ListView_Adapter(List<DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean> data, LayoutInflater inflater) {
        this.inflater = inflater;

        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }


    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expandable_listview_nurseinfo_child_listview_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }

        holder.time.setText(data.get(position).getCreateTime());
        holder.content.setText(data.get(position).getValue());


        return convertView;
    }

    static class ViewHolder {

        TextView content;
        TextView time;

        public ViewHolder(View convertView) {
            content = ((TextView) convertView.findViewById(R.id.tv_data_nurseinfo_child_item));
            time = ((TextView) convertView.findViewById(R.id.tv_time_nurseinfo_child_item));

        }


    }


}
