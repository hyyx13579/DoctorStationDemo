package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.PatitentSelectListViewAdaptet;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.IntentPatientList;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ReadPlistFile;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

/**
 * Created by hyyx
 * <p>
 * 床位管理模块-选择病人功能
 */


public class PatitentSelectActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private String doctor_id;
    private String deptCode;
    private String startDate;
    private String endDate;
    public static final String TAG = PatitentSelectActivity.class.getSimpleName();
    private ListView listView;
    private PatitentSelectListViewAdaptet patitentSelectListViewAdaptet;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patitent_select);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        doctor_id = intent.getStringExtra("doctor_id");
        deptCode = intent.getStringExtra("DeptCode");
        startDate = intent.getStringExtra("StartDate");
        endDate = intent.getStringExtra("EndDate");
        listView = ((ListView) findViewById(R.id.listview_patitent_select));
        patitentSelectListViewAdaptet = new PatitentSelectListViewAdaptet(getBaseContext(), null);
        listView.setAdapter(patitentSelectListViewAdaptet);
        listView.setOnItemClickListener(this);
        back = ((ImageView) findViewById(R.id.back_patitent_select));
        back.setOnClickListener(this);


        getLoad();

    }

    private void getLoad() {

        /**
         * 特殊接口更改为9002
         * */

        String url = Url.BedManagementServerTwo + "GetIntentPatientList";
        RequestParams requestParams = new RequestParams(url);
        requestParams.addBodyParameter("DeptCode", deptCode);
        requestParams.addBodyParameter("StartDate", startDate + " 00:00:00");
        requestParams.addBodyParameter("EndDate", endDate + " 00:00:00");
        requestParams.addBodyParameter("DoctorNo", doctor_id);

        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e(TAG, "onSuccess---" + result);

                        Gson gson = new Gson();
                        IntentPatientList intentPatientList = gson.fromJson(result, IntentPatientList.class);
                        List<IntentPatientList.ValuesBean> values = intentPatientList.getValues();
                        patitentSelectListViewAdaptet.addRes(values);


                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e(TAG, "onError----" + ex);
                        getLoadOffLine();
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

    private void getLoadOffLine() {

        Gson gson = new Gson();
        IntentPatientList intentPatientList = null;
        switch (deptCode) {
            case "1000003":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000003"), IntentPatientList.class);
                break;

            case "1000029":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000029"), IntentPatientList.class);
                break;
            case "1000040":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000040"), IntentPatientList.class);
                break;
            case "1000066":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000066"), IntentPatientList.class);
                break;
            case "1000084":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000084"), IntentPatientList.class);
                break;
            case "1000108":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000108"), IntentPatientList.class);
                break;
            case "1000122":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000122"), IntentPatientList.class);
                break;
            case "1000133":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000133"), IntentPatientList.class);
                break;
            case "1000159":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000159"), IntentPatientList.class);
                break;
            case "1000174":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000174"), IntentPatientList.class);
                break;
            case "1000181":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000181"), IntentPatientList.class);
                break;
            case "1000192":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000192"), IntentPatientList.class);
                break;
            case "1000197":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000197"), IntentPatientList.class);
                break;
            case "1000209":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000209"), IntentPatientList.class);
                break;
            case "1000240":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000240"), IntentPatientList.class);
                break;
            case "1000638":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000638"), IntentPatientList.class);
                break;
            case "1000642":
                intentPatientList = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetIntentPatientList", "1000642"), IntentPatientList.class);
                break;
        }


        List<IntentPatientList.ValuesBean> values = intentPatientList.getValues();
        patitentSelectListViewAdaptet.addRes(values);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        IntentPatientList.ValuesBean itemAtPosition = (IntentPatientList.ValuesBean) parent.getItemAtPosition(position);
        Intent intent = new Intent(getApplicationContext(), BedActivity.class);
        intent.putExtra("PatitentSelectActivity_lv_item_data", itemAtPosition);
        setResult(99, intent);
        finish();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_patitent_select:
                finish();
                break;
        }
    }
}
