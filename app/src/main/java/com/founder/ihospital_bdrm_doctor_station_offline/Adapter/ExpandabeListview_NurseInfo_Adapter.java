package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Activity.ExpendableListViewItemContentActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCClinicalRecord;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.NursingInforGruopBean;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.View.MyGridView;
import com.founder.ihospital_bdrm_doctor_station_offline.View.MyListView;

import java.util.ArrayList;
import java.util.List;

public class ExpandabeListview_NurseInfo_Adapter extends BaseExpandableListAdapter {

    private List<NursingInforGruopBean> data;
    private Context context;
    private LayoutInflater inflater;


    public ExpandabeListview_NurseInfo_Adapter(List<NursingInforGruopBean> data, Context context) {
        this.context = context;
        inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }


    public void upRes(List<NursingInforGruopBean> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }


    @Override
    public int getGroupCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;//返回值写为1,否则数据会重复
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;//保证item元素的稳定性
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GruopViewHolder groupHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expandable_listview_group_nurseinfo_item, parent, false);
            groupHolder = new GruopViewHolder(convertView);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GruopViewHolder) convertView.getTag();
        }
        groupHolder.groupContent.setText(data.get(groupPosition).getGruopName());
        groupHolder.groupContent.setTextColor(context.getResources().getColor(R.color.titlecolor));
        groupHolder.img.setImageResource(R.mipmap.icon_open);
        if (!isExpanded) {
            groupHolder.img.setImageResource(R.mipmap.icon_close);
            groupHolder.groupContent.setTextColor(context.getResources().getColor(R.color.text_normal_color));

        }

        return convertView;
    }


    static class GruopViewHolder {

        TextView groupContent;
        ImageView img;


        public GruopViewHolder(View convertView) {
            groupContent = ((TextView) convertView.findViewById(R.id.expand_listview_nurseinfo_group_item_content));
            img = ((ImageView) convertView.findViewById(R.id.expand_listview_nurseinfo_group_item_img));
        }

    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expandable_listview_nurseinfo_child_listview, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = ((ChildViewHolder) convertView.getTag());
        }


        ExpanableListView_NurseInfo_ListView_Adapter adapter = new ExpanableListView_NurseInfo_ListView_Adapter(data.get(groupPosition).getGrouoInfo(), inflater);
        childViewHolder.lv.setAdapter(adapter);


        return convertView;
    }

    static class ChildViewHolder {

        MyListView lv;

        public ChildViewHolder(View convertView) {
            lv = ((MyListView) convertView.findViewById(R.id.expandable_child_lv));
        }

    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;//给child添加点击事件,返回true
    }
}
