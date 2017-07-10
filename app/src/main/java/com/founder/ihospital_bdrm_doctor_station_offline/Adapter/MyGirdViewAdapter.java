package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCDepartmentOfUser;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 呼延 on 2016/3/25.
 * 自定义GridView的适配器
 */
public class MyGirdViewAdapter extends BaseAdapter {

    private List<DCDepartmentOfUser.ValuesBean> data;
    private Context context;
    private LayoutInflater inflater;


    public MyGirdViewAdapter(List<DCDepartmentOfUser.ValuesBean> data, Context context) {
        inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        if (data != null) {
            this.data = data;
        } else {
                this.data = new ArrayList<>();
        }

    }

    public void addRes(List<DCDepartmentOfUser.ValuesBean> data) {
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
            convertView = inflater.inflate(R.layout.mygridview_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }

        holder.content.setText(data.get(position).getName());
        DCDepartmentOfUser.ValuesBean valuesBean = data.get(position);


        return convertView;
    }

    static class ViewHolder {
        TextView content;

        public ViewHolder(View convertView) {
            content = ((TextView) convertView.findViewById(R.id.mygrid_item_textview));
        }


    }

}
