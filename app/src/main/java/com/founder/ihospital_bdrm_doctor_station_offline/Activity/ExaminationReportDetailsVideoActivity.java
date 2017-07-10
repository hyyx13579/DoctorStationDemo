package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by hyyx and lxb
 * 移动查房模块-检查报告功能-三层展示界面(pacs),未完成
 */

public class ExaminationReportDetailsVideoActivity extends BaseActivity {


    private RollPagerView rollPagerView;
    int imgControl[] = {R.mipmap.start, R.mipmap.stop};
    private TestLoopAdapter mLoopAdapter;
    private List<Bitmap> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination_report_details_video);
        initView();
    }

    private void initView() {

        rollPagerView = ((RollPagerView) findViewById(R.id.loop_view_pager));
        rollPagerView.setImgsControl(imgControl);
        rollPagerView.setImgsLR(R.mipmap.anesthes_record_img_btn_leftpage2x, R.mipmap.anesthes_record_img_btn_rightpage2x);
        rollPagerView.setPlayDelay(500);


        rollPagerView.setAdapter(mLoopAdapter = new TestLoopAdapter(rollPagerView));


        try {
            InputStream is = null;
            Bitmap bitmap = null;
            String dirPath = "PacsImages";
            String photoName = null;
            AssetManager assetManager = getAssets();
            //使用list()方法获取某文件夹下所有文件的名字
            String[] photos = assetManager.list(dirPath);
            for (int i = 0; i < 63; i++) {
                photoName = photos[i];
                //利用dirPath+"/"+photoName组拼某文件完整路径
                is = assetManager.open(dirPath + "/" + photoName);
                bitmap = BitmapFactory.decodeStream(is);
                data.add(ThumbnailUtils.extractThumbnail(bitmap, 400, 400));
            }
        } catch (Exception e) {
        }
        mLoopAdapter.setImgs(data);

    }


    private class TestLoopAdapter extends LoopPagerAdapter {
        List<Bitmap> data = new ArrayList<>();

        public void setImgs(List<Bitmap> data) {
            this.data = data;
            notifyDataSetChanged();
        }


        public TestLoopAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {

            ImageView view = new ImageView(container.getContext());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("RollViewPager", "onClick");
                }
            });

            view.setImageBitmap(this.data.get(position));
            view.setScaleType(ImageView.ScaleType.FIT_XY);

            return view;
        }

        @Override
        public int getRealCount() {
            return this.data.size();
        }

    }


}
