package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.MyStickListViewAdapterWardSelect;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DealWardformation;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.WardIn2formation;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.DeptWardList_JsonUtils;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ReadPlistFile;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by hyyx
 * <p/>
 * 床位管理模块-选择科室病区功能
 */


public class WardSelect_BedActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private RadioGroup rGroup;
    private RadioButton rbOne;
    private RadioButton rbTwo;
    private RadioButton rbThree;
    private ImageView back_btn;
    private StickyListHeadersListView stickListView;
    private Map<String, String> elementsMap;
    private String doctor_id;
    public final String TAG = WardSelect_BedActivity.class.getSimpleName();

    private List<HashMap<String, Object>> dataNewHosp;//存储新院的病区
    private List<HashMap<String, Object>> dataOldHosp;//存储老院的病区
    private List<HashMap<String, Object>> dataHaiDianHosp;//存储海淀医院的病区

    private MyStickListViewAdapterWardSelect myStickListViewAdapterWardSelect;


    private List<WardIn2formation> dealData; //存储转换处理后的数据
    private WardIn2formation wardInformation;
    private String selectDeptCode;

    private List<WardIn2formation> data;
    private ArrayList<DealWardformation> selectData;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ward_select__bed);
        initView();
    }

    private void initView() {

        Intent intent = getIntent();
        doctor_id = intent.getStringExtra("doctor_id");
        Log.e(TAG, "doctor_id---" + doctor_id);


        relativeLayout = ((RelativeLayout) findViewById(R.id.rv_wardSelectBed));

        rGroup = ((RadioGroup) findViewById(R.id.rg_ward_select_bed));
        rGroup.setOnCheckedChangeListener(this);

        rbOne = ((RadioButton) findViewById(R.id.rb_one_ward_select_bed));
        rbTwo = ((RadioButton) findViewById(R.id.rb_two_ward_select_bed));
        rbThree = ((RadioButton) findViewById(R.id.rb_three_ward_select_bed));

        back_btn = ((ImageView) findViewById(R.id.back_ward_select_bed));
        back_btn.setOnClickListener(this);

        stickListView = ((StickyListHeadersListView) findViewById(R.id.stick_lv_ward_select_bed));

        dataNewHosp = new ArrayList<>();
        dataOldHosp = new ArrayList<>();
        dataHaiDianHosp = new ArrayList<>();
        myStickListViewAdapterWardSelect = new MyStickListViewAdapterWardSelect(getData(), getApplicationContext());
        stickListView.setAdapter(myStickListViewAdapterWardSelect);
        stickListView.setOnItemClickListener(this);


    }

    private List<WardIn2formation> getData() {
        relativeLayout.setVisibility(View.VISIBLE);

        String url = Url.BedManagementServerTwo + "GetDeptWardList";
        RequestParams requestParams = new RequestParams(url);

        requestParams.addBodyParameter("doctorNo", doctor_id);
        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e(TAG, "onSuccess---" + result);
                        List<HashMap<String, Object>> listFromJson = DeptWardList_JsonUtils.getListFromJson(result);
//                        Log.e(TAG, "listFromJson---" + listFromJson);
//                        String deptName = ((String) listFromJson.get(1).get("DeptName"));
//                        List<HashMap<String, Object>> wardList = ((List<HashMap<String, Object>>) listFromJson.get(1).get("WardList"));
//                        Log.e(TAG, "deptName---" + deptName);
//                        Log.e(TAG, "wardList---" + wardList+"----"+wardList.size());
//                        String deptName1 = ((String) wardList.get(0).get("DeptName"));
//                        List<HashMap<String, Object>> wardListTwo = ((List<HashMap<String, Object>>) wardList.get(0).get("WardList"));
//                        Log.e(TAG, "deptName1---" + deptName1);
//                        Log.e(TAG, "wardListTwo---" + wardListTwo+"----"+wardListTwo.size());
                        if (listFromJson.size() != 0) {
                            dataNewHosp = ((List<HashMap<String, Object>>) listFromJson.get(0).get("WardList"));
                            dataOldHosp = ((List<HashMap<String, Object>>) listFromJson.get(1).get("WardList"));
                            dataHaiDianHosp = ((List<HashMap<String, Object>>) listFromJson.get(2).get("WardList"));
                            Log.e(TAG, "onSuccess---初始化数据完成");

                            myStickListViewAdapterWardSelect.addRes(onChangeDataOrder(dataNewHosp));
                            data = new ArrayList<WardIn2formation>();
                            data = onChangeDataOrder(dataNewHosp);

                            relativeLayout.setVisibility(View.GONE);

                        } else {
                            Log.e(TAG, "人工选择进入onError_List为0");
                            Url.ErrorToastTwo(getBaseContext());
                            relativeLayout.setVisibility(View.GONE);
                        }


                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e(TAG, "onError---------------------" + ex);
                        //  Url.ErrorToast(getBaseContext());
                        getDataOffLine();
                        relativeLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                }

        );
        return null;
    }


    private List<WardIn2formation> getDataOffLine() {
        List<HashMap<String, Object>> listFromJson = DeptWardList_JsonUtils.getListFromJson(
                ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetDeptWardList", "default"));
        if (listFromJson.size() != 0) {
            dataNewHosp = ((List<HashMap<String, Object>>) listFromJson.get(0).get("WardList"));
            dataOldHosp = ((List<HashMap<String, Object>>) listFromJson.get(1).get("WardList"));
            dataHaiDianHosp = ((List<HashMap<String, Object>>) listFromJson.get(2).get("WardList"));
            Log.e(TAG, "onSuccess---初始化数据完成");

            myStickListViewAdapterWardSelect.addRes(onChangeDataOrder(dataNewHosp));
            data = new ArrayList<WardIn2formation>();
            data = onChangeDataOrder(dataNewHosp);


        }
        return null;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        data = new ArrayList<>();
        switch (checkedId) {
            case R.id.rb_one_ward_select_bed:
                ChangeColor(rbOne, rbTwo, rbThree);
                myStickListViewAdapterWardSelect.updateRes(onChangeDataOrder(dataNewHosp));
                data = onChangeDataOrder(dataNewHosp);
                break;

            case R.id.rb_two_ward_select_bed:
                ChangeColor(rbTwo, rbOne, rbThree);

                myStickListViewAdapterWardSelect.updateRes(onChangeDataOrder(dataOldHosp));
                data = onChangeDataOrder(dataOldHosp);
                break;

            case R.id.rb_three_ward_select_bed:
                ChangeColor(rbThree, rbOne, rbTwo);

                myStickListViewAdapterWardSelect.updateRes(onChangeDataOrder(dataHaiDianHosp));
                data = onChangeDataOrder(dataHaiDianHosp);
                break;
        }


    }

    public void ChangeColor(RadioButton checkId, RadioButton noCheckIdOne, RadioButton noCheckIdTwo) {

        checkId.setTextColor(Color.WHITE);
        noCheckIdOne.setTextColor(getResources().getColor(R.color.titlecolor));
        noCheckIdTwo.setTextColor(getResources().getColor(R.color.titlecolor));


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_ward_select_bed:
                finish();
                break;
        }


    }

    /**
     * @param data 需要传入已经处理分类(新院,老院,海淀医院)的数据
     */
    public List<WardIn2formation> onChangeDataOrder(List<HashMap<String, Object>> data) {

        dealData = new ArrayList<>();


        for (int i = 0; i < data.size(); i++) {
            List<HashMap<String, Object>> wardList = (List<HashMap<String, Object>>) data.get(i).get("WardList");

            for (int j = 0; j < wardList.size(); j++) {
                wardInformation = new WardIn2formation(
                        data.get(i).get("DeptName").toString(),
                        data.get(i).get("DeptCode").toString(),
                        wardList.get(j).get("DeptCode").toString(),
                        wardList.get(j).get("DeptName").toString());
                dealData.add(wardInformation);

            }
        }
        Log.e(TAG, dealData.toString());
        return dealData;
    }

    /**
     * @param data           转换处理后的数据
     * @param selectDeptCode 选择的科室的编码
     */
    public ArrayList<DealWardformation> onSelectData(List<WardIn2formation> data, String selectDeptCode) {

        selectData = new ArrayList<DealWardformation>();

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getDeptCode().equals(selectDeptCode)) {
                selectData.add(new DealWardformation(
                        data.get(i).getDeptName(),
                        data.get(i).getDeptCode(),
                        data.get(i).getWardName(),
                        data.get(i).getWardCode()
                ));
            }
        }
        Log.e(TAG, "" + selectData.size());


        return selectData;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        WardIn2formation itemAtPosition = (WardIn2formation) parent.getItemAtPosition(position);
        selectDeptCode = itemAtPosition.getDeptCode();


//        itemAtPosition.getWardName();
//        itemAtPosition.getDeptName();
//        itemAtPosition.getWardCode();
//        Log.e(TAG, itemAtPosition.getDeptCode() + itemAtPosition.getDeptName() + "---" + itemAtPosition.getWardCode() + itemAtPosition.getWardName());
        Intent intent = new Intent(getApplicationContext(), BedActivity.class);
        intent.putExtra("WardSelect_BedActivity_sticklv_item_data", itemAtPosition);

        intent.putParcelableArrayListExtra("onSelectData", onSelectData(data, selectDeptCode));
        setResult(100, intent);
        finish();


    }
}


