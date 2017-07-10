package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DealWardformation;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：呼延 on 2016/5/4 09:42
 * 邮箱：huyanyx@founder.com.cn
 */
public class SickRoomListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<DealWardformation> data;

    public SickRoomListViewAdapter(Context context, List<DealWardformation> data) {
        inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }


    public void addRes(List<DealWardformation> data) {
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
        ViewHolder holder= null;
        if (convertView==null){
            convertView = inflater.inflate(R.layout.sickroom_listview_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = ((ViewHolder) convertView.getTag());
        }

         holder.content.setText(data.get(position).getWardName());


        return convertView;
    }

    static class ViewHolder {

         TextView content;

        public ViewHolder(View convertView) {
            content = ((TextView) convertView.findViewById(R.id.name_sickroom_lv_item));

        }


    }


}
