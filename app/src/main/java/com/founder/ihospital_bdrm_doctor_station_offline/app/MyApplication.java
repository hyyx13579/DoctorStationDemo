package com.founder.ihospital_bdrm_doctor_station_offline.app;

import android.app.Application;
import android.content.SharedPreferences;

import com.founder.ihospital_bdrm_doctor_station_offline.Utils.CustomConstants;

import org.xutils.x;

/**
 * Created by 呼延 on 2016/3/22.

 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initXutils();

    }

    private void initXutils() {
        x.Ext.init(this);
    }
    private void removeTempFromPref()
    {
        SharedPreferences sp = getSharedPreferences(
                CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
        sp.edit().remove(CustomConstants.PREF_TEMP_IMAGES).commit();
    }
}
