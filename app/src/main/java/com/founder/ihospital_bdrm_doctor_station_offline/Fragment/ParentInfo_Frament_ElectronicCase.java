package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.ExpandabeListviewAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCClinicalRecord;
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

import java.util.List;

/**
 * Created by 呼延 on 2016/3/30.
 * <p/>
 * 移动查房模块-电子病例功能
 */
public class ParentInfo_Frament_ElectronicCase extends BaseFragment {


    private ExpandableListView expanableLv;
    public static final String TAG = ParentInfo_Frament_ElectronicCase.class.getSimpleName();
    private ExpandabeListviewAdapter expandabeListviewAdapter;
    private DCPatientHROfDepartment.ValuesBean data;
    private RelativeLayout relativeLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.parent_info_frag_electroniccase, container, false);
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

        if (no.getPagerNo() == 1) {
            Log.e(TAG, "onEvent进入");
            getDataOffLine();
        }
    }


    private void initView() {
        Bundle arguments = getArguments();
        data = (DCPatientHROfDepartment.ValuesBean) arguments.getSerializable("data");
        expanableLv = ((ExpandableListView) layout.findViewById(R.id.expande_lv_parent_frag_two));
        expandabeListviewAdapter = new ExpandabeListviewAdapter(null, getContext());
        expanableLv.setAdapter(expandabeListviewAdapter);
        expanableLv.setGroupIndicator(null);
        relativeLayout = ((RelativeLayout) layout.findViewById(R.id.relativeLayout_parent_1));
        getDataOffLine();


    }

    private void getData() {
        relativeLayout.setVisibility(View.VISIBLE);
        String ID = data.getID();
        String subID = data.getSubID();
        Log.e(TAG, ID + "-----" + subID + "------" + data.getCaseID());
        String url = Url.DoctorServer + "GetMedicalRecordsOfPatientHR";
        RequestParams requestParams = new RequestParams(url);
        requestParams.addBodyParameter("patientID", ID);
        requestParams.addBodyParameter("visitID", subID);
        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        DCClinicalRecord dcClinicalRecord = gson.fromJson(result, DCClinicalRecord.class);
                        List<DCClinicalRecord.ValuesBean> values = dcClinicalRecord.getValues();
                        Log.e(TAG, "onSuccess-----" + values);
                        expandabeListviewAdapter.upRes(values);

                        //设置默认展开
//                        for (int i = 0; i < values.size(); i++) {
//                            expanableLv.expandGroup(i);
//                        }

                        relativeLayout.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e(TAG, "onError---------------------" + ex);
                        getDataOffLine();
                        relativeLayout.setVisibility(View.GONE);
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

    private void getDataOffLine() {
        DCClinicalRecord dcClinicalRecord = null;
        String patientID = data.getID();
        Gson gson = new Gson();
        switch (patientID) {
            case "29379827":
                dcClinicalRecord = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetMedicalRecordsOfPatientHR", "29379827"), DCClinicalRecord.class);
                break;

            case "29542151":
                dcClinicalRecord = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetMedicalRecordsOfPatientHR", "29542151"), DCClinicalRecord.class);
                break;

            case "29554021":
                dcClinicalRecord = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetMedicalRecordsOfPatientHR", "29554021"), DCClinicalRecord.class);

                break;
            case "29555473":
                dcClinicalRecord = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetMedicalRecordsOfPatientHR", "29555473"), DCClinicalRecord.class);
                break;
            case "29557134":
                dcClinicalRecord = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetMedicalRecordsOfPatientHR", "29557134"), DCClinicalRecord.class);
                break;
            case "29591075":
                dcClinicalRecord = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetMedicalRecordsOfPatientHR", "29591075"), DCClinicalRecord.class);
                break;


        }
        List<DCClinicalRecord.ValuesBean> values = dcClinicalRecord.getValues();
        expandabeListviewAdapter.upRes(values);


    }

}


