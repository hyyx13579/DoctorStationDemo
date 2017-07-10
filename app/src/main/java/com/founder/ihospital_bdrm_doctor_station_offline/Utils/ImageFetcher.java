package com.founder.ihospital_bdrm_doctor_station_offline.Utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;
import android.util.Log;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.ImageBucket;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.ImageItem;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.VideoBucket;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.VideoItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/**
 * 图片工具类
 * 
 */

public class ImageFetcher
{
	private static ImageFetcher instance;
	private Context mContext;
	private HashMap<String,VideoBucket> vBucketList = new HashMap<String, VideoBucket>();
	private HashMap<String, ImageBucket> mBucketList = new HashMap<String, ImageBucket>();
	private HashMap<String, String> mThumbnailList = new HashMap<String, String>();

	private ImageFetcher()
	{
	}

	private ImageFetcher(Context context)
	{
		this.mContext = context;
	}

	public static ImageFetcher getInstance(Context context)
	{
		// if(context==null)
		// context = MyApplication.getMyApplicationContext(); TODO

		if (instance == null)
		{
			synchronized (ImageFetcher.class)
			{
				instance = new ImageFetcher(context);
			}
		}
		return instance;
	}

	/**
	 * 是否已加载过了相册集合
	 */
	boolean hasBuildImagesBucketList = false;
	boolean hasBuildVideoBucketList = false;

	/**
	 * 得到图片集
	 * 
	 * @param refresh
	 * @return
	 */
	public List<ImageBucket> getImagesBucketList(boolean refresh)
	{
		if (refresh || (!refresh && !hasBuildImagesBucketList))
		{
			buildImagesBucketList();
		}
		List<ImageBucket> tmpList = new ArrayList<ImageBucket>();
		Iterator<Entry<String, ImageBucket>> itr = mBucketList.entrySet()
				.iterator();
		while (itr.hasNext())
		{
			Entry<String, ImageBucket> entry = (Entry<String, ImageBucket>) itr
					.next();
			tmpList.add(entry.getValue());
		}
		return tmpList;
	}
	public List<VideoBucket> getVideoBucketList(boolean flag){
		if (flag || (!flag && !hasBuildVideoBucketList)){
			buildVideoBucketList();
			Log.i("datalist", "getVideoBucketList: ");
		}
		List<VideoBucket> tmpList = new ArrayList<VideoBucket>();
		Iterator<Entry<String, VideoBucket>> itr = vBucketList.entrySet()
				.iterator();
		while (itr.hasNext())
		{
			Entry<String, VideoBucket> entry = (Entry<String,VideoBucket>) itr
					.next();
			tmpList.add(entry.getValue());
		}
		return tmpList;
	}

	private void buildVideoBucketList() {
		Cursor cur = null;
		try
		{
			long startTime = System.currentTimeMillis();

			// 构造缩略图索引
			getThunbnailVideo();

			// 构造视频索引
			String columns[] = new String[] { MediaStore.Video.Media._ID, MediaStore.Video.Media.BUCKET_ID,
					MediaStore.Video.Media.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME };
			// 得到一个游标
			cur = mContext.getContentResolver().query(
					MediaStore.Video.Media.EXTERNAL_CONTENT_URI, columns, null, null, null);
			if (cur.moveToFirst())
			{
				// 获取指定列的索引
				int videoIDIndex = cur.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
				int videoPathIndex = cur.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
				int bucketDisplayNameIndex = cur
						.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);
				int bucketIdIndex = cur.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_ID);

				do
				{
					String _id = cur.getString(videoIDIndex);
					String path = cur.getString(videoPathIndex);
					String bucketName = cur.getString(bucketDisplayNameIndex);
					String bucketId = cur.getString(bucketIdIndex);

					VideoBucket bucket = vBucketList.get(bucketId);
					if (bucket == null)
					{
						bucket = new VideoBucket();
						vBucketList.put(bucketId, bucket);
						bucket.videoList = new ArrayList<VideoItem>();
						bucket.bucketName = bucketName;
					}
					bucket.count++;
					VideoItem videoItem = new VideoItem();
					videoItem.videoId = _id;
					videoItem.sourcePath = path;
					videoItem.thumbnailPath = mThumbnailList.get(_id);
					bucket.videoList.add(videoItem);

				}
				while (cur.moveToNext());
			}

			hasBuildVideoBucketList = true;
			long endTime = System.currentTimeMillis();
			Log.d(ImageFetcher.class.getName(), "use time: "
					+ (endTime - startTime) + " ms");
		}
		finally
		{
			cur.close();
		}
	}

	/**
	 * 得到图片集
	 */
	private void buildImagesBucketList()
	{
		Cursor cur = null;
		try
		{
			long startTime = System.currentTimeMillis();

			// 构造缩略图索引
			getThumbnail();

			// 构造相册索引
			String columns[] = new String[] { Media._ID, Media.BUCKET_ID,
					Media.DATA, Media.BUCKET_DISPLAY_NAME };
			// 得到一个游标
			cur = mContext.getContentResolver().query(
					Media.EXTERNAL_CONTENT_URI, columns, null, null, null);
			if (cur.moveToFirst())
			{
				// 获取指定列的索引
				int photoIDIndex = cur.getColumnIndexOrThrow(Media._ID);
				int photoPathIndex = cur.getColumnIndexOrThrow(Media.DATA);
				int bucketDisplayNameIndex = cur
						.getColumnIndexOrThrow(Media.BUCKET_DISPLAY_NAME);
				int bucketIdIndex = cur.getColumnIndexOrThrow(Media.BUCKET_ID);

				do
				{
					String _id = cur.getString(photoIDIndex);
					String path = cur.getString(photoPathIndex);
					String bucketName = cur.getString(bucketDisplayNameIndex);
					String bucketId = cur.getString(bucketIdIndex);

					ImageBucket bucket = mBucketList.get(bucketId);
					if (bucket == null)
					{
						bucket = new ImageBucket();
						mBucketList.put(bucketId, bucket);
						bucket.imageList = new ArrayList<ImageItem>();
						bucket.bucketName = bucketName;
					}
					bucket.count++;
					ImageItem imageItem = new ImageItem();
					imageItem.imageId = _id;
					imageItem.sourcePath = path;
					imageItem.thumbnailPath = mThumbnailList.get(_id);
					bucket.imageList.add(imageItem);

				}
				while (cur.moveToNext());
			}

			hasBuildImagesBucketList = true;
			long endTime = System.currentTimeMillis();
			Log.d(ImageFetcher.class.getName(), "use time: "
					+ (endTime - startTime) + " ms");
		}
		finally
		{
			cur.close();
		}
	}

	/**
	 * 得到Video数据库列表
	 */
	private void getThunbnailVideo(){
		Cursor cursor = null;
		try
		{
			String[] projection = {MediaStore.Video.Thumbnails.VIDEO_ID, MediaStore.Video.Thumbnails.DATA };
			cursor = mContext.getContentResolver().query(
					MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, projection, null, null,
					null);
			getThumbnailColumnVideoData(cursor);
		}
		finally
		{
			cursor.close();
		}


	}

	/**
	 *
	 * @param cursor Video的Thumbnail表格
     */
	private void getThumbnailColumnVideoData(Cursor cursor) {
		if (cursor.moveToFirst())
		{
			int video_id;
			String video_path;
			int video_idColumn = cursor.getColumnIndex(MediaStore.Video.Thumbnails.VIDEO_ID);
			int dataColumn = cursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA);

			do
			{
				video_id = cursor.getInt(video_idColumn);
				video_path = cursor.getString(dataColumn);

				mThumbnailList.put("" + video_id, video_path);
			}
			while (cursor.moveToNext());
		}
	}

	/**
	 * 得到缩略图
	 */
	private void getThumbnail()
	{
		Cursor cursor = null;
		try
		{
			String[] projection = { Thumbnails.IMAGE_ID, Thumbnails.DATA };
			cursor = mContext.getContentResolver().query(
					Thumbnails.EXTERNAL_CONTENT_URI, projection, null, null,
					null);
			getThumbnailColumnData(cursor);
		}
		finally
		{
			cursor.close();
		}
	}

	/**
	 * 从数据库中得到缩略图
	 * 
	 * @param cur
	 */
	private void getThumbnailColumnData(Cursor cur)
	{
		if (cur.moveToFirst())
		{
			int image_id;
			String image_path;
			int image_idColumn = cur.getColumnIndex(Thumbnails.IMAGE_ID);
			int dataColumn = cur.getColumnIndex(Thumbnails.DATA);

			do
			{
				image_id = cur.getInt(image_idColumn);
				image_path = cur.getString(dataColumn);

				mThumbnailList.put("" + image_id, image_path);
			}
			while (cur.moveToNext());
		}
	}


}
