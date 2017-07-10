package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.EventPagerNo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by 呼延 on 2016/3/30.
 * 病人页面，基本信息页面
 */
public class ParentInfo_Frament_BasicInformation extends BaseFragment {


    private DCPatientHROfDepartment.ValuesBean data;
    private TextView name;
    private TextView gender;
    private TextView age;
    private TextView nation;
    private TextView tel_num;
    private TextView marriage;
    private TextView id_Card;
    private TextView occupation;
    private TextView address;
    private TextView contact_person;
    private TextView contact_person_tel;
    private TextView bed_num;
    private TextView med_num;
    private TextView hospital_num;
    private TextView patient_num;
    private TextView inHospital_time;
    private TextView inHospital_day;
    private TextView main_doctor;
    private TextView response_nurse;
    private TextView payment;
    private TextView payment_type;
    private TextView prepaid;
    private TextView allCost;
    public static final String TAG = ParentInfo_Frament_BasicInformation.class.getSimpleName();
    private RelativeLayout relativeLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.parent_info_frag_basicinformation, container, false);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();


    }


    /**
     * 注册与反注册EventBus
     */
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Log.e(TAG, "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        Log.e(TAG, "onStop");
    }


    /**
     * 重写onEvent方法, 需要添加@Subscribe,否则可能注册不成功
     */
    @Subscribe
    public void onEvent(EventPagerNo no) {
        if (no.getPagerNo() == 0) {
            Log.e(TAG, "onEvent进入");
            relativeLayout.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mHandler.sendEmptyMessage(1);


                }
            }).start();
        }

    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    relativeLayout.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }

    };


    private void initData() {
        Bundle arguments = getArguments();
        data = ((DCPatientHROfDepartment.ValuesBean) arguments.getSerializable("data"));


        /**
         * 基本新信息栏的填充
         * */
        name.setText(data.getName());
        gender.setText(data.getSex());
        age.setText(data.getAge());
        nation.setText(data.getNation());
        tel_num.setText(((String) data.getPhone()));
        //未找到婚姻状态marriage
        id_Card.setText(data.getIdentityNumber());
        address.setText(data.getAddress());
        occupation.setText(data.getIdentityType());//职业为找到
        contact_person.setText(data.getLinkManName());
        contact_person_tel.setText(data.getLinkManPhone());


        /**
         * 住院信息填充
         * */

        bed_num.setText(data.getBedNo() + "床");
        med_num.setText(data.getMedicalInsuranceID());
        hospital_num.setText(data.getID());
        patient_num.setText(data.getHID());
        String[] split = data.getEnterDate().split(" ");
        inHospital_time.setText(split[0]);
        inHospital_day.setText("" + data.getInHosDay() + "天");
        main_doctor.setText(data.getDoctorInCharge());
        //未找到责任护士的   response_nurse


        /**
         * 费用信息填充
         * */

        //未找到付款方式 payment
        payment_type.setText(data.getPayment());
        prepaid.setText(data.getPrePayments());
        allCost.setText(data.getTotalCost());


    }

    private void initView() {
        //基本信息栏的控件
        name = ((TextView) layout.findViewById(R.id.name_parent_info_frag_one));
        gender = ((TextView) layout.findViewById(R.id.gender_parent_info_frag_one));
        age = ((TextView) layout.findViewById(R.id.age_parent_info_frag_one));
        nation = ((TextView) layout.findViewById(R.id.nation_parent_info_frag_one));
        tel_num = ((TextView) layout.findViewById(R.id.tel_num_parent_info_frag_one));
        marriage = ((TextView) layout.findViewById(R.id.marriage_parent_info_frag_one));
        id_Card = ((TextView) layout.findViewById(R.id.id_card__parent_info_frag_one));
        occupation = ((TextView) layout.findViewById(R.id.occupation_parent_info_frag_one));
        address = ((TextView) layout.findViewById(R.id.address_parent_info_frag_one));
        contact_person = ((TextView) layout.findViewById(R.id.contact_person_parent_info_frag_one));
        contact_person_tel = ((TextView) layout.findViewById(R.id.contact_telnum_parent_info_frag_one));

        //住院信息
        bed_num = ((TextView) layout.findViewById(R.id.bed_num_parent_info_frag_one));
        med_num = ((TextView) layout.findViewById(R.id.medicare_num_parent_info_frag_one));
        hospital_num = ((TextView) layout.findViewById(R.id.hospital_num_parent_info_frag_one));
        patient_num = ((TextView) layout.findViewById(R.id.patient_num_parent_info_frag_one));
        inHospital_time = ((TextView) layout.findViewById(R.id.inhospital_time_parent_info_frag_one));
        inHospital_day = ((TextView) layout.findViewById(R.id.inhospital_day_parent_info_frag_one));
        main_doctor = ((TextView) layout.findViewById(R.id.main_doctor_parent_info_frag_one));
        response_nurse = ((TextView) layout.findViewById(R.id.response_nurse_parent_info_frag_one));

        //费用信息
        payment = ((TextView) layout.findViewById(R.id.payement_parent_info_frag_one));
        payment_type = ((TextView) layout.findViewById(R.id.payment_type_parent_info_frag_one));
        prepaid = ((TextView) layout.findViewById(R.id.prepaid_parent_info_frag_one));
        allCost = ((TextView) layout.findViewById(R.id.cost_expense_parent_info_frag_one));


        relativeLayout = ((RelativeLayout) layout.findViewById(R.id.relativeLayout_parent_0));


    }


}
