package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.ParentInfo_Frament_PhysicianOrderManagement;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCAdviceOfPatientHR;
import com.founder.ihospital_bdrm_doctor_station_offline.R;


/**
 * Created by hyyx and lxb
 * <p/>
 * 移动查房模块-医嘱管理功能-二层展示页面
 */



public class PhysicianOrderManagementDetailsActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btn_back;
    private TextView contentText;
    private TextView effectiveText;
    private TextView stateText;
    private TextView ueswayText;
    private TextView frequeneyText;
    private TextView scheduleText;
    private TextView dosageText;
    private TextView toaldosageText;
    private TextView createdoctornameText;
    private TextView createdatetimeText;
    private TextView checknursenameText;
    private TextView checkdatetimeText;
    private TextView doctodepandText;
    private ImageView mCheckImg;
    private ImageView mDoingImg;
    private ImageView mStopImg;
    private TextView stopTimeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_order_management_details);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        DCAdviceOfPatientHR.ValuesBean data = (DCAdviceOfPatientHR.ValuesBean) intent.getSerializableExtra("data");
        String content = data.getContent();


        String frequency = (String) data.getFrequency();
        String schedule = (String) data.getSchedule();
        String dosage = (String) data.getDosage();
        String totalDosage = (String) data.getTotalDosage();
        String createDoctorName = data.getCreateDoctorName();
        String createDateTime = data.getCreateDateTime();


        String effective = ((String) data.getEffective());
        String stateString = data.getStateString();
        String checkNurseName = ((String) data.getCheckNurseName());
        String checkDateTime = (String) data.getCheckDateTime();

        mCheckImg = (ImageView) findViewById(R.id.physician_order_checking_img);
        mDoingImg = (ImageView) findViewById(R.id.physician_order_doing_img);
        mStopImg = (ImageView) findViewById(R.id.physician_order_stop_img);

        btn_back = ((ImageView) findViewById(R.id.back_phy_order_manage_details));
        btn_back.setOnClickListener(this);

        contentText = ((TextView) findViewById(R.id.content_phy_order_manage_details));
        contentText.setText(content);

        effectiveText = ((TextView) findViewById(R.id.effective_phy_order_manage_details));
        effectiveText.setText(effective);

        stateText = ((TextView) findViewById(R.id.state_phy_order_manage_details));
        stateText.setText(stateString);

        //未找到填充数据类型
        ueswayText = ((TextView) findViewById(R.id.useway_phy_order_manage_details));

        frequeneyText = ((TextView) findViewById(R.id.frequeney_phy_order_manage_details));
        frequeneyText.setText(frequency);

        scheduleText = ((TextView) findViewById(R.id.schedule_phy_order_manage_details));
        scheduleText.setText(schedule);

        dosageText = ((TextView) findViewById(R.id.dosage_phy_order_manage_details));
        dosageText.setText(dosage);

        toaldosageText = ((TextView) findViewById(R.id.toaldosage_phy_order_manage_details));
        toaldosageText.setText(totalDosage);

        createdoctornameText = ((TextView) findViewById(R.id.createdoctorname_phy_order_manage_details));
        createdoctornameText.setText(createDoctorName);

        createdatetimeText = ((TextView) findViewById(R.id.createdatetime_phy_order_manage_details));
        createdatetimeText.setText(createDateTime);

        checknursenameText = ((TextView) findViewById(R.id.checknursename_phy_order_manage_details));
        checknursenameText.setText(checkNurseName);

        checkdatetimeText = ((TextView) findViewById(R.id.checkdatetime_phy_order_manage_details));
        checkdatetimeText.setText(checkDateTime);
        //医生嘱托
        doctodepandText = ((TextView) findViewById(R.id.doctordepand_phy_order_manage_details));



        stopTimeText = ((TextView) findViewById(R.id.stopdatetime_phy_order_manage_details));
        stopTimeText.setText(((String) data.getEndDateTime()));


        try {
            physicianOrderStateChange(data.getState());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_phy_order_manage_details:
                finish();
                break;
        }
    }

    private void physicianOrderStateChange(String state) {
        switch (state) {
            case ParentInfo_Frament_PhysicianOrderManagement.StateOne:
                mCheckImg.setBackgroundResource(R.mipmap.physician_order_check_on);
                mDoingImg.setBackgroundResource(R.mipmap.physician_order_doing_off);
                mStopImg.setBackgroundResource(R.mipmap.physician_order_stop_off);
                break;
            case ParentInfo_Frament_PhysicianOrderManagement.StateTwo:
                mCheckImg.setBackgroundResource(R.mipmap.physician_order_check_off);
                mDoingImg.setBackgroundResource(R.mipmap.physician_order_doing_on);
                mStopImg.setBackgroundResource(R.mipmap.physician_order_stop_off);
                break;
            case ParentInfo_Frament_PhysicianOrderManagement.StateThree:
                mCheckImg.setBackgroundResource(R.mipmap.physician_order_check_off);
                mDoingImg.setBackgroundResource(R.mipmap.physician_order_doing_off);
                mStopImg.setBackgroundResource(R.mipmap.physician_order_stop_on);
                break;
        }
    }
}
