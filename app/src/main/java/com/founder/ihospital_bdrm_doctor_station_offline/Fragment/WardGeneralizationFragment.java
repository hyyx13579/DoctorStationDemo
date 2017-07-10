package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Activity.ParentInformationActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.RecentlyPatientAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.BasePatientInfo;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.MyDbHelper;
import com.founder.ihospital_bdrm_doctor_station_offline.View.MyGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 呼延 on 2016/3/25.
 * 为首页卡片选择项中的病区概况的碎片
 */
public class WardGeneralizationFragment extends BaseFragment {

    public FragmentManager fm;
    public FragmentTransaction transaction;
    private WardGeneralizationFragment_Frag_one frag_one;
    public final String TAG = WardGeneralizationFragment.class.getSimpleName();
    private TextView tvWelcomTxt;
    private TextView tvKeShi;
    private ImageView ivRefresh;
    public static final String REFRESH_FRAGMENT = "refresh_fragment";
    private String id;
    private MyGridView gvRecentlyPaitent;
    private RelativeLayout rvPatientBackGround;
    private RecentlyPatientAdapter recentlyPatientAdapter;
    private BasePatientInfo.ValuesBean data;
    private DCPatientHROfDepartment.ValuesBean itemAtPosition;
    private MyDbHelper myDbHelper;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.main_fragment, container, false);
        return layout;

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setDefaultFragment();
    }

    private void setDefaultFragment() {

        myDbHelper = new MyDbHelper(getContext());

        Bundle arguments = getArguments();
        id = arguments.getString("id");
        String usename = arguments.getString("usename");
        String deptName = arguments.getString("deptName");
        tvWelcomTxt = ((TextView) layout.findViewById(R.id.welcome_txt_valus));
        tvWelcomTxt.setText(usename);
        tvKeShi = ((TextView) layout.findViewById(R.id.keshi_values));
        tvKeShi.setText(deptName);
//        ivRefresh = ((ImageView) layout.findViewById(R.id.refresh_iv));
//        ivRefresh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (frag_one.TYPE_PAGR.equals("gridview")) {
//                    frag_one.rvBackView.setVisibility(View.VISIBLE);
//                    frag_one.LoadValidDepartment();
//
//                } else if (frag_one.TYPE_PAGR.equals("pipeView")) {
//                    frag_one.rvBackView.setVisibility(View.VISIBLE);
//                    frag_one.LoadDepartmentCareLevel(frag_one.DEPARTCODE);
//
//                }
//            }
//        });

        fm = getChildFragmentManager();
        transaction = fm.beginTransaction();
        frag_one = new WardGeneralizationFragment_Frag_one();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        frag_one.setArguments(bundle);
        transaction.add(R.id.main_fragment_frame, frag_one, WardGeneralizationFragment_Frag_one.TAG);
        transaction.commit();
        /**
         * ------------最近查看患者卡片--------------------------------------------
         */
        gvRecentlyPaitent = ((MyGridView) layout.findViewById(R.id.recently_patitent_gv));
//        ((ImageView) layout.findViewById(R.id.refresh_recently_patient)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rvPatientBackGround.setVisibility(View.VISIBLE);
//                getLookPaitentList();
//            }
//        });
        rvPatientBackGround = ((RelativeLayout) layout.findViewById(R.id.relativeLayout_background_patient_gv));

        recentlyPatientAdapter = new RecentlyPatientAdapter(null, getContext());
        gvRecentlyPaitent.setAdapter(recentlyPatientAdapter);
        getLookPaitentList();
        gvRecentlyPaitent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemAtPosition = (DCPatientHROfDepartment.ValuesBean) parent.getItemAtPosition(position);
                Log.e(TAG, itemAtPosition.getDeptCode() + itemAtPosition.getID());
                Intent intent = new Intent(getContext(), ParentInformationActivity.class);
                intent.putExtra(MobileRoundFragmentItemOne.PATIENT_INFO, itemAtPosition);
                startActivity(intent);


            }

        });


    }


    public void getLookPaitentList() {
//        String url = Url.DoctorServer + "GetLookPatientList";
//        RequestParams requestParams = new RequestParams(url);
//        requestParams.addBodyParameter("doctorID", id);
//        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
//                    @Override
//                    public void onSuccess(String result) {
////                        rvPatientBackGround.setVisibility(View.GONE);
////                        Log.e(TAG, "onSuccess-------getLookPaitentList");
////                        Gson gson = new Gson();
////                        GetLookPatitentList getLookPatitentList = gson.fromJson(result, GetLookPatitentList.class);
////                        List<GetLookPatitentList.ValuesBean> values = getLookPatitentList.getValues();
////                        if (values != null) {
////                            recentlyPatientAdapter.updataRes(values);
////
////                        } else {
////                            Url.ErrorToast(getActivity().getApplicationContext(), "无请求数据");
////                        }
//
//
//                    }
//
//                    @Override
//                    public void onError(Throwable ex, boolean isOnCallback) {


        List<DCPatientHROfDepartment.ValuesBean> values = myDbHelper.getUserList(MobileRoundFragmentItemOne.LookPaitentList);
        if (values == null) {
            values = new ArrayList<>();
        }
        recentlyPatientAdapter.updataRes(values);

//                    }
//
//                    @Override
//                    public void onCancelled(CancelledException cex) {
//
//                    }
//
//                    @Override
//                    public void onFinished() {
//
//                    }
//                }
//
//        );
//
//
//    }


    }
}
