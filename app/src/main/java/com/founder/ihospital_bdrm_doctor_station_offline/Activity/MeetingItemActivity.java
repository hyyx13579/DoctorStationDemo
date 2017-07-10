package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCMeetingUndone;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;
import com.founder.ihospital_bdrm_doctor_station_offline.zxing.android.CaptureActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * create by zhangqun
 * 会诊模块
 */


public class MeetingItemActivity extends BaseActivity {

    private Button btn_Null;
    private TextView ConsultationCode;
    private TextView Name;
    private TextView WardName;
    private TextView BedNo;
    private TextView ConRequestDocName;
    private TextView DeptName;
    private TextView ConRequestDate;
    private TextView ReserPlace;
    private TextView PatientDesc;
    private TextView editText;
    private String url = "http://172.27.1.58:9001/api/ConsultationManagement/";
    private String path;
    private DCMeetingUndone.ValuesBean valuesBean;
    private String patientLeftReason = "";
    private ImageView backImg;
    private static final int REQUEST_CODE_SCAN = 0x0000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_item);
        Intent intent = getIntent();
        String searchType = intent.getStringExtra("searchType");
        valuesBean = ((DCMeetingUndone.ValuesBean) intent.getSerializableExtra("val"));
        initView(searchType);
        initData();
        setResult(100, intent);
    }

    private void initData() {

    }

    private void initView(String searchType) {
        backImg = ((ImageView) findViewById(R.id.meetingItemBack));
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ConsultationCode = ((TextView) findViewById(R.id.meetingConsultationCode));
        Name = ((TextView) findViewById(R.id.meetingName));
        WardName = ((TextView) findViewById(R.id.meetingWardName));
        BedNo = ((TextView) findViewById(R.id.meetingBedNO));
        ConRequestDocName = ((TextView) findViewById(R.id.meetingConRequestDocName));
        DeptName = ((TextView) findViewById(R.id.meetingDeptName));
        ConRequestDate = ((TextView) findViewById(R.id.meetingConRequestDate));
        ReserPlace = ((TextView) findViewById(R.id.meetingReservPlace));
        PatientDesc = ((TextView) findViewById(R.id.meetingPatientDesc));
        btn_Null = ((Button) findViewById(R.id.meetingBtnNull));
        if (searchType.equals("1")) {
            path = url + "ConsultationConfirmWriteBack";
            btn_Null.setText("接受");
            btn_Null.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("meetingPath", "onSuccess: " + 1);
                    RequestParams requestParams = new RequestParams(path);
                    requestParams.addBodyParameter("doctorID", "01095");
                    requestParams.addBodyParameter("consultationID", valuesBean.getConsultationID());
                    requestParams.addBodyParameter("result", "接受会诊");
                    x.http().post(requestParams, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Log.i("meetingPath", "onSuccess: " + result);
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {

                            Url.isToast(getBaseContext(), "成功");
                            finish();

                        }

                        @Override
                        public void onCancelled(CancelledException cex) {

                        }

                        @Override
                        public void onFinished() {

                        }
                    });
                }
            });
        } else if (searchType.equals("2")) {
            Log.i("meetingPath", "onSuccess: " + 2);
            path = url + "ConsultationSignWriteBack";
            Log.i("meetingPath", "path: " + path);
//            editText.setVisibility(View.VISIBLE);
//            editText.setHint("病人离开原因");
//            editText.addTextChangedListener(new TextWatcher() {
//
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                    if (!s.toString().isEmpty()) {
//                        patientLeftReason = s.toString();
//                    } else {
//                        patientLeftReason = " ";
//                    }
//                }
//            });
            btn_Null.setText("签到");
            btn_Null.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent scannerIntent = new Intent(MeetingItemActivity.this,
                            CaptureActivity.class);
                    startActivityForResult(scannerIntent, REQUEST_CODE_SCAN);


//                    RequestParams request = new RequestParams(path);
//                    request.addBodyParameter("doctorID", "01095");
//                    request.addBodyParameter("consultationID", valuesBean.getConsultationID());
//                    request.addBodyParameter("signSource", "3");
//                    if (!patientLeftReason.isEmpty()) {
//                        request.addBodyParameter("signNotes", patientLeftReason);
//                        Log.i("meetingPath", "patientleftreason: " + patientLeftReason);
//                        request.addBodyParameter("signType", "2");
//                        Log.i("meetingPath", "onClick: " + 2);
//                    } else {
//                        request.addBodyParameter("signNotes", " ");
//                        request.addBodyParameter("signType", "1");
//                        Log.i("meetingPath", "onClick: " + 1);
//                    }
//                    x.http().post(request, new Callback.CommonCallback<String>() {
//                        @Override
//                        public void onSuccess(String result) {
//                            Log.i("meetingPath", "onSuccess: " + result);
//                        }
//
//                        @Override
//                        public void onError(Throwable ex, boolean isOnCallback) {
//                            Log.i("meetingPath", "onError: " + ex);
//                            Url.isToast(getBaseContext(), "成功");
//                            finish();
//                        }
//
//                        @Override
//                        public void onCancelled(CancelledException cex) {
//                            Log.i("meetingPath", "onCancelled: ");
//                        }
//
//                        @Override
//                        public void onFinished() {
//                            Log.i("meetingPath", "onFinished: ");
//                        }
//                    });
                }
            });
        } else if (searchType.equals("3")) {
            Log.i("meetingPath", "onSuccess: " + 3);
            path = url + "ConsultationOpinionWriteBack";
//            editText.setVisibility(View.VISIBLE);
//            editText.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                    patientLeftReason = s.toString();
//                }
//            });
            btn_Null.setText("提交会诊意见");
            btn_Null.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RequestParams request = new RequestParams(path);
                    request.addBodyParameter("doctorID", "01095");
                    request.addBodyParameter("consultationID", valuesBean.getConsultationID());
                    if (patientLeftReason != null) {

                    } else {
                        patientLeftReason = "";
                    }
                    request.addBodyParameter("consultationOpinion", patientLeftReason);
                    x.http().post(request, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Log.i("meetingPath", "onSuccess: " + result);
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {
                            Url.isToast(getBaseContext(), "成功");
                            finish();
                        }

                        @Override
                        public void onCancelled(CancelledException cex) {

                        }

                        @Override
                        public void onFinished() {

                        }
                    });
                }
            });
        }
    }
}
