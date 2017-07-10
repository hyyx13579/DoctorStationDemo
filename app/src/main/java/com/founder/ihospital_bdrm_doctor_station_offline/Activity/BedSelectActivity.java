package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.SickBedListViewAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.BedInfo;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DealWardformation;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ReadPlistFile;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;


/**
 * Created by 呼延
 * 床位管理模块中,选择床位功能act
 */

public class BedSelectActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private DealWardformation sickRoomSelectActivity_lv_item_data;
    private String doctor_id;
    private ListView listView;
    private SickBedListViewAdapter sickBedListViewAdaptet;
    public static final String TAG = BedSelectActivity.class.getSimpleName();
    private ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_select);
        initView();
    }

    private void initView() {

        Intent intent = getIntent();
        sickRoomSelectActivity_lv_item_data = (DealWardformation) intent.getParcelableExtra("sickRoomSelectActivity_lv_item_data");
        doctor_id = intent.getStringExtra("doctor_id");
        listView = ((ListView) findViewById(R.id.listview_bed_select));
        sickBedListViewAdaptet = new SickBedListViewAdapter(getBaseContext(), getData());
        listView.setAdapter(sickBedListViewAdaptet);
        listView.setOnItemClickListener(this);
        back_btn = ((ImageView) findViewById(R.id.back_bed_select));
        back_btn.setOnClickListener(this);


    }

    private List<BedInfo.ValuesBean> getData() {

        /**
         * 特殊接口更改为9002
         *
         * */


        String url = Url.BedManagementServerTwo + "GetBedInfo";
        RequestParams requestParams = new RequestParams(url);

        requestParams.addBodyParameter("DeptCode", sickRoomSelectActivity_lv_item_data.getDeptCode());
        requestParams.addBodyParameter("WardCode", sickRoomSelectActivity_lv_item_data.getWardCode());
        requestParams.addBodyParameter("DoctorNo", doctor_id);


        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e(TAG, "onSuccess---" + result);
                        Gson gson = new Gson();
                        BedInfo bedInfo = gson.fromJson(result, BedInfo.class);
                        List<BedInfo.ValuesBean> values = bedInfo.getValues();
                        sickBedListViewAdaptet.addRes(values);


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
                }

        );


        return null;


    }

    private void getDataOffLine() {
        Gson gson = new Gson();
        BedInfo bedInfo = null;
        switch (sickRoomSelectActivity_lv_item_data.getWardCode()) {
            case "1000005":
                bedInfo = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetBedInfo", "1000005"), BedInfo.class);
                break;

            case "1000006":
                bedInfo = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetBedInfo", "1000006"), BedInfo.class);
                break;
            case "1000031":
                bedInfo = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetBedInfo", "1000031"), BedInfo.class);
                break;
            case "1000632":
                bedInfo = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetBedInfo", "1000632"), BedInfo.class);
                break;
            case "1000636":
                bedInfo = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetBedInfo", "1000636"), BedInfo.class);
                break;
            case "1000645":
                bedInfo = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetBedInfo", "1000645"), BedInfo.class);
                break;
            case "1000665":
                bedInfo = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetBedInfo", "1000665"), BedInfo.class);
                break;
            case "1000709":
                bedInfo = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetBedInfo", "1000709"), BedInfo.class);
                break;
            case "8000041":
                bedInfo = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetBedInfo", "8000041"), BedInfo.class);
                break;
        }
        List<BedInfo.ValuesBean> values = bedInfo.getValues();
        sickBedListViewAdaptet.addRes(values);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        BedInfo.ValuesBean itemAtPosition = (BedInfo.ValuesBean) parent.getItemAtPosition(position);
        Intent intent = new Intent(getBaseContext(), BedActivity.class);
        intent.putExtra("BedInfo.ValuesBean", itemAtPosition);
        setResult(97, intent);
        finish();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_bed_select:
                finish();
                break;
        }
    }
}
