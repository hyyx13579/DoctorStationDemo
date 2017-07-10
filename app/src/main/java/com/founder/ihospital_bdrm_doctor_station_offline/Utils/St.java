package com.founder.ihospital_bdrm_doctor_station_offline.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by duwang on 16/4/29.
 * 通用方法
 */
public class St {

    /**
     * 屏蔽证件号
     *
     * @return
     */
    public static String mdIdCard(String idcard) {
        String phoneHead = idcard.substring(0, 4);
        String phoneEnd = idcard.substring(idcard.length() - 4, idcard.length());
        return phoneHead + "******" + phoneEnd;
    }

    /**
     * 屏蔽手机
     *
     * @return
     */
    public static String mdPhone(String phone) {
        String phoneHead = phone.substring(0, 3);
        String phoneEnd = phone.substring(phone.length() - 4, phone.length());
        return phoneHead + "****" + phoneEnd;
    }

    /**
     * 判断上午下午
     *
     * @param time
     * @return
     */
    public static String getAmPm(String time) {
        if (time.equals("a") || time.equals("上午")) {
            return "上午";
        } else {
            return "下午";
        }
    }

    /**
     * 根据字符串数字 判断性别
     *
     * @param num 1男   2女
     * @return
     */
    public static String getSex(String num) {
        if (num.equals("2")) {
            return "女";
        } else {
            return "男";
        }
    }

    /**
     * 获取网络状态，未识别（-1），无连接（0），wap(1)，2g(2)，3g(3)，wifi(100)
     */
    public static int getNetWorkType(Context context) {
        int mNetWorkType = -1;
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            String type = networkInfo.getTypeName();

            if (type.equalsIgnoreCase("WIFI")) {
                mNetWorkType = 100;
            } else if (type.equalsIgnoreCase("MOBILE")) {
                @SuppressWarnings("deprecation")
                String proxyHost = android.net.Proxy.getDefaultHost();
                mNetWorkType = TextUtils.isEmpty(proxyHost) ? (isFastMobileNetwork(context) ? 3
                        : 2)
                        : 1;
            }
        } else {
            mNetWorkType = 0;
        }

        return mNetWorkType;
    }

    /**
     * 判斷網絡是3G還是2G
     */
    public static boolean isFastMobileNetwork(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        // 在中国，联通的3G为UMTS或HSDPA，移动和联通的2G为GPRS或EDGE，电信的2G为CDMA，电信的3G为EVDO
        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false; // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true; // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true; // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false; // ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true; // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true; // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true; // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true; // ~ 400-7000 kbps
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return true; // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return true; // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return true; // ~ 10-20 Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return false; // ~25 kbps
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true; // ~ 10+ Mbps
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;
            default:
                return false;
        }
    }

    public static String getNetWorkTypeName(int NetWorkType) {
        switch (NetWorkType) {
            case 0:
                return "无网络";
            case -1:
                return "4G";
            case 1:
                return "wap";
            case 2:
                return "2G";
            case 3:
                return "3G";
            case 100:
                return "WIFI";
            default:
                return "";
        }
    }
    /**
     * 设置editText 左边的图片状态
     * @param hasFocus 是否获得焦点
     * @param et  当前EditText
     * @param iv   左边的ImageView
     * @param defaultImg  默认的图片
     * @param pressImg   获取焦点后的图片
     */
    public static void setEditTextLeftImg(boolean hasFocus, EditText et, ImageView iv, int defaultImg, int pressImg) {
        if (!hasFocus) {
            if (TextUtils.isEmpty(et.getText().toString())) {
                iv.setBackgroundResource(defaultImg);
            } else {
                iv.setBackgroundResource(pressImg);
            }
        } else {
            iv.setBackgroundResource(pressImg);
        }
    }
    /**
     *判断是否是手机号
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
//        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Pattern p = Pattern.compile("^((13[0-9])|(14[5,7,9])|(15[^4,\\D])|(17[0,1,3,5-8])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isDigits(String s){
        Pattern pattern = Pattern.compile("^[-+]?[0-9]+(.[0-9]+)?$");
        if(pattern.matcher(s).matches()){
            return true;
        }
        return false;
    }
}
