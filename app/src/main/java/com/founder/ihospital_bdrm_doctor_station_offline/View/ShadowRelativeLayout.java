package com.founder.ihospital_bdrm_doctor_station_offline.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by bin_li on 16/5/24.
 * 带shadow的背景view
 */
public class ShadowRelativeLayout extends RelativeLayout {
    public ShadowRelativeLayout(Context context) {
        super(context);
        init();
    }

    public ShadowRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShadowRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mPaint;

    void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        setWillNotDraw(false);
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#FFFFFF"));


    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setShadowLayer(2, 0, 2, Color.parseColor("#2d000000"));
        canvas.drawRect(getLeft(), getTop(), getRight(), getBottom() - 4, mPaint);
        super.onDraw(canvas);
    }
}
