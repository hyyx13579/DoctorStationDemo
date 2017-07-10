package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.founder.ihospital_bdrm_doctor_station_offline.R;

/**
 * Created by 呼延 on 2016/3/24.
 * 公共act,方便更改一些公共信息
 */
public class BaseActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getWindow();
//            // Translucent status bar
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        setContentView(R.layout.act_base);
    }

}
