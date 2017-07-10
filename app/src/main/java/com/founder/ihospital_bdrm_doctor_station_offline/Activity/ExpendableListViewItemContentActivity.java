package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.R;


/**
 * Created by hyyx
 * 移动查房模块-电子病例功能-二层展示界面(web展示)
 */


public class ExpendableListViewItemContentActivity extends BaseActivity {

    private WebView webView;
    private TextView tv_title;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expendable_list_view__item_content);
        init();
    }

    private void init() {
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        tv_title = ((TextView) findViewById(R.id.titleName_expandable_info));
        String groupKey = intent.getStringExtra("groupKey");
        tv_title.setText(groupKey);
        img_back = ((ImageView) findViewById(R.id.back_expandable_info));
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        webView = ((WebView) findViewById(R.id.expandablelistview_item_web));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        /**
         *  LayoutAlgorithm是一个枚举，用来控制html的布局，总共有三种类型：
         NORMAL：正常显示，没有渲染变化。
         SINGLE_COLUMN：把所有内容放到WebView组件等宽的一列中。
         NARROW_COLUMNS：可能的话，使所有列的宽度不超过屏幕宽度。
         *
         * */
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        //webView.loadUrl(content);
        //打开本地的html文件
        webView.loadUrl("file:///android_asset/GetMedicalRecordsOfPatientHR/100196025_34130-1_20140306113409_1.html");


    }
}
