package com.founder.ihospital_bdrm_doctor_station_offline.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by hyyx on 16/8/25.
 */
public class NoClickRelativeLayout extends RelativeLayout {
    public NoClickRelativeLayout(Context context) {
        super(context);
    }

    public NoClickRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoClickRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        return true;

    }
}
