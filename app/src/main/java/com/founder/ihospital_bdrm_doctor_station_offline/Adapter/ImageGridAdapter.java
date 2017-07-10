package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.ImageItem;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.VideoItem;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ImageDisplayer;

import java.util.List;

public class ImageGridAdapter extends BaseAdapter
{
	private Context mContext;
	private List<Object> mDataList;

	public ImageGridAdapter(Context context, List<Object> dataList)
	{
		this.mContext = context;
		this.mDataList = dataList;
	}

	@Override
	public int getCount()
	{
		return mDataList == null ? 0 : mDataList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return mDataList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final ViewHolder mHolder;

		if (convertView == null)
		{
			convertView = View
					.inflate(mContext, R.layout.item_image_list, null);
			mHolder = new ViewHolder();
			mHolder.imageIv = (ImageView) convertView.findViewById(R.id.image);
			mHolder.selectedIv = (ImageView) convertView
					.findViewById(R.id.selected_tag);
			mHolder.selectedBgTv = (TextView) convertView
					.findViewById(R.id.image_selected_bg);
			convertView.setTag(mHolder);
		}
		else
		{
			mHolder = (ViewHolder) convertView.getTag();
		}
		if (mDataList.get(position) instanceof ImageItem){
			ImageItem item = (ImageItem) mDataList.get(position);
			ImageDisplayer.getInstance(mContext).displayBmp(mHolder.imageIv,
					item.thumbnailPath, item.sourcePath);
			if (item.isSelected)
			{
				mHolder.selectedIv.setImageDrawable(mContext.getResources()
						.getDrawable(R.mipmap.tag_selected));
				mHolder.selectedIv.setVisibility(View.VISIBLE);
				mHolder.selectedBgTv
						.setBackgroundResource(R.drawable.image_selected);
			}
			else
			{
				mHolder.selectedIv.setImageDrawable(null);
				mHolder.selectedIv.setVisibility(View.GONE);
			}
		}else if (mDataList.get(position) instanceof VideoItem){
			VideoItem item = ((VideoItem) mDataList.get(position));
			ImageDisplayer.getInstance(mContext).displayBmp(mHolder.imageIv,
					item.thumbnailPath, item.sourcePath);
			if (item.isSelected)
			{
				mHolder.selectedIv.setImageDrawable(mContext.getResources()
						.getDrawable(R.mipmap.tag_selected));
				mHolder.selectedIv.setVisibility(View.VISIBLE);
				mHolder.selectedBgTv
						.setBackgroundResource(R.drawable.image_selected);
			}
			else
			{
				mHolder.selectedIv.setImageDrawable(null);
				mHolder.selectedIv.setVisibility(View.GONE);
			}
		}


		return convertView;
	}

	static class ViewHolder
	{
		private ImageView imageIv;
		private ImageView selectedIv;
		private TextView selectedBgTv;
	}

}
