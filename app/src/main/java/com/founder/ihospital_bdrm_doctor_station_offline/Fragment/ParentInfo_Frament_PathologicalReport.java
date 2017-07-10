package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Activity.PathologicalReportItemActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.MyStickListViewAdapterPathologicalReport;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.PathologicalRecord;
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
public class ParentInfo_Frament_PathologicalReport extends BaseFragment {


    private StickyListHeadersListView stickHeaderListView;
    public static final String TAG = ParentInfo_Frament_PathologicalReport.class.getSimpleName();
    private DCPatientHROfDepartment.ValuesBean data;
    private String caseID;
    private String subID;
    public static final String PathologicalInfo = "PathologicalInfo";
    public static final String PatientHROfDepartmentInfo = "PatientHROfDepartmentInfo";

    /**
     * load界面
     */
    private RelativeLayout relativeLayout;
    private MyStickListViewAdapterPathologicalReport myStickListViewAdapterPathologicalReport;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.parent_info_frag_pathologicalreport, container, false);
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

        if (no.getPagerNo() == 6) {
            Log.e(TAG, "onEvent进入");
            getData();
        }
    }


    private void initView() {
        stickHeaderListView = ((StickyListHeadersListView) layout.findViewById(R.id.parent_frag_nine_sticklv));
        myStickListViewAdapterPathologicalReport = new MyStickListViewAdapterPathologicalReport(getContext(), null);
        stickHeaderListView.setAdapter(myStickListViewAdapterPathologicalReport);
        relativeLayout = ((RelativeLayout) layout.findViewById(R.id.relativeLayout_parent_9));
        getData();
        stickHeaderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private PathologicalRecord.ValuesBean itemAtPosition;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemAtPosition = ((PathologicalRecord.ValuesBean) parent.getItemAtPosition(position));
                Intent intent = new Intent(getContext(), PathologicalReportItemActivity.class);
                intent.putExtra(PatientHROfDepartmentInfo, data);
                intent.putExtra(PathologicalInfo, itemAtPosition);
                startActivity(intent);
            }
        });
    }

    private void getData() {

        relativeLayout.setVisibility(View.VISIBLE);
        Bundle arguments = getArguments();
        data = (DCPatientHROfDepartment.ValuesBean) arguments.getSerializable("data");
        String url = Url.DoctorServer + "GetPathologicalRecord";
        RequestParams requestParams = new RequestParams(url);
        //此时patientID是hid
        requestParams.addBodyParameter("patientID", data.getHID());
        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
            private List<PathologicalRecord.ValuesBean> values;

            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess---------------------");
                Gson gson = new Gson();
                values = gson.fromJson(result, PathologicalRecord.class).getValues();
                if (!values.isEmpty()) {
                    myStickListViewAdapterPathologicalReport.updateRes(values);
                } else {
                    Url.ErrorToast(getContext(), "此患者无病理报告");
                }
                relativeLayout.setVisibility(View.GONE);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError---------------------" + ex);
                getDataOffLine();

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
        List<PathologicalRecord.ValuesBean> values = new ArrayList<>();
        String patientID = data.getID();
        Gson gson = new Gson();
        switch (patientID) {
            case "29379827":
                values = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetPathologicalRecord", "29379827"), PathologicalRecord.class).getValues();
                break;

//            case "29542151":
//                values = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetPathologicalRecord", "29542151"), PathologicalRecord.class).getValues();
//                break;
//
//            case "29554021":
//                values = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetPathologicalRecord", "29554021"), PathologicalRecord.class).getValues();
//
//                break;
//            case "29555473":
//                values = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetPathologicalRecord", "29555473"), PathologicalRecord.class).getValues();
//                break;
//            case "29557134":
//                values = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetPathologicalRecord", "29557134"), PathologicalRecord.class).getValues();
//                break;
//            case "29591075":
//                values = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetPathologicalRecord", "29591075"), PathologicalRecord.class).getValues();
//                break;


        }
        myStickListViewAdapterPathologicalReport.updateRes(values);
        relativeLayout.setVisibility(View.GONE);


    }


}
