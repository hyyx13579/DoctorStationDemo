package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Activity.ParentInformationActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.ParentHROfDepartmentApater;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Contant;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.EventAllData;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.MyDbHelper;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.SPUtils;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 呼延 on 2016/3/23.
 * 为移动查房碎片里面viewpage下面的碎片,加载所有病区相对应的病人信息
 */
public class MobileRoundFragmentItemOne extends BaseFragment implements AdapterView.OnItemClickListener {

    private ParentHROfDepartmentApater parentHROfDepartmentApater;
    private ListView lv;
    private DCPatientHROfDepartment.ValuesBean values;
    public static final String TAG = MobileRoundFragmentItemOne.class.getSimpleName();
    public static final String PATIENT_INFO = "patient_info";
    /**
     * 带刷新的布局
     */
    private SwipeRefreshLayout swipeRefreshLayout;
    private int key;
    /**
     * 同病房下不同的状态的病人数据源
     */
    public static List<DCPatientHROfDepartment.ValuesBean> allData;
    private String format;
    private String wardName;
    private List<DCPatientHROfDepartment.ValuesBean> dealValue;
    private MyDbHelper myDbHelper;

    public static final String LookPaitentList = "look_patient";


    /**
     * 重写onEvent方法, 需要添加@Subscribe,否则可能注册不成功
     */
    @Subscribe
    public void onEvent(EventAllData data) {

        Log.e(TAG, "onEvent");
        dealValue = new ArrayList<>();
        allData = data.getData();
        wardName = data.getWardName();
        if (!TextUtils.isEmpty(wardName)) {
            if (wardName.equals("全部")) {
                if (allData.size() != 0) {
                    parentHROfDepartmentApater.updateRes(isValues(key, allData));

                } else {
                    parentHROfDepartmentApater.setEmptyData();
                }
            } else {
                for (int i = 0; i < allData.size(); i++) {
                    if (wardName.equals(allData.get(i).getWardName())) {
                        dealValue.add(allData.get(i));
                    }
                }
                parentHROfDepartmentApater.updateRes(isValues(key, dealValue));


            }
        } else {
            if (allData.size() != 0) {
                parentHROfDepartmentApater.updateRes(isValues(key, allData));

            } else {
                parentHROfDepartmentApater.setEmptyData();
            }
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.mrvp_frag, container, false);
        return layout;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    private void initView() {
        Date dt = new Date();
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
        format = matter1.format(dt);
        Bundle arguments = getArguments();
        key = arguments.getInt("key");
        Log.e(TAG, "" + key);
        lv = ((ListView) layout.findViewById(R.id.lv_mr_frag_item));
        parentHROfDepartmentApater = new ParentHROfDepartmentApater(null, getContext());
        lv.setAdapter(parentHROfDepartmentApater);
        lv.setOnItemClickListener(this);
        swipeRefreshLayout = ((SwipeRefreshLayout) layout.findViewById(R.id.swipeRefresh_mrvp_frag));
        Url.RefreshData(swipeRefreshLayout);
        allData = new ArrayList<>();
        setRefesh(allData);

        myDbHelper = new MyDbHelper(getContext());


    }


    private void setRefesh(final List<DCPatientHROfDepartment.ValuesBean> data) {
        if (!data.isEmpty()) {
            dealValue = new ArrayList<>();
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    if (!TextUtils.isEmpty(wardName)) {
                        if (wardName.equals("全部")) {
                            parentHROfDepartmentApater.updateRes(isValues(key, data));
                        } else {
                            for (int i = 0; i < data.size(); i++) {
                                if (wardName.equals(data.get(i).getWardName())) {
                                    dealValue.add(data.get(i));
                                }
                            }
                            parentHROfDepartmentApater.updateRes(isValues(key, dealValue));
                        }
                    }
                    swipeRefreshLayout.setRefreshing(false);
                }
            });


        } else {
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    swipeRefreshLayout.setRefreshing(false);
                }
            });

        }
    }


    private String DeptCode;
    private String BedNo;
    private String PatientName;
    private String Sex;
    private String DoctorID;
    private String VisitSn;
    private String DeptName;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), ParentInformationActivity.class);
        values = ((DCPatientHROfDepartment.ValuesBean) parent.getItemAtPosition(position));

        DeptCode = values.getDeptCode();
        BedNo = values.getBedNo();
        PatientName = values.getName();
        Sex = values.getSex();
        DoctorID = SPUtils.get(getContext(), Contant.USERID, "").toString();
        VisitSn = values.getID();
        DeptName = values.getDeptName();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PATIENT_INFO, values);
        intent.putExtras(bundle);
        startActivity(intent);
        // TODO: 16/9/23 增加添加最近查看患者

        myDbHelper.insertUserInfo(LookPaitentList, values.getName() + LookPaitentList, new Gson().toJson(values));
        myDbHelper.close();


    }


    /**
     * 判断应加载是那个标题栏下的数据
     *
     * @param key
     * @param values
     * @return
     */
    public List<DCPatientHROfDepartment.ValuesBean> isValues(int key, List<DCPatientHROfDepartment.ValuesBean> values) {
        List<DCPatientHROfDepartment.ValuesBean> data = new ArrayList<>();
        /**
         * 0--all
         * 1--新入,当天入院为新入
         * 2--I级护理 ,getCareLevel为特级护理
         * */
        switch (key) {
            case 0:
                data = values;
                break;
            case 1:
                for (int i = 0; i < values.size(); i++) {
                    if (format.equals(Url.dateToSimpleDateTwo(values.get(i).getEnterDate()))) {
                        data.add(values.get(i));
                    }
                }
                break;

            case 2:
                for (int i = 0; i < values.size(); i++) {
                    if (!TextUtils.isEmpty(values.get(i).getCareLevel()) && values.get(i).getCareLevel().contains("特级护理")) {
                        data.add(values.get(i));
                    }
                }
                break;
        }
        return data;
    }


}
