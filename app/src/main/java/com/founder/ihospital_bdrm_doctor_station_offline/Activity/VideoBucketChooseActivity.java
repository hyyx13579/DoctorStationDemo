package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.VideoBucketAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.VideoBucket;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.CustomConstants;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ImageFetcher;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.IntentConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangqun on 16/4/21.
 *
 * 视频
 */
public class VideoBucketChooseActivity extends BaseActivity{

    private ImageFetcher mHelper;
    private List<VideoBucket> mDataList = new ArrayList<VideoBucket>();
    private ListView mListView;
    private VideoBucketAdapter mAdapter;
    private int availableSize;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_image_bucket_choose);

        mHelper = ImageFetcher.getInstance(getApplicationContext());
        initData();
        initView();
    }

    private void initData()
    {
        mDataList = mHelper.getVideoBucketList(false);
        availableSize = getIntent().getIntExtra(IntentConstants.EXTRA_CAN_ADD_IMAGE_SIZE, CustomConstants.MAX_IMAGE_SIZE);
    }

    private void initView()
    {
        mListView = (ListView) findViewById(R.id.listview);
        mAdapter = new VideoBucketAdapter(this, mDataList);
        mListView.setAdapter(mAdapter);
        TextView titleTv  = (TextView) findViewById(R.id.title);
        titleTv.setText("视频");
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                selectOne(position);
                Intent intent = new Intent(VideoBucketChooseActivity.this,
                        VideoChooseActivity.class);
                intent.putExtra(IntentConstants.EXTRA_IMAGE_LIST,
                        (Serializable) mDataList.get(position).videoList);
                intent.putExtra(IntentConstants.EXTRA_BUCKET_NAME,
                        mDataList.get(position).bucketName);
                intent.putExtra(IntentConstants.EXTRA_CAN_ADD_IMAGE_SIZE,
                        availableSize);
                startActivity(intent);
            }
        });

    }

    private void selectOne(int position)
    {
        int size = mDataList.size();
        for (int i = 0; i != size; i++)
        {
            if (i == position) mDataList.get(i).selected = true;
            else
            {
                mDataList.get(i).selected = false;
            }
        }
        mAdapter.notifyDataSetChanged();
    }


}
