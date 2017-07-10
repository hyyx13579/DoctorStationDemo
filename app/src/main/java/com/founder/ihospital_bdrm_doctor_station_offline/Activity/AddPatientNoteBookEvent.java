package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.ImagePublishAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCDoctorMemorandum;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCPatientHROfDepartment;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.ImageItem;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.RadioItem;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.VideoItem;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.CustomConstants;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.founder.ihospital_bdrm_doctor_station_offline.Utils.IntentConstants.EXTRA_CAN_ADD_IMAGE_SIZE;
import static com.founder.ihospital_bdrm_doctor_station_offline.Utils.IntentConstants.EXTRA_CURRENT_IMG_POSITION;
import static com.founder.ihospital_bdrm_doctor_station_offline.Utils.IntentConstants.EXTRA_IMAGE_LIST;


/**
 * Created by 张群
 * 患者备忘功能添加患者备忘主界面
 */


public class AddPatientNoteBookEvent extends BaseActivity implements View.OnClickListener {
    private static final String TAG = AddPatientNoteBookEvent.class.getSimpleName();
    private ArrayList<Button> buttons;
    private EditText noteTitle;
    private EditText noteContent;
    private GridView noteProView;
    private ImageView addNoteImage;
    private ImageView addNoteVideo;
    private ImageView addNoteRadio;
    public static List<Object> mDataList = new ArrayList<Object>();
    public List<Object> cacheList = new ArrayList<Object>();
    private ImagePublishAdapter adapter;
    List<DCDoctorMemorandum.ValuesBean.SubMemorandumListBean> listBeen = new ArrayList<DCDoctorMemorandum.ValuesBean.SubMemorandumListBean>();
    private Button proAll;
    private Button proImg;
    private Button proVideo;
    private Button proRudio;
    private TextView saveData;
    private DCDoctorMemorandum.ValuesBean memorandum;
    private ImageView backImg;
    private int color;
    private DCPatientHROfDepartment.ValuesBean data;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient_note_book_event);
        Log.i("intent_return", "onCreate: ");
        initData();
        initView();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        noteProView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(AddPatientNoteBookEvent.this,
                        ImageZoomActivity.class);
                intent.putExtra(EXTRA_IMAGE_LIST,
                        (Serializable) mDataList);
                intent.putExtra(EXTRA_CURRENT_IMG_POSITION, position);
                startActivity(intent);
            }
        });



    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        List<ImageItem> incomingDataList = (List<ImageItem>) intent
                .getSerializableExtra(EXTRA_IMAGE_LIST);
        if (incomingDataList != null) {
            mDataList.addAll(incomingDataList);
            adapter.notifyDataSetChanged();
            cacheList.clear();
            cacheList.addAll(mDataList);
            initBtnText();

            for (int i = 0; i < mDataList.size(); i++) {
                DCDoctorMemorandum.ValuesBean.SubMemorandumListBean item = new DCDoctorMemorandum.ValuesBean.SubMemorandumListBean();
                if (mDataList.get(i) instanceof ImageItem) {
                    item.setFileType("0");
                    item.setFileName(((ImageItem) mDataList.get(i)).imageId);
                    item.setFilePath(((ImageItem) mDataList.get(i)).sourcePath);
                    item.setFileBytes(new String(getBytesFromFile(((ImageItem) mDataList.get(i)).sourcePath)));


                } else if (mDataList.get(i) instanceof VideoItem) {
                    item.setFileType("2");
                    item.setFileName(((VideoItem) mDataList.get(i)).videoId);
                    item.setFilePath(((VideoItem) mDataList.get(i)).sourcePath);
                    item.setFileBytes(new String(getBytesFromFile(((VideoItem) mDataList.get(i)).sourcePath)));
                } else if (mDataList.get(i) instanceof RadioItem) {
                    item.setFileType("1");
                    item.setFileName(((RadioItem) mDataList.get(i)).sourcePath);
                    item.setFilePath(((RadioItem) mDataList.get(i)).sourcePath);
                    item.setFileBytes(new String(getBytesFromFile(((RadioItem) mDataList.get(i)).sourcePath)));
                }
                listBeen.add(item);
            }
            memorandum.setSubMemorandumList(listBeen);
        }

    }

    public static byte[] getBytesFromFile(String path) {
        File f = new File(path);
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1) {
                out.write(b, 0, n);
            }
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }

    public void initBtnText() {
        proAll.setText("全部[" + chooseBtn(0).size() + "]");
        proImg.setText("图片[" + chooseBtn(1).size() + "]");
        proVideo.setText("视频[" + chooseBtn(2).size() + "]");
        proRudio.setText("录音[" + chooseBtn(3).size() + "]");
    }

    private void initView() {

        Intent intent = getIntent();
        data = ((DCPatientHROfDepartment.ValuesBean) intent.getSerializableExtra("data"));


        backImg = ((ImageView) findViewById(R.id.addPatientNoteActivityBack));
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        noteTitle = ((EditText) findViewById(R.id.addPatientNoteBookEventTitle));
        noteContent = ((EditText) findViewById(R.id.addPatientNoteBookEventContent));
        noteProView = ((GridView) findViewById(R.id.addPatientNoteBookEventProView));
        addNoteImage = ((ImageView) findViewById(R.id.addPatientNoteBookEventImage));
        addNoteVideo = ((ImageView) findViewById(R.id.addPatientNoteBookEventVideo));
        addNoteRadio = (ImageView) findViewById(R.id.addPatientNoteBookEventRadio);
        proAll = ((Button) findViewById(R.id.addImageProAll));
        proImg = ((Button) findViewById(R.id.addImageproImg));
        proVideo = ((Button) findViewById(R.id.addImageProVideo));
        proRudio = ((Button) findViewById(R.id.addImageProRudio));
        buttons = new ArrayList<>();
        buttons.add(proAll);
        buttons.add(proImg);
        buttons.add(proRudio);
        buttons.add(proVideo);
        saveData = ((TextView) findViewById(R.id.addPatientNoteBookSaveData));
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                RequestParams params = new RequestParams("http://172.27.1.58:9001/api/DoctorServer/SaveDoctorMemorandum");

                if (!TextUtils.isEmpty(memorandum.getTitle()) &&
                        !TextUtils.isEmpty(memorandum.getContent())
                        ) {
                    memorandum.setGUID(UUID.randomUUID().toString());
                    memorandum.setPatientID(data.getID());
                    memorandum.setVisitID(data.getSubID());
                    memorandum.setCreateDoctorName(data.getDoctorInCharge());

                    //获取当前时间
                    Date date = new Date();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String time = format.format(date);


                    memorandum.setCreateTime(time);
                    Log.e(TAG, time + "----" + UUID.randomUUID().toString() + "----" + data.getID() + "---" + data.getSubID() + "---" + data.getDoctorInCharge());
                    params.setBodyContent(gson.toJson(memorandum));

                    x.http().post(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Log.e(TAG, result);
                            Toast.makeText(getBaseContext(), "保存成功", Toast.LENGTH_SHORT).show();


                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {

                        }

                        @Override
                        public void onCancelled(CancelledException cex) {

                        }

                        @Override
                        public void onFinished() {

                        }
                    });
                } else {
                    Toast.makeText(getBaseContext(), "信息不全,不能上传", Toast.LENGTH_SHORT).show();
                }
            }
        });
        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                memorandum.setTitle(s.toString());
            }
        });
        noteContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                memorandum.setContent(s.toString());
            }
        });
        adapter = new ImagePublishAdapter(this, mDataList);
        noteProView.setAdapter(adapter);
        color = getResources().getColor(R.color.titlecolor);
        proAll.setBackgroundColor(color);
        proAll.setTextColor(Color.WHITE);
        proAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataList.clear();
                Log.i("choose", "onClick: ");
                mDataList.addAll(chooseBtn(0));
                adapter.notifyDataSetChanged();
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setBackgroundColor(Color.WHITE);
                    buttons.get(i).setTextColor(Color.GRAY);
                }
                proAll.setBackgroundColor(color);
                proAll.setTextColor(Color.WHITE);
            }
        });
        proImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataList.clear();
                mDataList.addAll(chooseBtn(1));
                adapter.notifyDataSetChanged();
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setBackgroundColor(Color.WHITE);
                    buttons.get(i).setTextColor(Color.GRAY);
                }
                proImg.setBackgroundColor(color);
                proImg.setTextColor(Color.WHITE);
            }
        });
        proVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataList.clear();
                mDataList.addAll(chooseBtn(2));
                adapter.notifyDataSetChanged();
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setBackgroundColor(Color.WHITE);
                    buttons.get(i).setTextColor(Color.GRAY);
                }
                proVideo.setBackgroundColor(color);
                proVideo.setTextColor(Color.WHITE);
            }
        });
        proRudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataList.clear();
                mDataList.addAll(chooseBtn(3));
                adapter.notifyDataSetChanged();
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setBackgroundColor(Color.WHITE);
                    buttons.get(i).setTextColor(Color.GRAY);
                }
                proRudio.setBackgroundColor(color);
                proRudio.setTextColor(Color.WHITE);
            }
        });
    }

    protected void onPause() {
        super.onPause();
        saveTempToPref();
    }

    protected void onResume() {
        super.onResume();
        cacheList.clear();
        cacheList.addAll(mDataList);
        initBtnText();
        notifyDataChanged(); //当在ImageZoomActivity中删除图片时，返回这里需要刷新
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveTempToPref();
    }

    private void saveTempToPref() {
        SharedPreferences sp = getSharedPreferences(
                CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
        String prefStr = JSON.toJSONString(mDataList);
        sp.edit().putString(CustomConstants.PREF_TEMP_IMAGES, prefStr).commit();

    }

    private void removeTempFromPref() {
        SharedPreferences sp = getSharedPreferences(
                CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
        sp.edit().remove(CustomConstants.PREF_TEMP_IMAGES).commit();
    }

    private int getDataSize() {
        return mDataList == null ? 0 : mDataList.size();
    }

    private int getAvailableSize() {
        int availSize = CustomConstants.MAX_IMAGE_SIZE - mDataList.size();
        if (availSize >= 0) {
            return availSize;
        }
        return 0;
    }

    public String getString(String s) {
        String path = null;
        if (s == null) return "";
        for (int i = s.length() - 1; i > 0; i++) {
            s.charAt(i);
        }
        return path;
    }

    private void getTempFromPref() {
        SharedPreferences sp = getSharedPreferences(
                CustomConstants.APPLICATION_NAME, MODE_PRIVATE);
        String prefStr = sp.getString(CustomConstants.PREF_TEMP_IMAGES, null);
        if (!TextUtils.isEmpty(prefStr)) {
            List<ImageItem> tempImages = JSON.parseArray(prefStr,
                    ImageItem.class);
        }
    }

    private void initData() {
        memorandum = new DCDoctorMemorandum.ValuesBean();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addPatientNoteBookEventImage:
                new PopupWindows(AddPatientNoteBookEvent.this, noteProView, "image");
                break;
            case R.id.addPatientNoteBookEventVideo:
                new PopupWindows(AddPatientNoteBookEvent.this, noteProView, "vadio");
                break;
            case R.id.addPatientNoteBookEventRadio:
                new PopupRadio(AddPatientNoteBookEvent.this, noteProView);
                break;
        }
    }

    public List<Object> chooseBtn(int type) {
        List<Object> list = new ArrayList<Object>();
        switch (type) {
            case 0:
                list.addAll(cacheList);
                break;
            case 1:
                for (int i = 0; i < cacheList.size(); i++) {
                    if (cacheList.get(i) instanceof ImageItem) {
                        list.add(cacheList.get(i));
                    }
                }
                break;
            case 2:
                for (int i = 0; i < cacheList.size(); i++) {
                    if (cacheList.get(i) instanceof VideoItem) {
                        list.add(cacheList.get(i));
                    }
                }
                break;
            case 3:
                for (int i = 0; i < cacheList.size(); i++) {
                    if (cacheList.get(i) instanceof RadioItem) {
                        list.add(cacheList.get(i));
                    }
                }
                break;
        }
        return list;
    }


    public class PopupRadio extends PopupWindow {
        private final MediaRecorder mediaRecorder;
        private File vFile;
        private AnimationDrawable animator;



        public PopupRadio(Context mContext, View parent) {
            View view = View.inflate(mContext, R.layout.popup_radio, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.fade_ins));
            RelativeLayout radio_popup = (RelativeLayout) view
                    .findViewById(R.id.radio_popup);
            radio_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.push_bottom_in_2));

            setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            update();
            mediaRecorder = new MediaRecorder();
            try {
                mediaRecorder.prepare();
            } catch (IOException e) {
            }
            Button positiveBtn = ((Button) view.findViewById(R.id.radioBtnPositive));
            Button cancel = ((Button) view.findViewById(R.id.radioBtnCancel));
            final ImageView radio = (ImageView) view.findViewById(R.id.radioStartAndEnd);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            radio.setOnTouchListener(new View.OnTouchListener() {


                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            if (animator == null) {
                                start_record();
                                animator = (AnimationDrawable) radio.getDrawable();
                                animator.start();
                                Log.i("radio", "start: ");
                            } else if (!animator.isRunning()) {
                                Toast.makeText(AddPatientNoteBookEvent.this, "请选择确定或取消", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case MotionEvent.ACTION_MOVE:
                            break;
                        case MotionEvent.ACTION_UP:
                            if (animator.isRunning()) {
                                stop_record();
                                Log.i("radio", "stop: ");
                                animator.stop();
                            }
                            break;
                    }
                    return true;
                }
            });

            positiveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    RadioItem item = new RadioItem();
                    if (vFile != null && vFile.exists()) {
                        item.sourcePath = vFile.getPath();
                        mDataList.add(item);
                        cacheList.add(item);
                        initBtnText();
                        adapter.notifyDataSetChanged();
                        dismiss();
                    }

                }
            });
        }


        public void start_record() {
            if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            } else {
                try {
                    // 创建保存录音的音频文件
                    vFile = new File(Environment.getExternalStorageDirectory()
                            + "/myRecorder/", String.valueOf(System.currentTimeMillis())
                            + ".amr");
                    // 如果目录不存在
                    if (!vFile.exists()) {
                        File vDirPath = vFile.getParentFile();
                        vDirPath.mkdirs();
                    } else {
                        if (vFile.exists()) {
                            vFile.delete();
                        }
                    }
                    path = vFile.getPath();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mediaRecorder.setOutputFile(path);
                    mediaRecorder.prepare();
                    // 开始录音
                    mediaRecorder.start();
                } catch (Exception e) {

                }
            }
        }

        public void stop_record() {
            if (vFile != null && vFile.exists()) {
                //ses_id = ses_id + 1;
                // 停止录音
                try {

                    mediaRecorder.stop();
                    // 释放资源
                    mediaRecorder.release();
                } catch (Exception e) {

                }


            }
        }
    }

    public class PopupWindows extends PopupWindow {
        private String path = "";
        private static final int TAKE_PICTURE = 0x000000;

        public PopupWindows(Context mContext, View parent, final String str) {

            View view = View.inflate(mContext, R.layout.item_popupwindow, null);
            view.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.fade_ins));
            LinearLayout ll_popup = (LinearLayout) view
                    .findViewById(R.id.ll_popup);
            ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext,
                    R.anim.push_bottom_in_2));

            setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            setFocusable(true);
            setOutsideTouchable(true);
            setContentView(view);
            showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            update();

            Button bt1 = (Button) view
                    .findViewById(R.id.item_popupwindows_camera);
            Button bt2 = (Button) view
                    .findViewById(R.id.item_popupwindows_Photo);
            Button bt3 = (Button) view
                    .findViewById(R.id.item_popupwindows_cancel);
            if (str.equals("image")) {
                bt1.setText("拍照");
                bt2.setText("从相册中选取");
                bt3.setText("取消");
            } else if (str.equals("vadio")) {
                bt1.setText("小视频");
                bt2.setText("从本地选取");
            }
            bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (str.equals("image")) {
                        takePhoto();
                    } else if (str.equals("vadio")) {
                        //take vadio
                        takeVadio();
                    }
                    dismiss();
                }
            });
            bt2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (str.equals("image")) {
                        //to show take photo activity
                        Intent intent = new Intent(AddPatientNoteBookEvent.this,
                                ImageBucketChooseActivity.class);
                        intent.putExtra(EXTRA_CAN_ADD_IMAGE_SIZE, getAvailableSize());
                        startActivity(intent);
                    } else {
                        //视频跳转界面
                        Intent intent = new Intent(AddPatientNoteBookEvent.this,
                                VideoBucketChooseActivity.class);
                        intent.putExtra(EXTRA_CAN_ADD_IMAGE_SIZE, getAvailableSize());
                        startActivity(intent);
                    }
                    dismiss();
                }
            });
            bt3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    dismiss();
                }
            });


        }

        private void takeVadio() {
            Intent openVadioIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            File vFile = new File(Environment.getExternalStorageDirectory()
                    + "/DCIM/Camera/", String.valueOf(System.currentTimeMillis())
                    + ".avi");
            if (!vFile.exists()) {
                File vDirPath = vFile.getParentFile();
                vDirPath.mkdirs();
            } else {
                if (vFile.exists()) {
                    vFile.delete();
                }
            }
            path = vFile.getPath();
            Uri cameraUri = Uri.fromFile(vFile);
            openVadioIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            openVadioIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
            startActivityForResult(openVadioIntent, TAKE_VADIO);
        }
    }

    public void takePhoto() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        File vFile = new File(Environment.getExternalStorageDirectory()
                + "/myimage/", String.valueOf(System.currentTimeMillis())
                + ".jpg");
        if (!vFile.exists()) {
            File vDirPath = vFile.getParentFile();
            vDirPath.mkdirs();
        } else {
            if (vFile.exists()) {
                vFile.delete();
            }
        }
        path = vFile.getPath();
        Uri cameraUri = Uri.fromFile(vFile);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        startActivityForResult(openCameraIntent, TAKE_PICTURE);
    }

    private static final int TAKE_PICTURE = 0x000000;
    private static final int TAKE_VADIO = 0x000200;
    private String path = "";

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PICTURE:
                if (mDataList.size() < CustomConstants.MAX_IMAGE_SIZE
                        && resultCode == RESULT_OK && !TextUtils.isEmpty(path)) {
                    ImageItem item = new ImageItem();
                    item.sourcePath = path;
                    mDataList.add(item);
                    cacheList.add(item);
                    initBtnText();
                }
                break;
            case TAKE_VADIO:
                if (resultCode == RESULT_OK) {
                    VideoItem item = new VideoItem();
                    item.sourcePath = data.getData().getPath();
                    Log.i("video", item.sourcePath);
                    mDataList.add(item);
                    cacheList.add(item);
                    initBtnText();
                    break;
                }
        }
    }

    private void notifyDataChanged() {
        adapter.notifyDataSetChanged();
    }

}
