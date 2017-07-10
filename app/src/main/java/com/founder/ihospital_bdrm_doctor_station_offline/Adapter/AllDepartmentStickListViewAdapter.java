package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.AddExtraValuesDCDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * 作者：呼延 on 2016/5/12 16:16
 * 邮箱：huyanyx@founder.com.cn
 */
public class AllDepartmentStickListViewAdapter extends BaseAdapter implements StickyListHeadersAdapter {


    private List<AddExtraValuesDCDepartment> data;
    private Context context;
    private LayoutInflater inflater;
    private List<Integer> integers;
    public static final String TAG = AllDepartmentStickListViewAdapter.class.getSimpleName();


    public AllDepartmentStickListViewAdapter(List<AddExtraValuesDCDepartment> data, Context context) {

        inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        if (data != null) {
            this.data = data;

        } else {
            this.data = new ArrayList<>();
        }

    }


    public void addRes(List<AddExtraValuesDCDepartment> data) {
        if (data != null) {
            this.data.addAll(data);

            notifyDataSetChanged();

        }

    }


    public void updateRes(List<AddExtraValuesDCDepartment> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();


        }
    }


    private List<Integer> getIntData(List<AddExtraValuesDCDepartment> data) {

        integers = new ArrayList<>();
        integers.add(0);
        for (int i = 0; i < data.size(); i++) {
            if (i != 0 && !data.get(i).getTitleFlag().equals(data.get(i - 1).getTitleFlag())) {
                integers.add(i);
                Log.e(TAG, "" + i);
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
            convertView = inflater.inflate(R.layout.alldepartment_expandable_listview_group_item, parent, false);
            holder = new HeaderViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((HeaderViewHolder) convertView.getTag());
        }

        holder.title.setText(data.get(position).getTitleFlag());
        holder.title.setBackgroundResource(R.color.btn_gray);

        return convertView;
    }

    @Override
    public long getHeaderId(int position) {


        return Long.valueOf(data.get(position).getTitleFlag().toCharArray()[0]).longValue();

    }


    static class HeaderViewHolder {

        TextView title;

        public HeaderViewHolder(View convertView) {
            title = ((TextView) convertView.findViewById(R.id.alldepartment_expand_listview_group_item_title));
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
            convertView = inflater.inflate(R.layout.alldepartment_expandable_listview_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }


        holder.content.setText(data.get(position).getName());

        return convertView;
    }

    static class ViewHolder {

        TextView content;

        public ViewHolder(View convertView) {
            content = ((TextView) convertView.findViewById(R.id.alldepartment_expand_listview_item_content));
        }

    }


}
