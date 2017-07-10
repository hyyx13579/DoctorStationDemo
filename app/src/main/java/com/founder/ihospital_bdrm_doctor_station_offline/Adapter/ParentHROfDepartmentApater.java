package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.CommonBean;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Contant;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.MyDbHelper;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.SPUtils;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by 呼延 on 2016/3/29.
 * 移动查房，各科室下的病人
 */
public class ParentHROfDepartmentApater extends BaseAdapter {

    private final MyDbHelper myDbHelper;
    private List<DCPatientHROfDepartment.ValuesBean> data;
    private Context context;
    private LayoutInflater inflater;
    private boolean isCollection = true;

    public static final String TAG = ParentHROfDepartmentApater.class.getSimpleName();
    private String doctor_id;
    private String colPatientInfo;

    public static final String COLLECTION_LIST = "collection_list";

    //private int clickPos;


    public ParentHROfDepartmentApater(List<DCPatientHROfDepartment.ValuesBean> data, Context context) {
        this.context = context;
        inflater = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        doctor_id = SPUtils.get(context, Contant.USERID, "").toString();
        myDbHelper = new MyDbHelper(context);


    }

    public void addRes(List<DCPatientHROfDepartment.ValuesBean> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }

    }

    public void updateRes(List<DCPatientHROfDepartment.ValuesBean> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();

        }

    }

    public void setEmptyData() {
        this.data.clear();
        notifyDataSetChanged();

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_mr_frag_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }

        holder.name.setText(data.get(position).getName());
        holder.bedNo.setText(data.get(position).getBedNo() + "床");
        holder.id.setText(data.get(position).getID());
        holder.age.setText(data.get(position).getAge());
        //  String[] split = data.get(position).getEnterDate().split(" ");
        holder.enterDate.setText(data.get(position).getInHosDay() + "天");

        holder.tvSex.setText(data.get(position).getSex() + ",");

        if (TextUtils.isEmpty(data.get(position).getWardName())) {
            holder.tvWardName.setText("无");
        } else {
            holder.tvWardName.setText(data.get(position).getWardName());
        }


        if (TextUtils.isEmpty(data.get(position).getDoctorInCharge())) {
            holder.doctorInCharge.setText("无");
        } else {
            holder.doctorInCharge.setText(data.get(position).getDoctorInCharge());
        }

        if (!TextUtils.isEmpty(data.get(position).getCurrentState()) && data.get(position).getCurrentState().equals("重")) {
            holder.level.setText("重");
            holder.level.setBackgroundResource(R.drawable.care_level_serious);
        } else {

            if (!TextUtils.isEmpty(data.get(position).getCareLevel())) {
                switch (data.get(position).getCareLevel()) {
                    case "一级护理":
                        holder.level.setText("一");
                        holder.level.setTextColor(context.getResources().getColor(R.color.care_level_one));
                        holder.level.setBackgroundResource(R.drawable.care_level_one);
                        break;

                    case "二级护理":
                        holder.level.setText("二");
                        holder.level.setTextColor(context.getResources().getColor(R.color.care_level_two));

                        holder.level.setBackgroundResource(R.drawable.care_level_two);
                        break;

                    case "三级护理":
                        holder.level.setText("三");
                        holder.level.setTextColor(context.getResources().getColor(R.color.care_level_three));

                        holder.level.setBackgroundResource(R.drawable.care_level_three);
                        break;
                    case "特级护理":
                        holder.level.setText("特");
                        holder.level.setTextColor(context.getResources().getColor(R.color.care_level_four));

                        holder.level.setBackgroundResource(R.drawable.care_level_four);
                        break;

                }
            }

        }
        switch (data.get(position).getIsCollection()) {
            case 0:
                holder.ivCollection.setBackgroundResource(R.mipmap.star_normal_xhdpi);
                holder.ivCollection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //clickPos = position;
                        LoadCollectPatient(position);

                    }
                });

                break;
            case 1:
                holder.ivCollection.setBackgroundResource(R.mipmap.star_selected_xhdpi);
                holder.ivCollection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //  clickPos = position;
                        LoadCancelCollectionPatient(position);
                    }
                });


                break;
        }


        return convertView;
    }


    private int status;

    /**
     * 添加收藏
     *
     * @param pos
     */
    private void LoadCollectPatient(final int pos) {

        String url = Url.DoctorServer + "CollectPatient";
        RequestParams requestParams = new RequestParams(url);
        requestParams.addBodyParameter("doctorID", doctor_id);
        //此处是patientId
        requestParams.addBodyParameter("visitSn", data.get(pos).getID());

        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            status = jsonObject.getInt("Status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Gson gson = new Gson();
                        if (status == 1) {
                            Url.ErrorToast(context, "收藏成功");
                            data.get(pos).setIsCollection(1);
                            notifyDataSetChanged();


                        } else if (status == 0) {
                            String values = gson.fromJson(result, CommonBean.class).getValues();
                            Url.ErrorToast(context, values);
                        }


                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e(TAG, "LoadData-onError-----" + ex);
                        data.get(pos).setIsCollection(1);
                        // getCollection(true, pos);
                        myDbHelper.insertUserInfo(data.get(pos).getDeptCode(), data.get(pos).getName(), new Gson().toJson(data.get(pos)));
                        myDbHelper.insertUserInfo(COLLECTION_LIST, data.get(pos).getName() + COLLECTION_LIST, new Gson().toJson(data.get(pos)));
                        myDbHelper.close();
                        notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                }

        );


    }


    /**
     * 取消收藏
     *
     * @param pos
     */
    private void LoadCancelCollectionPatient(final int pos) {
        String url = Url.DoctorServer + "CancelCollectionPatient";
        RequestParams requestParams = new RequestParams(url);
        requestParams.addBodyParameter("doctorID", doctor_id);
        //此处是patientId
        requestParams.addBodyParameter("visitSn", data.get(pos).getID());

        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {

                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            status = jsonObject.getInt("Status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Gson gson = new Gson();
                        if (status == 1) {
                            Url.ErrorToast(context, "取消收藏");
                            data.get(pos).setIsCollection(0);
                            notifyDataSetChanged();

                        } else if (status == 0) {
                            String values = gson.fromJson(result, CommonBean.class).getValues();
                            Url.ErrorToast(context, values);
                        }


                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e(TAG, "LoadData-onError-----" + ex);
                        data.get(pos).setIsCollection(0);
                        myDbHelper.insertUserInfo(data.get(pos).getDeptCode(), data.get(pos).getName(), new Gson().toJson(data.get(pos)));
                        myDbHelper.delete(data.get(pos).getName() + COLLECTION_LIST);
                        myDbHelper.close();
                        notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                }

        );
    }


    static class ViewHolder {


        TextView name;
        TextView bedNo;
        TextView id;
        TextView age;
        TextView enterDate;
        TextView doctorInCharge;
        TextView level;
        ImageView ivCollection;
        TextView tvSex;
        TextView tvWardName;

        public ViewHolder(View convertView) {
            name = ((TextView) convertView.findViewById(R.id.name_lv_mr_frag_item));
            bedNo = ((TextView) convertView.findViewById(R.id.badNo_lv_mr_frag_item));
            level = ((TextView) convertView.findViewById(R.id.level_lv_mr_frag_item));

            id = ((TextView) convertView.findViewById(R.id.HID_lv_mr_frag_item));
            age = ((TextView) convertView.findViewById(R.id.Age_lv_mr_frag_item));
            enterDate = ((TextView) convertView.findViewById(R.id.EnterDate_lv_mr_frag_item));
            doctorInCharge = ((TextView) convertView.findViewById(R.id.DoctorInCharge_lv_mr_frag_item));
            ivCollection = ((ImageView) convertView.findViewById(R.id.collection));
            tvSex = ((TextView) convertView.findViewById(R.id.sex_lv_mr_frag_item));
            tvWardName = ((TextView) convertView.findViewById(R.id.wardname_lv_mr_frag_item));


        }


    }
}
