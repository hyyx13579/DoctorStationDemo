package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.WardIn2formation;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * 作者：呼延 on 2016/4/27 10:42
 * 邮箱：huyanyx@founder.com.cn
 */
public class MyStickListViewAdapterWardSelect extends BaseAdapter implements StickyListHeadersAdapter {


    private List<WardIn2formation> data;
    private Context context;
    private LayoutInflater inflater;
    private List<Integer> integers;
    public static final String TAG = MyStickListViewAdapterWardSelect.class.getSimpleName();


    public MyStickListViewAdapterWardSelect(List<WardIn2formation> data, Context context) {

        inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        if (data != null) {
            this.data = data;
            deal(data);
        } else {
            this.data = new ArrayList<>();
        }

    }


    public void addRes(List<WardIn2formation> data) {
        if (data != null) {
            this.data.addAll(data);
            deal(data);
            notifyDataSetChanged();

        }

    }


    public void updateRes(List<WardIn2formation> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            deal(data);
            notifyDataSetChanged();


        }
    }

    /**
     * 对数据进行排序处理
     * 返回负数表示：lhs 小于rhs，
     * 返回0 表示：rhs和rhs相等，
     * 返回正数表示：rhs大于rhs。
     */


    public void deal(List<WardIn2formation> data) {
        Collections.sort(data, new Comparator<WardIn2formation>() {
            @Override
            public int compare(WardIn2formation lhs, WardIn2formation rhs) {

                if (Integer.parseInt(lhs.getDeptCode()) < Integer.parseInt(rhs.getDeptCode())) {
                    return 1;
                }
                if (Integer.parseInt(lhs.getDeptCode()) == Integer.parseInt(rhs.getDeptCode())) {
                    return 0;
                }


                return -1;
            }
        });
        for (int i = 0; i < data.size(); i++) {
            Log.e(TAG, "deal------------" + data.get(i).getDeptCode() + "------" + data.get(i).getDeptName() + "---" + data.get(i).getWardName()+"---" +data.get(i).getWardCode());
        }

      //  integers = getIntData(data);



    }

    private List<Integer> getIntData(List<WardIn2formation> data) {

        integers = new ArrayList<>();
        integers.add(0);
        for (int i = 0; i < data.size(); i++) {
            if (i != 0 && !data.get(i).getDeptCode().equals(data.get(i - 1).getDeptCode())) {
                integers.add(i);
                Log.e(TAG, ""+i);
            }
        }


        return integers;


    }

    /**
     * StickAdapter
     */

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.stick_listview_header_item, parent, false);
            holder = new HeaderViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((HeaderViewHolder) convertView.getTag());
        }

        holder.title.setText(data.get(position).getDeptName());


        // holder.title.setText(data.get(position).get("DeptName"));


        return convertView;
    }

    @Override
    public long getHeaderId(int position) {

        return Long.valueOf(data.get(position).getDeptCode()).longValue();
        //return position;
    }


    static class HeaderViewHolder {

        TextView title;

        public HeaderViewHolder(View convertView) {
            title = ((TextView) convertView.findViewById(R.id.stick_lv_header_item_tv));
        }

    }


    /**
     * 普通的Adapter
     */
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
            convertView = inflater.inflate(R.layout.stick_listview_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }


        holder.content.setText(data.get(position).getWardName());

        return convertView;
    }

    static class ViewHolder {

        TextView content;

        public ViewHolder(View convertView) {
            content = ((TextView) convertView.findViewById(R.id.stick_lv_item_tv));
        }

    }


}
