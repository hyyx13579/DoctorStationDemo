package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCTestOfPatientHR;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by 呼延 on 2016/4/8.
 * <p/>
 * 移动查房模块-检验报告功能-StickListViewAdapter
 */
public class MyStickListViewAdapterInspectionReport extends BaseAdapter implements StickyListHeadersAdapter {


    private Context context;
    private List<DCTestOfPatientHR.ValuesBean> data;
    private LayoutInflater inflater;

    private List<String> strData;
    private List<Integer> intData;
    public static final String TAG = MyStickListViewAdapterInspectionReport.class.getSimpleName();


    public MyStickListViewAdapterInspectionReport(Context context, List<DCTestOfPatientHR.ValuesBean> data) {
        inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        if (data != null) {
            this.data = data;
            deal(this.data);
        } else {
            this.data = new ArrayList<>();
        }

    }

    public void addRes(List<DCTestOfPatientHR.ValuesBean> data) {
        Log.e(TAG, "addRes");
        if (data != null) {
            this.data.addAll(data);
            deal(this.data);
            notifyDataSetChanged();

        }

    }

    public void updateRes(List<DCTestOfPatientHR.ValuesBean> data) {
        Log.e(TAG, "updateRes");
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            deal(this.data);
            notifyDataSetChanged();


        }
    }

    /**
     * 对数据进行排序处理
     * 返回负数表示：lhs 小于rhs，
     * 返回0 表示：rhs和rhs相等，
     * 返回正数表示：rhs大于rhs。
     */

    private void deal(List<DCTestOfPatientHR.ValuesBean> data) {
        Collections.sort(data, new Comparator<DCTestOfPatientHR.ValuesBean>() {
            @Override
            public int compare(DCTestOfPatientHR.ValuesBean lhs, DCTestOfPatientHR.ValuesBean rhs) {

                //用SimpleFormate处理后data可比较大小
                if (strToDate(lhs.getApplyDate()).getTime() < strToDate(rhs.getApplyDate()).getTime()) {
                    return 1;
                }
                if (strToDate(lhs.getApplyDate()).getTime() == strToDate(rhs.getApplyDate()).getTime()) {
                    return 0;
                }


                return -1;
            }
        });

        strData = getStrData(data);
        intData = getIntData(data);

        Log.e(TAG, "strData----" + strData.toString());
        Log.e(TAG, "intData----" + intData.toString());


    }

    /**
     * 获取ListviewHeader的标题字符串数组
     */

    private List<String> getStrData(List<DCTestOfPatientHR.ValuesBean> data) {

        /**
         *测试
         * */
        Log.e(TAG, "size-----" + data.size());
        if (data.size() > 1) {
            Log.e(TAG, "data-----" + dealString(data.get(0).getApplyDate()) + "----" + dealString(data.get(1).getApplyDate()));
        } else {
            if (data.size() == 1) {
                Log.e(TAG, "data-----" + dealString(data.get(0).getApplyDate()));
            } else if (data.size() == 0) {
                Log.e(TAG, "data-----00000");
            }


        }
        /**
         *
         * 正常逻辑代码
         *
         * */
        strData = new ArrayList<>();
        int size = 0;
        for (
                int i = 0;
                i < data.size(); i++)

        {
            if (i != 0 && !dealString(data.get(i).getApplyDate()).equals(dealString(data.get(i - 1).getApplyDate()))) {
                // Log.e(TAG, "getStrData----------Big---if");
                strData.add(dealString(data.get(i - 1).getApplyDate()) + " " + "[" + size + "]" + "项");
                size = 1;
                if (i == data.size() - 1) {
                    strData.add(dealString(data.get(i).getApplyDate()) + " " + "[" + size + "]" + "项");
                    // Log.e(TAG, "getStrData----------small------if");
                }
            } else if (i == data.size() - 1) {
                size++;
                strData.add(dealString(data.get(i).getApplyDate()) + " " + "[" + size + "]" + "项");
                // Log.e(TAG, "getStrData----------else if");

            } else {
                size++;
                // Log.e(TAG, "getStrData----------else");
            }

        }

        return strData;
    }

    /**
     * 获取ListviewHeader的标题字符串下角标数组
     */

    private List<Integer> getIntData(List<DCTestOfPatientHR.ValuesBean> data) {

        intData = new ArrayList<>();
        intData.add(0);
        for (int i = 0; i < data.size(); i++) {
            if (i != 0 && !dealString(data.get(i).getApplyDate()).equals(dealString(data.get(i - 1).getApplyDate()))) {
                intData.add(i);
            }
        }

        return intData;
    }


    /**
     * 使用SimpleDataFormat对String类型的数据处理,处理后Date可以进行大小的对比
     */

    private Date strToDate(String str) {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = simple.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 截取字符串，从 2013-02-08 09：01：42 到 2013-02-08
     */
    private String dealString(String str) {
        String newStr = str.substring(0, str.indexOf(" "));
        return newStr;
    }


    /**
     * StickyListHeadersAdapter的方法
     */
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        // Log.e(TAG, "getHeaderView----");
        HeaderViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.stick_listview_header_item, parent, false);
            holder = new HeaderViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((HeaderViewHolder) convertView.getTag());
        }
        for (int i = 0; i < intData.size(); i++) {
            if (intData.get(i) == position) {
                holder.title.setText(strData.get(i));
            }
        }

        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        Long aLong = Long.parseLong(dealString(data.get(position).getApplyDate()).replace("-", ""));
        return aLong;
    }

    static class HeaderViewHolder {

        TextView title;

        public HeaderViewHolder(View convertView) {
            title = ((TextView) convertView.findViewById(R.id.stick_lv_header_item_tv));
        }

    }


    /**
     * BaseAdapter的方法
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
        holder.content.setText(data.get(position).getContent());
        return convertView;
    }

    static class ViewHolder {

        TextView content;

        public ViewHolder(View convertView) {
            content = ((TextView) convertView.findViewById(R.id.stick_lv_item_tv));
        }

    }


}
