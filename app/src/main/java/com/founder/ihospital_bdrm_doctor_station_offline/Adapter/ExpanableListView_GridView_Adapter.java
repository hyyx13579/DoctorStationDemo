package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCClinicalRecord;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 呼延 on 2016/3/31.
 */
public class ExpanableListView_GridView_Adapter extends BaseAdapter {

    private List<DCClinicalRecord.ValuesBean.SubMedicalRecordBean> data;
    private Context context;
    private LayoutInflater inflater;
    private ViewHolder holder;

    public ExpanableListView_GridView_Adapter(List<DCClinicalRecord.ValuesBean.SubMedicalRecordBean> data, LayoutInflater inflater) {
        this.inflater = inflater;

        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }

    public void addRes(List<DCClinicalRecord.ValuesBean.SubMedicalRecordBean> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
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
            convertView = inflater.inflate(R.layout.expandable_listview_child_gridview_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }
        holder.content.setText(data.get(position).getTitle());


        holder.time.setText(Url.dateToSimpleDate((data.get(position).getCreateDateTime())));


        return convertView;
    }

    static class ViewHolder {

        TextView content;
        TextView time;

        public ViewHolder(View convertView) {
            content = ((TextView) convertView.findViewById(R.id.expandablelistview_child_gideview_item_content));
            time = ((TextView) convertView.findViewById(R.id.expandablelistview_child_gideview_item_time));

        }


    }


}
