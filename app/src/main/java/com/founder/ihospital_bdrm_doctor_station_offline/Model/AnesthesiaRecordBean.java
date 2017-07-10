package com.founder.ihospital_bdrm_doctor_station_offline.Model;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bin_li on 16/5/5.
 */
public class AnesthesiaRecordBean {
    /**
     * Status : 1
     * Values : {"20160427001222":"24942493","20160422093710":"24836287"}
     */

    private int Status;

    /**
     * 20160427001222 : 24942493
     * 20160422093710 : 24836287
     */
    private List<ValuesBean> valuesLsit;


    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public List<ValuesBean> getValusList() {
        return valuesLsit;
    }

    public List<ValuesBean> transferJson2Bean(String jsonStr) {
        valuesLsit = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(getString(jsonStr));
            Iterator it = jsonObject.keys();
            while (it.hasNext()) {
                ValuesBean bean = new ValuesBean();
                bean.dataTime = (String) it.next();
                bean.documentID = (String) jsonObject.get(bean.dataTime);
                valuesLsit.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return valuesLsit;
    }


    private static String getString(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            return jsonObject.get("Values").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static class ValuesBean {
        public String dataTime;
        public String documentID;
    }

    //{"Status":1,"Values":{"2016-04-27 00:12:22":"24942493","2016-04-22 09:37:10":"24836287"}}
}
