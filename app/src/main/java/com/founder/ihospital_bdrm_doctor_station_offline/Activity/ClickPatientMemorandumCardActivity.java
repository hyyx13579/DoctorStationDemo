package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCDoctorMemorandum;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.ImageItem;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.RadioItem;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.VideoItem;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.ArrayList;
import java.util.List;

public class ClickPatientMemorandumCardActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ClickPatientMemorandumCardActivity.class.getSimpleName();
    private TextView tvTitle;
    private TextView tvContent;
    private Button btnProAll;
    private Button btnProImage;
    private Button btnProVideo;
    private Button btnProRadio;
    private ArrayList<Button> buttons;
    private int color;
    public List<Object> cacheList = new ArrayList<Object>();
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_patient_memorandum_card);
        initView();
        initBtn();
    }

    private void initBtn() {
        btnProAll.setText("全部[" + chooseBtn(0).size() + "]");
        btnProImage.setText("图片[" + chooseBtn(1).size() + "]");
        btnProVideo.setText("视频[" + chooseBtn(2).size() + "]");
        btnProRadio.setText("录音[" + chooseBtn(3).size() + "]");
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

    private void initView() {
        Intent intent = getIntent();
        DCDoctorMemorandum.ValuesBean itemData = (DCDoctorMemorandum.ValuesBean) intent.getSerializableExtra("itemData");
        tvTitle = ((TextView) findViewById(R.id.title_click_patient_memorandun_card));
        tvTitle.setText(itemData.getTitle());
        tvContent = ((TextView) findViewById(R.id.content_click_patient_memorandun_card));
        tvContent.setText(itemData.getContent());

        ivBack = ((ImageView) findViewById(R.id.back_click_patient_memorandun_card));
        ivBack.setOnClickListener(this);


        btnProAll = ((Button) findViewById(R.id.addImageProAll_click_patient_memorandun_card));
        btnProImage = ((Button) findViewById(R.id.addImageProImg_click_patient_memorandun_card));
        btnProVideo = ((Button) findViewById(R.id.addImageProViedo_click_patient_memorandun_card_click_patient_memorandun_card));
        btnProRadio = ((Button) findViewById(R.id.addImageProRadio_click_patient_memorandun_card_click_patient_memorandun_card));


        btnProAll.setOnClickListener(this);
        btnProImage.setOnClickListener(this);
        btnProRadio.setOnClickListener(this);
        btnProVideo.setOnClickListener(this);


        buttons = new ArrayList<>();
        buttons.add(btnProAll);
        buttons.add(btnProImage);
        buttons.add(btnProVideo);
        buttons.add(btnProRadio);

        color = getResources().getColor(R.color.titlecolor);
        btnProAll.setBackgroundColor(color);
        btnProAll.setTextColor(Color.WHITE);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addImageProAll_click_patient_memorandun_card:

                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setBackgroundColor(Color.WHITE);
                    buttons.get(i).setTextColor(Color.GRAY);
                }
                btnProAll.setBackgroundColor(color);
                btnProAll.setTextColor(Color.WHITE);

                break;
            case R.id.addImageProImg_click_patient_memorandun_card:
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setBackgroundColor(Color.WHITE);
                    buttons.get(i).setTextColor(Color.GRAY);
                }
                btnProImage.setBackgroundColor(color);
                btnProImage.setTextColor(Color.WHITE);

                break;
            case R.id.addImageProViedo_click_patient_memorandun_card_click_patient_memorandun_card:
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setBackgroundColor(Color.WHITE);
                    buttons.get(i).setTextColor(Color.GRAY);
                }
                btnProVideo.setBackgroundColor(color);
                btnProVideo.setTextColor(Color.WHITE);
                break;
            case R.id.addImageProRadio_click_patient_memorandun_card_click_patient_memorandun_card:
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setBackgroundColor(Color.WHITE);
                    buttons.get(i).setTextColor(Color.GRAY);
                }
                btnProRadio.setBackgroundColor(color);
                btnProRadio.setTextColor(Color.WHITE);
                break;

            case R.id.back_click_patient_memorandun_card:
                finish();
                break;


        }


    }
}

