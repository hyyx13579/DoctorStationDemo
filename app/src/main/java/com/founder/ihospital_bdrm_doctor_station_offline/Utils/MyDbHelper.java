package com.founder.ihospital_bdrm_doctor_station_offline.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangqun on 16/7/30.
 */
public class MyDbHelper extends SQLiteOpenHelper {

    private static final String CREATE_INFO = "create table if not exists patientInfo("
            + "patientName varchar(50) primary key,"
            + "deptCode varchar(50),patientInfo varchar(65532))";
    private SQLiteDatabase db;

    private static final String DB_NAME = "patientInfo.db";

    private static final int DB_VERSION = 2;

    private static final String TABLE_NAME = "patientInfo";

    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CREATE_INFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(String deptCode, String patientName, String patientInfo) {
        ContentValues values = new ContentValues();
        values.put("deptCode", deptCode);
        values.put("patientName", patientName);
        values.put("patientInfo", patientInfo);
        db = getWritableDatabase();
        int insert = (int) db.insert(TABLE_NAME, null, values);
        if (insert != -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(String deptCode, String patientName, String patientInfo) {
        ContentValues values = new ContentValues();
        values.put("deptCode", deptCode);
        values.put("patientName", patientName);
        values.put("patientInfo", patientInfo);
        db = getWritableDatabase();
        int update = db.update(TABLE_NAME, values, "patientName=?", new String[]{patientName});
        if (update >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete( String patientName) {
        db = getWritableDatabase();
        int delete = db.delete(TABLE_NAME, "patientName=?", new String[]{patientName});
        if (delete >= 1) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor query() {
        db = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToNext() == true) {
            return cursor;
        } else {
            return null;
        }
    }

    public List<DCPatientHROfDepartment.ValuesBean> getUserList(String deptCode) {
        Cursor cursor = query();
        Gson gson = new Gson();
        if (cursor != null && cursor.moveToFirst()) {
            List<DCPatientHROfDepartment.ValuesBean> list = new ArrayList<>();
            do {
//                String user_name = cursor.getString(cursor.getColumnIndex("user_name"));
//                String nick = cursor.getString(cursor.getColumnIndex("nick"));
//                String img_path = cursor.getString(cursor.getColumnIndex("img_path"));
//                EaseUser user = new EaseUser(user_name);
//                user.setNick(nick);
//                user.setAvatar(img_path);
//                list.add(user);
                String code = cursor.getString(cursor.getColumnIndex("deptCode"));
                String patientInfo = cursor.getString(cursor.getColumnIndex("patientInfo"));
                if (deptCode.equals(code)) {
                    DCPatientHROfDepartment.ValuesBean valuesBean = gson.fromJson(patientInfo, DCPatientHROfDepartment.ValuesBean.class);
                    list.add(valuesBean);
                }
            } while (cursor.moveToNext());
            return list;
        } else {
            return null;
        }
    }

    public boolean insertUserInfo(String deptCode, String patientName, String patientInfo) {
        boolean dex = false;
        boolean isInsert = false;
        List<DCPatientHROfDepartment.ValuesBean> userList = getUserList(deptCode);
        if (userList == null) {
            insert(deptCode, patientName, patientInfo);
        } else {
            for (DCPatientHROfDepartment.ValuesBean user : userList) {
                if (user.getName().equals(patientName)) {
                    dex = update(deptCode, patientName, patientInfo);
                    isInsert = true;
                }
            }
            if (!isInsert) {
                dex = insert(deptCode, patientName, patientInfo);
            }
        }
        return dex;
    }
}
