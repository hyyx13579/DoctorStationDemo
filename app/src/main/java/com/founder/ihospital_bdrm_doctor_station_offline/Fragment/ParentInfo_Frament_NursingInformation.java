package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.ExpandabeListview_NurseInfo_Adapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.Collection;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.NuringInfoShapeBean;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.NursingInforGruopBean;
import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCNursingRecordOfPatientHR;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.EventPagerNo;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.HelperDataString;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ReadPlistFile;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;
import com.founder.ihospital_bdrm_doctor_station_offline.View.BarGraphView;
import com.founder.ihospital_bdrm_doctor_station_offline.care_info.CareinfoActivity;

import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by 呼延 on 2016/3/30.
 * <p/>
 * 移动查房模块-护理信息功能
 */


public class ParentInfo_Frament_NursingInformation extends BaseFragment {


    private BarGraphView frame_temp;
    private BarGraphView frame_BloodPresure;
    private BarGraphView frame_Pluse;
    private BarGraphView frame_BloodSugar;
    private BarGraphView frame_Breathe;
    private DCPatientHROfDepartment.ValuesBean data;
    //存储未处理前的各项源数据
    private List<DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean> itemTemp,
            itemBloodPresure, itemPulse, itemBloodSugar, itemBreathe;

    private List<NuringInfoShapeBean> tempList = new ArrayList<>();
    private List<NuringInfoShapeBean> pulseList = new ArrayList<>();
    private List<NuringInfoShapeBean> bloodSugarList = new ArrayList<>();
    private List<NuringInfoShapeBean> breatheList = new ArrayList<>();
    private List<NuringInfoShapeBean> bloodPresureList = new ArrayList<>();


    //获取到的 DCNursingRecordOfPatientHR json串 result
    private String mJsonStringDCNursingRecordOfPatientHR;
    public static final String EXTRA_JSON = "extra_json";


    public static final String TAG = ParentInfo_Frament_NursingInformation.class.getSimpleName();
    private FloatingActionButton fab;
    private RelativeLayout relativeLayout;
    private RadioGroup radioGroup;
    private RadioButton radioBtnLeft;
    private RadioButton radioBtnRight;
    private ExpandableListView expandLv;
    private CoordinatorLayout llShape;
    private List<NursingInforGruopBean> nurseInfo;
    private ExpandabeListview_NurseInfo_Adapter nurseInfoAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.parent_info_frag_nursinginformation, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();

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


    @Subscribe
    public void onEvent(EventPagerNo no) {
        if (no.getPagerNo() == 3) {
            getDataOffLine();
        }
    }


    private void initData() {
        getDataOffLine();
        fab.setVisibility(View.VISIBLE);
    }

    private void initTemp() {

        initXLables(itemTemp, false, tempList);
        frame_temp.setDefaultYdataInfo(BarGraphView.TYPE_TEMP);
        frame_temp.setAxisX(900, 9);
        frame_temp.setShapeInfo(tempList, BarGraphView.DRAWBROKRNLINE);
        frame_temp.setRedLine(36, 37);
        frame_temp.isDrawMarkX(true, false);
        frame_temp.isDrawMarkY(true, true);


    }


    /**
     * 初始化脉搏的柱状图
     */
    private void initPluse() {
        initXLables(itemPulse, false, pulseList);
        frame_Pluse.setDefaultYdataInfo(BarGraphView.TYPE_PULSE);
        frame_Pluse.setShapeInfo(pulseList, BarGraphView.DRAWColumn);
        frame_Pluse.setAxisX(900, 9);

    }


    /**
     * 初始化血糖
     */
    private void initBloodSugar() {
        initXLables(itemBloodSugar, true, bloodSugarList);
        frame_BloodSugar.setDefaultYdataInfo(BarGraphView.TYPE_BLOODSUGAR);
        frame_BloodSugar.setShapeInfo(bloodSugarList, BarGraphView.DRAWColumn);
        frame_BloodSugar.setAxisX(900, 9);

    }


    /**
     * 初始化呼吸
     */
    private void initBreathe() {
        initXLables(itemBreathe, false, breatheList);
        frame_Breathe.setDefaultYdataInfo(BarGraphView.TYPE_BREATHE);
        frame_Breathe.setShapeInfo(breatheList, BarGraphView.DRAWColumn);
        frame_Breathe.setAxisX(900, 9);


    }


    private void initBloodPresure() {
        initBloodPresureXLables(itemBloodPresure, bloodPresureList);
        frame_BloodPresure.setDefaultYdataInfo(BarGraphView.TYPE_BLOODPRESURE);
        frame_BloodPresure.setBloodpresureShape(false, true);
        frame_BloodPresure.setShapeInfo(bloodPresureList, BarGraphView.DRAWBROKRNLINE);
        frame_BloodPresure.setAxisX(900, 9);


    }


    /**
     * -------------------------------处理数据的两个方法----------------------------
     * initXLables 适用于一般类型的数据
     * initBloodPresureXLables  适用于血压
     */


    private void initXLables(List<DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean> item, boolean bloodSugar, List<NuringInfoShapeBean> shapeList) {


        for (int i = 0; i < 7; i++) {
            if (i >= item.size()) {
                break;
            } else {
                if (bloodSugar) {
                    shapeList.add(new NuringInfoShapeBean(String.valueOf(Double.valueOf(item.get(i).getValue()) * 18), new HelperDataString().dateTostrModel4((item
                            .get(i).getCreateTime()))));
                } else {
                    shapeList.add(new NuringInfoShapeBean(String.valueOf(Double.valueOf(item.get(i).getValue())), new HelperDataString().dateTostrModel4((item
                            .get(i).getCreateTime()))));
                }
            }
        }
        Collections.reverse(shapeList);


    }

    private void initBloodPresureXLables(List<DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean> item,
                                         List<NuringInfoShapeBean> shapeList) {
        String temp;
        int position;


        for (int i = 0; i < 7; i++) {
            if (i >= item.size()) {
                break;

            } else {

                temp = item.get(i).getValue();
                position = temp.lastIndexOf("/");
                String hightValue = String.valueOf(Double.valueOf(temp.substring(0, position)));
                String lowValue = String.valueOf(Double.valueOf(temp.substring(position + 1,
                        temp.length())));


                shapeList.add(new NuringInfoShapeBean(new HelperDataString().dateTostrModel4((item
                        .get(i).getCreateTime())), hightValue, lowValue));


            }
        }
        Collections.reverse(shapeList);

    }


    private void getDataOffLine() {
        Gson gson = new Gson();
        String caseID = data.getID();
        DCNursingRecordOfPatientHR dcNursingRecordOfPatientHR = null;

        switch (caseID) {
            case "29379827":
                dcNursingRecordOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetNursingRecordOfPatientHR", "29379827"), DCNursingRecordOfPatientHR.class);
                mJsonStringDCNursingRecordOfPatientHR = ReadPlistFile.onReadPatientInformation(getContext(), "GetNursingRecordOfPatientHR", "29379827");
                break;

            case "29542151":
                dcNursingRecordOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetNursingRecordOfPatientHR", "29542151"), DCNursingRecordOfPatientHR.class);
                mJsonStringDCNursingRecordOfPatientHR = ReadPlistFile.onReadPatientInformation(getContext(), "GetNursingRecordOfPatientHR", "29542151");
                break;

            case "29554021":
                dcNursingRecordOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetNursingRecordOfPatientHR", "29554021"), DCNursingRecordOfPatientHR.class);
                mJsonStringDCNursingRecordOfPatientHR = ReadPlistFile.onReadPatientInformation(getContext(), "GetNursingRecordOfPatientHR", "29554021");
                break;
            case "29555473":
                dcNursingRecordOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetNursingRecordOfPatientHR", "29555473"), DCNursingRecordOfPatientHR.class);
                mJsonStringDCNursingRecordOfPatientHR = ReadPlistFile.onReadPatientInformation(getContext(), "GetNursingRecordOfPatientHR", "29555473");
                break;
            case "29557134":
                dcNursingRecordOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetNursingRecordOfPatientHR", "29557134"), DCNursingRecordOfPatientHR.class);
                mJsonStringDCNursingRecordOfPatientHR = ReadPlistFile.onReadPatientInformation(getContext(), "GetNursingRecordOfPatientHR", "29557134");
                break;
            case "29591075":
                dcNursingRecordOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetNursingRecordOfPatientHR", "29591075"), DCNursingRecordOfPatientHR.class);
                mJsonStringDCNursingRecordOfPatientHR = ReadPlistFile.onReadPatientInformation(getContext(), "GetNursingRecordOfPatientHR", "29591075");
                break;

        }

        List<DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean> itemList = dcNursingRecordOfPatientHR.getValues().get(0).getNurseingRecordList().get(0).getItemList();
        dealData(itemList);

    }


    private void dealData(List<DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean> data) {


        if (data != null) {

            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getTypeName().equals("体温")) {
                    itemTemp.add(data.get(i));

                } else if (data.get(i).getTypeName().equals("血压")) {
                    itemBloodPresure.add(data.get(i));
                } else if (data.get(i).getTypeName().equals("脉搏")) {
                    itemPulse.add(data.get(i));
                } else if (data.get(i).getTypeName().equals("血糖")) {
                    itemBloodSugar.add(data.get(i));
                } else if (data.get(i).getTypeName().equals("呼吸")) {
                    itemBreathe.add(data.get(i));

                }

            }


            nurseInfo = new ArrayList<>();
            if (!itemTemp.isEmpty()) {
                setCompareList(itemTemp);
                nurseInfo.add(new NursingInforGruopBean("体温", itemTemp));
            }
            if (!itemBloodPresure.isEmpty()) {
                setCompareList(itemBloodPresure);
                nurseInfo.add(new NursingInforGruopBean("血压", itemBloodPresure));
            }

            if (!itemPulse.isEmpty()) {
                setCompareList(itemPulse);
                nurseInfo.add(new NursingInforGruopBean("脉搏", itemPulse));
            }
            if (!itemBloodSugar.isEmpty()) {
                setCompareList(itemBloodSugar);
                nurseInfo.add(new NursingInforGruopBean("血糖", itemBloodSugar));
            }
            if (!itemBreathe.isEmpty()) {
                setCompareList(itemBreathe);
                nurseInfo.add(new NursingInforGruopBean("呼吸", itemBreathe));
            }

            nurseInfoAdapter.upRes(nurseInfo);


            initTemp();
            initBloodPresure();
            initPluse();
            initBloodSugar();
            initBreathe();


        }


    }

    private List<DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean> setCompareList(List<DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean> data) {

        Collections.sort(data, new Comparator<DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean>() {
            @Override
            public int compare(DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean lhs, DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean rhs) {
                SimpleDateFormat fromat = new SimpleDateFormat("yy-MM-dd HH:mm");
                try {
                    Date dt1 = fromat.parse(lhs.getCreateTime());
                    Date dt2 = fromat.parse(rhs.getCreateTime());
                    if (dt1.getTime() > dt2.getTime()) {
                        return -1;
                    } else if (dt1.getTime() < dt2.getTime()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;


            }
        });


        return data;
    }

    private void initView() {

        Bundle arguments = getArguments();
        data = (DCPatientHROfDepartment.ValuesBean) arguments.getSerializable("data");


        frame_temp = ((BarGraphView) layout.findViewById(R.id.parent_info_frag_four_frame_one));
        frame_BloodPresure = ((BarGraphView) layout.findViewById(R.id.parent_info_frag_four_frame_two));
        frame_Pluse = ((BarGraphView) layout.findViewById(R.id.parent_info_frag_four_frame_three));
        frame_BloodSugar = ((BarGraphView) layout.findViewById(R.id.parent_info_frag_four_frame_four));
        frame_Breathe = ((BarGraphView) layout.findViewById(R.id.parent_info_frag_four_frame_five));


        itemTemp = new ArrayList<>();//存储体温的集合
        itemBloodPresure = new ArrayList<>();//存储血压的集合
        itemPulse = new ArrayList<>();//存储脉搏的集合
        itemBloodSugar = new ArrayList<>();//存储血糖的集合
        itemBreathe = new ArrayList<>();//存储呼吸的集合

        fab = ((FloatingActionButton) layout.findViewById(R.id.fab));
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CareinfoActivity.class);
                intent.putExtra(EXTRA_JSON, mJsonStringDCNursingRecordOfPatientHR);
                startActivity(intent);
            }
        });

        relativeLayout = ((RelativeLayout) layout.findViewById(R.id.relativeLayout_parent_3));

        radioGroup = ((RadioGroup) layout.findViewById(R.id.parent_frag_nurse_radiogruop));
        radioBtnLeft = ((RadioButton) layout.findViewById(R.id.parent_frag_nurse_radiobtn_left));
        radioBtnRight = ((RadioButton) layout.findViewById(R.id.parent_frag_nurse_radiobtn_right));
        expandLv = ((ExpandableListView) layout.findViewById(R.id.expandlv_nurse_fragmentl));
        expandLv.setGroupIndicator(null);
        nurseInfoAdapter = new ExpandabeListview_NurseInfo_Adapter(null, getContext());
        expandLv.setAdapter(nurseInfoAdapter);
        llShape = ((CoordinatorLayout) layout.findViewById(R.id.ll_shape_nurse_fragment));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.parent_frag_nurse_radiobtn_left:
                        radioBtnLeft.setTextColor(Color.WHITE);
                        radioBtnRight.setTextColor(getResources().getColor(R.color.titlecolor));
                        llShape.setVisibility(View.VISIBLE);
                        expandLv.setVisibility(View.GONE);

                        break;
                    case R.id.parent_frag_nurse_radiobtn_right:
                        radioBtnRight.setTextColor(Color.WHITE);
                        radioBtnLeft.setTextColor(getResources().getColor(R.color.titlecolor));
                        llShape.setVisibility(View.GONE);
                        expandLv.setVisibility(View.VISIBLE);

                        break;
                }
            }
        });


    }


}
