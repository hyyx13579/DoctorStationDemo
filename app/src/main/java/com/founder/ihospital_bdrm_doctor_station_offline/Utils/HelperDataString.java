package com.founder.ihospital_bdrm_doctor_station_offline.Utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 呼延 on 2016/4/5.
 */
public class HelperDataString {

    public HelperDataString() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * 将时间转换成Long
     */
    public long changeStringDateToLong(String str) {

        return Long.valueOf(str).longValue();

    }

    /**
     * 获取时间的年月日
     *
     * @param str
     * @return
     */

    public String getFormatDate(String str) {

        if (str == null) {
            return null;
        } else {
            ParsePosition position = new ParsePosition(0);
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(
                    "yyyy/MM/dd");

            Date dateValue = simpleDateFormat1.parse(str, position);
            String strvalue1 = simpleDateFormat1.format(dateValue);

            return strvalue1;
        }
    }

    /**
     * 获取时间的年月日，不含斜杠
     *
     * @param str
     * @return
     */
    public String getFormatDateNoSlash(String str) {

        if (str == null) {
            return null;
        } else {
            ParsePosition position = new ParsePosition(0);
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(
                    "yyyy/MM/dd");

            Date dateValue = simpleDateFormat1.parse(str, position);
            String strvalue1 = simpleDateFormat1.format(dateValue);

            return strvalue1.replace("/", "");
        }
    }

    public String dateTostrModel1(String str) {
        if (str == null) {
            return null;
        } else {
            ParsePosition position = new ParsePosition(0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "yy/MM/dd HH:mm");

            Date dateValue = simpleDateFormat.parse(str, position);
            String strvalue = simpleDateFormat.format(dateValue);
            return strvalue;
        }
    }

    public String dateTostrModel2(String str) {
        if (str == null) {
            return null;
        } else {
            ParsePosition position = new ParsePosition(0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "yy/MM/dd HH:mm");

            Date dateValue = simpleDateFormat.parse(str, position);
            String strvalue = simpleDateFormat.format(dateValue);
            return strvalue.replace("/", "").replace(":", "").replace(" ", "");
        }
    }

    public String dateTostrModel3(String str) {
        StringBuffer sb = new StringBuffer();
        if (str == null) {
            return null;
        } else {
            ParsePosition position = new ParsePosition(0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "yy-MM-dd HH:mm");

            Date dateValue = simpleDateFormat.parse(str, position);
            String strvalue = simpleDateFormat.format(dateValue);

            sb.append(strvalue.subSequence(9, 14));
            sb.append("\n");
            sb.append(strvalue.subSequence(6, 8));

            return sb.toString();
        }
    }

    public String dateTostrModel4(String str) {
        StringBuffer sb = new StringBuffer();
        if (str == null) {
            return null;
        } else {

            ParsePosition position = new ParsePosition(0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "yy-MM-dd HH:mm");

            Date dateValue = simpleDateFormat.parse(str, position);
            String strvalue = simpleDateFormat.format(dateValue);
            sb.append(strvalue.split("\\-")[1]);
            sb.append("-" + strvalue.split("\\-")[2]);


            return sb.toString();
        }

    }


}
