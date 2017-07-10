package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Activity.ExaminationReportDetailsActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.MyStickListViewAdapterExaminationReport;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCCheckOfPatientHR;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.EventPagerNo;
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
 * <p>
 * 移动查房模块-检查报告功能
 */
public class ParentInfo_Frament_ExaminationReport extends BaseFragment implements RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener {


    private RadioGroup rbGroup;
    private RadioButton btn_left;
    private RadioButton btn_right;
    private StickyListHeadersListView stickHeaderListView;
    private List<DCCheckOfPatientHR.ValuesBean> isReportDate;
    private List<DCCheckOfPatientHR.ValuesBean> noReportDate;
    public static final String TAG = ParentInfo_Frament_ExaminationReport.class.getSimpleName();
    private DCPatientHROfDepartment.ValuesBean data;
    private String caseID;
    private String subID;
    private List<DCCheckOfPatientHR.ValuesBean> values;
    private MyStickListViewAdapterExaminationReport myStickListViewAdapterFragmentSix;
    private RelativeLayout relativeLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.parent_info_frag_examinationreport, container, false);
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

        if (no.getPagerNo() == 5) {
            Log.e(TAG, "onEvent进入");
            btn_left.setChecked(true);
            btn_left.setTextColor(Color.WHITE);
            btn_right.setTextColor(getResources().getColor(R.color.titlecolor));
            getData();
        }
    }


    private void initView() {
        rbGroup = ((RadioGroup) layout.findViewById(R.id.parent_frag_six_radiogruop));
        rbGroup.setOnCheckedChangeListener(this);
        btn_left = ((RadioButton) layout.findViewById(R.id.parent_frag_six_radiobtn_left));
        btn_right = ((RadioButton) layout.findViewById(R.id.parent_frag_six_radiobtn_right));
        stickHeaderListView = ((StickyListHeadersListView) layout.findViewById(R.id.parent_frag_six_sticklv));
        myStickListViewAdapterFragmentSix = new MyStickListViewAdapterExaminationReport(getContext(), null);
        stickHeaderListView.setAdapter(myStickListViewAdapterFragmentSix);


        stickHeaderListView.setOnItemClickListener(this);

        relativeLayout = ((RelativeLayout) layout.findViewById(R.id.relativeLayout_parent_5));

        getData();
    }

    private void getData() {
        isReportDate = new ArrayList<>();
        noReportDate = new ArrayList<>();
        relativeLayout.setVisibility(View.VISIBLE);
        Bundle arguments = getArguments();
        data = (DCPatientHROfDepartment.ValuesBean) arguments.getSerializable("data");
        caseID = data.getID();
        subID = data.getSubID();

        String url = Url.DoctorServer + "GetCheckOfPatientHR";
        RequestParams requestParams = new RequestParams(url);
        requestParams.addBodyParameter("patientID", caseID);
        requestParams.addBodyParameter("visitID", subID);
        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess---------------------");
                Gson gson = new Gson();
                DCCheckOfPatientHR dcCheckOfPatientHR = gson.fromJson(result, DCCheckOfPatientHR.class);
                values = dcCheckOfPatientHR.getValues();
                Log.e(TAG, "onData---" + values.toString());
                dealData(values);
                relativeLayout.setVisibility(View.GONE);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError---------------------" + ex);
                // Url.ErrorToast(getContext());
                getDataOffLine();
                relativeLayout.setVisibility(View.GONE);
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

        DCCheckOfPatientHR dcCheckOfPatientHR = null;
        Gson gson = new Gson();
        switch (caseID) {
            case "29379827":
                dcCheckOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetCheckOfPatientHR", "29379827"), DCCheckOfPatientHR.class);


                break;

            case "29542151":
                dcCheckOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetCheckOfPatientHR", "29542151"), DCCheckOfPatientHR.class);

                break;

            case "29554021":
                dcCheckOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetCheckOfPatientHR", "29554021"), DCCheckOfPatientHR.class);

                break;
            case "29555473":
                dcCheckOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetCheckOfPatientHR", "29555473"), DCCheckOfPatientHR.class);

                break;
            case "29557134":
                dcCheckOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetCheckOfPatientHR", "29557134"), DCCheckOfPatientHR.class);

                break;
            case "29591075":
                dcCheckOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetCheckOfPatientHR", "29591075"), DCCheckOfPatientHR.class);

                break;


        }
        values = dcCheckOfPatientHR.getValues();
        dealData(values);
    }


    private void dealData(List<DCCheckOfPatientHR.ValuesBean> values) {
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).getReportDate() != null) {
                isReportDate.add(values.get(i));
            } else {
                noReportDate.add(values.get(i));
            }
        }
        myStickListViewAdapterFragmentSix.updateRes(isReportDate);
        btn_left.setText("检查报告" + "(" + isReportDate.size() + ")");
        btn_right.setText("检查预约" + "(" + noReportDate.size() + ")");

        Log.e(TAG, "dealData-----" + isReportDate.toString());
        Log.e(TAG, "dealData-----" + noReportDate.toString());

    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.parent_frag_six_radiobtn_left:
                btn_left.setTextColor(Color.WHITE);
                btn_right.setTextColor(getResources().getColor(R.color.titlecolor));
                myStickListViewAdapterFragmentSix.updateRes(isReportDate);
                break;
            case R.id.parent_frag_six_radiobtn_right:
                btn_right.setTextColor(Color.WHITE);
                btn_left.setTextColor(getResources().getColor(R.color.titlecolor));
                myStickListViewAdapterFragmentSix.updateRes(noReportDate);
                break;


        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        DCCheckOfPatientHR.ValuesBean itemAtPosition = (DCCheckOfPatientHR.ValuesBean) adapterView.getItemAtPosition(i);
        Intent intent = new Intent(getContext(), ExaminationReportDetailsActivity.class);
        intent.putExtra("data", itemAtPosition);
        startActivity(intent);


    }
}
