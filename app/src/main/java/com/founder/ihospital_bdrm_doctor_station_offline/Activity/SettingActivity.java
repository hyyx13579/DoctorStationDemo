package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.founder.ihospital_bdrm_doctor_station_offline.R;


/**
 * Created by hyyx
 * <p/>
 * 配置功能未实现
 */


public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        ivBack = ((ImageView) findViewById(R.id.iv_back_setting));
        ivBack.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back_setting:
                finish();
                break;
        }
    }
}
