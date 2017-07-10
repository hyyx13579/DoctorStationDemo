package com.founder.ihospital_bdrm_doctor_station_offline.care_info;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCNursingRecordOfPatientHR;
import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.care_info.bean.DrawStringBean;
import com.founder.ihospital_bdrm_doctor_station_offline.care_info.util.TimeUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by bin_li on 16/5/3.
 * 自定义体征单view
 */
public class CareInfoCustomView extends ViewGroup {

    private static int TEXT_SIZE_1 = 24;
    private static int TEXT_SIZE_2 = 30;
    private static final int PAINT_WIDTH_1 = 1;
    private static final int PAINT_WIDTH_2 = 2;

    private static final int TITLE_ITEM_COUNT = 4;
    private static final int BOTTOM_ITEM_COUNT = 8;
    private static final int UNIT_VERTICAL_COUNT = 48;
    private static final int BASE_LINE_UNIT_COUNT = 28;

    private CareInfoManager mCareInfoManager;
    private Paint mPaint;
    private Rect mClipRect;


    private Context mContext;
    private Scroller mScroller;
    private int mMaxHorizontalScroll;
    private int mMaxVerticalScroll;
    private GestureDetector mGestureDetector;

    private int daySum;
    private List<String> mDataList;
    private Map<String, List<ItemListBean>> mCareInfoItemsMap;
    private String mEnterTimeInDate;
    private String mEnterTimeInHour;


    public CareInfoCustomView(Context context) {
        this(context, null);
    }

    public CareInfoCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        this.mContext = context;
        init();
    }

    public CareInfoCustomView(Context context, AttributeSet attrs,
                              int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        this.mContext = context;
        init();
    }

    private int mColor79;
    private int mColorD8;
    private int mColorTextNor;
    private int mColorPulse;
    private int mColorTemper;

    private void init() {
        mColor79 = getResources().getColor(R.color.careinfo_gray_color_79);
        mColorD8 = getResources().getColor(R.color.careinfo_gray_color_d8);
        mColorTextNor = getResources().getColor(R.color.text_normal_color);
        mColorPulse = Color.parseColor("#EE5C67");
        mColorTemper = Color.parseColor("#51A7F9");
        TEXT_SIZE_1 = getResources().getDimensionPixelSize(R.dimen.care_info_text_1);
        TEXT_SIZE_2 = getResources().getDimensionPixelSize(R.dimen.care_info_text_2);

        mScroller = new Scroller(mContext);
        mScroller.setFriction(0.2f);
        mGestureDetector = new GestureDetector(mContext, new OnGestureListener());
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//直接添加抗锯齿属性
        mCareInfoManager = new CareInfoManager(this);
    }

    public void setDataToInitCanvas(final Map<String, List<DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean>> map, final List<String> list, String firstTime) {
        this.daySum = list.size();
        this.mDataList = list;
        this.mCareInfoItemsMap = map;
        String strs[] = firstTime.split(" ");
        this.mEnterTimeInDate = strs[0];
        this.mEnterTimeInHour = strs[1];
        mCareInfoManager.initDimen(daySum);
        postInvalidate();//不需要搭配Handler
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateMaxHorizontalScroll();
        calculateMaxVerticalScroll();

    }

    private void calculateMaxHorizontalScroll() {
        this.mMaxHorizontalScroll = mCareInfoManager.getMaxWidth() - getWidth();
    }

    private void calculateMaxVerticalScroll() {
        this.mMaxVerticalScroll = mCareInfoManager.getMaxHeight() - getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (daySum == 0) {
            return;
        }
        mClipRect = new Rect();
        drawDetail(canvas);
        drawBorderLine(canvas);
        drawTopTitle(canvas);
        drawDetailTitle(canvas);
        drawTop(canvas);
        drawBottomTitle(canvas);
        drawBottom(canvas);

    }

    /**
     * 外边框的4条线
     */

    private void drawBorderLine(Canvas canvas) {
        mClipRect.left = getScrollX();
        mClipRect.top = getScrollY();
        mClipRect.right = getScrollX() + getWidth();
        mClipRect.bottom = getScrollY() + getHeight();
        canvas.save();//保存
        canvas.clipRect(mClipRect);
        mPaint.setColor(mColor79);
        mPaint.setStrokeWidth(PAINT_WIDTH_1);
        canvas.drawLine(mClipRect.left, mClipRect.top, mClipRect.left, mClipRect.bottom, mPaint);//左侧|||
        canvas.drawLine(mClipRect.right - 1, mClipRect.top, mClipRect.right - 1, mClipRect.bottom, mPaint);//右侧|||
        canvas.drawLine(mClipRect.left, mClipRect.top, mClipRect.right, mClipRect.top, mPaint);//顶部---
        canvas.drawLine(mClipRect.left, mClipRect.bottom - 1, mClipRect.right, mClipRect.bottom - 1, mPaint);//底部的---
        canvas.restore();//读取

    }

    /**
     * 画线填充标题栏(日期,住院天数,手术后天数,时间)
     */

    private void drawTopTitle(Canvas canvas) {

        mClipRect.left = getScrollX();
        mClipRect.top = getScrollY();
        mClipRect.right = mClipRect.left + mCareInfoManager.getTitleWidth();
        mClipRect.bottom = mClipRect.top + mCareInfoManager.getTitleHeight();
        canvas.save();
        canvas.clipRect(mClipRect);
        mPaint.setColor(mColor79);
        mPaint.setStrokeWidth(PAINT_WIDTH_1);
        //draw horizontal line
        List<Integer> titleY = mCareInfoManager.getTitleHorzontalLineY();
        for (int i = 0; i < titleY.size(); i++) {
            canvas.drawLine(getScrollX(), getScrollY() + titleY.get(i), getScrollX() + mCareInfoManager.getTitleWidth(), getScrollY() + titleY.get(i), mPaint);
        }
        //draw vertical line
        List<Integer> titleX = mCareInfoManager.getTopTitleVerticalX();
        for (int i = 0; i < titleX.size(); i++) {
            canvas.drawLine(titleX.get(i), mClipRect.top, titleX.get(i), getScrollY() + mCareInfoManager.getTitleHeight(), mPaint);
        }
        //draw title name
        mPaint.setColor(mColorTextNor);
        mPaint.setTextSize(TEXT_SIZE_2);
        List<DrawStringBean> nameList = mCareInfoManager.getTopTitleDrawString(mPaint);
        for (int i = 0; i < nameList.size(); i++) {
            DrawStringBean bean = nameList.get(i);
            canvas.drawText(bean.mString, bean.mPoint.x, bean.mPoint.y, mPaint);
        }

        canvas.restore();
    }

    private void drawTop(Canvas canvas) {
        mClipRect.left = getScrollX() + mCareInfoManager.getTitleWidth();
        mClipRect.top = getScrollY();
        mClipRect.right = mClipRect.left + getWidth();
        mClipRect.bottom = mClipRect.top + mCareInfoManager.getTitleHeight();
        canvas.save();
        canvas.clipRect(mClipRect);
        mPaint.setColor(mColor79);
        mPaint.setStrokeWidth(PAINT_WIDTH_1);
        //draw horizontal line
        List<Integer> titleY = mCareInfoManager.getTitleHorzontalLineY();
        for (int i = 0; i < titleY.size(); i++) {
            canvas.drawLine(mClipRect.left, getScrollY() + titleY.get(i), mClipRect.right, getScrollY() + titleY.get(i), mPaint);
        }
        //draw vertical line
        List<Integer> titleX = mCareInfoManager.getTopVerticalX();
        for (int i = 0; i < titleX.size(); i++) {
            canvas.drawLine(titleX.get(i), 0, titleX.get(i), getScrollY() + mCareInfoManager.getTitleHeight(), mPaint);
        }
        //draw time vertical line
        List<Integer> unitVerticalLineX = mCareInfoManager.getUnitVerticalLine();
        for (int i = 0; i < unitVerticalLineX.size(); i++) {
            if (i % 6 == 0) {
                mPaint.setColor(mColor79);
            } else {
                mPaint.setColor(mColorD8);
            }
            canvas.drawLine(unitVerticalLineX.get(i), getScrollY() + mCareInfoManager.getItemHeight() * 3, unitVerticalLineX.get(i), getScrollY() + mCareInfoManager.getItemHeight() * 4, mPaint);
        }

        //draw data text
        mPaint.setColor(mColorTextNor);
        mPaint.setTextSize(TEXT_SIZE_2);
        List<DrawStringBean> mDataNameText = mCareInfoManager.getTopDateDrawString(mPaint, mDataList);
        for (int i = 0; i < mDataNameText.size(); i++) {
            DrawStringBean bean = mDataNameText.get(i);
            canvas.drawText(bean.mString, bean.mPoint.x, bean.mPoint.y, mPaint);
        }
        drawHospitaledTime(canvas);
        //draw time text
        mPaint.setTextSize(TEXT_SIZE_1);
        mPaint.setColor(mColorTextNor);
        List<DrawStringBean> mTimeNameText = mCareInfoManager.getTopTimeDrawString(mPaint, daySum);
        for (int i = 0; i < mTimeNameText.size(); i++) {
            DrawStringBean bean = mTimeNameText.get(i);
            canvas.drawText(bean.mString, bean.mPoint.x, bean.mPoint.y, mPaint);
        }
        canvas.restore();
    }

    private PathEffect effect = new DashPathEffect(new float[]{getScrollX() + 4, getScrollX() + 6}, 2);

    private void drawDetail(Canvas canvas) {
        mClipRect.left = getScrollX() + mCareInfoManager.getTitleWidth();
        mClipRect.top = getScrollY() + mCareInfoManager.getTitleHeight();
        mClipRect.right = mClipRect.left + mCareInfoManager.getDetilsWidth();
        mClipRect.bottom = mClipRect.top + mCareInfoManager.getDetilsHeight();
        canvas.save();
        canvas.clipRect(mClipRect);
        //draw horizontal line
        mPaint.setColor(mColorD8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setPathEffect(effect);
        List<Integer> detailHorizontalLiens = mCareInfoManager.getDetailHorizontalLines();

        for (int i = 0; i < detailHorizontalLiens.size(); i++) {
            if ((i == 2) || (i - 2) > 0 && (i - 2) % 5 == 0) {
                mPaint.setStrokeWidth(PAINT_WIDTH_2);
                canvas.drawLine(mCareInfoManager.getTitleWidth(), detailHorizontalLiens.get(i), getScrollX() + getWidth(), detailHorizontalLiens.get(i), mPaint);
            } else {
                Path path = new Path();
                mPaint.setStrokeWidth(PAINT_WIDTH_1);
                path.moveTo(mCareInfoManager.getTitleWidth(), detailHorizontalLiens.get(i));
                path.lineTo(getScrollX() + getWidth(), detailHorizontalLiens.get(i));
                path.close();
                canvas.drawPath(path, mPaint);
            }
        }
        //draw vertical line
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(null);
        List<Integer> detailVerticalLines = mCareInfoManager.getDetailVerticalLines();
        for (int i = 0; i < detailVerticalLines.size(); i++) {
            if (i % 6 == 0) {
                mPaint.setColor(mColor79);
            } else {
                mPaint.setColor(mColorD8);
            }
            canvas.drawLine(detailVerticalLines.get(i), getScrollY() + mCareInfoManager.getTitleHeight(), detailVerticalLines.get(i), mCareInfoManager.getTitleHeight() + mCareInfoManager.getDetilsHeight(), mPaint);
        }
        drawBaseLine(canvas);
        drawPulsePoint(canvas);
        drawTemperaturePoint(canvas);
        drawEnterTimeInHour(canvas);
        canvas.restore();
    }

    private void drawDetailTitle(Canvas canvas) {
        mClipRect.left = getScrollX();
        mClipRect.top = getScrollY() + mCareInfoManager.getTitleHeight();
        mClipRect.right = mClipRect.left + mCareInfoManager.getTitleWidth();
        mClipRect.bottom = mClipRect.top + mCareInfoManager.getDetilsHeight();
        canvas.save();
        canvas.clipRect(mClipRect);
        //draw detail title vertical line
        mPaint.setColor(mColor79);
        mPaint.setStrokeWidth(PAINT_WIDTH_1);
        List<Integer> detailTitleLines = mCareInfoManager.getDetailTitleLine();
        for (int i = 0; i < detailTitleLines.size(); i++) {
            canvas.drawLine(detailTitleLines.get(i), mCareInfoManager.getTitleHeight(), detailTitleLines.get(i), mCareInfoManager.getTitleHeight() + mCareInfoManager.getDetilsHeight(), mPaint);
        }
        //draw detail title name
        mPaint.setTextSize(TEXT_SIZE_1);
        List<DrawStringBean> nameList = mCareInfoManager.getDetailsTitleDrawString(mPaint);
        for (int i = 0; i < nameList.size(); i++) {
            DrawStringBean bean = nameList.get(i);
            if (i == 0 || i == 1 || i == nameList.size() / 2 || i == nameList.size() / 2 + 1)
                mPaint.setTextSize(TEXT_SIZE_1);
            else
                mPaint.setTextSize(TEXT_SIZE_2);
            if (CareInfoManager.mPulse.equals(bean.mString)) {
                mPaint.setColor(mColorPulse);
            }
            if (CareInfoManager.mTemperature.equals(bean.mString)) {
                mPaint.setColor(mColorTemper);
            }
            canvas.drawText(bean.mString, bean.mPoint.x, bean.mPoint.y, mPaint);
        }
        canvas.restore();
    }

    private void drawBottomTitle(Canvas canvas) {
        mClipRect.left = getScrollX();
        mClipRect.top = mCareInfoManager.getTitleHeight() + mCareInfoManager.getDetilsHeight();
        mClipRect.right = mClipRect.left + mCareInfoManager.getTitleWidth();
        mClipRect.bottom = mClipRect.top + mCareInfoManager.getBottomHeight();
        canvas.save();
        canvas.clipRect(mClipRect);
        mPaint.setColor(mColor79);
        mPaint.setStrokeWidth(PAINT_WIDTH_1);
        //bottom title horizontal line
        List<Integer> bottomTitleLines = mCareInfoManager.getBottomTitleHorizontalLine();
        for (int i = 0; i < bottomTitleLines.size(); i++) {
            canvas.drawLine(getScrollX(), bottomTitleLines.get(i), mClipRect.right, bottomTitleLines.get(i), mPaint);
        }
        //bottom title vertical line
        List<Integer> verticalLine = mCareInfoManager.getBottomTitleVerticalLine();
        for (int i = 0; i < verticalLine.size(); i++) {
            canvas.drawLine(verticalLine.get(i), mClipRect.top, verticalLine.get(i), mClipRect.bottom, mPaint);
        }
        // draw title name
        mPaint.setTextSize(TEXT_SIZE_2);
        mPaint.setColor(mColorTextNor);
        List<DrawStringBean> textList = mCareInfoManager.getBottomTitleName(mPaint);
        for (int i = 0; i < textList.size(); i++) {
            DrawStringBean bean = textList.get(i);
            canvas.drawText(bean.mString, bean.mPoint.x, bean.mPoint.y, mPaint);
        }
        canvas.restore();
    }

    private void drawBottom(Canvas canvas) {
        mClipRect.left = getScrollX() + mCareInfoManager.getTitleWidth();
        mClipRect.top = mCareInfoManager.getTitleHeight() + mCareInfoManager.getDetilsHeight();
        mClipRect.right = getScrollX() + getWidth();
        mClipRect.bottom = mClipRect.top + mCareInfoManager.getBottomHeight();
        canvas.save();
        canvas.clipRect(mClipRect);
        mPaint.setColor(mColor79);
        mPaint.setStrokeWidth(PAINT_WIDTH_1);
        //horizontal line
        List<Integer> horizontalLine = mCareInfoManager.getBottomHorizontalLine();
        for (int i = 0; i < horizontalLine.size(); i++) {
            canvas.drawLine(mCareInfoManager.getTitleWidth(), horizontalLine.get(i), mClipRect.right, horizontalLine.get(i), mPaint);
        }
        //vertical line
        List<Integer> verticalLine = mCareInfoManager.getBottomVertocalLine();
        for (int i = 0; i < verticalLine.size(); i++) {
            canvas.drawLine(verticalLine.get(i), mClipRect.top, verticalLine.get(i), mClipRect.bottom, mPaint);
        }
        //vertical time line
        List<Integer> unitVerticalLineX = mCareInfoManager.getUnitVerticalLine();
        for (int i = 0; i < unitVerticalLineX.size(); i++) {
            canvas.drawLine(unitVerticalLineX.get(i), mClipRect.top, unitVerticalLineX.get(i), mClipRect.top + mCareInfoManager.getItemHeight(), mPaint);
        }
        //draw point
        drawBottomPoints(canvas);
        canvas.restore();
    }

    private void drawBaseLine(Canvas canvas) {
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(PAINT_WIDTH_2);
        int h = mCareInfoManager.getTitleHeight() + BASE_LINE_UNIT_COUNT * mCareInfoManager.getUnitHeight();
        canvas.drawLine(mCareInfoManager.getTitleWidth(), h, getScrollX() + getWidth(), h, mPaint);

    }

    private void drawEnterTimeInHour(Canvas canvas) {
        mPaint.setColor(mColorTextNor);
        mPaint.setTextSize(TEXT_SIZE_1);
        StringBuffer buff = new StringBuffer();
        buff.append("入院|");
        String s = TimeUtils.transferTime2Chinese(mEnterTimeInHour, "HH:mm:ss");
        buff.append(s);
        Rect rect = mCareInfoManager.getTextRect(mPaint, String.valueOf(buff.charAt(0)));
        s = buff.toString();
        for (int i = 0; i < s.length(); i++) {
            canvas.drawText(s, i, i + 1, mCareInfoManager.getTitleWidth() + 3 * mCareInfoManager.getUnitWidth(), mCareInfoManager.getTitleHeight() + mCareInfoManager.getUnitHeight() * 4 + i * rect.height(), mPaint);
        }
    }

    private void drawPulsePoint(Canvas canvas) {
        mPaint.setColor(mColorPulse);
        mPaint.setStrokeWidth(PAINT_WIDTH_2);
        List<Point> pulseList = mCareInfoManager.getPulsePoint(mCareInfoItemsMap, mDataList.get(0));
        for (int i = 0; i < pulseList.size(); i++) {
            Point p = pulseList.get(i);
            canvas.drawCircle(p.x, p.y, mCareInfoManager.getUnitWidth() / 4, mPaint);
        }
        //draw line
        for (int i = 1; i < pulseList.size(); i++) {
            Point p = pulseList.get(i);
            canvas.drawLine(pulseList.get(i - 1).x, pulseList.get(i - 1).y, p.x, p.y, mPaint);
        }
    }

    private void drawTemperaturePoint(Canvas canvas) {
        mPaint.setColor(mColorTemper);
        mPaint.setStrokeWidth(PAINT_WIDTH_2);
        List<Point> temList = mCareInfoManager.getTemPoint(mCareInfoItemsMap, mDataList.get(0));
        int radus = mCareInfoManager.getUnitWidth() / 4;
        for (int i = 0; i < temList.size(); i++) {
            Point p = temList.get(i);
            canvas.drawLine(p.x - radus, p.y - radus, p.x + radus, p.y + radus, mPaint);
            canvas.drawLine(p.x - radus, p.y + radus, p.x + radus, p.y - radus, mPaint);
        }
        //draw line
        for (int i = 1; i < temList.size(); i++) {
            Point p = temList.get(i);
            canvas.drawLine(temList.get(i - 1).x, temList.get(i - 1).y, p.x, p.y, mPaint);
        }
    }

    private void drawBottomPoints(Canvas canvas) {
        mPaint.setColor(mColorTextNor);
        mPaint.setTextSize(TEXT_SIZE_1);
        List<DrawStringBean> breatheList = mCareInfoManager.getBreathePos(mPaint, mCareInfoItemsMap, mDataList.get(0));
        List<DrawStringBean> otherList = mCareInfoManager.getBottomPos(mPaint, mCareInfoItemsMap, mDataList.get(0));
        for (int i = 0; i < breatheList.size(); i++) {
            DrawStringBean bean = breatheList.get(i);
            canvas.drawText(bean.mString, bean.mPoint.x, bean.mPoint.y, mPaint);
        }
        for (int i = 0; i < otherList.size(); i++) {
            DrawStringBean bean = otherList.get(i);
            canvas.drawText(bean.mString, bean.mPoint.x, bean.mPoint.y, mPaint);
        }
    }

    private void drawHospitaledTime(Canvas canvas) {
        mPaint.setColor(mColorTextNor);
        mPaint.setTextSize(TEXT_SIZE_2);
        List<DrawStringBean> enterList = mCareInfoManager.getEnterDaysPoint(mEnterTimeInDate, mDataList, mPaint);
        for (int i = 0; i < enterList.size(); i++) {
            DrawStringBean bean = enterList.get(i);
            canvas.drawText(bean.mString, bean.mPoint.x, bean.mPoint.y, mPaint);
        }
    }

    public void reDraw() {
        invalidate();
        requestLayout();
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }

    /**********************
     * 手势区域分割线
     ********************/
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
            int dy = (int) distanceY;
            int x = getScrollX();
            int y = getScrollY();


            // Avoid over scrolling
            if (x + dx < 0) {
                dx = 0 - x;
            }
            if (y + dy < 0) {
                dy = 0 - y;
            }
            if (mMaxHorizontalScroll < 0) {
                dx = 0;
            } else if (x + dx > mMaxHorizontalScroll) {
                dx = mMaxHorizontalScroll - x;
            }
            if (mMaxVerticalScroll < 0) {
                dy = 0;
            } else if (y + dy > mMaxVerticalScroll) {
                dy = mMaxVerticalScroll - y;
            }

            scrollBy(dx, dy);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float vX, float vY) {

            mScroller.fling(getScrollX(), getScrollY(), -(int) vX,
                    -(int) vY, 0, mMaxHorizontalScroll, 0, mMaxVerticalScroll);

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
