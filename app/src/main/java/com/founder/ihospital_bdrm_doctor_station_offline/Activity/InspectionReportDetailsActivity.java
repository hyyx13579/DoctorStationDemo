package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.InspectionReportDetailsAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.ParentInfo_Frament_InspectionReport;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCTestOfPatientHR;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;
import com.founder.ihospital_bdrm_doctor_station_offline.View.MyListView;

import java.util.List;

/**
 * Created by hyyx and lxb
 * 移动查房模块-检验报告功能-二层展示界面
 */


public class InspectionReportDetailsActivity extends BaseActivity implements View.OnClickListener {

    public static final String CURRENT_ID = "current_id";
    public static final String JSON_DATA_STR = "json_data_str";
    private TextView title;
    private TextView contentTitleText;
    private TextView applytimeText;
    private TextView reportdateText;
    private TextView sampleText;
    private List<DCTestOfPatientHR.ValuesBean.TestItemListBean> testItemList;
    private TextView verifypersonnameText;
    private TextView reportdoctornameText;
    private TextView senddatetimeText;
    private Button analy_btn;
    private MyListView myListView;
    private ImageView back_btn;
    private DCTestOfPatientHR dcTestOfPatientHR;
    private DCTestOfPatientHR.ValuesBean data;
    private String mJsonDataStr;
    private int mCurrentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_report_details);
        initView();
        initCtrl();


    }

    private void initCtrl() {
        InspectionReportDetailsAdapter adapter = new InspectionReportDetailsAdapter(testItemList, getApplicationContext());
        myListView.setAdapter(adapter);


    }

    private void initView() {
        Intent intent = getIntent();
        mJsonDataStr = intent.getStringExtra(ParentInfo_Frament_InspectionReport.VALUE_BEAN_LIST);
        mCurrentId = intent.getIntExtra(ParentInfo_Frament_InspectionReport.CHOSE_ID, 0);
        Gson gson = new Gson();
        dcTestOfPatientHR = gson.fromJson(mJsonDataStr, DCTestOfPatientHR.class);
        data = dcTestOfPatientHR.getValues().get(mCurrentId);
        String content = data.getContent();
        String applyDate = data.getApplyDate();
        String reportDate = (String) data.getReportDate();
        String sample = data.getSample();
        testItemList = data.getTestItemList();
        String verifyPersonName = (String) data.getVerifyPersonName();
        String reportDoctorName = (String) data.getReportDoctorName();
        //String sendDateTime =  data.getSendDateTime();
        // senddatetimeText.setText(Url.dealDate(sendDateTime));


        title = ((TextView) findViewById(R.id.title_inspection_repoet_details));
        contentTitleText = ((TextView) findViewById(R.id.titlecontent_inspection_repoet_details));
        title.setText(content);
        contentTitleText.setText(content);

        applytimeText = ((TextView) findViewById(R.id.applydate_inspection_repoet_details));
        applytimeText.setText(Url.dealDate(applyDate));

        reportdateText = ((TextView) findViewById(R.id.reportdate_inspection_repoet_details));
        reportdateText.setText(Url.dealDate(reportDate));

        sampleText = ((TextView) findViewById(R.id.sample_inspection_repoet_details));
        sampleText.setText(sample);

        verifypersonnameText = ((TextView) findViewById(R.id.verifypersonname_inspection_repoet_details));
        verifypersonnameText.setText(verifyPersonName);

        reportdoctornameText = ((TextView) findViewById(R.id.reportdoctorname_inspection_repoet_details));
        reportdoctornameText.setText(reportDoctorName);

        senddatetimeText = ((TextView) findViewById(R.id.senddatetime_inspection_repoet_details));
        senddatetimeText.setText(Url.dealDate(applyDate));


        analy_btn = ((Button) findViewById(R.id.btn_inspection_repoet_details));
        analy_btn.setOnClickListener(this);

        back_btn = ((ImageView) findViewById(R.id.back_inspection_repoet_details));
        back_btn.setOnClickListener(this);


        myListView = ((MyListView) findViewById(R.id.lv_inspection_repoet_details));


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_inspection_repoet_details:
                if (dcTestOfPatientHR != null) {
                    if (dcTestOfPatientHR.getValues().size() > 1) {
                        Intent intent = new Intent(getApplicationContext(), InspectionReportDetailsAnalysisActivity.class);
                        intent.putExtra(JSON_DATA_STR, mJsonDataStr);
                        intent.putExtra(CURRENT_ID, mCurrentId);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "没有比较对象", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.back_inspection_repoet_details:
                finish();
                break;


        }
    }
}
