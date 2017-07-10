package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;
import com.founder.ihospital_bdrm_doctor_station_offline.View.ProgressWebView;
import com.founder.ihospital_bdrm_doctor_station_offline.View.WebViewFormatTool;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class OperationActivity extends AppCompatActivity {

    private TextView tvTitleName;
    private ImageView ivTitleBack;
    private ProgressWebView progressWebView;
    public static final String TAG = OperationActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        initView();
        dealView();

    }


    private void initView() {
        tvTitleName = ((TextView) findViewById(R.id.titleName_operation_info));
        ivTitleBack = ((ImageView) findViewById(R.id.back_operation_info));
        progressWebView = ((ProgressWebView) findViewById(R.id.operation_web));


    }

    private void dealView() {
        tvTitleName.setText("手术排班");
        ivTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getPathologicalRecord();


    }

    private void getPathologicalRecord() {


        String url = Url.OperationSchedule + "GetOperationScheduleURL";
        RequestParams requestParams = new RequestParams(url);

        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
                    private String webUrl;

                    @Override
                    public void onSuccess(String result) {
                        Log.e(TAG, "onSuccess---" + result);
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            webUrl = jsonObject.getString("Values");
                            Log.e(TAG, "onSuccess---url" + webUrl);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        WebViewFormatTool.setWebView(progressWebView, webUrl);


                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e(TAG, "onError----" + ex);
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


}


