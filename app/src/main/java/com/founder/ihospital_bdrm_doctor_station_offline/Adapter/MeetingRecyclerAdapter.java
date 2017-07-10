package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCMeetingUndone;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.List;

/**
 * Created by zhangqun on 16/4/26.
 */
public class MeetingRecyclerAdapter extends RecyclerView.Adapter<MeetingRecyclerAdapter.ViewHolder> {
    private Context context;

    public void setListener(MeetingRecyclerListener listener) {
        this.listener = listener;
    }

    private MeetingRecyclerListener listener;

    public MeetingRecyclerAdapter(List<DCMeetingUndone.ValuesBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    private List<DCMeetingUndone.ValuesBean> data;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.frag_meeting_recycler_adapter, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.priorityTv.setText(data.get(position).getPriority());
        holder.wardNameTv.setText(data.get(position).getWardName());
        holder.bedNoTv.setText(data.get(position).getBedNO());
        holder.patientName.setText(data.get(position).getName());
        holder.requestDocName.setText(data.get(position).getConRequestDocName());
        holder.requestDate.setText(data.get(position).getConRequestDate());
    }

    public static interface MeetingRecyclerListener {
        public void OnItemClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView priorityTv;
        private final TextView wardNameTv;
        private final TextView bedNoTv;
        private final TextView patientName;
        private final TextView requestDocName;
        private final TextView requestDate;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            priorityTv = ((TextView) itemView.findViewById(R.id.meetingPriority));
            wardNameTv = ((TextView) itemView.findViewById(R.id.meetingWardName));
            bedNoTv = ((TextView) itemView.findViewById(R.id.meetingBedNO));
            patientName = ((TextView) itemView.findViewById(R.id.meetingName));
            requestDocName = ((TextView) itemView.findViewById(R.id.meetingConRequestDocName));
            requestDate = ((TextView) itemView.findViewById(R.id.meetingConRequestDate));
        }

        @Override
        public void onClick(View v) {
            listener.OnItemClick(itemView, getAdapterPosition());
        }
    }

    public void refreshData() {
        super.notifyDataSetChanged();
    }

    public void updateData(List<DCMeetingUndone.ValuesBean> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }


}
