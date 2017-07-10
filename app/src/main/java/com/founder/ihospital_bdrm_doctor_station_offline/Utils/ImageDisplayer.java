package com.founder.ihospital_bdrm_doctor_station_offline.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;

public class ImageDisplayer
{
	private static ImageDisplayer instance;
	private Context context;
	private static final int THUMB_WIDTH = 256;
	private static final int THUMB_HEIGHT = 256;
	private int mScreenWidth;
	private int mScreenHeight;

	public static ImageDisplayer getInstance(Context context)
	{
		if (instance == null)
		{
			synchronized (ImageDisplayer.class)
			{
				instance = new ImageDisplayer(context);
			}
		}

		return instance;
	}

	public ImageDisplayer(Context context)
	{
		if (context.getApplicationContext() != null) this.context = context
				.getApplicationContext();
		else
			this.context = context;

		DisplayMetrics dm = new DisplayMetrics();
		dm = this.context.getResources().getDisplayMetrics();
		mScreenWidth = dm.widthPixels;
		mScreenHeight = dm.heightPixels;
	}

	public Handler h = new Handler();
	public final String TAG = getClass().getSimpleName();
	private HashMap<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();

	public void put(String key, Bitmap bmp)
	{
		if (!TextUtils.isEmpty(key) && bmp != null)
		{
			imageCache.put(key, new SoftReference<Bitmap>(bmp));
		}
	}

	public void displayBmp(final ImageView iv, final String thumbPath,
			final String sourcePath)
	{
		displayBmp(iv, thumbPath, sourcePath, true);
	}

	public void displayBmp(final ImageView iv, final String thumbPath,
			final String sourcePath, final boolean showThumb)
	{
		if (TextUtils.isEmpty(thumbPath) && TextUtils.isEmpty(sourcePath))
		{
			Log.e(TAG, "no paths pass in");
			return;
		}

		if (iv.getTag() != null && iv.getTag().equals(sourcePath))
		{
			return;
		}

		showDefault(iv);

		final String path;
		if (!TextUtils.isEmpty(thumbPath) && showThumb)
		{
			path = thumbPath;
		}
		else if (!TextUtils.isEmpty(sourcePath))
		{
			path = sourcePath;
			Log.i("video","----->"+path);
		}
		else
		{
			return;
		}

		iv.setTag(path);

		/***
		 * 查看是否存在缓存
		 */
		if (imageCache.containsKey(showThumb ? path + THUMB_WIDTH
				+ THUMB_HEIGHT : path))
		{
			SoftReference<Bitmap> reference = imageCache.get(showThumb ? path
					+ THUMB_WIDTH + THUMB_HEIGHT : path);
			// 可以用LruCahche会好些
			Bitmap imgInCache = reference.get();
			if (imgInCache != null)
			{
				refreshView(iv, imgInCache, path);
				return;
			}
		}
		iv.setImageBitmap(null);

		// 不在缓存则加载图片
		new Thread()
		{
			Bitmap img;

			public void run()
			{

				try
				{
					if (path != null && path.equals(thumbPath))
					{
						img = BitmapFactory.decodeFile(path);
					}
					if (img == null)
					{
						Log.i("video", "run: ");
						img = compressImg(sourcePath, showThumb);
					}
				}
				catch (Exception e)
				{

				}

				if (img != null)
				{
					put(showThumb ? path + THUMB_WIDTH + THUMB_HEIGHT : path,
							img);

				}
				h.post(new Runnable()
				{
					@Override
					public void run()
					{
						refreshView(iv, img, path);
					}
				});
			}
		}.start();

	}

	private void refreshView(ImageView imageView, Bitmap bitmap, String path)
	{
		if (imageView != null && bitmap != null)
		{
			if (path != null)
			{
				((ImageView) imageView).setImageBitmap(bitmap);
				imageView.setTag(path);
			}
		}
	}

	private void showDefault(ImageView iv)
	{
		iv.setBackgroundResource(R.mipmap.ic_launcher);
	}

	public Bitmap compressImg(String path, boolean showThumb)
			throws IOException
	{
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				new File(path)));
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(in, null, opt);
		in.close();
		int i = 0;
		Bitmap bitmap = null;
		if (showThumb)
		{
			if (path.endsWith(".avi")){
				MediaMetadataRetriever retriever = new MediaMetadataRetriever();
				retriever.setDataSource(path);
				bitmap = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.MICRO_KIND);
				bitmap = ThumbnailUtils.extractThumbnail(bitmap,THUMB_WIDTH,THUMB_HEIGHT, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
			}else{
			while (true)
			{
				if ((opt.outWidth >> i <= THUMB_WIDTH)
						&& (opt.outHeight >> i <= THUMB_HEIGHT))
				{
					in = new BufferedInputStream(new FileInputStream(new File(
							path)));
					opt.inSampleSize = (int) Math.pow(2.0D, i);
					opt.inJustDecodeBounds = false;
					bitmap = BitmapFactory.decodeStream(in, null, opt);
					break;
				}
				i += 1;
			}
			}
		}
		else
		{
			while (true)
			{
				if ((opt.outWidth >> i <= mScreenWidth)
						&& (opt.outHeight >> i <= mScreenHeight))
				{
					in = new BufferedInputStream(new FileInputStream(new File(
							path)));
					opt.inSampleSize = (int) Math.pow(2.0D, i);
					opt.inJustDecodeBounds = false;
					bitmap = BitmapFactory.decodeStream(in, null, opt);
					break;
				}
				i += 1;
			}
		}
		return bitmap;
	}

	public interface ImageCallback
	{
		public void imageLoad(ImageView imageView, Bitmap bitmap,
							  Object... params);
	}
}
