package com.founder.ihospital_bdrm_doctor_station_offline.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCDoctorMemorandum;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.List;

/**
 * Created by Administrator on 2016/4/12.
 */
public class PatientNoteBookRecyclerAdapter extends RecyclerView.Adapter<PatientNoteBookRecyclerAdapter.ViewHolder>{
    private Context context;
    private List<DCDoctorMemorandum.ValuesBean> data;

    /**
     * 自定义OnRecyclerViewListener接口，用于点击事件回调
     */
    public static interface OnRecyclerViewListener{
        public void OnItemClick(View view,int position);
    }

    /**
     * 声明接口
     */
    public static OnRecyclerViewListener recyclerViewListener;

    /**
     * 添加RecyclerView的点击监听事件
     * @param listener 自定义的接口
     */
    public void setOnRecyclerViewListener(OnRecyclerViewListener listener){
        this.recyclerViewListener = listener;
    }

    /**
     *适配器的构造方法
     * @param context Activity上下文信息
     * @param data 需要展示的数据
     */
    public PatientNoteBookRecyclerAdapter(Context context,List<DCDoctorMemorandum.ValuesBean> data){
        this.context = context;
        this.data = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.patient_recyclerview_adapter,null);
        ViewHolder holer = new ViewHolder(view);
        return holer;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.title.setText(data.get(position).getTitle());
        holder.content.setText(data.get(position).getContent());
        holder.setTime.setText(data.get(position).getCreateTime());
        holder.imageNum.setText(getMediaNum(position,"0")+"");
        holder.videoNum.setText(getMediaNum(position,"2")+"");
        holder.radioNum.setText(getMediaNum(position,"1")+"");
    }
    public int getMediaNum(int position,String type){
        int mediaNum = 0;
        for (int i = 0; i < data.get(position).getSubMemorandumList().size(); i++) {
            if (data.get(position).getSubMemorandumList().get(i).getFileType().equals(type)){
                mediaNum++;
            }
        }
        return mediaNum;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public  DCDoctorMemorandum.ValuesBean getItemData(int position){
        DCDoctorMemorandum.ValuesBean valuesBean = data.get(position);
        return valuesBean;

    }

    /**
     * 在ViewHolder中，设置父控件的点击事件
     * 在点击事件中，实现接口传递
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView title;
        private final TextView content;
        private final TextView setTime;
        private TextView imageNum;
        private TextView videoNum;
        private TextView radioNum;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = ((TextView) itemView.findViewById(R.id.patientNoteBookItemTitle));
            content = ((TextView) itemView.findViewById(R.id.patientNoteBookItemContent));
            setTime = ((TextView) itemView.findViewById(R.id.patientNoteBookItemSetTime));
            imageNum = ((TextView) itemView.findViewById(R.id.patientNoteBookItemImageNum));
            videoNum = ((TextView) itemView.findViewById(R.id.patientNoteBookItemVideoNum));
            radioNum = ((TextView) itemView.findViewById(R.id.patientNoteBookItemRadioNum));
        }

        @Override
        public void onClick(View v) {
            recyclerViewListener.OnItemClick(itemView,getAdapterPosition());
        }
    }
}
