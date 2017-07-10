package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCTestOfPatientHR;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 呼延 on 2016/4/21.
 */
public class InspectionReportDetailsAnalysisAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<DCTestOfPatientHR.ValuesBean.TestItemListBean> data;
    private AnalysisAdapterItemClickCallback mItemClickCallback;
    private List<Integer> mClickedList;

    public InspectionReportDetailsAnalysisAdapter(Context context, List<DCTestOfPatientHR.ValuesBean.TestItemListBean> data, AnalysisAdapterItemClickCallback callback) {
        this.mItemClickCallback = callback;
        this.mContext = context;
        inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        mClickedList = new ArrayList<>();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = inflater.inflate(R.layout.activity_inspection_report_details_analysis_lv_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = ((ViewHolder) view.getTag());
        }

        int pos = i + 1;
        holder.id.setText("" + pos);
        holder.name.setText(data.get(i).getProjectName());
        holder.value.setText(((String) data.get(i).getReferenceNormal()));
        if (mClickedList.contains((Integer) i)) {
            holder.ly.setBackgroundColor(mContext.getResources().getColor(R.color.titlecolor));
            holder.id.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.name.setTextColor(mContext.getResources().getColor(R.color.white));
            holder.value.setTextColor(mContext.getResources().getColor(R.color.white));
        } else {
            holder.ly.setBackgroundColor(Color.parseColor("#00000000"));
            holder.id.setTextColor(mContext.getResources().getColor(R.color.text_normal_color));
            holder.name.setTextColor(mContext.getResources().getColor(R.color.text_normal_color));
            holder.value.setTextColor(mContext.getResources().getColor(R.color.text_normal_color));
        }
        holder.ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickCallback.onItemClick(data.get(i).getProjectName());
                if (mClickedList.contains((Integer) i)) {
                    mClickedList.remove((Integer) i);
                } else {
                    mClickedList.add((Integer) i);
                }
                InspectionReportDetailsAnalysisAdapter.this.notifyDataSetChanged();
            }
        });

        return view;
    }

    static class ViewHolder {

        LinearLayout ly;
        TextView id;
        TextView name;
        TextView value;


        public ViewHolder(View view) {
            ly = (LinearLayout) view.findViewById(R.id.insp_report_detais_analysis_ly);
            id = ((TextView) view.findViewById(R.id.id_insp_report_detais_analysis_lv_item));
            name = ((TextView) view.findViewById(R.id.name_insp_report_detais_analysis_lv_item));
            value = ((TextView) view.findViewById(R.id.vlaue_insp_report_detais_analysis_lv_item));


        }
    }

    public interface AnalysisAdapterItemClickCallback {
        public void onItemClick(String name);
    }
}
