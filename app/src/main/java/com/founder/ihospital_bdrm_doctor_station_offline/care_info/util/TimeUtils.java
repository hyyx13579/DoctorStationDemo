package com.founder.ihospital_bdrm_doctor_station_offline.care_info.util;


import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bin_li on 16/5/14.
 */
public class TimeUtils {
    private static final String DATE_FORMAT_STR = "yyyy-MM-dd";

    public static long parseTimeString(String time, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null)
            return -1;
        return date.getTime();
    }


    @SuppressLint("SimpleDateFormat")
    public static String getTargetDateStr(long timeInMillis, int dayAddNum) {
        Date newDate2 = new Date(timeInMillis + dayAddNum * 24 * 60 * 60
                * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_STR);
        return simpleDateFormat.format(newDate2);
    }

    /**
     *
     *
     */
    public static String transferTime2Chinese(String time, String formate) {

        StringBuilder hourfix = new StringBuilder(); // 整数部分转化的结果
        final char[] NUMBER_CHAR = "零一二三四五六七八九".toCharArray(); // 大写数字
        long t = parseTimeString(time, formate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(t);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        if (hour > 9) {
            hourfix.append(NUMBER_CHAR[hour / 10]);
            hourfix.append("十");
            hourfix.append(NUMBER_CHAR[hour % 10]);
            hourfix.append("时");
        } else {
            hourfix.append(NUMBER_CHAR[hour]);
            hourfix.append("时");
        }
        if (min > 9) {
            hourfix.append(NUMBER_CHAR[min / 10]);
            hourfix.append("十");
            hourfix.append(NUMBER_CHAR[min % 10]);
            hourfix.append("分");
        } else {
            hourfix.append(NUMBER_CHAR[min]);
            hourfix.append("分");
        }

        return hourfix.toString();
    }

}
