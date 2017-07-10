package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 呼延 on 2016/3/22.
 * 移动查房模块,Act标题栏的pooup里面listview的适配器
 */
public class PopListViewAdapter extends BaseAdapter {

    private List<DCDepartment.ValuesBean> data;
    private Context context;
    private LayoutInflater inflater;

    public PopListViewAdapter(List<DCDepartment.ValuesBean> data, Context context) {
        inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }

    }

    public void addRes(List<DCDepartment.ValuesBean> data) {
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
            convertView = inflater.inflate(R.layout.popup_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }

        holder.name.setText(data.get(position).getName().toString());


        return convertView;
    }

    static class ViewHolder {

        TextView name;

        public ViewHolder(View convertView) {
            name = ((TextView) convertView.findViewById(R.id.name_item));
        }


    }

}
