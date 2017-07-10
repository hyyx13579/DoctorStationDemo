package com.founder.ihospital_bdrm_doctor_station_offline.Utils;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 呼延 on 2016/3/22.
 * 公共类
 */
public class Url {


    /**
     * 医院移动护理接口
     */

    public static final String DoctorServer = "http://172.27.1.58:9001/api/DoctorServer/";


    /**
     * 正式地址
     */
    //  public static final String DoctorServer = "http://10.8.5.71:9001/api/DoctorServer/";

    /**
     *公司移动护理接口
     * */

    // public static final String DoctorServer = "http://172.18.132.67:9008/api/DoctorServer/";

    /**
     * 医院床位管理接口 9001
     */
    // public static final String BedManagementServer = "http://172.27.1.58:9001/api/BedManagementServer/";

    /**
     * 医院床位管理接口 9002
     */
    //  public static final String BedManagementServerTwo = "http://172.27.1.58:9002/api/BedManagementServer/";

    /**
     * 医院床位内网接口
     */
    public static final String BedManagementServerTwo = "http://10.8.5.71:9001/api/BedManagementServer/";


    /**
     * 医院会诊内网接口
     */
    public static final String ConsultationManagement = "http://10.8.5.71:9001/api/ConsultationManagement/";

    /**
     * 医院会诊接口  9002
     */
    //public static final String ConsultationManagement = "http://172.27.1.58:9002/api/ConsultationManagement/";

    /**
     * 医院设备管理内网接口
     */

    public static final String DeviceManageServer = "http://10.8.5.71:9001/api/DeviceManageServer/";


    public static final String VerifyVerisonUrl = "https://172.27.1.58:443";


    /**
     * 医院手术排班内网接口
     */
    public static final String OperationSchedule = "http://10.8.5.71:9001/api/OperationSchedule/";

    /**
     * 医院手术排班接口  9001
     */
    // public static final String OperationSchedule = "http://172.27.1.58:9001/api/OperationSchedule/";


    /**
     * Toast的方法
     * --------------------------------------------------------------------
     */

    public static void isToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    public static void ErrorToast(Context context) {
        Toast.makeText(context, "网络请求失败，请检查网络", Toast.LENGTH_SHORT).show();
    }

    public static void ErrorToast(Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }


    public static void ErrorToastTwo(Context context) {
        Toast.makeText(context, "数据问题,请联系后台查询数据", Toast.LENGTH_SHORT).show();
    }

    /**
     * 处理日期
     * ---------------------------------------------------------------------
     */


    public static String dealDate(String content) {
        String[] split = content.split(" ");
        return split[0];
    }

    /**
     * 2016-03-21 09:30:28
     */
    public static String dateToSimpleDate(String date) {

        String[] split = date.split(" ");
        String replace = split[0].replace("-", "/");
        String newDate = replace.replace(replace.substring(0, 3), replace.substring(2, 3)) + " " + split[1];


        return newDate;
    }

    public static String dateToSimpleDateTwo(String date) {

        String[] split = date.split(" ");
        String newDate = split[0];


        return newDate;
    }


    /**
     * InpatientPermitDate : 2015-08-19T18:28:41
     */

    public static String inpatientPermitDateToSimpleDate(String inpatientPermitDate) {

        String[] ts = inpatientPermitDate.split("T");
        return ts[0];


    }


    public static Date strToDate(String str) {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = simple.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    //SwipeRefreshLayout的设置
    public static void RefreshData(SwipeRefreshLayout swipeRefreshLayout) {

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);


    }
}
