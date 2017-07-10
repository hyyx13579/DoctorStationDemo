package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.ParentInfo_Frament_PathologicalReport;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.PathologicalRecord;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

public class PathologicalReportItemActivity extends AppCompatActivity {

    private ImageView ivTitleBack;
    private PathologicalRecord.ValuesBean pathologicalInfo;
    private DCPatientHROfDepartment.ValuesBean patientHRInfo;
    private TextView tvTitleName;
    private TextView tvItemName;
    private TextView tvPatientName;
    private TextView tvPatientSex;
    private TextView tvPatientAge;
    private TextView tvCheckMethod;
    private TextView tvConclusion;
    private TextView tvAuditingDoctor;
    private TextView tvReportDoctor;
    private TextView tvReportDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pathological_report_item);
        initView();
        dealView();
    }


    private void initView() {

        pathologicalInfo = ((PathologicalRecord.ValuesBean) getIntent().getSerializableExtra(ParentInfo_Frament_PathologicalReport.PathologicalInfo));
        patientHRInfo = ((DCPatientHROfDepartment.ValuesBean) getIntent().getSerializableExtra(ParentInfo_Frament_PathologicalReport.PatientHROfDepartmentInfo));
        ivTitleBack = ((ImageView) findViewById(R.id.back_pathologica_details));
        tvTitleName = ((TextView) findViewById(R.id.title_pathological_details));
        tvItemName = ((TextView) findViewById(R.id.pathological_report_titlename_tv));
        tvPatientName = ((TextView) findViewById(R.id.pathological_report_patientname_tv));
        tvPatientSex = ((TextView) findViewById(R.id.pathological_report_patientsex_tv));
        tvPatientAge = ((TextView) findViewById(R.id.pathological_report_patientage_tv));
        tvCheckMethod = ((TextView) findViewById(R.id.pathological_report_checkmethod_tv));
        tvConclusion = ((TextView) findViewById(R.id.pathological_report_conclusion_tv));
        tvAuditingDoctor = ((TextView) findViewById(R.id.pathological_report_auditingdoctor_tv));
        tvReportDoctor = ((TextView) findViewById(R.id.pathological_report_reportdoctor_tv));
        tvReportDate = ((TextView) findViewById(R.id.pathological_report_reportdate_tv));


    }

    private void dealView() {
        ivTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // TODO: 16/8/31 判断名字的逻辑,以后可能会修改
        if ((pathologicalInfo.getConclusion()).contains("：") && !pathologicalInfo.getConclusion().contains("@")) {
            String[] split = pathologicalInfo.getConclusion().split("：");
            if (pathologicalInfo.getCheckMethod() == null) {
                if (!TextUtils.isEmpty(split[0])) {
                    tvItemName.setText(split[0]);
                    tvCheckMethod.setText(split[0]);

                } else {
                    tvItemName.setText("无");
                    tvCheckMethod.setText("无");

                }
            }
        } else {
            tvItemName.setText("无");
            tvCheckMethod.setText("无");
        }


        tvTitleName.setText(patientHRInfo.getName());
        tvPatientName.setText(patientHRInfo.getName());
        tvPatientSex.setText(patientHRInfo.getSex());
        if (patientHRInfo.getAge().contains("岁")) {
            tvPatientAge.setText(patientHRInfo.getAge());
        } else {
            tvPatientAge.setText(patientHRInfo.getAge() + "岁");
        }

        tvConclusion.setText(pathologicalInfo.getConclusion());
        tvAuditingDoctor.setText(pathologicalInfo.getAuditingDoctor());
        tvReportDoctor.setText(pathologicalInfo.getReportDoctor());
        tvReportDate.setText(pathologicalInfo.getReportDate());


    }
}
