package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by 呼延 on 2016/3/23.
 * 为公共碎片,方便大面积更改一些信息
 */
public class BaseFragment extends Fragment {
    protected View layout;
    protected Context mContext = getActivity();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




}
