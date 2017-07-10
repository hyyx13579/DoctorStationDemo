package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Activity.AnesthesiaRecordImageActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.AnesthesiaRecordBean.ValuesBean;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bin_li on 16/5/5.
 * 麻醉记录fragment 的Adapter
 */
public class AnesthesiaRecordListAdapter extends BaseAdapter {
    private Context mContext;
    private List<ValuesBean> list;

    public AnesthesiaRecordListAdapter(Context context, List<ValuesBean> mList) {
        this.mContext = context;
        if (list != null) {
            this.list = mList;
        }
        this.list = new ArrayList<>();

    }

    public void setDataList(List<ValuesBean> list) {
        if (list != null) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();


        }
    }

    @Override
    public int getCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_anesthesia_record_list, null);
            holder.view = view;
            holder.mTextView = (TextView) view.findViewById(R.id.item_anesthesia_record_time_text);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mTextView.setText(list.get(i).dataTime);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, AnesthesiaRecordImageActivity.class);
                intent.putExtra(AnesthesiaRecordImageActivity.EXTRA_DOCUMENT_ID, list.get(i).documentID);
                intent.putExtra(AnesthesiaRecordImageActivity.EXTRA_PATIENT_NAME, "人民医院");
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    class ViewHolder {
        public View view;
        public TextView mTextView;
    }
}
