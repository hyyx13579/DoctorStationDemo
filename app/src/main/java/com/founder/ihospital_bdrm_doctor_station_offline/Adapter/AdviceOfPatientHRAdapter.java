package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCAdviceOfPatientHR;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 呼延 on 2016/4/1.
 * <p/>
 * 移动查房模块-医嘱护理功能-listview-Adapter
 */


public class AdviceOfPatientHRAdapter extends BaseAdapter {


    private List<DCAdviceOfPatientHR.ValuesBean> data;
    private LayoutInflater inflater;
    private Context context;

    public AdviceOfPatientHRAdapter(List<DCAdviceOfPatientHR.ValuesBean> data, Context context) {
        inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        this.context = context;
    }

    public void addRes(List<DCAdviceOfPatientHR.ValuesBean> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }

    }

    public void updateRes(List<DCAdviceOfPatientHR.ValuesBean> data) {
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
            convertView = inflater.inflate(R.layout.parent_info_frg_physicianordermanagement_lv_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }
        String[] split = data.get(position).getStartExectueTime().split(" ");
        holder.time.setText(split[1]);
        holder.year.setText(split[0]);

        String ordertypename = "";

        if (data.get(position).getIsLongTerm().contains("临")) {
            ordertypename = "临嘱";
        } else if (data.get(position).getIsLongTerm().contains("长")) {
            ordertypename = "长嘱";
        }

        String orderClassString = data.get(position).getOrderClassString();
        String content = data.get(position).getContent();
        String dosage = (String) data.get(position).getDosage();
        String dosageUnit = (String) data.get(position).getDosageUnit();
        String performance = (String) data.get(position).getPerformance();
        if (!TextUtils.isEmpty(dosage)) {

            holder.content.setText("(" + ordertypename + ")" + "(" + orderClassString + ")" + content + dosage);
        } else {
            if (!TextUtils.isEmpty(dosageUnit)&&!TextUtils.isEmpty(performance)){

                holder.content.setText("(" + ordertypename + ")" + "(" + orderClassString + ")" + content + dosageUnit + performance);
            }else {
                holder.content.setText("(" + ordertypename + ")" + "(" + orderClassString + ")" + content );

            }


        }


        switch (data.get(position).getState()) {

            case "1":
                holder.year.setTextColor(context.getResources().getColor(R.color.text_light_color));
                holder.time.setTextColor(context.getResources().getColor(R.color.text_light_color));
                holder.content.setTextColor(context.getResources().getColor(R.color.text_light_color));
                break;
            case "2":
                holder.year.setTextColor(context.getResources().getColor(R.color.text_normal_color));
                holder.time.setTextColor(context.getResources().getColor(R.color.text_normal_color));
                holder.content.setTextColor(context.getResources().getColor(R.color.text_normal_color));
                break;
            case "3":
                holder.year.setTextColor(context.getResources().getColor(R.color.text_red_color));
                holder.time.setTextColor(context.getResources().getColor(R.color.text_red_color));
                holder.content.setTextColor(context.getResources().getColor(R.color.text_red_color));
                break;


        }

        if (position != 0 && position % 2 == 1) {

            convertView.setBackgroundColor(context.getResources().getColor(R.color.activity_base_bg));

        } else {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.white));

        }


        return convertView;
    }


    static class ViewHolder {
        TextView time;
        TextView content;
        TextView year;

        public ViewHolder(View convertView) {
            year = ((TextView) convertView.findViewById(R.id.parent_frag_three_lv_item_year));
            time = ((TextView) convertView.findViewById(R.id.parent_frag_three_lv_item_time));
            content = ((TextView) convertView.findViewById(R.id.parent_frag_three_lv_item_content));
        }


    }


}
