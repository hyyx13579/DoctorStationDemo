package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Contant;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.SPUtils;
import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.InspectionReportDetailsAnalysisAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.InspectionReportDetailsAnalysisAdapter.AnalysisAdapterItemClickCallback;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.ReportCompareAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCTestOfPatientHR;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCTestOfPatientHR.ValuesBean;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCTestOfPatientHR.ValuesBean.TestItemListBean;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxb
 * 移动查房模块-检验报告功能-三层展示界面
 */


public class InspectionReportDetailsAnalysisActivity extends BaseActivity implements View.OnClickListener {

    private static final int ANIM_UP_MSG_WHAT = 0;
    private static final int ANIM_DOWN_MSG_WHAT = 1;
    private List<TestItemListBean> testItemList;
    private ListView lv;
    private ImageView btn;
    private ImageView back;
    private ListView mCompareListView;
    private ReportCompareAdapter mCompareAdapter;
    private LinearLayout mCompareLinearLayout;
    private RelativeLayout mTopDataRy;
    private int mAnimMaxHeight;
    private int mAnimMinHeight;
    private boolean mIsCompareFillScreen;
    private List<ValuesBean> mValuesList;
    private Map<String, List<TestItemListBean>> mMapList;
    private List<ValuesBean> allDatalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspection_report_details_analysis);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String jsonDataStr = intent.getStringExtra(InspectionReportDetailsActivity.JSON_DATA_STR);
        int currentId = intent.getIntExtra(InspectionReportDetailsActivity.CURRENT_ID, 0);


        Gson gson = new Gson();

        //String allReport = SPUtils.get(InspectionReportDetailsAnalysisActivity.this, Contant.INSPECTION_REPORT_DATA, "").toString();
        // gson.fromJson(allReport,DCTestOfPatientHR.class);


        DCTestOfPatientHR dcTestOfPatientHR = gson.fromJson(jsonDataStr, DCTestOfPatientHR.class);
        allDatalist = dcTestOfPatientHR.getValues();
        ValuesBean data = allDatalist.get(currentId);
        testItemList = data.getTestItemList();
        lv = ((ListView) findViewById(R.id.lv_insp_report_detais_analysis));
        InspectionReportDetailsAnalysisAdapter adapter = new InspectionReportDetailsAnalysisAdapter(getApplicationContext(), testItemList, mItemClickCallback);
        lv.setAdapter(adapter);
        btn = ((ImageView) findViewById(R.id.report_analysis_anim_btn));
        btn.setOnClickListener(this);
        back = ((ImageView) findViewById(R.id.back_insp_report_detais_analysis));
        back.setOnClickListener(this);
        mCompareListView = (ListView) findViewById(R.id.report_analysis_compare_list);
        mCompareLinearLayout = (LinearLayout) findViewById(R.id.report_analysis_bottom_layout);
        mTopDataRy = (RelativeLayout) findViewById(R.id.report_analysis_top_relative);


        //  mValuesList = filterList(list, data.getContent());
        //  mMapList = handlerMapList(mValuesList);
        mCompareAdapter = new ReportCompareAdapter(this);
        mCompareListView.setAdapter(mCompareAdapter);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.report_analysis_anim_btn:
                btn.setClickable(false);
                if (!mIsCompareFillScreen) {
                    mIsCompareFillScreen = true;
                    mAnimMaxHeight = mTopDataRy.getHeight();
                    mAnimMinHeight = mCompareLinearLayout.getLayoutParams().height;
                    handler.removeMessages(ANIM_DOWN_MSG_WHAT);
                    handler.sendEmptyMessageDelayed(ANIM_UP_MSG_WHAT, 20);
                } else {
                    mIsCompareFillScreen = false;
                    handler.removeMessages(ANIM_UP_MSG_WHAT);
                    handler.sendEmptyMessageDelayed(ANIM_DOWN_MSG_WHAT, 20);
                }

                break;
            case R.id.back_insp_report_detais_analysis:
                finish();
                break;

        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ANIM_UP_MSG_WHAT:
                    if (mCompareLinearLayout.getLayoutParams().height + 150 < mAnimMaxHeight) {
                        mCompareLinearLayout.getLayoutParams().height += 150;
                        handler.sendEmptyMessageDelayed(ANIM_UP_MSG_WHAT, 20);
                    } else {
                        mCompareLinearLayout.getLayoutParams().height = mAnimMaxHeight;
                        btn.setClickable(true);
                        btn.setImageResource(R.mipmap.double_down);
                        handler.removeMessages(ANIM_UP_MSG_WHAT);
                    }
                    mCompareLinearLayout.requestLayout();
                    break;
                case ANIM_DOWN_MSG_WHAT:
                    if (mCompareLinearLayout.getLayoutParams().height - 150 > mAnimMinHeight) {
                        mCompareLinearLayout.getLayoutParams().height -= 150;
                        handler.sendEmptyMessageDelayed(ANIM_DOWN_MSG_WHAT, 20);
                    } else {
                        mCompareLinearLayout.getLayoutParams().height = mAnimMinHeight;
                        btn.setClickable(true);
                        btn.setImageResource(R.mipmap.double_up);
                        handler.removeMessages(ANIM_DOWN_MSG_WHAT);
                    }
                    mCompareLinearLayout.requestLayout();
                    break;
            }
        }
    };
    private AnalysisAdapterItemClickCallback mItemClickCallback = new AnalysisAdapterItemClickCallback() {
        @Override
        public void onItemClick(String name) {
            // mCompareAdapter.updataList(name);
            mValuesList = filterList(allDatalist, name);
            Collections.reverse(mValuesList);
            mMapList = handlerMapList(mValuesList);
            mCompareAdapter.updataList(name, mMapList);
        }
    };

    //获取相同名字的对比类
    private List<DCTestOfPatientHR.ValuesBean> filterList(List<DCTestOfPatientHR.ValuesBean> list, String id) {
        ArrayList<DCTestOfPatientHR.ValuesBean> retList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<TestItemListBean> testItemList = list.get(i).getTestItemList();
            for (int j = 0; j < testItemList.size(); j++) {
                if (id.equals(testItemList.get(j).getProjectName())) {
                    retList.add(list.get(i));
                }

            }
        }
        return retList;
    }

    //获取对比每项的map
    private Map<String, List<TestItemListBean>> handlerMapList(List<ValuesBean> list) {
        Map<String, List<TestItemListBean>> retMap = new HashMap<>();
        List<TestItemListBean> handleList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<TestItemListBean> tempList = list.get(i).getTestItemList();
            for (int j = 0; j < tempList.size(); j++) {
                tempList.get(j).setReportDate(list.get(i).getReportDate());
                handleList.add(tempList.get(j));
            }
            //handleList.addAll(list.get(i).getTestItemList());
        }
        for (int i = 0; i < handleList.size(); i++) {
            String name = handleList.get(i).getProjectName();
            List<TestItemListBean> tempList = null;
            if (retMap.containsKey(name)) {
                retMap.get(name).add(handleList.get(i));
            } else {
                tempList = new ArrayList<>();
                tempList.add(handleList.get(i));
                retMap.put(name, tempList);
            }
        }
        return retMap;
    }
}
