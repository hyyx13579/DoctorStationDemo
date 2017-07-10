package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCTestOfPatientHR;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 呼延 on 2016/4/15.
 */
public class InspectionReportDetailsAdapter extends BaseAdapter {

    private List<DCTestOfPatientHR.ValuesBean.TestItemListBean> data;
    private Context context;
    private LayoutInflater inflater;
    public static final String TAG = InspectionReportDetailsAdapter.class.getSimpleName();

    public InspectionReportDetailsAdapter(List<DCTestOfPatientHR.ValuesBean.TestItemListBean> data, Context context) {
        inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        this.context = context;
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
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.inspection_report_details_listview_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = ((ViewHolder) view.getTag());
        }

        holder.id.setText((i + 1) + "");
        holder.projectName.setText(data.get(i).getProjectName());


        try {
            if (data.get(i).getResult().equals("高")) {
                holder.resultValue.setBackgroundColor(Color.parseColor("#F07D7A"));
                holder.resultValue.setTextColor(context.getResources().getColor(R.color.white));
            } else if (data.get(i).getResult().equals("低")) {
                holder.resultValue.setBackgroundColor(Color.parseColor("#60AFDE"));
                holder.resultValue.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                holder.resultValue.setBackgroundColor(Color.parseColor("#00000000"));
                holder.resultValue.setTextColor(context.getResources().getColor(R.color.text_normal_color));
            }

        } catch (NullPointerException e) {
            Log.e(TAG, "" + e);
        }

        holder.resultValue.setText(data.get(i).getReusltValue());
        if (!TextUtils.isEmpty(((String) data.get(i).getReferenceNormal()))){
            holder.referenceNormal.setText(((String) data.get(i).getReferenceNormal()));
        }else {
            holder.referenceNormal.setText("<");

        }


        holder.result.setText(data.get(i).getResult());


        return view;
    }


    static class ViewHolder {
        TextView id;
        TextView projectName;
        TextView resultValue;
        TextView referenceNormal;
        TextView result;

        public ViewHolder(View view) {
            id = ((TextView) view.findViewById(R.id.id_insp_report_detais_lv_item));
            projectName = ((TextView) view.findViewById(R.id.projectname_insp_report_detais_lv_item));
            resultValue = ((TextView) view.findViewById(R.id.resultvalue_insp_report_detais_lv_item));
            referenceNormal = ((TextView) view.findViewById(R.id.referencenormal_insp_report_detais_lv_item));
            result = ((TextView) view.findViewById(R.id.result_insp_report_detais_lv_item));

        }


    }


}
