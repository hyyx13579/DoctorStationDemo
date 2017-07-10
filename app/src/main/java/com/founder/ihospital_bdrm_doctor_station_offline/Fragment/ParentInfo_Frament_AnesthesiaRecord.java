package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.AnesthesiaRecordListAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.AnesthesiaRecordBean;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.EventPagerNo;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ReadPlistFile;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 呼延 on 2016/3/30.
 * 麻醉记录模块
 */
public class ParentInfo_Frament_AnesthesiaRecord extends BaseFragment {
    private static String PATIENT_NO = "29287352";
    private static String VISIT_ID = "1";
    private static final String MOTHED = "GetAnesthesiaItemList";
    public static final String TAG = ParentInfo_Frament_AnesthesiaRecord.class.getSimpleName();

    private Context mContext;

    private ListView mAnesthesiaList;
    private TextView mAnesthesiaRecordCount;
    private AnesthesiaRecordListAdapter mAdapter;
    private RelativeLayout relativeLayout;
    private DCPatientHROfDepartment.ValuesBean data;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        DCPatientHROfDepartment.ValuesBean data = (DCPatientHROfDepartment.ValuesBean) arguments.getSerializable("data");
        PATIENT_NO = data.getID();
        VISIT_ID = data.getSubID();
        mContext = getContext();
        layout = inflater.inflate(R.layout.parent_info_frag_anesthesiarecord, container, false);
        initView(layout);
        requestData();
        return layout;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    /**
     * 重写onEvent方法, 需要添加@Subscribe,否则可能注册不成功
     */
    @Subscribe
    public void onEvent(EventPagerNo no) {

        if (no.getPagerNo() == 6) {

            requestData();
        }
    }


    private void initView(View v) {
        mAnesthesiaList = (ListView) v.findViewById(R.id.anesthesiarecord_list);
        mAnesthesiaRecordCount = (TextView) v.findViewById(R.id.anesthesiarecord_count_text);
        mAdapter = new AnesthesiaRecordListAdapter(mContext, null);
        mAnesthesiaList.setAdapter(mAdapter);
        relativeLayout = ((RelativeLayout) v.findViewById(R.id.relativeLayout_parent_6));

    }

    private void requestData() {
        relativeLayout.setVisibility(View.VISIBLE);
        String url = Url.DoctorServer + MOTHED + "?" + "patientNo=" + PATIENT_NO + "&" + "visitID=" + VISIT_ID;
        RequestParams request = new RequestParams(url);
        x.http().get(request, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e(TAG, "onSuccess");
                AnesthesiaRecordBean bean = new AnesthesiaRecordBean();
                List<AnesthesiaRecordBean.ValuesBean> list = bean.transferJson2Bean(result);
                mAnesthesiaRecordCount.setText(mContext.getString(R.string.anesthesia_record_count_text, list.size()));
                mAdapter.setDataList(list);
                relativeLayout.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("麻醉", "" + ex);
                relativeLayout.setVisibility(View.GONE);
                requestDataOffLine();
                // Url.ErrorToast(getContext());

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    private void requestDataOffLine() {
        Bundle arguments = getArguments();
        data = (DCPatientHROfDepartment.ValuesBean) arguments.getSerializable("data");
        String caseID = data.getID();

        AnesthesiaRecordBean bean = new AnesthesiaRecordBean();
        List<AnesthesiaRecordBean.ValuesBean> list = new ArrayList<>();
        switch (caseID) {
            case "29379827":
                list = bean.transferJson2Bean(ReadPlistFile.onReadPatientInformation(getContext(), "GetAnesthesiaItemList", "29379827"));
                break;

            case "29542151":
                list = bean.transferJson2Bean(ReadPlistFile.onReadPatientInformation(getContext(), "GetAnesthesiaItemList", "29542151"));

                break;

            case "29554021":
                list = bean.transferJson2Bean(ReadPlistFile.onReadPatientInformation(getContext(), "GetAnesthesiaItemList", "29554021"));

                break;
            case "29555473":
                list = bean.transferJson2Bean(ReadPlistFile.onReadPatientInformation(getContext(), "GetAnesthesiaItemList", "29555473"));

                break;
            case "29557134":
                list = bean.transferJson2Bean(ReadPlistFile.onReadPatientInformation(getContext(), "GetAnesthesiaItemList", "29557134"));

                break;
            case "29591075":
                list = bean.transferJson2Bean(ReadPlistFile.onReadPatientInformation(getContext(), "GetAnesthesiaItemList", "29591075"));

                break;


        }
        mAnesthesiaRecordCount.setText(mContext.getString(R.string.anesthesia_record_count_text, list.size()));
        mAdapter.setDataList(list);
    }

}
