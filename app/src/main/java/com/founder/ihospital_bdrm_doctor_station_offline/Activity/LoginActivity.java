package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.MainActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCUser;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ReadPlistFile;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by hyyx
 * 登录主界面
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button mLogin;
    private EditText mName;
    private EditText mPwd;
    public final String TAG = LoginActivity.class.getSimpleName();
    private int statusDc;
    private String id;

    private String userName;

    private String user;
    private String passWord;
    private RelativeLayout relativeLayout;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    protected void onResume() {
        super.onResume();
        relativeLayout.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        relativeLayout.setVisibility(View.GONE);
    }


    private void initView() {
        mLogin = ((Button) findViewById(R.id.btn_login));
        mName = ((EditText) findViewById(R.id.etName_login));
        mPwd = ((EditText) findViewById(R.id.etPwd_login));


        user = mName.getText().toString();
        passWord = mPwd.getText().toString();


        relativeLayout = ((RelativeLayout) findViewById(R.id.relativeLayout_login));
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        linearLayout = ((LinearLayout) findViewById(R.id.linearlayout_login));
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
            }
        });


        mLogin.setOnClickListener(this);

    }

    private void hideKeyBoard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                // isLogin();
                isLoginTwo();
                relativeLayout.setVisibility(View.VISIBLE);
                break;
        }


    }

    private void isLogin() {
        String url = Url.DoctorServer + "Login";
        RequestParams requestParams = new RequestParams(url);
        requestParams.addBodyParameter("loginName", "wds");
        requestParams.addBodyParameter("password", "wds");
        /**
         *
         *
         * 固定医生登录为wds医生
         *
         *
         * */
//        requestParams.addBodyParameter("loginName", mName.getText().toString().trim());
//        requestParams.addBodyParameter("password", mPwd.getText().toString().trim());
        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e(TAG, "onSuccess---------------------");
                        Gson gson = new Gson();
                        DCUser dcUser = gson.fromJson(result, DCUser.class);
                        statusDc = dcUser.getStatus();
                        id = dcUser.getValues().getID();
                        userName = dcUser.getValues().getUserName();
                        Log.e(TAG, id + "----" + userName);

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("usename", userName);
                        startActivity(intent);
                        finish();


                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e(TAG, "onError---------------------" + ex);
                        Url.ErrorToast(getBaseContext());
                        relativeLayout.setVisibility(View.INVISIBLE);
//                        String data = "{\n" +
//                                "  \"Status\": 1,\n" +
//                                "  \"Values\": {\n" +
//                                "    \"ModulePermission\": [\n" +
//                                "      10000,\n" +
//                                "      10001,\n" +
//                                "      10002,\n" +
//                                "      10003,\n" +
//                                "      10004,\n" +
//                                "      10005,\n" +
//                                "      10006,\n" +
//                                "      10007,\n" +
//                                "      10008\n" +
//                                "    ],\n" +
//                                "    \"ID\": \"03267\",\n" +
//                                "    \"Certification\": null,\n" +
//                                "    \"CertificationRequired\": false,\n" +
//                                "    \"Name\": \"测试\",\n" +
//                                "    \"UserName\": \"wds\",\n" +
//                                "    \"Password\": \"wds\",\n" +
//                                "    \"IdentityCard\": null,\n" +
//                                "    \"Sex\": null,\n" +
//                                "    \"Nation\": null,\n" +
//                                "    \"Title\": \"主任医师\",\n" +
//                                "    \"LevelCode\": null,\n" +
//                                "    \"ImageUri\": null,\n" +
//                                "    \"UserRole\": 0,\n" +
//                                "    \"SursurgeryClass\": null,\n" +
//                                "    \"DeptName\": \"测试科室\",\n" +
//                                "    \"DeptCode\": \"4080000\",\n" +
//                                "    \"Dept_IDS\": null\n" +
//                                "  }\n" +
//                                "}";
//                        Log.i("login++", "onError: "+data);
//                        Gson gson = new Gson();
//                        DCUser dcUser = gson.fromJson(data, DCUser.class);
//                        statusDc = dcUser.getStatus();
//                        id = dcUser.getValues().getID();
//                        userName = dcUser.getValues().getUserName();
//                        Log.e(TAG, id + "----" + userName);
//
//                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                        intent.putExtra("id", id);
//                        intent.putExtra("usename", userName);
//                        startActivity(intent);

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
     * 离线登录
     */
    private void isLoginTwo() {

        relativeLayout.setVisibility(View.INVISIBLE);

        Gson gson = new Gson();
        DCUser dcUser = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "Login", "wds"), DCUser.class);
        statusDc = dcUser.getStatus();
        id = dcUser.getValues().getID();
        userName = dcUser.getValues().getUserName();
        Log.e(TAG, id + "----" + userName);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("usename", userName);
        intent.putExtra("deptName", dcUser.getValues().getDeptName());
        startActivity(intent);
        finish();


    }


}

