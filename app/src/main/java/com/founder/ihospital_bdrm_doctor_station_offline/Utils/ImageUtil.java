package com.founder.ihospital_bdrm_doctor_station_offline.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Thumbnails;
import android.text.TextPaint;
import android.util.Base64;

public class ImageUtil {
    /**
     * 将base64转换成bitmap图片
     *
     * @param string base64字符串
     * @return bitmap
     */
    public static Bitmap getBitmapFromBase64String(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                    bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static InputStream getRequest(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        if (conn.getResponseCode() == 200) {
            return conn.getInputStream();
        }
        return null;
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }

    public static Drawable loadImageFromUrl(String url) {
        URL m;
        InputStream i = null;
        try {
            m = new URL(url);
            i = (InputStream) m.getContent();
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(i, "src");
        return d;
    }

    /****
     * 根据路径生成图片 本地已存在不再下载
     *
     * @param url
     * @param path
     * @return
     */
    public static Bitmap getBitmapFromLocalDir(String url, String path) {
        if (url != null && url.trim().length() > 0 && path != null) {
            File tmpDir = new File(path);
            if (tmpDir.exists() && tmpDir.isDirectory()) {
                String fileName = url.substring(url.lastIndexOf("/") + 1, url.length());
                if (fileName != null && fileName.length() > 0) {
                    return BitmapFactory.decodeFile(path + File.separator + fileName);
                }
            } else {
                tmpDir.mkdirs();
            }
            tmpDir = null;
        }
        return null;
    }

    public static Drawable getDrawableFromUrl(String url) throws Exception {
        return Drawable.createFromStream(getRequest(url), null);
    }

    public static Bitmap getBitmapFromUrl(String url) throws Exception {
        try {
            byte[] bytes = getBytesFromUrl(url);
            return byteToBitmap(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap getRoundBitmapFromUrl(String url, int pixels) throws Exception {
        byte[] bytes = getBytesFromUrl(url);
        Bitmap bitmap = byteToBitmap(bytes);
        return toRoundCorner(bitmap, pixels);
    }

    public static Drawable geRoundDrawableFromUrl(String url, int pixels) throws Exception {
        byte[] bytes = getBytesFromUrl(url);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) byteToDrawable(bytes);
        return toRoundCorner(bitmapDrawable, pixels);
    }

    public static byte[] getBytesFromUrl(String url) throws Exception {
        return readInputStream(getRequest(url));
    }

    public static Bitmap byteToBitmap(byte[] byteArray) {
        try {
            if (byteArray.length != 0) {
                return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Drawable byteToDrawable(byte[] byteArray) {
        ByteArrayInputStream ins = new ByteArrayInputStream(byteArray);
        return Drawable.createFromStream(ins, null);
    }

    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap decodeSampledBitmapFromFile(String filename,
                                                     int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filename, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filename, options);
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }


    /*
    * 以最省内存的方式读取本地资源的图�?
    */
    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 图片去色,返回灰度图片
     *
     * @param bmpOriginal 传入的图�?
     * @return 去色后的图片
     */
    public static Bitmap toGrayscale(Bitmap bmpOriginal) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }

    /**
     * 去色同时加圆�?
     *
     * @param bmpOriginal 原图
     * @param pixels      圆角弧度
     * @return 修改后的图片
     */
    public static Bitmap toGrayscale(Bitmap bmpOriginal, int pixels) {
        return toRoundCorner(toGrayscale(bmpOriginal), pixels);
    }

    /**
     * 把图片变成圆�?
     *
     * @param bitmap �?��修改的图�?
     * @param pixels 圆角的弧�?
     * @return 圆角图片
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * 根据手机的分辨率�?dp 的单�?转成�?px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，包管文字大小不变
     * <p/>
     * ＠param pxValue ＠param fontScale ＠return
     */
    public static int px2sp(float pxValue, float fontScale) {
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，包管文字大小不变
     * <p/>
     * ＠param spValue ＠param fontScale ＠return
     */
    public static int sp2px(float spValue, float fontScale) {
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 使圆角功能支持BitampDrawable
     *
     * @param bitmapDrawable
     * @param pixels
     * @return
     */
    public static BitmapDrawable toRoundCorner(BitmapDrawable bitmapDrawable, int pixels) {
        Bitmap bitmap = bitmapDrawable.getBitmap();
        bitmapDrawable = new BitmapDrawable(toRoundCorner(bitmap, pixels));
        return bitmapDrawable;
    }

    /**
     * Bitmap转换Drawable
     */
    public static Drawable toDrawable(Bitmap bm) {
        return new BitmapDrawable(bm);
    }

    // 将压缩后的图片保存至本地
    public static void saveCompressBitmapToLocalDir(Bitmap map, String path) {
        if (map != null && path != null && path.trim().length() > 0) {
            // System.out.println("IMAGE_TEMP_DIR:"+IMAGE_TEMP_DIR);
            String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
            if (fileName != null && fileName.length() > 0) {
                File imageFile = new File(path);
                imageFile.delete();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(imageFile);
                    map.compress(Bitmap.CompressFormat.PNG, 75, fos);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException ioe) {
                            // do nothing
                        }
                    }
                }
            }
        }
    }

    // 将压缩后的图片保存至本地
    public static void saveCompressBitmapToLocalDir(Bitmap map, String path, int quality) {
        if (map != null && path != null && path.trim().length() > 0) {
            // System.out.println("IMAGE_TEMP_DIR:"+IMAGE_TEMP_DIR);
            String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
            if (fileName != null && fileName.length() > 0) {
                File imageFile = new File(path);
                imageFile.delete();
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(imageFile);
                    if (!map.isRecycled())
                        map.compress(Bitmap.CompressFormat.JPEG, quality, fos);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException ioe) {
                            // do nothing
                        }
                    }
                }
            }
        }
    }


    public static void writeToPhotoExifInfo(String path) {
        try {
            ExifInterface exif = new ExifInterface(path);
            exif.setAttribute(ExifInterface.TAG_MAKE, "pixshow");
            exif.saveAttributes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片适配大小 ,同时缩放比例
     */
    public static Bitmap charge(String path, int sHeigth, int sWidth) {
        Bitmap bm = change(path);
        if (bm != null) {
            bm = zoomImage(bm, sHeigth, sWidth);
        }
        return bm;
    }

    public static Bitmap loadImageFromId(Context context, long id) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        opt.inSampleSize = 1;
        // -------------------只能在sdk2.0及以上版本才能使用下面的方法
        return new BitmapDrawable(Thumbnails.getThumbnail(context.getContentResolver(), id, Thumbnails.MINI_KIND, opt)).getBitmap();
    }

    /**
     * 图片适配大小
     */
    public static Bitmap change(String path) {
        Bitmap bm = null;

        BitmapFactory.Options optsa = new BitmapFactory.Options();
        optsa.inJustDecodeBounds = false;
        optsa.inPreferredConfig = Config.ARGB_4444;
        optsa.inSampleSize = 5;
        optsa.inPurgeable = true;
        optsa.inInputShareable = true;
        optsa.outWidth = 200;
        optsa.outHeight = 200;

        bm = BitmapFactory.decodeFile(path, optsa);
        if (bm != null) {
        } else {
            return null;
        }
        // 原图片高小于480,宽小�?20
        if ((bm.getHeight() * 8) <= 480 && (bm.getWidth() * 8) <= 320) {
            // 还原
            bm = BitmapFactory.decodeFile(path);
        }

        return bm;
    }

    /***
     * 图片的缩放方�?
     *
     * @param bgimage   ：源图片资源
     * @param newWidth  ：缩放后宽度
     * @param newHeight ：缩放后高度
     * @return
     */
    public static Bitmap zoomImage(Bitmap bgimage, int newWidth, int newHeight) {
        // 获取这个图片的宽和高
        int width = bgimage.getWidth();
        int height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算缩放率，新尺寸除原始尺寸
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, width, height, matrix, true);
        return bitmap;
    }

    /***
     * 图片的缩放方�?
     *
     * @param bgimage   ：源图片资源
     * @param newWidth  ：缩放后宽度
     * @param newHeight ：缩放后高度
     * @return
     */
    public static Bitmap zoomImage(Bitmap bgimage, int newWidth, int newHeight, int Orientation) {
        // 获取这个图片的宽和高
        int width = bgimage.getWidth();
        int height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算缩放率，新尺寸除原始尺寸
        // float scaleWidth = ((float) newWidth) / width;
        // float scaleHeight = ((float) newHeight) / height;
        // 旋转图片
        if (Orientation == 6) { // 竖拍
            matrix.postRotate(90);
        }
        if (Orientation == 3) { // 后置像头 镜头朝向右时 �?80�?
            matrix.postRotate(180);
        }
        if (Orientation == 8) { // 前置摄像头竖�?
            matrix.postRotate(-90);
        }
        // 缩放图片动作
        // matrix.postScale(scaleWidth, scaleHeight);

        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, width, height, matrix, true);
        return bitmap;
    }

    /**
     * 获得照片实际路径
     */
    public static String getRealPathFromURI(Uri uri, ContentResolver resolver) {
        String[] proj = {Images.Media.DATA};
        Cursor cursor = resolver.query(uri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(Images.Media.DATA);
        cursor.moveToFirst();
        String str = cursor.getString(column_index);
        cursor.close();

        return str;
    }

    /***
     * 创建圆形bitmap
     *
     * @param bitmap
     * @return
     */
    public static Bitmap getCircleBitmap(Bitmap bitmap) {
        int x = bitmap.getWidth();
        Bitmap output = Bitmap.createBitmap(x,
                x, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        // 根据原来图片大小画一个矩�?
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        paint.setColor(color);
        // 画出�?���?
        canvas.drawCircle(x / 2, x / 2, x / 2, paint);
//        canvas.translate(-5, -6);
        // 取两层绘制交�?显示上层
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        // 将图片画上去
        canvas.drawBitmap(bitmap, rect, rect, paint);
        // 返回Bitmap对象
        return output;
    }

    /**
     * 加水�?也可以加文字
     *
     * @param src
     * @param watermark
     * @param title
     * @return
     */
    public static Bitmap watermarkBitmap(Bitmap src, Bitmap watermark,
                                         String title) {
        if (src == null) {
            return null;
        }
        int w = src.getWidth();
        int h = src.getHeight();
        //�?��处理图片太大造成的内存超过的问题,这里我的图片很小�?��不写相应代码�?       
        Bitmap newb = Bitmap.createBitmap(w, h, Config.ARGB_8888);// 创建�?��新的和SRC长度宽度�?��的位�?
        Canvas cv = new Canvas(newb);
        cv.drawBitmap(src, 0, 0, null);// �?0�?坐标�?��画入src    
        Paint paint = new Paint();
        //加入图片
        if (watermark != null) {
//            cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, paint);// 在src的右下角画入水印         
            cv.drawBitmap(watermark, 0, 0, paint);// 在src的左上角画入水印      
        } else {
//            LogUtil.e("water mark failed");
        }
        //加入文字
        if (title != null) {
            String familyName = "宋体";
            Typeface font = Typeface.create(familyName, Typeface.NORMAL);
            TextPaint textPaint = new TextPaint();
            textPaint.setColor(Color.RED);
            textPaint.setTypeface(font);
            textPaint.setTextSize(40);
            //这里是自动换行的
//            StaticLayout layout = new StaticLayout(title,textPaint,w,Alignment.ALIGN_OPPOSITE,1.0F,0.0F,true);
//            layout.draw(cv);
            //文字就加左上角算�?
            cv.drawText(title, w - 400, h - 40, textPaint);
        }
        cv.save(Canvas.ALL_SAVE_FLAG);// 保存
        cv.restore();// 存储
        return newb;
    }
}