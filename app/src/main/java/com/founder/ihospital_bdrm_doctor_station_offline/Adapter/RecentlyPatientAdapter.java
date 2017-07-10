package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.ArrayList;
import java.util.List;


public class RecentlyPatientAdapter extends BaseAdapter {

    private List<DCPatientHROfDepartment.ValuesBean> data;
    private Context context;
    private LayoutInflater inflater;


    public RecentlyPatientAdapter(List<DCPatientHROfDepartment.ValuesBean> data, Context context) {
        inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }

    }

    public void addRes(List<DCPatientHROfDepartment.ValuesBean> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void updataRes(List<DCPatientHROfDepartment.ValuesBean> data) {
        if (data != null) {
            this.data.clear();
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
            convertView = inflater.inflate(R.layout.item_recently_patient, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }
        holder.tvName.setText(data.get(position).getName());
        holder.tvBedNo.setText(data.get(position).getBedNo());
        holder.tvSex.setText(data.get(position).getSex());
        holder.tvDepartName.setText(data.get(position).getDeptName());
        int isCollection = data.get(position).getIsCollection();
        if (isCollection==1){
            holder.ivStar.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView ivStar;
        TextView tvSex;
        TextView tvBedNo;
        TextView tvDepartName;
        TextView tvName;

        public ViewHolder(View convertView) {
            tvName = ((TextView) convertView.findViewById(R.id.name_item_recently_patient));
            ivStar = ((ImageView) convertView.findViewById(R.id.star_item_recently_patient));
            tvSex = ((TextView) convertView.findViewById(R.id.sex_item_recently_patient));
            tvBedNo = ((TextView) convertView.findViewById(R.id.bedNo_item_recently_patient));
            tvDepartName = ((TextView) convertView.findViewById(R.id.departname_item_recently_patient));

        }


    }



}
