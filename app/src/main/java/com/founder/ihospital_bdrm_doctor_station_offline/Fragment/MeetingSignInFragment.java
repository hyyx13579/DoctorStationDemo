package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCMeetingUndone;

/**
 * Created by zhangqun on 16/4/25.
 */
public class MeetingSignInFragment extends MeetingBaseFragment{

    /**
     * 当前fragment的type,用于网络请求,详情请看MeetingBaseFragment
     * @return
     */
    @Override
    public String setSearchType() {
        return "3";
    }

    @Override
    public DCMeetingUndone setData() {
        return null;
    }
}
