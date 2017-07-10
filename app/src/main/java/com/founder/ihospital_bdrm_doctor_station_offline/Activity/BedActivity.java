package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.AllocationPatientBed;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.BedInfo;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DealWardformation;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.IntentPatientList;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.WardIn2formation;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;
import com.founder.ihospital_bdrm_doctor_station_offline.zxing.android.CaptureActivity;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by 呼延
 * 床位管理模块主页
 */

public class BedActivity extends BaseActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_SCAN = 0x0000;
    private ImageView back_btn;
    private LinearLayout wardSelectBtn;
    private TextView joinTimeBtn;
    private TextView leaveTimeBtn;
    private LinearLayout sickroomSelectBtn;
    private LinearLayout bedNoBtn;
    private Button btn;
    private LinearLayout patientSelectBtn;
    private TextView wardSelectText;
    private TextView patientSelectText;
    private TextView sickroomSelectText;
    private TextView bedNoText;
    private Calendar calendar;
    private SpannableStringBuilder ssb;
    private String doctor_id;
    private WardIn2formation wardIn2formation_data;
    private String startTime;
    private String endTime;
    public static final String TAG = BedActivity.class.getSimpleName();
    private TextView name;
    private TextView age;
    private TextView style;
    private TextView money;
    private TextView time;
    private IntentPatientList.ValuesBean patitentSelectActivity_lv_item_data;
    private ArrayList<DealWardformation> onSelectData;
    private DealWardformation sickRoomSelectActivity_lv_item_data;
    private BedInfo.ValuesBean valuesBean_bedInfo;
    private TextView bedNo_text;
    private TextView bedStyle;
    private TextView monitor;
    private TextView bedPrice;
    private TextView window;

    private ImageView mScannerLy;
    private int i;

    private String deptName = "";
    private String wardName = "";
    private String comeWardName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed);
        initView();

    }

    private void initView() {
        Intent intent = getIntent();
        doctor_id = intent.getStringExtra("doctor_id");
        back_btn = ((ImageView) findViewById(R.id.back_bed));
        back_btn.setOnClickListener(this);
        wardSelectBtn = ((LinearLayout) findViewById(R.id.ward_select_bed));
        wardSelectBtn.setOnClickListener(this);
        joinTimeBtn = ((TextView) findViewById(R.id.join_hosptial_bed));
        joinTimeBtn.setOnClickListener(this);
        leaveTimeBtn = ((TextView) findViewById(R.id.leave_hosptial_bed));
        leaveTimeBtn.setOnClickListener(this);
        patientSelectBtn = ((LinearLayout) findViewById(R.id.patient_bed));
        patientSelectBtn.setOnClickListener(this);

        sickroomSelectBtn = ((LinearLayout) findViewById(R.id.sickroom_bed));
        sickroomSelectBtn.setOnClickListener(this);

        bedNoBtn = ((LinearLayout) findViewById(R.id.bedNo_bed));
        bedNoBtn.setOnClickListener(this);
        btn = ((Button) findViewById(R.id.btn_bed));
        btn.setOnClickListener(this);
        mScannerLy = (ImageView) findViewById(R.id.scanner_entrance_ly);
        mScannerLy.setOnClickListener(this);

        wardSelectText = ((TextView) findViewById(R.id.ward_select_tv_bed));
        sickroomSelectText = ((TextView) findViewById(R.id.sickroom_tv_bed));
        calendar = Calendar.getInstance();

        initPatientCard();
        initBedCard();


    }

    private void initBedCard() {

        bedNo_text = ((TextView) findViewById(R.id.bedNo_textview_bed));
        bedStyle = ((TextView) findViewById(R.id.bedStyle_textview_bed));
        monitor = ((TextView) findViewById(R.id.monitor_textview_bed));
        bedPrice = ((TextView) findViewById(R.id.bedPrice_textview_bed));
        window = ((TextView) findViewById(R.id.window_textview_bed));


    }

    private void initPatientCard() {
        name = ((TextView) findViewById(R.id.name_patitent_select));
        age = ((TextView) findViewById(R.id.age_patitent_select));
        style = ((TextView) findViewById(R.id.style_patitent_select));
        money = ((TextView) findViewById(R.id.money_patitent_select));
        time = ((TextView) findViewById(R.id.time_patitent_select));

    }

    @Override
    public void onClick(View view) {

        Intent intent = null;


        switch (view.getId()) {
            case R.id.back_bed:
                finish();
                break;
            //选择病区
            case R.id.ward_select_bed:
                intent = new Intent(getApplication(), WardSelect_BedActivity.class);
                intent.putExtra("doctor_id", doctor_id);
                startActivityForResult(intent, 0);
                break;

            //选择病人
            case R.id.patient_bed:
                Log.e(TAG, startTime + endTime);
                if (wardIn2formation_data != null && startTime != null && endTime != null) {
                    long result = Url.strToDate(endTime).getTime() - Url.strToDate(startTime).getTime();
                    Log.e(TAG, "result" + result);
                    if (result > 0) {
                        intent = new Intent(getApplication(), PatitentSelectActivity.class);
                        intent.putExtra("doctor_id", doctor_id);
                        intent.putExtra("DeptCode", wardIn2formation_data.getDeptCode());
                        intent.putExtra("StartDate", startTime);
                        intent.putExtra("EndDate", endTime);
                        startActivityForResult(intent, 1);
                    } else {
                        Url.isToast(getApplicationContext(), "出院时间必须大于入院时间,请重新选择");
                    }


                } else {
                    Url.isToast(getApplicationContext(), "未选择科室或选择时间有误");

                }


                break;

//选择入住的病房
            case R.id.sickroom_bed:
                if (wardIn2formation_data != null) {
                    intent = new Intent(getApplication(), SickRoomSelectActivity.class);
                    intent.putParcelableArrayListExtra("onSelectData", onSelectData);
                    startActivityForResult(intent, 2);


                } else {
                    Url.isToast(getApplicationContext(), "未选择科室");
                }


                break;

//选择床位
            case R.id.bedNo_bed:
                if (sickRoomSelectActivity_lv_item_data != null) {
                    intent = new Intent(getApplication(), BedSelectActivity.class);
                    intent.putExtra("doctor_id", doctor_id);
                    intent.putExtra("sickRoomSelectActivity_lv_item_data", sickRoomSelectActivity_lv_item_data);
                    startActivityForResult(intent, 3);
                } else {
                    Url.isToast(getApplicationContext(), "未选择病房");
                }


                break;


            case R.id.join_hosptial_bed:
                setDateDialog(true);
                break;
            case R.id.leave_hosptial_bed:
                setDateDialog(false);
                break;

            case R.id.btn_bed:
                setDateDialogTwo();

                break;
            case R.id.scanner_entrance_ly:

                Intent scannerIntent = new Intent(BedActivity.this,
                        CaptureActivity.class);
                startActivityForResult(scannerIntent, REQUEST_CODE_SCAN);
                break;

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent intent = null;
        switch (resultCode) {
            case 100:

                wardIn2formation_data = (WardIn2formation) data.getSerializableExtra("WardSelect_BedActivity_sticklv_item_data");
                wardSelectText.setText(wardIn2formation_data.getDeptName() + "--" + wardIn2formation_data.getWardName());

                onSelectData = data.getParcelableArrayListExtra("onSelectData");

                if (!deptName.equals(wardIn2formation_data.getDeptName()) || !wardName.equals(wardIn2formation_data.getWardName())) {
                    name.setText("");
                    age.setText("");
                    style.setText("");
                    money.setText("");
                    time.setText("");
                    deptName = wardIn2formation_data.getDeptName();
                    wardName = wardIn2formation_data.getWardName();
                }


                break;
            case 99:

                patitentSelectActivity_lv_item_data = ((IntentPatientList.ValuesBean) data.getSerializableExtra("PatitentSelectActivity_lv_item_data"));
                name.setText(patitentSelectActivity_lv_item_data.getName());
                age.setText(patitentSelectActivity_lv_item_data.getAge());
                style.setText(patitentSelectActivity_lv_item_data.getOutDiagnosis());
                money.setText("" + patitentSelectActivity_lv_item_data.getDeposit());
                time.setText(patitentSelectActivity_lv_item_data.getInpatientPermitDate());
                break;
            case 98:
                sickRoomSelectActivity_lv_item_data = (DealWardformation) data.getParcelableExtra("SickRoomSelectActivity_lv_item_data");
                sickroomSelectText.setText(sickRoomSelectActivity_lv_item_data.getWardName());

                if (!comeWardName.equals(sickRoomSelectActivity_lv_item_data.getWardName())) {
                    bedNo_text.setText("");
                    bedStyle.setText("");
                    monitor.setText("");
                    bedPrice.setText("");
                    window.setText("");
                    comeWardName = sickRoomSelectActivity_lv_item_data.getWardName();
                }


                break;

            case 97:
                valuesBean_bedInfo = ((BedInfo.ValuesBean) data.getSerializableExtra("BedInfo.ValuesBean"));
                bedNo_text.setText(valuesBean_bedInfo.getBedNO());
                bedStyle.setText(valuesBean_bedInfo.getBedTypeName());
                monitor.setText(valuesBean_bedInfo.getMonitor());
                bedPrice.setText(valuesBean_bedInfo.getBedPrice());
                window.setText(valuesBean_bedInfo.getWindow());


        }


    }

    public void setDateDialog(final boolean flag) {
//通过自定义控件AlertDialog实现
        AlertDialog.Builder builder = new AlertDialog.Builder(BedActivity.this);
        View view = (LinearLayout) getLayoutInflater().inflate(R.layout.date_dialog, null);
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        //设置日期简略显示 否则详细显示 包括:星期周
        // datePicker.setCalendarViewShown(false);
        //初始化当前日期
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), null);

        //设置date布局
        builder.setView(view);
        if (flag) {
            builder.setTitle("设置入院时间");
        } else {

            builder.setTitle("设置出院时间");
        }


        builder.setPositiveButton("确 定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //日期格式
                StringBuffer sb = new StringBuffer();
                sb.append(String.format("%d-%02d-%02d",
                        datePicker.getYear(),
                        datePicker.getMonth() + 1,
                        datePicker.getDayOfMonth()));


                if (flag) {
                    ssb = new SpannableStringBuilder(sb + " 入院");
                    RelativeSizeSpan span = new RelativeSizeSpan(1.5f);
                    ssb.setSpan(span, 11, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    joinTimeBtn.setText(ssb);
                    startTime = sb.toString();
                } else {
                    ssb = new SpannableStringBuilder(sb + " 出院");
                    RelativeSizeSpan span = new RelativeSizeSpan(1.5f);
                    ssb.setSpan(span, 11, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    leaveTimeBtn.setText(ssb);
                    endTime = sb.toString();
                }


                dialog.cancel();
            }
        });
        builder.setNegativeButton("取  消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();


    }

    public void setDateDialogTwo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BedActivity.this);
        View view = (LinearLayout) getLayoutInflater().inflate(R.layout.date_dialog, null);
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), null);

        //设置date布局
        builder.setView(view);
        builder.setTitle("设置入住时间");


        builder.setPositiveButton("确 定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //日期格式
                StringBuffer sb = new StringBuffer();
                sb.append(String.format("%d-%02d-%02d",
                        datePicker.getYear(),
                        datePicker.getMonth() + 1,
                        datePicker.getDayOfMonth()));
                if (valuesBean_bedInfo != null && patitentSelectActivity_lv_item_data != null && !TextUtils.isEmpty(name.getText()) && !TextUtils.isEmpty(bedNo_text.getText())) {
                    // savePatientInfor(sb);
                    Url.isToast(getApplicationContext(), "分配成功");
                    finish();
                } else {
                    Url.isToast(getApplicationContext(), "信息未选择完全");
                }


                dialog.cancel();
            }
        });
        builder.setNegativeButton("取  消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    private void savePatientInfor(StringBuffer adissTime) {

        String url = Url.BedManagementServerTwo + "AllocationPatientBed";
        RequestParams requestParams = new RequestParams(url);

        requestParams.addBodyParameter("AdissTime", adissTime.toString() + " 00:00:00");


        requestParams.addBodyParameter("BedNO", valuesBean_bedInfo.getBedNO());
        requestParams.addBodyParameter("Name", valuesBean_bedInfo.getName());
        requestParams.addBodyParameter("DeptCode", valuesBean_bedInfo.getDeptCode());
        requestParams.addBodyParameter("WardCode", valuesBean_bedInfo.getWardCode());


        requestParams.addBodyParameter("OutpatientID", patitentSelectActivity_lv_item_data.getOutpatientID());
        requestParams.addBodyParameter("PatientID", patitentSelectActivity_lv_item_data.getPatientID());
        requestParams.addBodyParameter("Series", patitentSelectActivity_lv_item_data.getSeries());


        requestParams.addBodyParameter("DoctorNo", doctor_id);


        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e(TAG, "onSuccess---" + result);
                        Gson gson = new Gson();
                        AllocationPatientBed allocationPatientBed = gson.fromJson(result, AllocationPatientBed.class);
                        String values = allocationPatientBed.getValues();
                        int status = allocationPatientBed.getStatus();
                        Url.isToast(getApplicationContext(), values + status);
                        if (status == 1) {
                            finish();
                        }


                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e(TAG, "onError----" + ex);
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                }

        );


    }


}
