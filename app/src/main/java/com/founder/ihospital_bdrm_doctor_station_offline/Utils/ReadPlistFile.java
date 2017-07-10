package com.founder.ihospital_bdrm_doctor_station_offline.Utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016/6/7 0007.
 * 读取.plist文件的解析类
 */
public class ReadPlistFile {


    /**
     * 读取每个病人用到信息的方法
     *
     * @param context
     * @param wayName   网络请求所使用的方法名
     * @param patientID 通用为病人的id,特殊情况为具体文件名
     */
    public static String onReadPatientInformation(Context context, String wayName, String patientID) {
        String[] list = null;
        String result = null;
        try {
            list = context.getResources().getAssets().list(wayName);
            result = getAssetString(wayName + "/" + patientID + ".plist", context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getAssetString(String asset, Context context) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(asset)));
            String line = null;
            StringBuilder builder = new StringBuilder();
            while (null != (line = bufferedReader.readLine())) {
                builder.append(line).append("\n");
            }
            return builder.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            bufferedReader = null;
        }
        return "";
    }


}
