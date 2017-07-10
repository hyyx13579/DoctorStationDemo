package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Activity.ClickPatientMemorandumCardActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.PatientNoteBookRecyclerAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCDoctorMemorandum;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ReadPlistFile;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.founder.ihospital_bdrm_doctor_station_offline.Adapter.PatientNoteBookRecyclerAdapter.OnRecyclerViewListener;

/**
 * Created by 呼延 on 2016/3/30.
 * UpDate by zhangqun
 * 移动查房模块-患者备忘功能
 */
public class ParentInfo_Frament_Memorandum extends BaseFragment {

    private Map<String, List<DCDoctorMemorandum.ValuesBean>> timesBean;
    private Gson gson;
    private Spinner spinner;//用来展示患者备忘信息的日期
    private RecyclerView recyclerView;//用来显示患者备忘信息的列表
    private List<DCDoctorMemorandum.ValuesBean> data;//暂时拟定的测试数据
    private List<String> times;
    private PatientNoteBookRecyclerAdapter adapter;
    private ArrayAdapter<String> spinnerAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.parent_info_frag_memorandum, container, false);
        spinner = ((Spinner) layout.findViewById(R.id.patientNoteBookSpinner));
        recyclerView = ((RecyclerView) layout.findViewById(R.id.patientNoteBookRecyclerView));
        data = new ArrayList<DCDoctorMemorandum.ValuesBean>();
        times = new ArrayList<String>();
        adapter = new PatientNoteBookRecyclerAdapter(getActivity(), data);
        initView();//初始化控件
        createData();//创建模拟数据
        return layout;
    }

    /**
     * 判断list time是否含有String item
     *
     * @param times List<String>
     * @param item  String
     * @return
     */
    public boolean isExist(List<String> times, String item) {
        boolean isExist = false;
        for (int i = 0; i < times.size(); i++) {
            if (times.get(i).equals(item)) {
                isExist = true;
            }
        }
        return isExist;
    }

    private void createData() {
        timesBean = new HashMap<String, List<DCDoctorMemorandum.ValuesBean>>();
        RequestParams params = new RequestParams("http://172.27.1.58:9001/api/DoctorServer/GetDoctorMemorandumList");
        params.addBodyParameter("patientID", "29281256");
        params.addBodyParameter("visitID", "1");
        params.addBodyParameter("doctorName", "");
        x.http().post(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                Log.i("https", "onSuccess: " + result);
                DCDoctorMemorandum bean = gson.fromJson(result, DCDoctorMemorandum.class);
                data.clear();
                data.addAll(bean.getValues());
                adapter.notifyDataSetChanged();
                if (!data.isEmpty()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/M/d");
                    try {
                        for (int i = 0; i < data.size(); i++) {
                            Date parse = sdf.parse(data.get(i).getCreateTime());
                            String format = sdf1.format(parse);
                            if (!isExist(times, format)) {
                                times.add(format);
                                timesBean.put(format, new ArrayList<DCDoctorMemorandum.ValuesBean>());
                                timesBean.get(format).add(data.get(i));
                            } else {
                                timesBean.get(format).add(data.get(i));
                            }
                        }
                    } catch (ParseException e) {

                    }
                }
                spinnerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
//                String str = "{\"Status\":1,\"Values\":[{\"GUID\":\"E45FBDB6-4B65-4914-BA31-2FA3DE86EFA8\",\"PatientID\":\"29591075\",\"VisitID\":\"1\",\"Title\":\"病情变更\",\"Content\":\"病情恶化\",\"CreateTime\":\"2016/5/16 17:09:27\",\"CreateDoctorName\":\"测试\",\"SubMemorandumList\":[{\"GUID\":null,\"MainID\":\"E45FBDB6-4B65-4914-BA31-2FA3DE86EFA8\",\"FileType\":\"1\",\"FileName\":\"2f38b36ec7fb4fcbb610958f96ef6433.mp3\",\"FilePath\":\"29591075_1\",\"FileBytes\":null,\"CreateTime\":\"2016/5/16 17:09:27\",\"FileUrl\":\"http://172.27.1.58:9001/Resources/MemorandumUpload/29591075_1/2f38b36ec7fb4fcbb610958f96ef6433.mp3\"}]},{\"GUID\":\"F3909AFD-F801-447C-B883-B7C61B1AD193\",\"PatientID\":\"29591075\",\"VisitID\":\"1\",\"Title\":\"主任查房\",\"Content\":\"今天病情稳定\",\"CreateTime\":\"2016/5/16 17:05:27\",\"CreateDoctorName\":\"测试\",\"SubMemorandumList\":[]}]}";
//                DCDoctorMemorandum bean = gson.fromJson(str, DCDoctorMemorandum.class);
//                data.clear();
//                data.addAll(bean.getValues());
//                adapter.notifyDataSetChanged();
//                if (!data.isEmpty()) {
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/M/d");
//                    try {
//                        for (int i = 0; i < data.size(); i++) {
//                            Date parse = sdf.parse(data.get(i).getCreateTime());
//                            String format = sdf1.format(parse);
//                            if (!isExist(times, format)) {
//                                times.add(format);
//                                timesBean.put(format, new ArrayList<DCDoctorMemorandum.ValuesBean>());
//                                timesBean.get(format).add(data.get(i));
//                            } else {
//                                timesBean.get(format).add(data.get(i));
//                            }
//                        }
//                    } catch (ParseException e) {
//
//                    }
//                }
//                spinnerAdapter.notifyDataSetChanged();

                getDataOffLine();

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        spinnerAdapter.notifyDataSetChanged();
    }


    private void getDataOffLine() {
        Bundle arguments = getArguments();
        DCPatientHROfDepartment.ValuesBean valus =
                (DCPatientHROfDepartment.ValuesBean) arguments.getSerializable("data");
        DCDoctorMemorandum bean = null;

        switch (valus.getID()) {
            case "29379827":
                bean = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetDoctorMemorandumList", "29379827"), DCDoctorMemorandum.class);
                break;

            case "29542151":
                bean = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetDoctorMemorandumList", "29542151"), DCDoctorMemorandum.class);
                break;

            case "29554021":
                bean = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetDoctorMemorandumList", "29554021"), DCDoctorMemorandum.class);
                break;

            case "29555473":
                bean = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetDoctorMemorandumList", "29555473"), DCDoctorMemorandum.class);
                break;
            case "29557134":
                bean = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetDoctorMemorandumList", "29557134"), DCDoctorMemorandum.class);
                break;
            case "29591075":
                bean = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetDoctorMemorandumList", "29591075"), DCDoctorMemorandum.class);
                break;


        }

        data.clear();
        data.addAll(bean.getValues());
        adapter.notifyDataSetChanged();
        if (!data.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/M/d");
            try {
                for (int i = 0; i < data.size(); i++) {
                    Date parse = sdf.parse(data.get(i).getCreateTime());
                    String format = sdf1.format(parse);
                    if (!isExist(times, format)) {
                        times.add(format);
                        timesBean.put(format, new ArrayList<DCDoctorMemorandum.ValuesBean>());
                        timesBean.get(format).add(data.get(i));
                    } else {
                        timesBean.get(format).add(data.get(i));
                    }
                }
            } catch (ParseException e) {

            }
        }
        spinnerAdapter.notifyDataSetChanged();


    }

    private void initView() {
        gson = new Gson();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        int spacingInPixels = 30;
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        recyclerView.setAdapter(adapter);
        //RecyclerView的Item点击事件
        adapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {
            @Override
            public void OnItemClick(View view, int position) {
                //准备跳转的界面
                // Toast.makeText(getActivity(), "这是第：" + position + "个", Toast.LENGTH_SHORT).show();
                DCDoctorMemorandum.ValuesBean itemData = adapter.getItemData(position);
                Intent intent = new Intent(getContext(), ClickPatientMemorandumCardActivity.class);
                intent.putExtra("itemData", itemData);
                startActivity(intent);



            }
        });
        spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, times);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                data.clear();
                data.addAll(timesBean.get(times.get(position)));
                adapter.notifyDataSetChanged();
                TextView tv = (TextView) view;
                tv.setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 继承RecyclerView固有类，设置RecyclerView的Item的属性，装饰
     */
    class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        /**
         * @param outRect 偏移量
         * @param view
         * @param parent
         * @param state
         */
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            if (parent.getChildPosition(view) == 0)
                outRect.top = space;
        }
    }


}

