package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.ImageGridAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.VideoItem;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.CustomConstants;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.IntentConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhangqun on 16/4/22.
 * 视频选择
 */
public class VideoChooseActivity extends BaseActivity{

    private List<Object> mDataList = new ArrayList<Object>();
    private String mBucketName;
    private int availableSize;
    private GridView mGridView;
    private TextView mBucketNameTv;
    private ImageGridAdapter mAdapter;
    private Button mFinishBtn;
    private HashMap<String, VideoItem> selectedVideos = new HashMap<String, VideoItem>();

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_image_choose);

        mDataList = (List<Object>) getIntent().getSerializableExtra(
                IntentConstants.EXTRA_IMAGE_LIST);
        if (mDataList == null) {
            mDataList = new ArrayList<Object>();
        }
        mBucketName = getIntent().getStringExtra(
                IntentConstants.EXTRA_BUCKET_NAME);

        if (TextUtils.isEmpty(mBucketName))
        {
            mBucketName = "请选择";
        }
        availableSize = getIntent().getIntExtra(
                IntentConstants.EXTRA_CAN_ADD_IMAGE_SIZE,
                CustomConstants.MAX_IMAGE_SIZE);

        initView();
        initListener();

    }

    private void initView()
    {
        mBucketNameTv = (TextView) findViewById(R.id.title);
        mBucketNameTv.setText(mBucketName);

        mGridView = (GridView) findViewById(R.id.gridview);
        mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mAdapter = new ImageGridAdapter(this, mDataList);
        mGridView.setAdapter(mAdapter);
        mFinishBtn = (Button) findViewById(R.id.finish_btn);


        mFinishBtn.setText("完成" + "(" + selectedVideos.size() + "/"
                + availableSize + ")");
        mAdapter.notifyDataSetChanged();
    }

    private void initListener()
    {
        mFinishBtn.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent intent = new Intent(VideoChooseActivity.this,
                        AddPatientNoteBookEvent.class);
                intent.putExtra(
                        IntentConstants.EXTRA_IMAGE_LIST,
                        (Serializable) new ArrayList<VideoItem>(selectedVideos
                                .values()));
                startActivity(intent);
                finish();
            }

        });

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {

                VideoItem item = (VideoItem) mDataList.get(position);
                if (item.isSelected)
                {
                    item.isSelected = false;
                    selectedVideos.remove(item.videoId);
                }
                else
                {
                    if (selectedVideos.size() >= availableSize)
                    {
                        Toast.makeText(VideoChooseActivity.this,
                                "最多选择" + availableSize + "张图片",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    item.isSelected = true;
                    selectedVideos.put(item.videoId, item);
                }

                mFinishBtn.setText("完成" + "(" + selectedVideos.size() + "/"
                        + availableSize + ")");
                mAdapter.notifyDataSetChanged();
            }

        });

    }
}
