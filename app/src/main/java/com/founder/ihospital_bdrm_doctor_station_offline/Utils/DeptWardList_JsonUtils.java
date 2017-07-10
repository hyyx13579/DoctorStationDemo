package com.founder.ihospital_bdrm_doctor_station_offline.Utils;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.WardIn2formation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 作者：呼延 on 2016/4/26 16:35
 * 邮箱：huyanyx@founder.com.cn
 */
public class DeptWardList_JsonUtils {

    public static List<HashMap<String, Object>> getListFromJson(String jsonString) {

        List<HashMap<String, Object>> dataList = new ArrayList<HashMap<String, Object>>();
        List<HashMap<String, Object>> dataListTwo = null;
        List<HashMap<String, Object>> dataListThree = null;

        HashMap<String, Object> mapOne = null;
        HashMap<String, Object> maptwo = null;
        HashMap<String, Object> mapthree = null;

        WardIn2formation wardIn2formation = null;


        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray values = jsonObject.getJSONArray("Values");
            for (int i = 0; i < values.length(); i++) {
                JSONObject jsonObject1 = values.getJSONObject(i);
                mapOne = new HashMap<String, Object>();
                mapOne.put("DeptCode", jsonObject1.getString("DeptCode"));
                mapOne.put("DeptName", jsonObject1.getString("DeptName"));
                dataListTwo = new ArrayList<HashMap<String, Object>>();
                JSONArray wardList = jsonObject1.getJSONArray("WardList");
                for (int j = 0; j < wardList.length(); j++) {
                    JSONObject jsonObject2 = wardList.getJSONObject(j);
                    maptwo = new HashMap<String, Object>();
                    maptwo.put("DeptCode", jsonObject2.getString("DeptCode"));
                    maptwo.put("DeptName", jsonObject2.getString("DeptName"));
                    dataListThree = new ArrayList<HashMap<String, Object>>();
                    JSONArray wardListTwo = jsonObject2.getJSONArray("WardList");
                    for (int k = 0; k < wardListTwo.length(); k++) {
                        JSONObject jsonObject3 = wardListTwo.getJSONObject(k);
//                        wardIn2formation.setDeptCode(jsonObject2.getString("DeptCode"));
//                        wardIn2formation.setDeptName(jsonObject2.getString("DeptName"));
//                        wardIn2formation.setWardName(jsonObject3.getString("DeptName"));
//                        wardIn2formation.setWardCode(jsonObject3.getString("DeptCode"));
                        mapthree = new HashMap<String, Object>();
                        mapthree.put("DeptCode", jsonObject3.getString("DeptCode"));
                        mapthree.put("DeptName", jsonObject3.getString("DeptName"));
                        dataListThree.add(mapthree);
                    }
                    maptwo.put("WardList", dataListThree);
                    dataListTwo.add(maptwo);


                }
                mapOne.put("WardList", dataListTwo);

                dataList.add(mapOne);
            }
            return dataList;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }


}
