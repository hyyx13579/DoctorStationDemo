package com.founder.ihospital_bdrm_doctor_station_offline.mediaUtils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.founder.ihospital_bdrm_doctor_station_offline.Activity.AddPatientNoteBookEvent;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.RadioItem;
import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by hyyx on 2017/1/10.
 */

public class MediaUtils {

    private AnimationDrawable animator;
    private MediaRecorder mediaRecorder;
    private File vFile;

    public void startRadio(ImageView imgRadio, Context context) {
        mediaRecorder = new MediaRecorder();
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (animator == null) {
            start_record();
            animator = (AnimationDrawable) imgRadio.getDrawable();
            animator.start();
            Log.i("radio", "start: ");
        } else if (!animator.isRunning()) {
            Toast.makeText(context, "请选择确定或取消", Toast.LENGTH_SHORT).show();
        }

    }

    public void stopRadio() {
        if (animator.isRunning()) {
            stop_record();
            Log.i("radio", "stop: ");
            animator.stop();
        }
    }

    public void sureRadio() {
        RadioItem item = new RadioItem();
        if (vFile != null && vFile.exists()) {
            item.sourcePath = vFile.getPath();
        }

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
                String path = vFile.getPath();
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
