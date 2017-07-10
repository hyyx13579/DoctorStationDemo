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
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.View.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 呼延 on 2016/3/31.
 * <p/>
 * 移动查房模块-电子病例功能-ExpandabeListviewAdapter
 */
public class ExpandabeListviewAdapter extends BaseExpandableListAdapter implements AdapterView.OnItemClickListener {

    private List<DCClinicalRecord.ValuesBean> data;
    private Context context;
    private LayoutInflater inflater;
    private DCClinicalRecord.ValuesBean.SubMedicalRecordBean values;

    public ExpandabeListviewAdapter(List<DCClinicalRecord.ValuesBean> data, Context context) {
        this.context = context;
        inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
    }

    public void addRes(List<DCClinicalRecord.ValuesBean> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void upRes(List<DCClinicalRecord.ValuesBean> data) {
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
            convertView = inflater.inflate(R.layout.expandable_listview_group_item, parent, false);
            groupHolder = new GruopViewHolder(convertView);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GruopViewHolder) convertView.getTag();
        }
        groupHolder.groupContent.setText(data.get(groupPosition).getTitle());
        groupHolder.img.setImageResource(R.mipmap.expand_open);
        if (!isExpanded) {
            groupHolder.img.setImageResource(R.mipmap.expand_close);
        }

        return convertView;
    }


    static class GruopViewHolder {

        TextView groupContent;
        ImageView img;


        public GruopViewHolder(View convertView) {
            groupContent = ((TextView) convertView.findViewById(R.id.expand_listview_group_item_content));
            img = ((ImageView) convertView.findViewById(R.id.expand_listview_group_item_img));
        }

    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expandable_listview_child_gridview, parent, false);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = ((ChildViewHolder) convertView.getTag());
        }

        ExpanableListView_GridView_Adapter expanableListView_gridView_adapter = new ExpanableListView_GridView_Adapter(null, inflater);
        expanableListView_gridView_adapter.addRes(data.get(groupPosition).getSubMedicalRecord());
        childViewHolder.gridView.setAdapter(expanableListView_gridView_adapter);
        childViewHolder.gridView.setOnItemClickListener(this);


        return convertView;
    }

    static class ChildViewHolder {

        MyGridView gridView;

        public ChildViewHolder(View convertView) {
            gridView = ((MyGridView) convertView.findViewById(R.id.expandable_child_gridview));
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        values = ((DCClinicalRecord.ValuesBean.SubMedicalRecordBean) parent.getItemAtPosition(position));
        String content = values.getContent();
        String groupKey = values.getGroupKey();
        Intent intent = new Intent(context, ExpendableListViewItemContentActivity.class);
        intent.putExtra("content", content);
        intent.putExtra("groupKey",groupKey);
        context.startActivity(intent);

    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;//给child添加点击事件,返回true
    }
}
