package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.VideoBucket;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ImageDisplayer;

import java.util.List;

/**
 * Created by zhangqun on 16/4/21.
 */
public class VideoBucketAdapter extends BaseAdapter{
    private List<VideoBucket> mDataList;
    private Context mContext;

    public VideoBucketAdapter(Context context,List<VideoBucket> list){
        this.mContext = context;
        this.mDataList = list;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
        {
            final ViewHolder mHolder;
            if (convertView == null)
            {
                convertView = View.inflate(mContext, R.layout.item_bucket_list,
                        null);
                mHolder = new ViewHolder();
                mHolder.coverIv = (ImageView) convertView.findViewById(R.id.cover);
                mHolder.titleTv = (TextView) convertView.findViewById(R.id.title);
                mHolder.countTv = (TextView) convertView.findViewById(R.id.count);
                convertView.setTag(mHolder);
            }
            else
            {
                mHolder = (ViewHolder) convertView.getTag();
            }

            final VideoBucket item = mDataList.get(position);

            if (item.videoList != null && item.videoList.size() > 0)
            {
                String thumbPath = item.videoList.get(0).thumbnailPath;
                String sourcePath = item.videoList.get(0).sourcePath;
                ImageDisplayer.getInstance(mContext).displayBmp(mHolder.coverIv, thumbPath,
                        sourcePath);
            }
            else
            {
                mHolder.coverIv.setImageBitmap(null);
            }

            mHolder.titleTv.setText(item.bucketName);
            mHolder.countTv.setText(item.count + "张");

            return convertView;

    }
    static class ViewHolder
    {
        private ImageView coverIv;
        private TextView titleTv;
        private TextView countTv;
    }
}
