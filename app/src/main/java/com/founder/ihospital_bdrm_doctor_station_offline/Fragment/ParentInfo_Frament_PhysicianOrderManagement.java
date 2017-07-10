package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Activity.PhysicianOrderManagementDetailsActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.AdviceOfPatientHRAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCAdviceOfPatientHR;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.EventPagerNo;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ReadPlistFile;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;
import com.founder.ihospital_bdrm_doctor_station_offline.View.MyListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 呼延 on 2016/3/30.
 * <p/>
 * 移动查房模块-遗嘱管理功能
 */
public class ParentInfo_Frament_PhysicianOrderManagement extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {


    /**
     * 可点击的三个按钮(全部医嘱,长期医嘱,临时医嘱)
     */
    private TextView tv_one;
    private TextView tv_two;
    private TextView tv_three;
    private LinearLayout lin_order_tv;
    private MyListView myListView;
    private AdviceOfPatientHRAdapter adviceOfPatientHRAdapter;
    private DCPatientHROfDepartment.ValuesBean data;
    public static final String TAG = ParentInfo_Frament_PhysicianOrderManagement.class.getSimpleName();
    /**
     * 三项不同的数据(全部医嘱,长期医嘱,临时医嘱)
     */
    private List<DCAdviceOfPatientHR.ValuesBean> allData = new ArrayList<>();
    private List<DCAdviceOfPatientHR.ValuesBean> longTermData = new ArrayList<>();
    private List<DCAdviceOfPatientHR.ValuesBean> shortTermData = new ArrayList<>();

    private RelativeLayout relativeLayout;
    private TextView tvSelectState;
    private PopupWindow popupWindow;
    private View view;
    private LinearLayout lvPpwTerm;
    private TextView tvTermAll;
    private TextView tvTermShenHe;
    private TextView tvTermZaiZhi;
    private TextView tvTermStop;
    private List<TextView> tvList;
    private TextView tvTypeAll;
    private TextView tvTypeYaoPin;
    private TextView tvTypeJianCha;
    private TextView tvTypeShouShu;
    private TextView tvTypeHuLi;
    private TextView tvTypeQiTa;
    private TextView tvSure;
    private TextView tvShowType;
    private TextView tvShowTermState;

    /**
     * 用到的一些数据
     */
    public static final String LongTerm = "长期医嘱";
    public static final String ShortTerm = "临时医嘱";
    public static final String StateOne = "1";
    public static final String StateTwo = "2";
    public static final String StateThree = "3";
    public static String TermState = "全部";
    public static String TermType = "全部";
    public static int OneRequir = 10; //10-12
    public static int TwoRequir = 100;//100-103
    public static int ThreeRequir = 1000;//1000-1005
    public static final String ZZYP = "针剂药品";
    public static final String PJYP = "片剂药品";
    public static final String ZLL = "治疗类";
    public static final String XZL = "小治疗";
    public static final String DSY = "大输液";
    public static final String HYL = "化验类";
    public static final String JCL = "检查类";
    public static final String FSL = "放射类";
    public static final String SSL = "手术类";
    public static final String MZ = "麻醉";
    public static final String HLL = "护理类";
    public static final String YSL = "饮食类";


    private List<DCAdviceOfPatientHR.ValuesBean> values = new ArrayList<>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.parent_info_frag_physicianordermanagement, container, false);
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
        //初始化数据
        OneRequir = 10;
        TwoRequir = 100;
        ThreeRequir = 1000;
        TermState = "全部";
        TermType = "全部";
        tvShowTermState.setText(TermState);
        tvShowType.setText(TermType);
    }


    /**
     * 重写onEvent方法, 需要添加@Subscribe,否则可能注册不成功
     */
    @Subscribe
    public void onEvent(EventPagerNo no) {
        if (no.getPagerNo() == 2) {
            Log.e(TAG, "onEvent进入");
            getData();

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    /**
     * 网络请求获取数据
     *
     * @return
     */

    @Nullable
    private List<DCAdviceOfPatientHR.ValuesBean> getData() {
        relativeLayout.setVisibility(View.VISIBLE);
        String caseID = data.getID();
        String subID = data.getSubID();
        String url = Url.DoctorServer + "GetDoctorAdviceOfPatientHR";
        final RequestParams requestParams = new RequestParams(url);
        //正常方法,不需要@符号
        requestParams.addBodyParameter("patientID", "@" + caseID);
        requestParams.addBodyParameter("visitID", subID);
        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                relativeLayout.setVisibility(View.GONE);
                Gson gson = new Gson();
                DCAdviceOfPatientHR dcAdviceOfPatientHR = gson.fromJson(result, DCAdviceOfPatientHR.class);
                values = dcAdviceOfPatientHR.getValues();
                Log.e(TAG, values.toString());
                for (int i = 0; i < lin_order_tv.getChildCount(); i++) {
                    TextView tv = (TextView) lin_order_tv.getChildAt(i);
                    tv.setTextColor(getResources().getColor(R.color.text_normal_color));
                    tv.setBackgroundResource(R.color.activity_base_bg);
                }
                /**
                 * 初始化三种数据(全部医嘱,长期医嘱,临时医嘱)
                 */
                for (int i = 0; i < values.size(); i++) {
                    allData.add(values.get(i));
                    if (LongTerm.equals(values.get(i).getIsLongTerm())) {
                        longTermData.add(values.get(i));
                    } else if (ShortTerm.equals(values.get(i).getIsLongTerm())) {
                        shortTermData.add(values.get(i));
                    }
                }
                TextView tv = (TextView) lin_order_tv.getChildAt(0);
                tv.setTextColor(Color.WHITE);
                tv.setBackgroundResource(R.color.titlecolor);
                adviceOfPatientHRAdapter.updateRes(values);
                relativeLayout.setVisibility(View.GONE);


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e(TAG, "onError---------------------" + ex);
                //Url.ErrorToast(getContext());
                for (int i = 0; i < lin_order_tv.getChildCount(); i++) {
                    TextView tv = (TextView) lin_order_tv.getChildAt(i);
                    tv.setTextColor(getResources().getColor(R.color.text_normal_color));
                    tv.setBackgroundResource(R.color.activity_base_bg);
                }
                TextView tv = (TextView) lin_order_tv.getChildAt(0);
                tv.setTextColor(Color.WHITE);
                tv.setBackgroundResource(R.color.titlecolor);
                adviceOfPatientHRAdapter.updateRes(getDataOffLine());
                relativeLayout.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


        return null;
    }


    private void initView() {
        Bundle arguments = getArguments();
        data = (DCPatientHROfDepartment.ValuesBean) arguments.getSerializable("data");
        tv_one = ((TextView) layout.findViewById(R.id.order_tv_one));
        tv_two = ((TextView) layout.findViewById(R.id.order_tv_two));
        tv_three = ((TextView) layout.findViewById(R.id.order_tv_three));

        lin_order_tv = ((LinearLayout) layout.findViewById(R.id.order_linearlayout_tv));
        tv_one.setOnClickListener(this);
        tv_two.setOnClickListener(this);
        tv_three.setOnClickListener(this);
        //设置第一行选项初始化(全部,长期,临时)
        for (int i = 0; i < lin_order_tv.getChildCount(); i++) {
            TextView tv = (TextView) lin_order_tv.getChildAt(i);
            tv.setTextColor(this.getResources().getColor(R.color.text_normal_color));
            tv.setBackgroundResource(R.color.activity_base_bg);
        }
        TextView tv = (TextView) lin_order_tv.getChildAt(0);
        tv.setTextColor(Color.WHITE);
        tv.setBackgroundResource(R.color.titlecolor);

        myListView = ((MyListView) layout.findViewById(R.id.parent_frag_three_lv));
        relativeLayout = ((RelativeLayout) layout.findViewById(R.id.relativeLayout_parent_2));
        tvSelectState = ((TextView) layout.findViewById(R.id.doctor_select));
        tvShowType = ((TextView) layout.findViewById(R.id.doctor_type_tv));
        tvShowTermState = ((TextView) layout.findViewById(R.id.doctor_isTerm_tv));
        tvSelectState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPpw(v);
            }
        });
        adviceOfPatientHRAdapter = new AdviceOfPatientHRAdapter(null, getContext());
        myListView.setAdapter(adviceOfPatientHRAdapter);
        myListView.setOnItemClickListener(this);
        getData();


    }

    /**
     * 展示Ppw
     *
     * @param parent
     */
    private void showPpw(View parent) {

        if (popupWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View inflate = layoutInflater.inflate(R.layout.ppw_select_state, null);
            popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            initPpwView(inflate);


        }
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(parent);


    }

    /**
     * 初始化Ppw
     *
     * @param layout
     */
    private void initPpwView(View layout) {
        view = ((View) layout.findViewById(R.id.ppw_select_view));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        lvPpwTerm = ((LinearLayout) layout.findViewById(R.id.ppw_select_isTerm_lv));

        //第二行的初始化(全部,审核,在执,停止)
        for (int i = 0; i < lvPpwTerm.getChildCount(); i++) {
            ((TextView) lvPpwTerm.getChildAt(i)).setTextColor(getResources().getColor(R.color.btn_gray));
            ((TextView) lvPpwTerm.getChildAt(i)).setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_gray_circle_noclick));
        }
        ((TextView) lvPpwTerm.getChildAt(0)).setTextColor(getResources().getColor(R.color.white));
        ((TextView) lvPpwTerm.getChildAt(0)).setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_gray_circle_isclick));


        tvTermAll = ((TextView) layout.findViewById(R.id.ppw_select_isTerm_all_tv));
        tvTermAll.setOnClickListener(this);
        tvTermShenHe = ((TextView) layout.findViewById(R.id.ppw_select_isTerm_shenhe_tv));
        tvTermShenHe.setOnClickListener(this);
        tvTermZaiZhi = ((TextView) layout.findViewById(R.id.ppw_select_isTerm_zaizhi_tv));
        tvTermZaiZhi.setOnClickListener(this);
        tvTermStop = ((TextView) layout.findViewById(R.id.ppw_select_isTerm_stop_tv));
        tvTermStop.setOnClickListener(this);


        tvList = new ArrayList<>();
        tvTypeAll = ((TextView) layout.findViewById(R.id.ppw_select_type_all_tv));
        tvTypeAll.setOnClickListener(this);
        tvTypeYaoPin = ((TextView) layout.findViewById(R.id.ppw_select_type_yaopinzhiliao_tv));
        tvTypeYaoPin.setOnClickListener(this);
        tvTypeJianCha = ((TextView) layout.findViewById(R.id.ppw_select_type_jianyanjiancha_tv));
        tvTypeJianCha.setOnClickListener(this);
        tvTypeShouShu = ((TextView) layout.findViewById(R.id.ppw_select_type_shoushumazui_tv));
        tvTypeShouShu.setOnClickListener(this);
        tvTypeHuLi = ((TextView) layout.findViewById(R.id.ppw_select_type_hulishanshi_tv));
        tvTypeHuLi.setOnClickListener(this);
        tvTypeQiTa = ((TextView) layout.findViewById(R.id.ppw_select_type_qita_tv));
        tvTypeQiTa.setOnClickListener(this);
        tvList.add(tvTypeAll);
        tvList.add(tvTypeYaoPin);
        tvList.add(tvTypeJianCha);
        tvList.add(tvTypeHuLi);
        tvList.add(tvTypeShouShu);
        tvList.add(tvTypeQiTa);
        //第三行的初始化(全部,药品治疗,检验检查,手术麻醉,护理膳食,其他)
        for (int i = 0; i < tvList.size(); i++) {
            tvList.get(i).setTextColor(getResources().getColor(R.color.btn_gray));
            tvList.get(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_gray_circle_noclick));
        }
        tvList.get(0).setTextColor(getResources().getColor(R.color.white));
        tvList.get(0).setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_gray_circle_isclick));


        tvSure = ((TextView) layout.findViewById(R.id.ppw_select_sure_tv));
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvShowTermState.setText(TermState);
                tvShowType.setText(TermType);
                filterData();
                popupWindow.dismiss();
            }
        });

    }


    /**
     * 筛选方法
     */
    private void filterData() {
        relativeLayout.setVisibility(View.VISIBLE);
        new Thread() {
            @Override
            public void run() {
                super.run();
                List<DCAdviceOfPatientHR.ValuesBean> initList = null;
                final ArrayList<DCAdviceOfPatientHR.ValuesBean> updataList = new ArrayList<>();
                switch (OneRequir) {
                    case 10:
                        initList = allData;
                        break;
                    case 11:
                        initList = longTermData;
                        break;
                    case 12:
                        initList = shortTermData;
                        break;


                }
                for (int i = 0; i < initList.size(); i++) {
                    if (dealTwoRequir(initList.get(i)) && dealThreeRequir(initList.get(i))) {
                        updataList.add(initList.get(i));
                    }
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adviceOfPatientHRAdapter.updateRes(updataList);
                        relativeLayout.setVisibility(View.GONE);
                    }
                });

            }
        }.start();


    }


    /**
     * 医嘱状态条件筛选
     *
     * @param bean
     * @return
     */
    private boolean dealTwoRequir(DCAdviceOfPatientHR.ValuesBean bean) {
        switch (TwoRequir) {
            case 100:
                return true;
            case 101:
                if (StateOne.equals(bean.getState())) {
                    return true;
                }
                break;
            case 102:
                if (StateTwo.equals(bean.getState())) {
                    return true;
                }
                break;
            case 103:
                if (StateThree.equals(bean.getState())) {
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * 医嘱类型的条件筛选
     *
     * @param bean
     * @return
     */
    private boolean dealThreeRequir(DCAdviceOfPatientHR.ValuesBean bean) {
        switch (ThreeRequir) {
            case 1000:
                return true;
            case 1001:
                if (ZZYP.equals(bean.getOrderClassString()) || PJYP.equals(bean.getOrderClassString())
                        || ZLL.equals(bean.getOrderClassString())
                        || XZL.equals(bean.getOrderClassString())
                        || DSY.equals(bean.getOrderClassString())) {
                    return true;
                }
                break;
            case 1002:
                if (HYL.equals(bean.getOrderClassString()) || JCL.equals(bean.getOrderClassString())
                        || FSL.equals(bean.getOrderClassString())) {
                    return true;
                }
                break;
            case 1003:
                if (SSL.equals(bean.getOrderClassString()) || MZ.equals(bean.getOrderClassString())) {
                    return true;
                }
                break;
            case 1004:
                if (HLL.equals(bean.getOrderClassString()) || YSL.equals(bean.getOrderClassString())) {
                    return true;
                }

                break;
            case 1005:
                if (!ZZYP.equals(bean.getOrderClassString()) || !PJYP.equals(bean.getOrderClassString())
                        || !ZLL.equals(bean.getOrderClassString()) || !XZL.equals(bean.getOrderClassString())
                        || !DSY.equals(bean.getOrderClassString()) || !HYL.equals(bean.getOrderClassString())
                        || !JCL.equals(bean.getOrderClassString()) || !FSL.equals(bean.getOrderClassString())
                        || !SSL.equals(bean.getOrderClassString()) || !MZ.equals(bean.getOrderClassString())
                        || !HLL.equals(bean.getOrderClassString()) || !YSL.equals(bean.getOrderClassString())
                        ) {
                    return true;
                }
                break;
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //全部医嘱
            case R.id.order_tv_one:
                onSelectTextViewClick(v, 0);
                OneRequir = 10;
                filterData();
                break;
            //长期医嘱
            case R.id.order_tv_two:
                OneRequir = 11;
                onSelectTextViewClick(v, 0);
                filterData();
                break;
            //临时医嘱
            case R.id.order_tv_three:
                OneRequir = 12;
                onSelectTextViewClick(v, 0);
                filterData();
                break;

            //医嘱状态:全部
            case R.id.ppw_select_isTerm_all_tv:
                TwoRequir = 100;
                onSelectTextViewClick(v, 1);
                TermState = "全部";
                break;
            //医嘱状态:审核
            case R.id.ppw_select_isTerm_shenhe_tv:
                TwoRequir = 101;
                onSelectTextViewClick(v, 1);
                TermState = "审核";
                break;
            //医嘱状态:在执
            case R.id.ppw_select_isTerm_zaizhi_tv:
                TwoRequir = 102;
                onSelectTextViewClick(v, 1);
                TermState = "在执";
                break;
            //医嘱状态:停止
            case R.id.ppw_select_isTerm_stop_tv:
                TwoRequir = 103;
                onSelectTextViewClick(v, 1);
                TermState = "停止";
                break;

            //医嘱类型:全部
            case R.id.ppw_select_type_all_tv:
                ThreeRequir = 1000;
                onSelectTextViewClick(v, 2);
                TermType = "全部";
                break;
            //医嘱类型:药品治疗
            case R.id.ppw_select_type_yaopinzhiliao_tv:
                ThreeRequir = 1001;
                onSelectTextViewClick(v, 2);
                TermType = "药品治疗";
                break;
            //医嘱类型:检验检查
            case R.id.ppw_select_type_jianyanjiancha_tv:
                ThreeRequir = 1002;
                onSelectTextViewClick(v, 2);
                TermType = "检验检查";
                break;
            //医嘱类型:手术麻醉
            case R.id.ppw_select_type_shoushumazui_tv:
                ThreeRequir = 1003;
                onSelectTextViewClick(v, 2);
                TermType = "手术麻醉";
                break;
            //医嘱类型:护理膳食
            case R.id.ppw_select_type_hulishanshi_tv:
                ThreeRequir = 1004;
                onSelectTextViewClick(v, 2);
                TermType = "护理膳食";
                break;
            //医嘱类型:其他
            case R.id.ppw_select_type_qita_tv:
                ThreeRequir = 1005;
                onSelectTextViewClick(v, 2);
                TermType = "其他";
                break;


        }


    }

    /**
     * 选择变色
     *
     * @param view
     * @param k
     */
    public void onSelectTextViewClick(View view, int k) {

        switch (k) {
            case 0:
                for (int i = 0; i < lin_order_tv.getChildCount(); i++) {
                    TextView tv = (TextView) lin_order_tv.getChildAt(i);
                    tv.setTextColor(this.getResources().getColor(R.color.text_normal_color));
                    tv.setBackgroundResource(R.color.activity_base_bg);
                }
                ((TextView) view).setTextColor(Color.WHITE);
                ((TextView) view).setBackgroundResource(R.color.titlecolor);
                break;
            case 1:
                for (int i = 0; i < lvPpwTerm.getChildCount(); i++) {
                    ((TextView) lvPpwTerm.getChildAt(i)).setTextColor(getResources().getColor(R.color.btn_gray));
                    ((TextView) lvPpwTerm.getChildAt(i)).setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_gray_circle_noclick));
                }
                ((TextView) view).setTextColor(getResources().getColor(R.color.white));
                ((TextView) view).setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_gray_circle_isclick));

                break;


            case 2:

                for (int i = 0; i < tvList.size(); i++) {
                    tvList.get(i).setTextColor(getResources().getColor(R.color.btn_gray));
                    tvList.get(i).setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_gray_circle_noclick));
                }
                ((TextView) view).setTextColor(getResources().getColor(R.color.white));
                ((TextView) view).setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_gray_circle_isclick));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        DCAdviceOfPatientHR.ValuesBean itemAtPosition = (DCAdviceOfPatientHR.ValuesBean) adapterView.getItemAtPosition(i);
        Intent intent = new Intent(getContext(), PhysicianOrderManagementDetailsActivity.class);
        intent.putExtra("data", itemAtPosition);
        startActivity(intent);

    }

    private List<DCAdviceOfPatientHR.ValuesBean> getDataOffLine() {
        DCAdviceOfPatientHR dcAdviceOfPatientHR = null;
        String patientID = data.getID();
        Gson gson = new Gson();
        switch (patientID) {
            case "29379827":
                dcAdviceOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetDoctorAdviceOfPatientHR", "29379827"), DCAdviceOfPatientHR.class);
                break;

            case "29542151":
                dcAdviceOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetDoctorAdviceOfPatientHR", "29542151"), DCAdviceOfPatientHR.class);
                break;

            case "29554021":
                dcAdviceOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetDoctorAdviceOfPatientHR", "29554021"), DCAdviceOfPatientHR.class);

                break;
            case "29555473":
                dcAdviceOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetDoctorAdviceOfPatientHR", "29555473"), DCAdviceOfPatientHR.class);
                break;
            case "29557134":
                dcAdviceOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetDoctorAdviceOfPatientHR", "29557134"), DCAdviceOfPatientHR.class);
                break;
            case "29591075":
                dcAdviceOfPatientHR = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetDoctorAdviceOfPatientHR", "29591075"), DCAdviceOfPatientHR.class);
                break;


        }
        List<DCAdviceOfPatientHR.ValuesBean> values = dcAdviceOfPatientHR.getValues();
        for (int i = 0; i < values.size(); i++) {
            allData.add(values.get(i));
            if (values.get(i).getIsLongTerm().equals(LongTerm)) {
                longTermData.add(values.get(i));
            } else if (values.get(i).getIsLongTerm().equals(ShortTerm)) {
                shortTermData.add(values.get(i));
            }

        }

        return values;

    }


}




