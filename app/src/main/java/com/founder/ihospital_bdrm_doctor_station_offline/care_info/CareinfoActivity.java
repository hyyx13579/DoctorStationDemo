package com.founder.ihospital_bdrm_doctor_station_offline.care_info;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.ParentInfo_Frament_NursingInformation;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.care_info.util.CareInfoDataHandler;

/**
 * Created by bin_li on 16/5/16.
 * 体征单 页面Activity
 */
public class CareinfoActivity extends Activity implements OnClickListener {

    private CareInfoCustomView mCareInfoView;
    private CareInfoDataHandler mDataHandler;
    private ImageView mBackImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_careinfo);
        initView();
        initData();
    }

    private void initView() {
        mCareInfoView = (CareInfoCustomView) findViewById(R.id.care_info_view);
        mBackImg = (ImageView) findViewById(R.id.care_info_title_back);
        mBackImg.setOnClickListener(this);
    }

    private void initData() {
        String jsonStr = getIntent().getStringExtra(ParentInfo_Frament_NursingInformation.EXTRA_JSON);
        mDataHandler = new CareInfoDataHandler(jsonStr);
        mCareInfoView.setDataToInitCanvas(mDataHandler.getMap(), mDataHandler.getShowDateStrs(), mDataHandler.getFirstTime());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.care_info_title_back:
                finish();
                break;
        }
    }

}
