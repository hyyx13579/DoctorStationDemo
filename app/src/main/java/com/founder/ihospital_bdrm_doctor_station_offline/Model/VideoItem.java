package com.founder.ihospital_bdrm_doctor_station_offline.Model;

import java.io.Serializable;

/**
 * Created by zhangqun on 16/4/20.
 */
public class VideoItem implements Serializable{
    private static final long serialVersionUID = -7188270558443739436L;
    public String videoId;
    public String thumbnailPath;
    public String sourcePath;
    public boolean isSelected = false;
}
