package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.EventMsg;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.ReadPlistFile;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangqun on 16/4/25.
 */
public class MeetingUndoneFragment extends BaseFragment {

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


    public Context mContext = getActivity();

    public void initData(View view) {
        String s = SPUtils.get(getContext(), Contant.ISMEETING, "").toString();
        if (!"true".equals(s)) {
            Gson gson = new Gson();
            DCMeetingUndone data = null;
            data = gson.fromJson(ReadPlistFile.onReadPatientInformation(getContext(), "GetConsultationList", "1"), DCMeetingUndone.class);
            values.addAll(data.getValues());
            adapter.refreshData();
        }


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
                intent.putExtra("searchType", "1");
                intent.putExtra("val", values.get(position));
                startActivityForResult(intent, 200);
            }
        });
    }


    public View getLayoutView() {
        return View.inflate(getActivity(), R.layout.fragment_meeting_undone, null);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if (resultCode == 100) {
                String s = SPUtils.get(getContext(), Contant.ISMEETING, "").toString();
                if (!"true".equals(s)) {
                    List<DCMeetingUndone.ValuesBean> values = new ArrayList<>();
                    adapter.updateData(values);
                    adapter.refreshData();
                    SPUtils.put(getContext(), Contant.ISMEETING, "true");
                    EventBus.getDefault().post(new EventMsg("Refresh"));
                }

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
