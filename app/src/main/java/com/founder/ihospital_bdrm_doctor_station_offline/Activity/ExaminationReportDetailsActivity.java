package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCCheckOfPatientHR;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;

/**
 * Created by hyyx and lxb
 * 移动查房模块-检查报告功能-二层展示页面
 */

public class ExaminationReportDetailsActivity extends BaseActivity implements View.OnClickListener {

    public static final String TAG = ExaminationReportDetailsActivity.class.getSimpleName();
    private ImageView btn_back;
    private TextView bigTitle;
    private TextView typeText;
    private TextView applyDateText;
    private TextView positionText;
    private TextView reportDateText;
    private TextView doctorNameText;
    private TextView applyNameOrReportNameText;
    private TextView paramterText;
    private TextView descriptionText;
    private TextView impressionText;
    private Button btn_video;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination_report_details);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        DCCheckOfPatientHR.ValuesBean data = (DCCheckOfPatientHR.ValuesBean) intent.getSerializableExtra("data");

        String type = data.getType();
        String applyDate = data.getApplyDate();
        String position = ((String) data.getPosition());
        String reportDate = data.getReportDate();
        String applyDoctorName = data.getApplyDoctorName();
        String reportDoctorName = data.getReportDoctorName();
        String description = ((String) data.getDescription());
        String impression = ((String) data.getImpression());
        String content = data.getContent();
        String para = data.getPara();



        btn_back = ((ImageView) findViewById(R.id.back_examreport_details));
        btn_back.setOnClickListener(this);
        bigTitle = ((TextView) findViewById(R.id.title_examreport_details));
        bigTitle.setText(type);
        typeText = ((TextView) findViewById(R.id.type_examreport_details));
        typeText.setText(type);
        applyDateText = ((TextView) findViewById(R.id.applydate_examreport_details));
        applyDateText.setText(Url.dealDate(applyDate));
        positionText = ((TextView) findViewById(R.id.position_examreport_details));
        positionText.setText(position);
        reportDateText = ((TextView) findViewById(R.id.reportdate_examreport_details));
        reportDateText.setText(Url.dealDate(reportDate));
        //未找到医生名字,暂不填充
        doctorNameText = ((TextView) findViewById(R.id.doctorname_examreport_details));
        applyNameOrReportNameText = ((TextView) findViewById(R.id.applyname_reportname_examreport_details));
        applyNameOrReportNameText.setText(applyDoctorName + "/" + reportDoctorName);

        paramterText = ((TextView) findViewById(R.id.parameter_reportname_examreport_details));
        paramterText.setText(para);

        descriptionText = ((TextView) findViewById(R.id.description_reportname_examreport_details));
       // descriptionText.setText(description);

        impressionText = ((TextView) findViewById(R.id.impression_reportname_examreport_details));
        //impressionText.setText(impression);

        btn_video = ((Button) findViewById(R.id.video_reportname_examreport_details));
        btn_video.setOnClickListener(this);

        String[] s = content.split("\\n\\n");

        for (int i = 0; i < s.length; i++) {
            if (i == 3){
                descriptionText.setText(s[i]);
            }else if (i ==5){
                impressionText.setText(s[i]);
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_examreport_details:
                finish();
                break;

            case R.id.video_reportname_examreport_details:
                Intent intent = new Intent(getApplicationContext(), ExaminationReportDetailsVideoActivity.class);
                startActivity(intent);
        }
    }
}
