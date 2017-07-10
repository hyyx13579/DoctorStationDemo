package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.IntentPatientList;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：呼延 on 2016/5/4 09:42
 * 邮箱：huyanyx@founder.com.cn
 */
public class PatitentSelectListViewAdaptet extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<IntentPatientList.ValuesBean> data;

    public PatitentSelectListViewAdaptet(Context context, List<IntentPatientList.ValuesBean> data) {
        inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }


    public void addRes(List<IntentPatientList.ValuesBean> data) {
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
            convertView = inflater.inflate(R.layout.patitentselectlistview_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }
        holder.name.setText(data.get(position).getName());
        holder.age.setText(data.get(position).getAge());
        holder.diagnosis.setText(data.get(position).getOutDiagnosis());
        holder.money.setText("" + data.get(position).getDeposit());
        holder.time.setText(Url.inpatientPermitDateToSimpleDate(data.get(position).getInpatientPermitDate()));


        return convertView;
    }

    static class ViewHolder {

        TextView name;
        TextView age;
        TextView diagnosis;
        TextView money;
        TextView time;

        public ViewHolder(View convertView) {
            name = ((TextView) convertView.findViewById(R.id.name_patientselect));
            age = ((TextView) convertView.findViewById(R.id.age_patientselect));
            diagnosis = ((TextView) convertView.findViewById(R.id.diagnosis_patientselect));
            money = ((TextView) convertView.findViewById(R.id.money_patientselect));
            time = ((TextView) convertView.findViewById(R.id.time_patientselect));
        }


    }


}
