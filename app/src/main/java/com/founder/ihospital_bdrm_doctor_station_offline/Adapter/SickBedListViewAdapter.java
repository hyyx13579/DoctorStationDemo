package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.BedInfo;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：呼延 on 2016/5/4 09:42
 * 邮箱：huyanyx@founder.com.cn
 */
public class SickBedListViewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<BedInfo.ValuesBean> data;

    public SickBedListViewAdapter(Context context, List<BedInfo.ValuesBean> data) {
        inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }


    public void addRes(List<BedInfo.ValuesBean> data) {
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
            convertView = inflater.inflate(R.layout.bed_select_listview_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }

        holder.bedNo.setText(data.get(position).getBedNO());
        holder.bedStyle.setText(data.get(position).getBedTypeName());
        holder.monitor.setText(data.get(position).getMonitor());
        holder.bedPrice.setText(data.get(position).getBedPrice());
        holder.window.setText(data.get(position).getWindow());


        return convertView;
    }

    static class ViewHolder {

        TextView bedNo;
        TextView bedStyle;
        TextView monitor;
        TextView bedPrice;
        TextView window;


        public ViewHolder(View convertView) {
            bedNo = ((TextView) convertView.findViewById(R.id.bedNo_bed_select_listview_item));
            bedStyle = ((TextView) convertView.findViewById(R.id.bedStyle_bed_select_listview_item));
            monitor = ((TextView) convertView.findViewById(R.id.monitor_bed_select_listview_item));
            bedPrice = ((TextView) convertView.findViewById(R.id.bedPrice_bed_select_listview_item));
            window = ((TextView) convertView.findViewById(R.id.window_bed_select_listview_item));


        }


    }


}
