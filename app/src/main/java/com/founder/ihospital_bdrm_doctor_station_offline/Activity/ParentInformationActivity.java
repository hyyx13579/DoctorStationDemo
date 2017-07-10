package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.MobileRoundFragmentItemOne;
import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.ParentInfo_Frament_AnesthesiaRecord;
import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.ParentInfo_Frament_BasicInformation;
import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.ParentInfo_Frament_ElectronicCase;
import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.ParentInfo_Frament_ExaminationReport;
import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.ParentInfo_Frament_InspectionReport;
import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.ParentInfo_Frament_Memorandum;
import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.ParentInfo_Frament_NursingInformation;
import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.ParentInfo_Frament_PathologicalReport;
import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.ParentInfo_Frament_PhysicianOrderManagement;
import com.founder.ihospital_bdrm_doctor_station_offline.MainActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.EventPagerNo;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by hyyx and lxb
 * <p/>
 * 移动查房模块-科室选择-点击具体病人后跳转,病人信息的8个小功能展示页
 */

public class ParentInformationActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private DCPatientHROfDepartment.ValuesBean data;
    /**
     * 标题栏的内容
     */
    private TextView titleName;
    /**
     * 标题栏的返回
     */
    private ImageView ima_back;
    public static final String TAG = ParentInformationActivity.class.getSimpleName();
    private ViewPager viewPager;
    private FragmentPagerAdapter fragmentPagerAdapter;
    /**
     * 对应的八个功能区域
     */
    private LinearLayout rb_one;
    private LinearLayout rb_two;
    private LinearLayout rb_three;
    private LinearLayout rb_four;
    private LinearLayout rb_five;
    private LinearLayout rb_six;
    private LinearLayout rb_seven;
    private LinearLayout rb_eight;
    /**
     * 对应的八个功能区域内的文字
     */
    private TextView text_1;
    private TextView text_2;
    private TextView text_3;
    private TextView text_4;
    private TextView text_5;
    private TextView text_6;
    private TextView text_7;
    private TextView text_8;
    /**
     * 对应的八个功能区域内的图标
     */
    private ImageView img_1;
    private ImageView img_2;
    private ImageView img_3;
    private ImageView img_4;
    private ImageView img_5;
    private ImageView img_6;
    private ImageView img_7;
    private ImageView img_8;


    private HorizontalScrollView horScro;
    /**
     * 备注功能的标题栏,需要更改为添加图标
     */
    private ImageView addPatientNoteBookEvent;
    private TextView text_9;
    private ImageView img_9;
    private LinearLayout rb_nine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_information);
        initView();
        initData();


    }

    private void initData() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        data = ((DCPatientHROfDepartment.ValuesBean) extras.getSerializable(MobileRoundFragmentItemOne.PATIENT_INFO));
        Log.e(TAG, data.getName() + data.getDoctorInCharge() + data.getID() + data.getEnterDate() + data.getHID());
        String name = data.getName();
        titleName.setText(name);


        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 9;
            }

            @Override
            public Fragment getItem(int position) {
                Fragment baseFrag = new Fragment();
                Bundle bundle = new Bundle();
                switch (position) {
                    case 0:
                        baseFrag = new ParentInfo_Frament_BasicInformation();
                        break;
                    case 1:
                        baseFrag = new ParentInfo_Frament_ElectronicCase();
                        break;
                    case 2:
                        baseFrag = new ParentInfo_Frament_PhysicianOrderManagement();
                        break;
                    case 3:
                        baseFrag = new ParentInfo_Frament_NursingInformation();
                        break;
                    case 4:
                        baseFrag = new ParentInfo_Frament_InspectionReport();
                        break;
                    case 5:
                        baseFrag = new ParentInfo_Frament_ExaminationReport();
                        break;

                    case 6:
                        baseFrag = new ParentInfo_Frament_PathologicalReport();
                        break;

                    case 7:
                        baseFrag = new ParentInfo_Frament_AnesthesiaRecord();
                        break;
                    case 8:
                        baseFrag = new ParentInfo_Frament_Memorandum();
                        break;


                }
                bundle.putSerializable("data", data);
                baseFrag.setArguments(bundle);
                return baseFrag;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                // super.destroyItem(container, position, object);
            }
        };

        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.addOnPageChangeListener(this);


    }


    private void initView() {
        titleName = ((TextView) findViewById(R.id.titleName_parent_info));
        ima_back = ((ImageView) findViewById(R.id.back_parent_info));
        ima_back.setOnClickListener(this);
        addPatientNoteBookEvent = ((ImageView) findViewById(R.id.addPatientNoteBookEvent));
        viewPager = ((ViewPager) findViewById(R.id.viewPager_paremt_info));
        viewPager.setOffscreenPageLimit(2);
        rb_one = ((LinearLayout) findViewById(R.id.bottom_indicate_1_ly));
        rb_two = ((LinearLayout) findViewById(R.id.bottom_indicate_2_ly));
        rb_three = ((LinearLayout) findViewById(R.id.bottom_indicate_3_ly));
        rb_four = ((LinearLayout) findViewById(R.id.bottom_indicate_4_ly));
        rb_five = ((LinearLayout) findViewById(R.id.bottom_indicate_5_ly));
        rb_six = ((LinearLayout) findViewById(R.id.bottom_indicate_6_ly));
        text_1 = (TextView) findViewById(R.id.bottom_indicate_1_text);
        text_2 = (TextView) findViewById(R.id.bottom_indicate_2_text);
        text_3 = (TextView) findViewById(R.id.bottom_indicate_3_text);
        text_4 = (TextView) findViewById(R.id.bottom_indicate_4_text);
        text_5 = (TextView) findViewById(R.id.bottom_indicate_5_text);
        text_6 = (TextView) findViewById(R.id.bottom_indicate_6_text);
        text_7 = (TextView) findViewById(R.id.bottom_indicate_7_text);
        text_8 = (TextView) findViewById(R.id.bottom_indicate_8_text);

        img_1 = (ImageView) findViewById(R.id.bottom_indicate_1_img);
        img_2 = (ImageView) findViewById(R.id.bottom_indicate_2_img);
        img_3 = (ImageView) findViewById(R.id.bottom_indicate_3_img);
        img_4 = (ImageView) findViewById(R.id.bottom_indicate_4_img);
        img_5 = (ImageView) findViewById(R.id.bottom_indicate_5_img);
        img_6 = (ImageView) findViewById(R.id.bottom_indicate_6_img);
        img_7 = (ImageView) findViewById(R.id.bottom_indicate_7_img);
        img_8 = (ImageView) findViewById(R.id.bottom_indicate_8_img);


        img_9 = ((ImageView) findViewById(R.id.bottom_indicate_9_img));
        text_9 = ((TextView) findViewById(R.id.bottom_indicate_9_text));

        rb_one.setOnClickListener(this);
        rb_two.setOnClickListener(this);
        rb_three.setOnClickListener(this);
        rb_four.setOnClickListener(this);
        rb_five.setOnClickListener(this);
        rb_six.setOnClickListener(this);
        addPatientNoteBookEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager.getCurrentItem() == 8) {
                    Toast.makeText(ParentInformationActivity.this, "添加备忘录", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ParentInformationActivity.this, AddPatientNoteBookEvent.class);
                    startActivity(intent);
                } else {

                    EventBus.getDefault().post(new EventPagerNo(viewPager.getCurrentItem()));
                }
            }
        });

        rb_seven = ((LinearLayout) findViewById(R.id.bottom_indicate_7_ly));
        rb_seven.setOnClickListener(this);
        rb_eight = ((LinearLayout) findViewById(R.id.bottom_indicate_8_ly));
        rb_eight.setOnClickListener(this);

        rb_nine = ((LinearLayout) findViewById(R.id.bottom_indicate_9_ly));
        rb_nine.setOnClickListener(this);

        horScro = ((HorizontalScrollView) findViewById(R.id.horscro_parent_info));
        changeModel(1);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.back_parent_info:
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                // overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                break;

            case R.id.bottom_indicate_1_ly:
                viewPager.setCurrentItem(0);
                changeModel(1);
                break;

            case R.id.bottom_indicate_2_ly:
                viewPager.setCurrentItem(1);
                changeModel(2);
                break;

            case R.id.bottom_indicate_3_ly:
                viewPager.setCurrentItem(2);
                changeModel(3);
                break;

            case R.id.bottom_indicate_4_ly:
                viewPager.setCurrentItem(3);
                changeModel(4);
                break;

            case R.id.bottom_indicate_5_ly:
                viewPager.setCurrentItem(4);
                changeModel(5);
                break;

            case R.id.bottom_indicate_6_ly:
                viewPager.setCurrentItem(5);
                changeModel(6);
                break;

            case R.id.bottom_indicate_9_ly:
                viewPager.setCurrentItem(6);
                changeModel(7);
                break;

            case R.id.bottom_indicate_7_ly:
                viewPager.setCurrentItem(7);
                changeModel(8);
                break;

            case R.id.bottom_indicate_8_ly:
                viewPager.setCurrentItem(8);
                changeModel(9);
                break;


        }
        if (viewPager.getCurrentItem() == 8) {
            Log.i("qwer", "initView: ");
            addPatientNoteBookEvent.setImageResource(R.mipmap.nav_note);
        } else {
            addPatientNoteBookEvent.setImageResource(R.mipmap.nav_reload);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {

            case 0:
                changeModel(1);
                break;
            case 1:
                changeModel(2);
                break;
            case 2:
                changeModel(3);
                break;
            case 3:
                changeModel(4);
                break;
            case 4:
                changeModel(5);
                break;
            case 5:
                changeModel(6);
                break;
            case 6:
                changeModel(7);
                break;
            case 7:
                changeModel(8);
                break;
            case 8:
                changeModel(9);
                break;
        }
        horScro.smoothScrollTo(position * rb_one.getWidth(), 0);
    }

    private void changeModel(int id) {
        switch (id) {

            case 1:
                img_1.setBackgroundResource(R.mipmap.basic_infor_hl);
                text_1.setTextColor(getResources().getColor(R.color.titlecolor));
                img_2.setBackgroundResource(R.mipmap.elec_case);
                text_2.setTextColor(getResources().getColor(R.color.text_light_color));
                img_3.setBackgroundResource(R.mipmap.phy_order);
                text_3.setTextColor(getResources().getColor(R.color.text_light_color));
                img_4.setBackgroundResource(R.mipmap.nurse_infor);
                text_4.setTextColor(getResources().getColor(R.color.text_light_color));
                img_5.setBackgroundResource(R.mipmap.inspec_report);
                text_5.setTextColor(getResources().getColor(R.color.text_light_color));
                img_6.setBackgroundResource(R.mipmap.exam_report);
                text_6.setTextColor(getResources().getColor(R.color.text_light_color));
                img_7.setBackgroundResource(R.mipmap.anesthesia_record);
                text_7.setTextColor(getResources().getColor(R.color.text_light_color));
                img_8.setBackgroundResource(R.mipmap.memory);
                text_8.setTextColor(getResources().getColor(R.color.text_light_color));
                addPatientNoteBookEvent.setImageResource(R.mipmap.nav_reload);

                img_9.setBackgroundResource(R.mipmap.binglibaogao_normal_xhdpi);
                text_9.setTextColor(getResources().getColor(R.color.text_light_color));


                break;
            case 2:
                img_1.setBackgroundResource(R.mipmap.basic_infor);
                text_1.setTextColor(getResources().getColor(R.color.text_light_color));
                img_2.setBackgroundResource(R.mipmap.elec_case_hl);
                text_2.setTextColor(getResources().getColor(R.color.titlecolor));
                img_3.setBackgroundResource(R.mipmap.phy_order);
                text_3.setTextColor(getResources().getColor(R.color.text_light_color));
                img_4.setBackgroundResource(R.mipmap.nurse_infor);
                text_4.setTextColor(getResources().getColor(R.color.text_light_color));
                img_5.setBackgroundResource(R.mipmap.inspec_report);
                text_5.setTextColor(getResources().getColor(R.color.text_light_color));
                img_6.setBackgroundResource(R.mipmap.exam_report);
                text_6.setTextColor(getResources().getColor(R.color.text_light_color));
                img_7.setBackgroundResource(R.mipmap.anesthesia_record);
                text_7.setTextColor(getResources().getColor(R.color.text_light_color));
                img_8.setBackgroundResource(R.mipmap.memory);
                text_8.setTextColor(getResources().getColor(R.color.text_light_color));
                addPatientNoteBookEvent.setImageResource(R.mipmap.nav_reload);
                img_9.setBackgroundResource(R.mipmap.binglibaogao_normal_xhdpi);
                text_9.setTextColor(getResources().getColor(R.color.text_light_color));

                break;
            case 3:
                img_1.setBackgroundResource(R.mipmap.basic_infor);
                text_1.setTextColor(getResources().getColor(R.color.text_light_color));
                img_2.setBackgroundResource(R.mipmap.elec_case);
                text_2.setTextColor(getResources().getColor(R.color.text_light_color));
                img_3.setBackgroundResource(R.mipmap.phy_order_hl);
                text_3.setTextColor(getResources().getColor(R.color.titlecolor));
                img_4.setBackgroundResource(R.mipmap.nurse_infor);
                text_4.setTextColor(getResources().getColor(R.color.text_light_color));
                img_5.setBackgroundResource(R.mipmap.inspec_report);
                text_5.setTextColor(getResources().getColor(R.color.text_light_color));
                img_6.setBackgroundResource(R.mipmap.exam_report);
                text_6.setTextColor(getResources().getColor(R.color.text_light_color));
                img_7.setBackgroundResource(R.mipmap.anesthesia_record);
                text_7.setTextColor(getResources().getColor(R.color.text_light_color));
                img_8.setBackgroundResource(R.mipmap.memory);
                text_8.setTextColor(getResources().getColor(R.color.text_light_color));
                addPatientNoteBookEvent.setImageResource(R.mipmap.nav_reload);
                img_9.setBackgroundResource(R.mipmap.binglibaogao_normal_xhdpi);
                text_9.setTextColor(getResources().getColor(R.color.text_light_color));

                break;
            case 4:
                img_1.setBackgroundResource(R.mipmap.basic_infor);
                text_1.setTextColor(getResources().getColor(R.color.text_light_color));
                img_2.setBackgroundResource(R.mipmap.elec_case);
                text_2.setTextColor(getResources().getColor(R.color.text_light_color));
                img_3.setBackgroundResource(R.mipmap.phy_order);
                text_3.setTextColor(getResources().getColor(R.color.text_light_color));
                img_4.setBackgroundResource(R.mipmap.nurse_infor_hl);
                text_4.setTextColor(getResources().getColor(R.color.titlecolor));
                img_5.setBackgroundResource(R.mipmap.inspec_report);
                text_5.setTextColor(getResources().getColor(R.color.text_light_color));
                img_6.setBackgroundResource(R.mipmap.exam_report);
                text_6.setTextColor(getResources().getColor(R.color.text_light_color));
                img_7.setBackgroundResource(R.mipmap.anesthesia_record);
                text_7.setTextColor(getResources().getColor(R.color.text_light_color));
                img_8.setBackgroundResource(R.mipmap.memory);
                text_8.setTextColor(getResources().getColor(R.color.text_light_color));
                addPatientNoteBookEvent.setImageResource(R.mipmap.nav_reload);
                img_9.setBackgroundResource(R.mipmap.binglibaogao_normal_xhdpi);
                text_9.setTextColor(getResources().getColor(R.color.text_light_color));

                break;
            case 5:
                img_1.setBackgroundResource(R.mipmap.basic_infor);
                text_1.setTextColor(getResources().getColor(R.color.text_light_color));
                img_2.setBackgroundResource(R.mipmap.elec_case);
                text_2.setTextColor(getResources().getColor(R.color.text_light_color));
                img_3.setBackgroundResource(R.mipmap.phy_order);
                text_3.setTextColor(getResources().getColor(R.color.text_light_color));
                img_4.setBackgroundResource(R.mipmap.nurse_infor);
                text_4.setTextColor(getResources().getColor(R.color.text_light_color));
                img_5.setBackgroundResource(R.mipmap.inspec_report_hl);
                text_5.setTextColor(getResources().getColor(R.color.titlecolor));
                img_6.setBackgroundResource(R.mipmap.exam_report);
                text_6.setTextColor(getResources().getColor(R.color.text_light_color));
                img_7.setBackgroundResource(R.mipmap.anesthesia_record);
                text_7.setTextColor(getResources().getColor(R.color.text_light_color));
                img_8.setBackgroundResource(R.mipmap.memory);
                text_8.setTextColor(getResources().getColor(R.color.text_light_color));
                addPatientNoteBookEvent.setImageResource(R.mipmap.nav_reload);
                img_9.setBackgroundResource(R.mipmap.binglibaogao_normal_xhdpi);
                text_9.setTextColor(getResources().getColor(R.color.text_light_color));

                break;
            case 6:
                img_1.setBackgroundResource(R.mipmap.basic_infor);
                text_1.setTextColor(getResources().getColor(R.color.text_light_color));
                img_2.setBackgroundResource(R.mipmap.elec_case);
                text_2.setTextColor(getResources().getColor(R.color.text_light_color));
                img_3.setBackgroundResource(R.mipmap.phy_order);
                text_3.setTextColor(getResources().getColor(R.color.text_light_color));
                img_4.setBackgroundResource(R.mipmap.nurse_infor);
                text_4.setTextColor(getResources().getColor(R.color.text_light_color));
                img_5.setBackgroundResource(R.mipmap.inspec_report);
                text_5.setTextColor(getResources().getColor(R.color.text_light_color));
                img_6.setBackgroundResource(R.mipmap.exam_report_hl);
                text_6.setTextColor(getResources().getColor(R.color.titlecolor));
                img_7.setBackgroundResource(R.mipmap.anesthesia_record);
                text_7.setTextColor(getResources().getColor(R.color.text_light_color));
                img_8.setBackgroundResource(R.mipmap.memory);
                text_8.setTextColor(getResources().getColor(R.color.text_light_color));
                addPatientNoteBookEvent.setImageResource(R.mipmap.nav_reload);
                img_9.setBackgroundResource(R.mipmap.binglibaogao_normal_xhdpi);
                text_9.setTextColor(getResources().getColor(R.color.text_light_color));
                break;
            case 7:
                img_1.setBackgroundResource(R.mipmap.basic_infor);
                text_1.setTextColor(getResources().getColor(R.color.text_light_color));
                img_2.setBackgroundResource(R.mipmap.elec_case);
                text_2.setTextColor(getResources().getColor(R.color.text_light_color));
                img_3.setBackgroundResource(R.mipmap.phy_order);
                text_3.setTextColor(getResources().getColor(R.color.text_light_color));
                img_4.setBackgroundResource(R.mipmap.nurse_infor);
                text_4.setTextColor(getResources().getColor(R.color.text_light_color));
                img_5.setBackgroundResource(R.mipmap.inspec_report);
                text_5.setTextColor(getResources().getColor(R.color.text_light_color));
                img_6.setBackgroundResource(R.mipmap.exam_report);
                text_6.setTextColor(getResources().getColor(R.color.text_light_color));
                img_7.setBackgroundResource(R.mipmap.anesthesia_record);
                text_7.setTextColor(getResources().getColor(R.color.text_light_color));
                img_8.setBackgroundResource(R.mipmap.memory);
                text_8.setTextColor(getResources().getColor(R.color.text_light_color));
                addPatientNoteBookEvent.setImageResource(R.mipmap.nav_reload);
                img_9.setBackgroundResource(R.mipmap.binglibaogao_selected_xhdpi);
                text_9.setTextColor(getResources().getColor(R.color.titlecolor));
                break;


            case 8:
                img_1.setBackgroundResource(R.mipmap.basic_infor);
                text_1.setTextColor(getResources().getColor(R.color.text_light_color));
                img_2.setBackgroundResource(R.mipmap.elec_case);
                text_2.setTextColor(getResources().getColor(R.color.text_light_color));
                img_3.setBackgroundResource(R.mipmap.phy_order);
                text_3.setTextColor(getResources().getColor(R.color.text_light_color));
                img_4.setBackgroundResource(R.mipmap.nurse_infor);
                text_4.setTextColor(getResources().getColor(R.color.text_light_color));
                img_5.setBackgroundResource(R.mipmap.inspec_report);
                text_5.setTextColor(getResources().getColor(R.color.text_light_color));
                img_6.setBackgroundResource(R.mipmap.exam_report);
                text_6.setTextColor(getResources().getColor(R.color.text_light_color));
                img_7.setBackgroundResource(R.mipmap.anesthesia_record_hl);
                text_7.setTextColor(getResources().getColor(R.color.titlecolor));
                img_8.setBackgroundResource(R.mipmap.memory);
                text_8.setTextColor(getResources().getColor(R.color.text_light_color));
                addPatientNoteBookEvent.setImageResource(R.mipmap.nav_reload);
                img_9.setBackgroundResource(R.mipmap.binglibaogao_normal_xhdpi);
                text_9.setTextColor(getResources().getColor(R.color.text_light_color));

                break;
            case 9:
                img_1.setBackgroundResource(R.mipmap.basic_infor);
                text_1.setTextColor(getResources().getColor(R.color.text_light_color));
                img_2.setBackgroundResource(R.mipmap.elec_case);
                text_2.setTextColor(getResources().getColor(R.color.text_light_color));
                img_3.setBackgroundResource(R.mipmap.phy_order);
                text_3.setTextColor(getResources().getColor(R.color.text_light_color));
                img_4.setBackgroundResource(R.mipmap.nurse_infor);
                text_4.setTextColor(getResources().getColor(R.color.text_light_color));
                img_5.setBackgroundResource(R.mipmap.inspec_report);
                text_5.setTextColor(getResources().getColor(R.color.text_light_color));
                img_6.setBackgroundResource(R.mipmap.exam_report);
                text_6.setTextColor(getResources().getColor(R.color.text_light_color));
                img_7.setBackgroundResource(R.mipmap.anesthesia_record);
                text_7.setTextColor(getResources().getColor(R.color.text_light_color));
                img_8.setBackgroundResource(R.mipmap.memory_hl);
                text_8.setTextColor(getResources().getColor(R.color.titlecolor));
                addPatientNoteBookEvent.setImageResource(R.mipmap.nav_note);
                img_9.setBackgroundResource(R.mipmap.binglibaogao_normal_xhdpi);
                text_9.setTextColor(getResources().getColor(R.color.text_light_color));
                break;

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
