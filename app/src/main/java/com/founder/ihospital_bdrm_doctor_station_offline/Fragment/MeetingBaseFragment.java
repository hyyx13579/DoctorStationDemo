package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.founder.ihospital_bdrm_doctor_station_offline.Activity.MeetingItemActivity;
import com.founder.ihospital_bdrm_doctor_station_offline.Adapter.MeetingRecyclerAdapter;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCMeetingUndone;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Contant;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ReadPlistFile;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangqun on 16/4/25.
 */
public abstract class MeetingBaseFragment extends Fragment {
    private RecyclerView meetingRecyclerView;
    private List<DCMeetingUndone.ValuesBean> values;
    private MeetingRecyclerAdapter adapter;
    public String searchType;
    private static boolean isMeeting = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayoutView();
        initView(view);
        initData(view);
        return view;
    }

    public abstract String setSearchType();

    public abstract DCMeetingUndone setData();

    public Context mContext = getActivity();

    public void initData(View view) {
        dataOffLine();

    }
//            String url = "http://172.27.1.58:9001/api/ConsultationManagement/GetConsultationList";
//            //String url = "";
//            RequestParams requestParams = new RequestParams(url);
//            requestParams.addBodyParameter("doctorID", "01095");
//            requestParams.addBodyParameter("searchType", setSearchType());
//            requestParams.addBodyParameter("pageSize", "24");
//            requestParams.addBodyParameter("pageNo", "1");
//            x.http().post(requestParams, new Callback.CommonCallback<String>() {
//                @Override
//                public void onSuccess(String result) {
//                    Gson gson = new Gson();
//                    Log.i("aabbcc", "onSuccess: " + result);
//                    DCMeetingUndone data = gson.fromJson(result, DCMeetingUndone.class);
//                    values.addAll(data.getValues());
//                    adapter.refreshData();
//                }
//
//                @Override
//                public void onError(Throwable ex, boolean isOnCallback) {
//                    //Toast.makeText(mContext,"网络请求失败",Toast.LENGTH_SHORT).show();
//                    Log.i("aabbcc", "onError: " + ex);
////                    String str = "";
////                    switch (setSearchType()){
////                        case "1":;
////                            str = "{\"Status\":1,\"Values\":[{\"TotalCount\":\"\",\"PageSize\":\"\",\"PageNo\":\"\",\"ConsultationID\":\"2963\",\"ConsultationCode\":\"102965\",\"PatientID\":\"001521383000\",\"Series\":\"5\",\"InpatientNo\":\"4232682\",\"Name\":\"李亚珍\",\"WardCode\":\"10C病区\",\"WardName\":\"10C病区\",\"DeptCode\":\"120000000\",\"DeptName\":\"消化内科\",\"BedNO\":\"001\",\"ConRequestDate\":\"2016-05-23 10:32:54.0\",\"ConRequestDoc\":\"984\",\"ConRequestDocName\":\"本院医生\",\"SignDocCode\":\"\",\"SignDoc\":\"\",\"SignDate\":\"\",\"SignStatus\":\"\",\"SignType\":\"\",\"SignNotes\":\"\",\"ReservPlace\":\"10C病区001床\",\"Priority\":\"0\",\"ConsultationType\":\"1\",\"PatientDesc\":\"敬悉病史：\\r\\n\\r\\n\\r\\n\\r\\n\\r\\n           敬邀！\",\"Place\":\"\",\"Purpose\":\"\",\"ConsultationOpinion\":\"\"}]}";
////                            break;
////                        case "2":;
////                            str = "{\"Status\":1,\"Values\":[{\"TotalCount\":\"\",\"PageSize\":\"\",\"PageNo\":\"\",\"ConsultationID\":\"2965\",\"ConsultationCode\":\"102967\",\"PatientID\":\"001538452600\",\"Series\":\"1\",\"InpatientNo\":\"4237400\",\"Name\":\"牛瑞芳\",\"WardCode\":\"10C病区\",\"WardName\":\"10C病区\",\"DeptCode\":\"120000000\",\"DeptName\":\"消化内科\",\"BedNO\":\"005\",\"ConRequestDate\":\"2016-05-23 10:34:43.0\",\"ConRequestDoc\":\"00000\",\"ConRequestDocName\":\"本院医生\",\"SignDocCode\":\"01095\",\"SignDoc\":\"崔淑芳\",\"SignDate\":\"2016-05-23 16:25:33.0\",\"SignStatus\":\"null\",\"SignType\":\"2\",\"SignNotes\":\"离床原因...\",\"ReservPlace\":\"10C病区005床\",\"Priority\":\"0\",\"ConsultationType\":\"1\",\"PatientDesc\":\"敬悉病史：\\r\\n\\r\\n\\r\\n\\r\\n\\r\\n           敬邀！\",\"Place\":\"\",\"Purpose\":\"\",\"ConsultationOpinion\":\"\"},{\"TotalCount\":\"\",\"PageSize\":\"\",\"PageNo\":\"\",\"ConsultationID\":\"2964\",\"ConsultationCode\":\"102966\",\"PatientID\":\"001539207600\",\"Series\":\"1\",\"InpatientNo\":\"4237819\",\"Name\":\"苏苏\",\"WardCode\":\"10C病区\",\"WardName\":\"10C病区\",\"DeptCode\":\"120000000\",\"DeptName\":\"消化内科\",\"BedNO\":\"002\",\"ConRequestDate\":\"2016-05-23 10:33:58.0\",\"ConRequestDoc\":\"00000\",\"ConRequestDocName\":\"本院医生\",\"SignDocCode\":\"01095\",\"SignDoc\":\"崔淑芳\",\"SignDate\":\"2016-05-23 16:25:48.0\",\"SignStatus\":\"null\",\"SignType\":\"2\",\"SignNotes\":\"离床原因...\",\"ReservPlace\":\"10C病区002床\",\"Priority\":\"0\",\"ConsultationType\":\"1\",\"PatientDesc\":\"新增申请理由1\\r\\n测试一下修改\",\"Place\":\"\",\"Purpose\":\"\",\"ConsultationOpinion\":\"\"}]}";
////                            break;
////                        case "3":;
////                            str = "{\"Status\":1,\"Values\":[]}";
////                            break;
////                    }
////                    Gson gson = new Gson();
////
////                      Log.i("aabbcc", "onSuccess: "+str);
////
////                    DCMeetingUndone data = gson.fromJson(str, DCMeetingUndone.class);
////                    values.addAll(data.getValues());
////                    adapter.refreshData();
//
//
//                }
//
//                @Override
//                public void onCancelled(CancelledException cex) {
//
//                }
//
//                @Override
//                public void onFinished() {
//
//                }
//            });
//        }


    private void dataOffLine() {
        Gson gson = new Gson();
        DCMeetingUndone data = null;

        switch (setSearchType()) {

            case "1":
                data = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetConsultationList", "1"), DCMeetingUndone.class);
                break;
            case "2":
                data = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetConsultationList", "1"), DCMeetingUndone.class);
                break;
            case "3":
                data = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetConsultationList", "3"), DCMeetingUndone.class);
                break;


        }

        if (data != null) {
            values.addAll(data.getValues());
        }

        adapter.refreshData();

    }


    public void initView(View view) {
        values = new ArrayList<DCMeetingUndone.ValuesBean>();
        meetingRecyclerView = ((RecyclerView) view.findViewById(R.id.meetingRecyclerView));
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayout.VERTICAL);
        meetingRecyclerView.setLayoutManager(manager);
        int spacingInPixels = 30;
        meetingRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        adapter = new MeetingRecyclerAdapter(values, getActivity());
        meetingRecyclerView.setAdapter(adapter);
        adapter.setListener(new MeetingRecyclerAdapter.MeetingRecyclerListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), MeetingItemActivity.class);
                intent.putExtra("searchType", setSearchType());
                intent.putExtra("val", values.get(position));
                startActivityForResult(intent, 200);
            }
        });
    }

    ;

    public View getLayoutView() {
        return View.inflate(getActivity(), R.layout.fragment_meeting_undone, null);
    }

    ;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if (resultCode == 100) {
                List<DCMeetingUndone.ValuesBean> values = new ArrayList<>();
                adapter.updateData(values);
                adapter.refreshData();
                SPUtils.put(getContext(), Contant.ISMEETING, "true");
            }

            Log.i("finish", "refreshData: ");
        }
    }


    class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        /**
         * @param outRect 偏移量
         * @param view
         * @param parent
         * @param state
         */
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            if (parent.getChildPosition(view) == 0)
                outRect.top = space;
        }
    }
}
