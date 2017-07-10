package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.MeetingCompleteFragment;
import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.MeetingSignInFragment;
import com.founder.ihospital_bdrm_doctor_station_offline.Fragment.MeetingUndoneFragment;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.ArrayList;
import java.util.List;

/**
 * create by zhangqun
 * 会诊模块
 */

public class MeetingActivity extends BaseActivity {

    private RadioGroup radioGroup;
    private ViewPager viewPager;
    private List<RadioButton> buttons;
    private RadioButton btnMeetingUndone;
    private RadioButton btnComplete;
    private RadioButton btnSignIn;
    private List<Fragment> fragments;
    private ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        initView();
    }

    private void initView() {
        radioGroup = ((RadioGroup) findViewById(R.id.meetingRadioGroup));
        viewPager = ((ViewPager) findViewById(R.id.meetingViewPager));
        buttons = new ArrayList<RadioButton>();
        btnMeetingUndone = ((RadioButton) findViewById(R.id.meetingUndone));
        btnComplete = ((RadioButton) findViewById(R.id.meetingComplete));
        btnSignIn = ((RadioButton) findViewById(R.id.meetingSignIn));
        backImg = ((ImageView) findViewById(R.id.meetingActivityBack));
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buttons.add(btnMeetingUndone);
        buttons.add(btnComplete);
        buttons.add(btnSignIn);
        fragments = new ArrayList<>();
        Fragment undoneFragment = new MeetingUndoneFragment();
        Fragment completeFragment = new MeetingCompleteFragment();
        Fragment signInFragment = new MeetingSignInFragment();
        fragments.add(undoneFragment);
        fragments.add(completeFragment);
        fragments.add(signInFragment);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        btnMeetingUndone.setTextColor(Color.WHITE);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.meetingUndone:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.meetingComplete:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.meetingSignIn:
                        viewPager.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setChecked(false);
                    buttons.get(i).setTextColor(getResources().getColor(R.color.text_normal_color));
                }
                buttons.get(position).setChecked(true);
                buttons.get(position).setTextColor(Color.WHITE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
