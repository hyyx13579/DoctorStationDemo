package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.ImageItem;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.RadioItem;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.VideoItem;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ImageDisplayer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ImagePublishAdapter extends BaseAdapter
{
	private List<Object> mDataList = new ArrayList<Object>();
	private Context mContext;

	public ImagePublishAdapter(Context context, List<Object> dataList)
	{
		this.mContext = context;
		this.mDataList = dataList;
	}

	public int getCount()
	{

			return mDataList.size();
	}

	public Object getItem(int position)
	{
			return mDataList.get(position);
	}

	public long getItemId(int position)
	{
		return position;
	}

	@SuppressLint("ViewHolder")
	public View getView(int position, View convertView, ViewGroup parent)
	{
		//所有Item展示不满一页，就不进行ViewHolder重用了，避免了一个拍照以后添加图片按钮被覆盖的奇怪问题
		convertView = View.inflate(mContext, R.layout.item_publish, null);
		ImageView imageIv = (ImageView) convertView.findViewById(R.id.item_grid_image);
			if (mDataList.get(position) instanceof ImageItem){
				ImageItem item = (ImageItem) mDataList.get(position);
				ImageDisplayer.getInstance(mContext).displayBmp(imageIv,
						item.thumbnailPath, item.sourcePath);

			}else if (mDataList.get(position) instanceof VideoItem){
				VideoItem item = (VideoItem) mDataList.get(position);
				//ImageDisplayer.getInstance(mContext).displayBmp(imageIv,item.thumbnailPath,item.sourcePath);
				//Bitmap bitmap2 = getBitmapFromSDK(item.sourcePath);
				//Bitmap bitmap1 = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.icon_start).copy(Bitmap.Config.ARGB_8888, true);;
				imageIv.setImageResource(R.mipmap.icon_video_add);

			}else if (mDataList.get(position) instanceof RadioItem){
				RadioItem item = (RadioItem) mDataList.get(position);
				imageIv.setImageResource(R.mipmap.icon_mic_add);
			}

		return convertView;
	}
	public static Bitmap getBitmapFromSDK(String path){
		Bitmap bitmap = null;
		InputStream in = null;
		BufferedOutputStream out = null;
		try
		{
			in = new BufferedInputStream(new URL(path).openStream());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			out = new BufferedOutputStream(baos);
			out.flush();
			byte[] data = baos.toByteArray();
			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			data = null;
			return bitmap;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
		Bitmap newBitmap = null;

		newBitmap = Bitmap.createBitmap(bmp1);
		Canvas canvas = new Canvas(newBitmap);
		Paint paint = new Paint();

		int w = bmp1.getWidth();
		int h = bmp1.getHeight();

		int w_2 = bmp2.getWidth();
		int h_2 = bmp2.getHeight();

		paint.setColor(Color.GRAY);
		paint.setAlpha(125);
		canvas.drawRect(0, 0, bmp1.getWidth(), bmp1.getHeight(), paint);

		paint = new Paint();
		canvas.drawBitmap(bmp2, Math.abs(w - w_2) / 2,
				Math.abs(h - h_2) / 2, paint);
		canvas.save(Canvas.ALL_SAVE_FLAG);
		// 存储新合成的图片
		canvas.restore();
		return newBitmap;
	}

}
