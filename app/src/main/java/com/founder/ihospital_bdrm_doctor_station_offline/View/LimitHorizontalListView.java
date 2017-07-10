package com.founder.ihospital_bdrm_doctor_station_offline.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by bin_li on 16/5/25.
 */
public class LimitHorizontalListView extends ListView {


    private GestureDetector mDetector;
    private Context mContext;

    public LimitHorizontalListView(Context context) {
        this(context, null);
    }

    public LimitHorizontalListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LimitHorizontalListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        mDetector = new GestureDetector(mContext, onGestureListener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mDetector.onTouchEvent(ev);
    }

    GestureDetector.OnGestureListener onGestureListener = new GestureDetector.OnGestureListener() {


        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            if (motionEvent == null || motionEvent1 == null)
                return true;
            float evX = motionEvent.getX();
            float evY = motionEvent.getY();
            float ev2X = motionEvent1.getY();
            float ev2Y = motionEvent1.getY();
            if (Math.abs(ev2X - evX) > Math.abs(ev2Y - evY))
                return false;
            else
                return true;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }
    };
}
