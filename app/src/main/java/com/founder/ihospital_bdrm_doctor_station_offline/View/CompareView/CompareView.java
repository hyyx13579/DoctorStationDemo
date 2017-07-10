package com.founder.ihospital_bdrm_doctor_station_offline.View.CompareView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.List;


/**
 * Created by bin_li on 16/5/10.
 * 对比分析的自定义view
 */
public class CompareView extends ViewGroup {
    private static final String RESULT_NORMAL = "正常";
    private static final String RESULT_BIGGER = "高";
    private static final String RESULT_SMALLER = "低";
    private Context mContext;
    private Scroller mScroller;
    private int mMaxHorizontalScroll;
    private GestureDetector mGestureDetector;
    private ComPareViewManager mCompareViewManager;

    private Paint mPaint;
    private static final int LINE_SIZE_1 = 1;
    private static final int LINE_SIZE_3 = 4;
    private int mTextColor;
    private int mBaseLineColor;
    private int mNormalPointColor;
    private int mUpPointColor;
    private int mDownPointColor;
    private int mTextSize = 40;
    private int mItemCount;

    private List<CompareBean> mCompareList;
    private double maxValue;

    private void init() {
        mTextColor = Color.parseColor("#505050");
        mBaseLineColor = Color.parseColor("#b4b4b4");
        mNormalPointColor = Color.parseColor("#6FCAB3");
        mUpPointColor = Color.parseColor("#F07D7A");
        mDownPointColor = Color.parseColor("#60AFDE");
        mTextSize = getResources().getDimensionPixelOffset(R.dimen.compare_text_size);
        mScroller = new Scroller(mContext);
        mScroller.setFriction(0.2f);
        mGestureDetector = new GestureDetector(mContext, new OnGestureListener());
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(mTextSize);
        mPaint.setStrokeWidth(LINE_SIZE_1);
        mCompareViewManager = new ComPareViewManager(this);
    }

    public CompareView(Context context) {
        super(context);
        this.mContext = context;
        setWillNotDraw(false);
        init();
    }

    public CompareView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        setWillNotDraw(false);
        init();
    }

    public CompareView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setWillNotDraw(false);
        init();
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    public void setData(List<CompareBean> list) {
        this.mCompareList = list;
        this.mItemCount = list.size();
        maxValue = mCompareViewManager.getMaxValue(mCompareList);
        postInvalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mItemCount == 0)
            return;
        mCompareViewManager.calcViewDimen(mPaint, mItemCount);
        Rect drawRect = new Rect();
        drawLine(canvas, drawRect);
        drawBottom(canvas, drawRect);
        drawPoint(canvas, drawRect);
    }

    private void drawLine(Canvas canvas, Rect rect) {
        rect.left = getScrollX();
        rect.top = 0;
        rect.right = rect.left + getWidth();
        rect.bottom = getHeight();
        canvas.save();
        canvas.clipRect(rect);

        Rect drawRect = new Rect();
        drawRect.left = getScrollX();
        drawRect.top = 0;
        drawRect.right = drawRect.left + getWidth();
        drawRect.bottom = mCompareViewManager.getTopViewHeight();
        mPaint.setColor(mBaseLineColor);
        mPaint.setStrokeWidth(LINE_SIZE_1);
        canvas.drawLine(drawRect.left + 2, drawRect.top, drawRect.left + 2, drawRect.bottom, mPaint);
        canvas.drawLine(drawRect.left, drawRect.bottom, drawRect.right, drawRect.bottom, mPaint);
        canvas.restore();
    }

    private void drawBottom(Canvas canvas, Rect rect) {
        rect.left = getScrollX();
        rect.top = 0;
        rect.right = rect.left + getWidth();
        rect.bottom = getHeight();
        canvas.save();
        canvas.clipRect(rect);
        //draw bottom vertical
        mPaint.setColor(mBaseLineColor);
        mPaint.setStrokeWidth(LINE_SIZE_1);
        for (int i = 0; i < mCompareList.size(); i++) {
            canvas.drawLine(i * mCompareViewManager.getItemWidth() + mCompareViewManager.getItemWidth() / 2, mCompareViewManager.getBottomVerticalLineTopY(), i * mCompareViewManager.getItemWidth() + mCompareViewManager.getItemWidth() / 2, mCompareViewManager.getBottomVerticalLineBottomY(), mPaint);
        }
        //draw bottom text
        mPaint.setColor(mTextColor);
        for (int i = 0; i < mCompareList.size(); i++) {
            canvas.drawText(mCompareList.get(i).date, i * mCompareViewManager.getItemWidth(), getHeight(), mPaint);
        }
        canvas.restore();
    }

    private void drawPoint(Canvas canvas, Rect rect) {
        rect.left = getScrollX();
        rect.top = 0;
        rect.right = rect.left + getWidth();
        rect.bottom = getHeight();
        canvas.save();
        canvas.clipRect(rect);
        for (int i = 0; i < mCompareList.size(); i++) {
            CompareBean bean = mCompareList.get(i);
            Point point = mCompareViewManager.getPointPos(i, maxValue, Double.parseDouble(bean.value));
            Point textPoint = mCompareViewManager.getTextPos(point, mPaint, bean.value + "");
            int radius = mCompareViewManager.getPointRadius();
            mPaint.setColor(mTextColor);
            canvas.drawText(bean.value, textPoint.x, textPoint.y, mPaint);
            if (i != 0) {
                mPaint.setColor(mNormalPointColor);
                mPaint.setStrokeWidth(LINE_SIZE_3);
                Point lastPoint = mCompareViewManager.getPointPos(i - 1, maxValue, Double.parseDouble(mCompareList.get(i - 1).value));
                canvas.drawLine(lastPoint.x + 2 * radius, lastPoint.y, point.x - 2 * radius, point.y, mPaint);
            }
            if (TextUtils.equals(RESULT_BIGGER, bean.result))
                mPaint.setColor(mUpPointColor);
            else if (TextUtils.equals(RESULT_SMALLER, bean.result))
                mPaint.setColor(mDownPointColor);
            else
                mPaint.setColor(mNormalPointColor);
            //外圆
            canvas.drawCircle(point.x, point.y, radius * 2, mPaint);
            //内圆
            mPaint.setColor(Color.parseColor("#ffffff"));
            canvas.drawCircle(point.x, point.y, radius, mPaint);
        }
        canvas.restore();
    }


    public void reDraw() {
        invalidate();
        requestLayout();
    }

    /**********************
     * 手势区域分割线
     ********************/

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateMaxHorizontalScroll();
    }


    private void calculateMaxHorizontalScroll() {
        //TODO
        if (mItemCount == 0) {
            mMaxHorizontalScroll = 0;
            return;
        }
        mMaxHorizontalScroll = (int) ((mPaint.measureText(ComPareViewManager.mMeasureStr) + mCompareViewManager.getBottomTextMarginLeft()) * mItemCount - getWidth());
        if (mMaxHorizontalScroll < 0)
            mMaxHorizontalScroll = 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    private class OnGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapUp(MotionEvent e) {

            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            int dx = (int) distanceX;
            int x = getScrollX();
            if (x + dx < 0) {
                dx = 0 - x;
            }
            if (mMaxHorizontalScroll < 0) {
                dx = 0;
            } else if (x + dx > mMaxHorizontalScroll) {
                dx = mMaxHorizontalScroll - x;
            }
            if (Math.abs(distanceX) > Math.abs(distanceY)) {

            }

            scrollBy(dx, 0);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float vX, float vY) {

            mScroller.fling(getScrollX(), 0, -(int) vX, 0, 0, mMaxHorizontalScroll, 0, 0);
            reDraw();
            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            if (!mScroller.isFinished()) {
                mScroller.forceFinished(true);
                return true;
            }
            return true;
        }

    }
}
