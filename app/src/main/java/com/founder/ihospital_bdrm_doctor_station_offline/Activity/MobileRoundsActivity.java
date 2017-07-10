package com.founder.ihospital_bdrm_doctor_station_offline.Activity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.AllDepartmentStickListViewAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.ParentHROfDepartmentApater;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.WardnameAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.MobileRoundFragmentItemOne;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.AddExtraValuesDCDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCDepartmentOfUser;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Contant;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.DealEnglishtoChineseList;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.EventAllData;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.MyDbHelper;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ReadPlistFile;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.SPUtils;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;

import org.greenrobot.eventbus.EventBus;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by hyyx
 * <p/>
 * 点击移动查房功能模块后跳转的Act(科室选择界面)
 */
public class MobileRoundsActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    /**
     * 标题栏选择病区的点击区域
     */
    private RelativeLayout mLine;
    /**
     * 标题栏返回
     */
    private ImageView mFanHui;
    /**
     * 标题栏主题
     */
    private TextView mTitle;
    public final String TAG = MobileRoundsActivity.class.getSimpleName();
    /**
     * 填充ppw的有滑动头的listView
     */
    private StickyListHeadersListView stickListView;
    /**
     * 填充ppw的适配器
     */
    private AllDepartmentStickListViewAdapter allDepartmentStickListViewAdapter;
    /**
     * 展示ppw的页面
     */
    private RelativeLayout relativeLayout;
    private int i;
    private List<AddExtraValuesDCDepartment> addValues;
    private RelativeLayout rvPopUpWindow;
    private LinearLayout linearLayout;
    private ViewPager viewPager;
    private HorizontalScrollView horizontalScrollView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    //  private TextView textView5;
    private FragmentPagerAdapter adapter;
    private static String deptCode;
    private static String name;
    private String doctor_id;

    /**
     * 病房下不同区域的数据源
     */
    private List<DCPatientHROfDepartment.ValuesBean> data;
    private List<DCPatientHROfDepartment.ValuesBean> dataTwo;
    private List<DCPatientHROfDepartment.ValuesBean> dataThree;
    /**
     * 病房下不同区域的病人数量
     */
    private int size;
    private int sizeTwo;
    private int sizeThree;
    /**
     * loading界面
     */
    private RelativeLayout rvBackground;
    private RelativeLayout rvPopUpWindowBackground;

    private MobileRoundFragmentItemOne fragment;

    private boolean isWardSecect = false;

    private RelativeLayout NCRvbackGround;
    private List<String> wardList;
    private String format;
    private ListView lViWardName;
    private WardnameAdapter wardnameAdapter;
    private EventAllData eventAllData;
    private MyDbHelper myDbHelper;

    public static boolean isSaveOne = true;
    public static boolean isSaveTwo = true;
    public static boolean isSaveThree = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_rounds);
        initView();
        initViewPagerLayout();
        initViewPagerCtrl();


    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        LoadSharePreference();
//    }

    @Override
    protected void onResume() {
        super.onResume();
        // TODO: 16/9/23 有时候点击移动查房加载不出来数据,因为LoadSharePreference的启动时间有冲突
        LoadSharePreference();
    }

    private void LoadSharePreference() {
        name = SPUtils.get(getBaseContext(), Contant.DEPARTNAME, "").toString();
        deptCode = SPUtils.get(getBaseContext(), Contant.DEPARTCODE, "").toString();

        if (!TextUtils.isEmpty(deptCode) && !TextUtils.isEmpty(name)) {

            if (name.equals("收藏患者")) {
                mTitle.setText(name);
                // TODO: 16/9/20  收藏列表
                LoadCollectionPatientList();
            } else {
                mTitle.setText(name);
                LoadData(deptCode);
            }

        }


    }


    private void initView() {

        myDbHelper = new MyDbHelper(this);
        eventAllData = new EventAllData();
        Date dt = new Date();
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
        format = matter1.format(dt);

        Intent intent = getIntent();
        doctor_id = intent.getStringExtra("doctor_id");
        mLine = ((RelativeLayout) findViewById(R.id.line_MR));
        mLine.setOnClickListener(this);
        mFanHui = ((ImageView) findViewById(R.id.fanhui_MR));
        mFanHui.setOnClickListener(this);
        mTitle = ((TextView) findViewById(R.id.title_MR));
        rvBackground = ((RelativeLayout) findViewById(R.id.relativeLayout_background_mobilerounds));

        initLikePopUpWindow();
        initRvWardSelect();


    }

    /**
     * 初始化选择病区弹窗
     */
    private void initRvWardSelect() {

        NCRvbackGround = ((RelativeLayout) findViewById(R.id.wardselect_relativeLayout_mobile_rounds));
        lViWardName = ((ListView) findViewById(R.id.wardselect_lisview_popup_moblie_rounds));
        wardnameAdapter = new WardnameAdapter(null, this);
        lViWardName.setAdapter(wardnameAdapter);
        lViWardName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemAtPosition = (String) parent.getItemAtPosition(position);
                textView1.setText(itemAtPosition);
                eventAllData.setWardName(itemAtPosition);
                EventBus.getDefault().post(eventAllData);
                NCRvbackGround.setVisibility(View.GONE);


            }
        });


    }


    private void initLikePopUpWindow() {

        relativeLayout = ((RelativeLayout) findViewById(R.id.relativeLayout_mobile_rounds));
        rvPopUpWindow = ((RelativeLayout) findViewById(R.id.linearlayout_mobile_rounds));
        stickListView = ((StickyListHeadersListView) findViewById(R.id.sticklv_popup_moblie_rounds));
        allDepartmentStickListViewAdapter = new AllDepartmentStickListViewAdapter(null, getBaseContext());
        stickListView.setAdapter(allDepartmentStickListViewAdapter);
        LoadListViewOffLine();
        stickListView.setOnItemClickListener(this);
        rvPopUpWindowBackground = ((RelativeLayout) findViewById(R.id.relativeLayout_background_popupwindow));

    }

    private void initViewPagerCtrl() {
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                fragment = new MobileRoundFragmentItemOne();
                Bundle bundle = new Bundle();
                switch (position) {

                    //全部
                    case 0:
                        bundle.putInt("key", 0);
                        break;
                    //新入
                    case 1:

                        bundle.putInt("key", 1);
                        break;
                    //I级护理
                    case 2:

                        bundle.putInt("key", 2);
                        break;
//                    //体征异常
//                    case 3:
//                        bundle.putInt("key", 3);
//                        break;
//                    case 4:
//                        bundle.putInt("key", 4);
//                        break;

                }
                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            public int getCount() {
                return linearLayout.getChildCount();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                // super.destroyItem(container, position, object);
            }
        };


        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                horizontalScrollView.smoothScrollTo(position * textView1.getWidth(), 0);
                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    TextView tv = (TextView) linearLayout.getChildAt(i);
                    tv.setTextColor(getResources().getColor(R.color.text_normal_color));
                    tv.setBackgroundResource(R.drawable.background_bar_uncheck);
                }
                // 将选中字体设置为白色
                TextView tv = (TextView) linearLayout.getChildAt(position);
                tv.setTextColor(0xffffffff);
                tv.setBackgroundResource(R.drawable.background_bar_ischeck);
                if (position == 0) {
                    isWardSecect = true;
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void initViewPagerLayout() {

        linearLayout = (LinearLayout) findViewById(R.id.linearlayout_frag_mr);
        viewPager = ((ViewPager) findViewById(R.id.viewPager_frag_mr));
        horizontalScrollView = ((HorizontalScrollView) findViewById(R.id.horizontalScrollView_frag_mr));
        textView1 = ((TextView) findViewById(R.id.text1));
        textView2 = ((TextView) findViewById(R.id.text2));
        textView3 = ((TextView) findViewById(R.id.text3));
        //textView4 = ((TextView) findViewById(R.id.text4));
        // textView5 = ((TextView) findViewById(R.id.text5));

        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        //  textView4.setOnClickListener(this);
        //  textView5.setOnClickListener(this);


        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView tv = (TextView) linearLayout.getChildAt(i);
            tv.setTextColor(getResources().getColor(R.color.text_normal_color));
            tv.setBackgroundResource(R.drawable.background_bar_uncheck);
        }
        // 将第一个字体设置为白色
        TextView tv = (TextView) linearLayout.getChildAt(0);
        tv.setTextColor(Color.WHITE);
        tv.setBackgroundResource(R.drawable.background_bar_ischeck);
        isWardSecect = true;


    }

    public List<String> setWardList(List<DCPatientHROfDepartment.ValuesBean> values) {
        wardList = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            if (!TextUtils.isEmpty(values.get(i).getWardName())) {
                wardList.add(values.get(i).getWardName());
            }
        }
        //使用hashset删除重复的数据
        HashSet h = new HashSet(wardList);
        wardList.clear();
        wardList.add(0, "全部");
        wardList.addAll(h);


        return wardList;
    }


    @Override
    public void onBackPressed() {

        if (NCRvbackGround.getVisibility() == View.VISIBLE) {
            NCRvbackGround.setVisibility(View.GONE);
            return;
        }
        if (relativeLayout.getVisibility() == View.VISIBLE) {
            relativeLayout.setVisibility(View.GONE);
            return;
        }
        super.onBackPressed();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.line_MR://标题栏的选择病房区域
                if (NCRvbackGround.getVisibility() == View.VISIBLE) {
                    NCRvbackGround.setVisibility(View.GONE);
                    break;
                }
                if (relativeLayout.getVisibility() == View.VISIBLE) {
                    relativeLayout.setVisibility(View.GONE);
                    break;
                }
                // TODO: 16/9/20 获取离线病房选择数据
                LoadListViewOffLine();
                relativeLayout.setVisibility(View.VISIBLE);
                relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        relativeLayout.setVisibility(View.GONE);
                    }
                });
                allDepartmentStickListViewAdapter.updateRes(addValues);


                break;


            case R.id.fanhui_MR://标题栏返回


                if (NCRvbackGround.getVisibility() == View.VISIBLE) {
                    NCRvbackGround.setVisibility(View.GONE);
                    break;
                }
                if (relativeLayout.getVisibility() == View.VISIBLE) {
                    relativeLayout.setVisibility(View.GONE);
                    break;
                }

                finish();


                break;

            case R.id.text1:
                if (isWardSecect) {
                    if (NCRvbackGround.getVisibility() == View.VISIBLE) {
                        NCRvbackGround.setVisibility(View.GONE);
                    } else {
                        NCRvbackGround.setVisibility(View.VISIBLE);
                        NCRvbackGround.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                NCRvbackGround.setVisibility(View.GONE);
                            }
                        });
                    }

                }
                onSelectClick(v);


                break;
            case R.id.text2:
                if (NCRvbackGround.getVisibility() == View.VISIBLE) {
                    NCRvbackGround.setVisibility(View.GONE);
                    break;
                }

                onSelectClick(v);
                break;
            case R.id.text3:
                if (NCRvbackGround.getVisibility() == View.VISIBLE) {
                    NCRvbackGround.setVisibility(View.GONE);
                    break;
                }
                onSelectClick(v);
                break;
//            case R.id.text4:
//                onSelectClick(v);
//               break;
//            case R.id.text5:
//                onSelectClick(v);
//
//
        }
    }

    public void onSelectClick(View view) {
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            TextView tv = (TextView) linearLayout.getChildAt(i);
            tv.setTextColor(getResources().getColor(R.color.text_normal_color));
            tv.setBackgroundResource(R.drawable.background_bar_uncheck);
        }
        if (view.getId() != R.id.text1) {
            isWardSecect = false;
        } else {
            isWardSecect = true;
        }
        ((TextView) view).setTextColor(Color.WHITE);
        ((TextView) view).setBackgroundResource(R.drawable.background_bar_ischeck);
        viewPager.setCurrentItem(linearLayout.indexOfChild(view));

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AddExtraValuesDCDepartment itemAtPosition = (AddExtraValuesDCDepartment) parent.getItemAtPosition(position);
        name = itemAtPosition.getName();
        mTitle.setText(name);
        deptCode = itemAtPosition.getDeptCode();
        Log.e(TAG, "-----" + deptCode);
        relativeLayout.setVisibility(View.GONE);
        i++;

        if (itemAtPosition.getTitleFlag().equals("#")) {
            if (itemAtPosition.getName().equals("收藏患者")) {
                SPUtils.put(getBaseContext(), Contant.DEPARTNAME, "收藏患者");
                // TODO: 16/9/20 获取收藏数据
                LoadCollectionPatientList();
                eventAllData.setWardName("");
                textView1.setText("全部");
            }
        } else {
            SPUtils.put(getBaseContext(), Contant.DEPARTCODE, deptCode);
            SPUtils.put(getBaseContext(), Contant.DEPARTNAME, name);
            // TODO: 16/9/20 获取病区数据
            LoadData(deptCode);
            eventAllData.setWardName("");
            textView1.setText("全部");

        }

    }


    /**
     * ---------------------------------------------------------------------
     * 离线请求数据的方法
     * ---------------------------------------------------------------------
     */

    public void LoadListViewOffLine() {
        Gson gson = new Gson();
        DCDepartmentOfUser dcDepartmentOfUser = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetValidDepartmentOfUser", "03267"), DCDepartmentOfUser.class);
        List<DCDepartmentOfUser.ValuesBean> values = dcDepartmentOfUser.getValues();
        //  values.add(new DCDepartmentOfUser.ValuesBean("我的患者", true));
        values.add(new DCDepartmentOfUser.ValuesBean("收藏患者", true));
        //处理数据源,增加flag,方便使用
        addValues = DealEnglishtoChineseList.AddExtraValues(values);
        Log.e(TAG, "onSuccess------" + values.size());
        allDepartmentStickListViewAdapter.updateRes(addValues);

    }

    private void LoadData(final String deptCode) {

        String url = Url.DoctorServer + "GetPatientHROfDepartmentNew";
        RequestParams requestParams = new RequestParams(url);
        requestParams.addBodyParameter("departmentID", deptCode);
        requestParams.addBodyParameter("doctorID", doctor_id);

        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        data = new ArrayList<>();//存储病重的List集合
                        dataTwo = new ArrayList<>();//存储全部病人的list集合
                        dataThree = new ArrayList<>();//存储I级护理的list集合
                        Log.e(TAG, "进入onSuccess");
                        Gson gson = new Gson();
                        DCPatientHROfDepartment dcPatientHROfDepartment = gson.fromJson(result, DCPatientHROfDepartment.class);
                        List<DCPatientHROfDepartment.ValuesBean> values = dcPatientHROfDepartment.getValues();
                        if (!values.isEmpty()) {
                            // isState(values);
                            wardnameAdapter.updateRes(setWardList(values));
                            eventAllData.setData(values);
                            EventBus.getDefault().post(eventAllData);
                        }

                        rvBackground.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        DCPatientHROfDepartment dcPatientHROfDepartment;
                        List<DCPatientHROfDepartment.ValuesBean> values = null;
                        Gson gson = new Gson();
                        switch (deptCode) {
                            case "4080000":
                                if (isSaveOne) {
                                    dcPatientHROfDepartment = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetPatientHROfDepartment", "4080000"), DCPatientHROfDepartment.class);
                                    values = dcPatientHROfDepartment.getValues();
                                    savePatientInfo(values, "4080000");
                                    isSaveOne = false;
                                } else {
                                    values = myDbHelper.getUserList("4080000");
                                    myDbHelper.close();
                                }

                                break;

                            case "1000603":

                                if (isSaveTwo) {

                                    dcPatientHROfDepartment = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetPatientHROfDepartment", "1000603"), DCPatientHROfDepartment.class);
                                    values = dcPatientHROfDepartment.getValues();
                                    savePatientInfo(values, "1000603");
                                    isSaveTwo = false;
                                } else {

                                    values = myDbHelper.getUserList("1000603");
                                    myDbHelper.close();
                                }
                                break;

                            case "1000197":

                                if (isSaveThree) {
                                    dcPatientHROfDepartment = gson.fromJson(ReadPlistFile.onReadPatientInformation(getBaseContext(), "GetPatientHROfDepartment", "1000197"), DCPatientHROfDepartment.class);
                                    values = dcPatientHROfDepartment.getValues();
                                    savePatientInfo(values, "1000197");
                                    isSaveThree = false;
                                } else {
                                    values = myDbHelper.getUserList("1000197");
                                    myDbHelper.close();
                                }
                                break;
                        }
                        wardnameAdapter.updateRes(setWardList(values));
                        eventAllData.setData(values);

                        EventBus.getDefault().post(eventAllData);
                        rvBackground.setVisibility(View.GONE);

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                }

        );

    }

    private void savePatientInfo(List<DCPatientHROfDepartment.ValuesBean> values, String deptCode) {
        Gson gson = new Gson();

        for (int i = 0; i < values.size(); i++) {
            DCPatientHROfDepartment.ValuesBean valuesBean = values.get(i);
            String patientName = valuesBean.getName();
            String patientInfo = gson.toJson(valuesBean);
            myDbHelper.insertUserInfo(deptCode, patientName, patientInfo);
        }

        myDbHelper.close();


    }


    /**
     * 请求收藏的患者
     */
    private void LoadCollectionPatientList() {

        String url = Url.DoctorServer + "GetCollectionPatientListByDoctorID";
        RequestParams requestParams = new RequestParams(url);
        requestParams.addBodyParameter("doctorID", doctor_id);
        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        data = new ArrayList<>();//存储病重的List集合
                        dataTwo = new ArrayList<>();//存储全部病人的list集合
                        dataThree = new ArrayList<>();//存储I级护理的list集合
                        Gson gson = new Gson();
                        DCPatientHROfDepartment dcPatientHROfDepartment = gson.fromJson(result, DCPatientHROfDepartment.class);
                        List<DCPatientHROfDepartment.ValuesBean> values = dcPatientHROfDepartment.getValues();
                        //isState(values);
                        if (!values.isEmpty()) {
                            wardnameAdapter.updateRes(setWardList(values));
                            eventAllData.setData(values);
                            EventBus.getDefault().post(eventAllData);
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        List<DCPatientHROfDepartment.ValuesBean> values = myDbHelper.getUserList(ParentHROfDepartmentApater.COLLECTION_LIST);
                        if (!values.isEmpty()) {
                            wardnameAdapter.updateRes(setWardList(values));
                            eventAllData.setData(values);
                            EventBus.getDefault().post(eventAllData);
                        } else {
                            values = new ArrayList<DCPatientHROfDepartment.ValuesBean>();
                            eventAllData.setData(values);
                            EventBus.getDefault().post(eventAllData);
                        }
                        myDbHelper.close();
                        rvBackground.setVisibility(View.GONE);

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                }

        );


    }


}



