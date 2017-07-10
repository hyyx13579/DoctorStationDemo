package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.MyGirdViewAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCDepartmentOfUser;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ReadPlistFile;
import com.founder.ihospital_bdrm_doctor_station_offline.View.MyGridView;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 呼延 on 2016/3/25.
 * 为病区概况的加载GridView的碎片
 */
public class WardGeneralizationFragment_Frag_one extends BaseFragment implements AdapterView.OnItemClickListener {


    private MyGridView gridView;
    private MyGirdViewAdapter gridAdapter;
    public static final String TAG = WardGeneralizationFragment_Frag_one.class.getSimpleName();
    private String userName;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.main_frame_frag_one, container, false);

        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    private void initView() {
        Log.e(TAG, "initView");
        gridView = ((MyGridView) layout.findViewById(R.id.mygrid));
        gridAdapter = new MyGirdViewAdapter(null, getContext());
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(this);
       LoadDataOffLine();
    }

    private void LoadData() {
        Bundle arguments = getArguments();
        userName = arguments.getString("id");
        Log.e(TAG, userName );
        String url = Url.DoctorServer + "GetValidDepartmentOfUser";
        RequestParams requestParams = new RequestParams(url);
        requestParams.addBodyParameter("userID", userName);
        x.http().request(HttpMethod.POST, requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.e(TAG, "onSuccess-------");
                        Gson gson = new Gson();
                        DCDepartmentOfUser dcDepartmentOfUser = gson.fromJson(result, DCDepartmentOfUser.class);
                        List<DCDepartmentOfUser.ValuesBean> values = dcDepartmentOfUser.getValues();
                        gridAdapter.addRes(values);

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e(TAG, "onError-------");
                        LoadDataOffLine();
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

    /**
     * 离线请求方法
     */
    private void LoadDataOffLine() {
        Bundle arguments = getArguments();
        String userName = arguments.getString("id");
        Gson gson = new Gson();
        DCDepartmentOfUser dcDepartmentOfUser = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetValidDepartmentOfUser", "GetValidDepartmentOfUser"), DCDepartmentOfUser.class);
        List<DCDepartmentOfUser.ValuesBean> values = dcDepartmentOfUser.getValues();
        gridAdapter.addRes(values);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        DCDepartmentOfUser.ValuesBean itemAtPosition = (DCDepartmentOfUser.ValuesBean) parent.getItemAtPosition(position);
        //需要传递到具体fragment的数据
        String name = itemAtPosition.getName();
        try {
            int myPatientInHos = itemAtPosition.getStaInfo().getMyPatientInHos();
            int patientInHos = itemAtPosition.getStaInfo().getPatientInHos();
            List<Integer> careLevelAndStateCount = itemAtPosition.getStaInfo().getCareLevelAndStateCount();
            switchFragment(name, myPatientInHos, patientInHos, careLevelAndStateCount);

        } catch (NullPointerException ex) {

        }


    }

    private void switchFragment(String name, int myPatientInHos, int patientInHos, List<Integer> careLevelAndStateCount) {
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < careLevelAndStateCount.size(); i++) {
            data.add(careLevelAndStateCount.get(i));
        }
        Bundle bundle = new Bundle();
        bundle.putString("myPatientInHos", "" + myPatientInHos);
        bundle.putString("patientInHos", "" + patientInHos);
        bundle.putString("name", name);
        bundle.putString("userName", userName);
        bundle.putIntegerArrayList("careLevelAndStateCount", data);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        WardGeneralizationFragment_Frag_two mainFragment_frag_two = new WardGeneralizationFragment_Frag_two();
        mainFragment_frag_two.setArguments(bundle);
        fragmentTransaction.replace(R.id.main_fragment_frame, mainFragment_frag_two)
                .commit();


    }


}
