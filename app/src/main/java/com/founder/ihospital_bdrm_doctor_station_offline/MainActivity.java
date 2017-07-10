package com.founder.ihospital_bdrm_doctor_station_offline;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.founder.ihospital_bdrm_doctor_station_offline.Activity.BaseActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Activity.BedActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Activity.LoginActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Activity.MeetingActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Activity.MobileRoundsActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Activity.OperationActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.WardGeneralizationFragment;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Contant;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.SPUtils;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by 呼延
 * 首页界面
 */


public class MainActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 留置图 loading
     */
    private RelativeLayout layout_general_main_dialog;
    private String id;
    private String userName;
    public final String TAG = MainActivity.class.getSimpleName();
    private Intent intentTwo;

    private WardGeneralizationFragment mainFrag;
    /**
     * 移动查房区域
     */
    private LinearLayout lv_verticalTitle_one;
    /**
     * 床位管理区域
     */
    private LinearLayout lv_verticalTitle_two;
    /**
     * 会诊管理区域
     */
    private LinearLayout lv_verticalTitle_three;
    /**
     * 设置区域
     */
    private LinearLayout lv_verticalTitle_four;
    /**
     * 关闭区域
     */
    private LinearLayout lv_verticalTitle_exit;
    /**
     * 移动查房区域的图标和名字
     */
    private ImageView imgOne;
    private TextView tv_one;
    /**
     * 床位管理区域的图标和名字
     */
    private ImageView imgTwo;
    private TextView tv_Two;
    /**
     * 会诊管理区域的图标和名字
     */
    private ImageView imgThree;
    private TextView tv_Three;
    /**
     * 设置区域的图标和名字
     */
    private ImageView imgFour;
    private TextView tv_Four;
    /**
     * 关闭区域的图标和名字
     */
    private ImageView imgExit;
    private TextView tv_Exit;
    private String deptName;
    private ImageView ivShouShu;
    private TextView tvShoushu;
    private LinearLayout lv_verticalTitle_five;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        layout_general_main_dialog.setVisibility(View.INVISIBLE);
        mainFrag.getLookPaitentList();

    }

    @Override
    protected void onStop() {
        super.onStop();
        layout_general_main_dialog.setVisibility(View.GONE);
        imgOne.setImageResource(R.mipmap.icon_spec_normal);
        tv_one.setTextColor(this.getResources().getColor(R.color.text_normal_color));

        imgTwo.setImageResource(R.mipmap.icon_bed_normal);
        tv_Two.setTextColor(this.getResources().getColor(R.color.text_normal_color));

        imgThree.setImageResource(R.mipmap.icon_meeting_normal);
        tv_Three.setTextColor(this.getResources().getColor(R.color.text_normal_color));

        imgFour.setImageResource(R.mipmap.icon_set_normal);
        tv_Four.setTextColor(this.getResources().getColor(R.color.text_normal_color));

        imgExit.setImageResource(R.mipmap.icon_out_normal);
        tv_Exit.setTextColor(this.getResources().getColor(R.color.text_normal_color));

        ivShouShu.setImageResource(R.mipmap.shoushu_normal_xhdpi);
        tvShoushu.setTextColor(this.getResources().getColor(R.color.text_normal_color));
    }


    private void initView() {

        intentTwo = getIntent();
        id = intentTwo.getStringExtra("id");
        userName = intentTwo.getStringExtra("usename");
        deptName = intentTwo.getStringExtra("deptName");
        Log.e(TAG, id + userName + deptName);
        lv_verticalTitle_one = ((LinearLayout) findViewById(R.id.lv_vertical_title_one));
        lv_verticalTitle_two = ((LinearLayout) findViewById(R.id.lv_vertical_title_two));
        lv_verticalTitle_three = ((LinearLayout) findViewById(R.id.lv_vertical_title_three));
        lv_verticalTitle_four = ((LinearLayout) findViewById(R.id.lv_vertical_title_four));
        lv_verticalTitle_exit = ((LinearLayout) findViewById(R.id.lv_vertical_title_exit));
        lv_verticalTitle_one.setOnClickListener(this);
        lv_verticalTitle_two.setOnClickListener(this);
        lv_verticalTitle_three.setOnClickListener(this);
        lv_verticalTitle_four.setOnClickListener(this);
        lv_verticalTitle_exit.setOnClickListener(this);
        imgOne = ((ImageView) findViewById(R.id.vertical_title_indicate_1_img));
        tv_one = ((TextView) findViewById(R.id.vertical_title_indicate_1_text));
        imgTwo = ((ImageView) findViewById(R.id.vertical_title_indicate_2_img));
        tv_Two = ((TextView) findViewById(R.id.vertical_title_indicate_2_text));
        imgThree = ((ImageView) findViewById(R.id.vertical_title_indicate_3_img));
        tv_Three = ((TextView) findViewById(R.id.vertical_title_indicate_3_text));
        imgFour = ((ImageView) findViewById(R.id.vertical_title_indicate_4_img));
        tv_Four = ((TextView) findViewById(R.id.vertical_title_indicate_4_text));
        imgExit = ((ImageView) findViewById(R.id.vertical_title_indicate_exit_img));
        tv_Exit = ((TextView) findViewById(R.id.vertical_title_indicate_exit_text));


        lv_verticalTitle_five = ((LinearLayout) findViewById(R.id.lv_vertical_title_five));
        lv_verticalTitle_five.setOnClickListener(this);
        ivShouShu = ((ImageView) findViewById(R.id.vertical_title_indicate_5_img));
        tvShoushu = ((TextView) findViewById(R.id.vertical_title_indicate_5_text));


        layout_general_main_dialog = ((RelativeLayout) findViewById(R.id.layout_general_main_dialog));
        setDefaultFragment();

    }

    private void setDefaultFragment() {
        Log.e(TAG, "setDefaultFragment");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mainFrag = new WardGeneralizationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("usename", userName);
        bundle.putString("deptName", deptName);
        mainFrag.setArguments(bundle);
        transaction.replace(R.id.main_menu_frame, mainFrag);
        transaction.commit();

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.lv_vertical_title_one://移动查房

                imgOne.setImageResource(R.mipmap.icon_spec_selected);
                tv_one.setTextColor(this.getResources().getColor(R.color.colorWhite));


                imgTwo.setImageResource(R.mipmap.icon_bed_normal);
                tv_Two.setTextColor(this.getResources().getColor(R.color.text_normal_color));

                imgThree.setImageResource(R.mipmap.icon_meeting_normal);
                tv_Three.setTextColor(this.getResources().getColor(R.color.text_normal_color));

                imgFour.setImageResource(R.mipmap.icon_set_normal);
                tv_Four.setTextColor(this.getResources().getColor(R.color.text_normal_color));

                imgExit.setImageResource(R.mipmap.icon_out_normal);
                tv_Exit.setTextColor(this.getResources().getColor(R.color.text_normal_color));


                intent = new Intent(getApplicationContext(), MobileRoundsActivity.class);
                intent.putExtra("doctor_id", id);
                layout_general_main_dialog.setVisibility(View.VISIBLE);
                startActivity(intent);
                break;


            case R.id.lv_vertical_title_two://床位管理


                imgOne.setImageResource(R.mipmap.icon_spec_normal);
                tv_one.setTextColor(this.getResources().getColor(R.color.text_normal_color));


                imgTwo.setImageResource(R.mipmap.icon_bed_selected);
                tv_Two.setTextColor(this.getResources().getColor(R.color.white));

                imgThree.setImageResource(R.mipmap.icon_meeting_normal);
                tv_Three.setTextColor(this.getResources().getColor(R.color.text_normal_color));

                imgFour.setImageResource(R.mipmap.icon_set_normal);
                tv_Four.setTextColor(this.getResources().getColor(R.color.text_normal_color));

                imgExit.setImageResource(R.mipmap.icon_out_normal);
                tv_Exit.setTextColor(this.getResources().getColor(R.color.text_normal_color));


                intent = new Intent(getApplicationContext(), BedActivity.class);
                layout_general_main_dialog.setVisibility(View.VISIBLE);
                intent.putExtra("doctor_id", id);
                startActivity(intent);
                break;

            case R.id.lv_vertical_title_three://会诊管理

                imgOne.setImageResource(R.mipmap.icon_spec_normal);
                tv_one.setTextColor(this.getResources().getColor(R.color.text_normal_color));


                imgTwo.setImageResource(R.mipmap.icon_bed_normal);
                tv_Two.setTextColor(this.getResources().getColor(R.color.text_normal_color));

                imgThree.setImageResource(R.mipmap.icon_meeting_selected);
                tv_Three.setTextColor(this.getResources().getColor(R.color.white));

                imgFour.setImageResource(R.mipmap.icon_set_normal);
                tv_Four.setTextColor(this.getResources().getColor(R.color.text_normal_color));

                imgExit.setImageResource(R.mipmap.icon_out_normal);
                tv_Exit.setTextColor(this.getResources().getColor(R.color.text_normal_color));


                intent = new Intent(getApplicationContext(), MeetingActivity.class);
                intent.putExtra("doctor_id", id);
                layout_general_main_dialog.setVisibility(View.VISIBLE);
                startActivity(intent);
                break;

//            case R.id.lv_vertical_title_four://设置
//
//                imgOne.setImageResource(R.mipmap.icon_spec_normal);
//                tv_one.setTextColor(this.getResources().getColor(R.color.text_normal_color));
//
//
//                imgTwo.setImageResource(R.mipmap.icon_bed_normal);
//                tv_Two.setTextColor(this.getResources().getColor(R.color.text_normal_color));
//
//                imgThree.setImageResource(R.mipmap.icon_meeting_normal);
//                tv_Three.setTextColor(this.getResources().getColor(R.color.text_normal_color));
//
//                imgFour.setImageResource(R.mipmap.icon_set_selected);
//                tv_Four.setTextColor(this.getResources().getColor(R.color.white));
//
//                imgExit.setImageResource(R.mipmap.icon_out_normal);
//                tv_Exit.setTextColor(this.getResources().getColor(R.color.text_normal_color));
//
//
//                intent = new Intent(getApplicationContext(), SettingActivity.class);
//                layout_general_main_dialog.setVisibility(View.VISIBLE);
//                startActivity(intent);
//                break;
            case R.id.lv_vertical_title_exit://关闭

                imgOne.setImageResource(R.mipmap.icon_spec_normal);
                tv_one.setTextColor(this.getResources().getColor(R.color.text_normal_color));


                imgTwo.setImageResource(R.mipmap.icon_bed_normal);
                tv_Two.setTextColor(this.getResources().getColor(R.color.text_normal_color));

                imgThree.setImageResource(R.mipmap.icon_meeting_normal);
                tv_Three.setTextColor(this.getResources().getColor(R.color.text_normal_color));

                imgFour.setImageResource(R.mipmap.icon_set_normal);
                tv_Four.setTextColor(this.getResources().getColor(R.color.text_normal_color));

                imgExit.setImageResource(R.mipmap.icon_out_selected);
                tv_Exit.setTextColor(this.getResources().getColor(R.color.white));

                SPUtils.remove(MainActivity.this, Contant.DEPARTMENTOFUSER);
                SPUtils.remove(MainActivity.this, Contant.DEPARTCODE);
                SPUtils.remove(MainActivity.this, Contant.DEPARTNAME);
                //  SPUtils.remove(MainActivity.this, Contant.SAVEPWD);
                // SPUtils.remove(MainActivity.this, Contant.USERPWD);
                SPUtils.remove(MainActivity.this, Contant.AUTOLOGIN);
                SPUtils.remove(MainActivity.this, Contant.ISMEETING);


                intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                break;


            case R.id.lv_vertical_title_five://手术排班
                imgOne.setImageResource(R.mipmap.icon_spec_normal);
                tv_one.setTextColor(this.getResources().getColor(R.color.text_normal_color));


                imgTwo.setImageResource(R.mipmap.icon_bed_normal);
                tv_Two.setTextColor(this.getResources().getColor(R.color.text_normal_color));

                imgThree.setImageResource(R.mipmap.icon_meeting_normal);
                tv_Three.setTextColor(this.getResources().getColor(R.color.text_normal_color));

                imgFour.setImageResource(R.mipmap.icon_set_normal);
                tv_Four.setTextColor(this.getResources().getColor(R.color.text_normal_color));

                imgExit.setImageResource(R.mipmap.icon_out_normal);
                tv_Exit.setTextColor(this.getResources().getColor(R.color.text_normal_color));


                ivShouShu.setImageResource(R.mipmap.shoushu_selected_xhdpi);
                tvShoushu.setTextColor(this.getResources().getColor(R.color.white));


                intent = new Intent(getApplicationContext(), OperationActivity.class);
                layout_general_main_dialog.setVisibility(View.VISIBLE);
                startActivity(intent);

        }


    }

    private static Boolean isExit = false;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) ;
        {
            exitBy2Click();
        }
        return false;
    }

    private void exitBy2Click() {
        // TODO Auto-generated method stub
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "Press Back again to quit", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            SPUtils.remove(MainActivity.this, Contant.DEPARTMENTOFUSER);
            SPUtils.remove(MainActivity.this, Contant.DEPARTCODE);
            SPUtils.remove(MainActivity.this, Contant.DEPARTNAME);
            finish();
            System.exit(0);
        }
    }


}
