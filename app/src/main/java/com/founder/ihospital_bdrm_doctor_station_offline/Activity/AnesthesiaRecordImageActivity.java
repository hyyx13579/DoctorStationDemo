package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.AnesthesiaRecordImageBean;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ImageUtil;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ReadPlistFile;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bin_li on 16/5/5.
 * <p/>
 * 麻醉记录功能展示照片主页
 */
public class AnesthesiaRecordImageActivity extends BaseActivity implements View.OnClickListener {
    public static final String EXTRA_DOCUMENT_ID = "extra_document_id";
    public static final String EXTRA_PATIENT_NAME = "extra_patient_name";
    private List<Bitmap> mBitmapList;
    private ImageView mImageView;
    private ImageView mLeftImg;
    private ImageView mRightImg;
    private RelativeLayout mLoadingRy;
    private ImageView mBackImg;
    private TextView mCountTxt;

    private int mCurrentShowImgID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anesthesia_record_image);
        initView();
        requestData();
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.anesthsia_img);
        mLeftImg = (ImageView) findViewById(R.id.anesthesia_record_img_to_left);
        mRightImg = (ImageView) findViewById(R.id.anesthesia_record_img_to_right);
        mLoadingRy = (RelativeLayout) findViewById(R.id.anesthesia_loading_relativelayout);
        mBackImg = (ImageView) findViewById(R.id.anesthesia_record_img_back_img);
        mCountTxt = (TextView) findViewById(R.id.anesthesia_record_img_count_txt);
        mLoadingRy.setClickable(true);
        mLeftImg.setOnClickListener(this);
        mRightImg.setOnClickListener(this);
        mBackImg.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.anesthesia_record_img_to_left:
                if (mCurrentShowImgID == 0)
                    return;
                mCurrentShowImgID -= 1;
                setViewData();
                break;
            case R.id.anesthesia_record_img_to_right:
                if (mCurrentShowImgID + 1 == mBitmapList.size())
                    return;
                mCurrentShowImgID += 1;
                setViewData();
                break;
            case R.id.anesthesia_record_img_back_img:
                finish();
                break;
        }
    }

    private void requestData() {
        String documentID = getIntent().getStringExtra(EXTRA_DOCUMENT_ID);
        String url = Url.DoctorServer + "GetAnesthesiaRecord?" + "documentID=" + documentID;
        RequestParams request = new RequestParams(url);
        x.http().get(request, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                mCurrentShowImgID = 0;
                parseResult(result);
                setViewData();
                mLoadingRy.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                mCurrentShowImgID = 0;
                parseResult(ReadPlistFile.onReadPatientInformation(getBaseContext(),"GetAnesthesiaRecord","24898063"));
                setViewData();
                mLoadingRy.setVisibility(View.GONE);


            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }





    private void parseResult(String result) {
        try {
            Gson gson = new Gson();
            AnesthesiaRecordImageBean bean = gson.fromJson(result, AnesthesiaRecordImageBean.class);
            mBitmapList = new ArrayList<Bitmap>();
            for (int i = 0; i < bean.getValues().size(); i++) {
                mBitmapList.add(ImageUtil.getBitmapFromBase64String(bean.getValues().get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setViewData() {
        if (mBitmapList != null && mBitmapList.size() > 0)
            mImageView.setImageBitmap(mBitmapList.get(mCurrentShowImgID));
        mCountTxt.setText(mCurrentShowImgID + 1 + "/" + mBitmapList.size());
        if (mCurrentShowImgID == 0) {
            mLeftImg.setClickable(false);
        } else {
            mLeftImg.setClickable(true);
        }
        if (mCurrentShowImgID + 1 == mBitmapList.size()) {
            mRightImg.setClickable(false);
        } else {
            mRightImg.setClickable(true);
        }

    }
}
