package com.founder.ihospital_bdrm_doctor_station_offline.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.SickRoomListViewAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DealWardformation;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.ArrayList;

/**
 * Created by hyyx
 * <p/>
 * 床位管理模块-选择入住病房功能
 */


public class SickRoomSelectActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ArrayList<DealWardformation> onSelectData;
    private TextView title;
    private ListView listView;

    private GoogleApiClient client;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sick_room_select);
        initView();

    }

    private void initView() {
        Intent intent = getIntent();
        onSelectData = intent.getParcelableArrayListExtra("onSelectData");
        title = ((TextView) findViewById(R.id.title_sickroom));
        title.setText(onSelectData.get(0).getDeptName());
        listView = ((ListView) findViewById(R.id.listview_sickroom));
        SickRoomListViewAdapter sickRoomListViewAdaptet = new SickRoomListViewAdapter(getBaseContext(), onSelectData);
        listView.setAdapter(sickRoomListViewAdaptet);
        listView.setOnItemClickListener(this);
        back = ((ImageView) findViewById(R.id.back_sickroom));
        back.setOnClickListener(this);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DealWardformation itemAtPosition = (DealWardformation) parent.getItemAtPosition(position);
        Intent intent = new Intent(getApplicationContext(), BedActivity.class);
        Log.e("onItemClick", itemAtPosition.getWardName());
        intent.putExtra("SickRoomSelectActivity_lv_item_data", itemAtPosition);
        setResult(98, intent);
        finish();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_sickroom:
                finish();
                break;


        }


    }
}
