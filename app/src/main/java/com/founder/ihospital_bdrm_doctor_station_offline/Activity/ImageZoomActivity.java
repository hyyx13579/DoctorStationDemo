package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.ImageItem;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.RadioItem;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.VideoItem;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ImageDisplayer;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.IntentConstants;
import com.founder.ihospital_bdrm_doctor_station_offline.View.MyVideoView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * create by zhangqun
 * 点击图片,显示原图的activity
 */
public class ImageZoomActivity extends BaseActivity {

    private ViewPager pager;
    private MyPageAdapter adapter;
    private MediaPlayer mediaPlayer;
    private int currentPosition;
    private List<Object> mDataList = new ArrayList<Object>();

    private RelativeLayout photo_relativeLayout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_zoom);

        photo_relativeLayout = (RelativeLayout) findViewById(R.id.photo_relativeLayout);
        photo_relativeLayout.setBackgroundColor(0x70000000);

        initData();

        Button photo_bt_exit = (Button) findViewById(R.id.photo_bt_exit);
        photo_bt_exit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        Button photo_bt_del = (Button) findViewById(R.id.photo_bt_del);
        photo_bt_del.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mDataList.size() == 1) {
                    removeImgs();
                    finish();
                } else {
                    removeImg(currentPosition);
                    pager.removeAllViews();
                    adapter.removeView(currentPosition);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setOnPageChangeListener(pageChangeListener);

        adapter = new MyPageAdapter(mDataList);
        pager.setAdapter(adapter);
        pager.setCurrentItem(currentPosition);
    }

    private void initData() {
        currentPosition = getIntent().getIntExtra(
                IntentConstants.EXTRA_CURRENT_IMG_POSITION, 0);
        mDataList = AddPatientNoteBookEvent.mDataList;
    }

    private void removeImgs() {
        mDataList.clear();

    }

    private void removeImg(int location) {
        if (location + 1 <= mDataList.size()) {
            mDataList.remove(location);
        }
    }

    private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

        public void onPageSelected(int arg0) {
            currentPosition = arg0;
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        public void onPageScrollStateChanged(int arg0) {

        }
    };

    class MyPageAdapter extends PagerAdapter {

        private List<Object> dataList = new ArrayList<Object>();
        private ArrayList<View> mViews = new ArrayList<View>();

        public MyPageAdapter(List<Object> dataList) {
            this.dataList = dataList;
            int size = dataList.size();
            for (int i = 0; i != size; i++) {

                if (dataList.get(i) instanceof ImageItem) {
                    ImageView iv = new ImageView(ImageZoomActivity.this);
                    ImageItem item = (ImageItem) dataList.get(i);
                    ImageDisplayer.getInstance(ImageZoomActivity.this).displayBmp(
                            iv, null, item.sourcePath, false);
                    iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                            LayoutParams.MATCH_PARENT));
                    mViews.add(iv);
                } else if (dataList.get(i) instanceof VideoItem) {
                    VideoItem item = (VideoItem) dataList.get(i);
//                    View vvLayout = LayoutInflater.from(ImageZoomActivity.this).inflate(R.layout.vv_zoom_activity, null);
//                    VideoView vv = (VideoView) vvLayout.findViewById(R.id.vv_zoom_activity_view);
                    MyVideoView vv = new MyVideoView(ImageZoomActivity.this);
                    vv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                    vv.setMediaController(null);
                    vv.setVideoPath(item.sourcePath);
                    vv.start();
                    mViews.add(vv);

                } else if (dataList.get(i) instanceof RadioItem) {
                    ImageView iv = new ImageView(ImageZoomActivity.this);
                    RadioItem item = (RadioItem) dataList.get(i);
                    iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                            LayoutParams.MATCH_PARENT));
                    iv.setImageResource(R.mipmap.icon_mic_add);
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(item.sourcePath);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {

                    }
                    mViews.add(iv);
                }


            }
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        public Object instantiateItem(View arg0, int arg1) {
            if (mViews.get(arg1) instanceof ImageView) {
                ImageView iv = (ImageView) mViews.get(arg1);
                ((ViewPager) arg0).addView(iv);
                return iv;
            } else if (mViews.get(arg1) instanceof VideoView) {
                View vvLayout = mViews.get(arg1);
                VideoView vv = ((VideoView) mViews.get(arg1));

                ((ViewPager) arg0).addView(vvLayout);
                return vvLayout;
            } else {
                return 0;
            }

        }

        public void destroyItem(View arg0, int arg1, Object arg2) {
            if (mViews.size() >= arg1 + 1) {
                ((ViewPager) arg0).removeView(mViews.get(arg1));
            }
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        public void removeView(int position) {
            if (position + 1 <= mViews.size()) {
                mViews.remove(position);
            }
        }

    }
}