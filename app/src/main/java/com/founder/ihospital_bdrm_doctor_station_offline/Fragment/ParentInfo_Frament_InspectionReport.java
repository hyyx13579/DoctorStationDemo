package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Contant;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.SPUtils;
import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Activity.InspectionReportDetailsActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.MyStickListViewAdapterInspectionReport;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCTestOfPatientHR;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.EventPagerNo;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.OnChangeRadioButtonListener;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ReadPlistFile;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by 呼延 on 2016/3/30.
 * 移动查房模块-检验报告功能
 */
public class ParentInfo_Frament_InspectionReport extends BaseFragment implements RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {


    public static final String CHOSE_ID = "chose_id";
    public static final String VALUE_BEAN_LIST = "data";
    private RadioGroup rbGroup;
    private RadioButton btn_left;
    private RadioButton btn_right;
    private DCPatientHROfDepartment.ValuesBean data;
    private String caseID;
    private String subID;
    private FragmentManager fm;
    private FragmentTransaction transaction;
    private Fragment baseFramgent;
    public static final String TAG = ParentInfo_Frament_InspectionReport.class.getSimpleName();
    private List<DCTestOfPatientHR.ValuesBean> isReportDate;
    private List<DCTestOfPatientHR.ValuesBean> noReportDate;
    private List<DCTestOfPatientHR.ValuesBean> values;
    private Bundle bundle;
    private int no;
    private OnChangeRadioButtonListener onChangeRadioButtonListener;
    private StickyListHeadersListView stickHeaderListView;
    private MyStickListViewAdapterInspectionReport myStickListViewAdapterFramgentFive;
    private String mDataString;
    private RelativeLayout relativeLayout;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.parent_info_frag_inspectionreport, container, false);
        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Log.e(TAG, "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        Log.e(TAG, "onStop");
    }


    /**
     * 重写onEvent方法, 需要添加@Subscribe,否则可能注册不成功
     */
    @Subscribe
    public void onEvent(EventPagerNo no) {

        if (no.getPagerNo() == 4) {
            Log.e(TAG, "onEvent进入");
            btn_left.setChecked(true);
            btn_left.setTextColor(Color.WHITE);
            btn_right.setTextColor(getResources().getColor(R.color.titlecolor));
            getData();
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        rbGroup = ((RadioGroup) layout.findViewById(R.id.parent_frag_five_radiogruop));
        rbGroup.setOnCheckedChangeListener(this);
        btn_left = ((RadioButton) layout.findViewById(R.id.parent_frag_five_radiobtn_left));
        btn_right = ((RadioButton) layout.findViewById(R.id.parent_frag_five_radiobtn_right));
        stickHeaderListView = ((StickyListHeadersListView) layout.findViewById(R.id.parent_frag_five_sticklv));
        myStickListViewAdapterFramgentFive = new MyStickListViewAdapterInspectionReport(getContext(), null);
        stickHeaderListView.setAdapter(myStickListViewAdapterFramgentFive);


        stickHeaderListView.setOnItemClickListener(this);
        relativeLayout = ((RelativeLayout) layout.findViewById(R.id.relativeLayout_parent_4));

        getData();
    }

    private void getData() {
        relativeLayout.setVisibility(View.VISIBLE);
        isReportDate = new ArrayList<>();
        noReportDate = new ArrayList<>();
        Bundle arguments = getArguments();
        data = (DCPatientHROfDepartment.ValuesBean) arguments.getSerializable("data");
        caseID = data.getID();
        subID = data.getSubID();

        String url = Url.DoctorServer + "GetTestOfPatientHR ";
        RequestParams requestParams = new RequestParams(url);
        requestParams.addBodyParameter("patientID", caseID);
        requestParams.addBodyParameter("visitID", subID);
        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess---------------------");
                mDataString = result;
                Gson gson = new Gson();
                DCTestOfPatientHR dcTestOfPatientHR = gson.fromJson(result, DCTestOfPatientHR.class);
                values = dcTestOfPatientHR.getValues();
                Log.e(TAG, "onData---" + values.toString());
                dealData(values);
                relativeLayout.setVisibility(View.GONE);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError---------------------" + ex);
                relativeLayout.setVisibility(View.GONE);
                getDataOffLine();
                //Url.ErrorToast(getContext());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    private void getDataOffLine() {

        DCTestOfPatientHR dcTestOfPatientHR = null;
        Gson gson = new Gson();
        switch (caseID) {
            case "29379827":
                dcTestOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetTestOfPatientHR", "29379827"), DCTestOfPatientHR.class);

                mDataString = ReadPlistFile.onReadPatientInformation(getContext(), "GetTestOfPatientHR", "29379827");

                break;

            case "29542151":
                dcTestOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetTestOfPatientHR", "29542151"), DCTestOfPatientHR.class);
                mDataString = ReadPlistFile.onReadPatientInformation(getContext(), "GetTestOfPatientHR", "29542151");
                break;

            case "29554021":
                dcTestOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetTestOfPatientHR", "29554021"), DCTestOfPatientHR.class);
                mDataString = ReadPlistFile.onReadPatientInformation(getContext(), "GetTestOfPatientHR", "29554021");
                break;
            case "29555473":
                dcTestOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetTestOfPatientHR", "29555473"), DCTestOfPatientHR.class);
                mDataString = ReadPlistFile.onReadPatientInformation(getContext(), "GetTestOfPatientHR", "29555473");
                break;
            case "29557134":
                dcTestOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetTestOfPatientHR", "29557134"), DCTestOfPatientHR.class);
                mDataString = ReadPlistFile.onReadPatientInformation(getContext(), "GetTestOfPatientHR", "29557134");
                break;
            case "29591075":
                dcTestOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetTestOfPatientHR", "29591075"), DCTestOfPatientHR.class);
                mDataString = ReadPlistFile.onReadPatientInformation(getContext(), "GetTestOfPatientHR", "29591075");
                break;


        }
        SPUtils.put(getContext(), Contant.INSPECTION_REPORT_DATA, mDataString);
        values = dcTestOfPatientHR.getValues();
        dealData(values);
    }


    private void dealData(List<DCTestOfPatientHR.ValuesBean> values) {
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).getReportDate() != null) {
                isReportDate.add(values.get(i));
            } else {
                noReportDate.add(values.get(i));
            }
        }
        myStickListViewAdapterFramgentFive.updateRes(isReportDate);
        btn_left.setText("已报告" + "(" + isReportDate.size() + ")");
        btn_right.setText("未报告" + "(" + noReportDate.size() + ")");

        Log.e(TAG, "dealData-----" + isReportDate.toString());
        Log.e(TAG, "dealData-----" + noReportDate.toString());

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId) {
            case R.id.parent_frag_five_radiobtn_left:
                btn_left.setTextColor(Color.WHITE);
                btn_right.setTextColor(getResources().getColor(R.color.titlecolor));
                myStickListViewAdapterFramgentFive.updateRes(isReportDate);

                break;
            case R.id.parent_frag_five_radiobtn_right:
                btn_right.setTextColor(Color.WHITE);
                btn_left.setTextColor(getResources().getColor(R.color.titlecolor));
                myStickListViewAdapterFramgentFive.updateRes(noReportDate);

                break;


        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//        DCTestOfPatientHR.ValuesBean itemAtPosition = (DCTestOfPatientHR.ValuesBean) adapterView.getItemAtPosition(i);
        Intent intent = new Intent(getContext(), InspectionReportDetailsActivity.class);
        intent.putExtra(CHOSE_ID, i);
        intent.putExtra(VALUE_BEAN_LIST, mDataString);
        startActivity(intent);


    }
}
