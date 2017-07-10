package com.founder.ihospital_bdrm_doctor_station_offline.View.CompareView;


import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;

import com.founder.ihospital_bdrm_doctor_station_offline.R;

import java.util.List;

/**
 * Created by bin_li on 16/5/10.
 */
public class ComPareViewManager {

    public static final String mMeasureStr = "07-19 09:09:09";
    private int mPointRadius;
    private View mView;
    private int mBottomVerticalLineHeight;
    private int mBottomTextMarginTop;
    private int mBottomTextMarginLeft;

    private int mViewTotalHeight;
    private int mViewTotalWidth;
    private int mTopViewHeight;
    private int mBottomTextHeight;
    private int mBottomTextWidth;
    private int mItemWidth;

    private int mBottomTextY;
    private int mBottomVerticalLineTopY;
    private int mBottomVerticalLineBottomY;

    public ComPareViewManager(View v) {
        this.mView = v;
        loadDimen();
    }

    public void loadDimen() {
        mPointRadius = mView.getResources().getDimensionPixelOffset(R.dimen.compare_point_radius);
        mBottomVerticalLineHeight = mView.getResources().getDimensionPixelOffset(R.dimen.compare_bottom_vertical_line_height);
        mBottomTextMarginTop = mView.getResources().getDimensionPixelOffset(R.dimen.compare_bottom_text_margin_top);
        mBottomTextMarginLeft = mView.getResources().getDimensionPixelOffset(R.dimen.compare_bottom_text_margin_left);
    }

    public void calcViewDimen(Paint p, int itemCount) {
        mViewTotalHeight = mView.getResources().getDimensionPixelOffset(R.dimen.compare_view_h);
        Rect rect = new Rect();
        p.getTextBounds(mMeasureStr, 0, mMeasureStr.length(), rect);
        mBottomTextHeight = rect.height();
        mBottomTextWidth = rect.width();
        mItemWidth = mBottomTextWidth + mBottomTextMarginLeft;
        mViewTotalWidth = mItemWidth * itemCount;
        mTopViewHeight = mViewTotalHeight - mBottomTextHeight - mBottomTextMarginTop - mBottomVerticalLineHeight;
        mBottomTextY = mView.getHeight();
        mBottomVerticalLineBottomY = mBottomTextY - mBottomTextHeight - mBottomTextMarginTop;
        mBottomVerticalLineTopY = mBottomVerticalLineBottomY - mBottomVerticalLineHeight;
    }

    public Point getPointPos(int pos, double max, double value) {
        Point p = new Point();
        p.x = pos * mItemWidth + mItemWidth / 2;
        double maxValue = max / 0.8;
        if (value == 0)
            p.y = mTopViewHeight;
        else
            p.y = (int) (mTopViewHeight - value * mTopViewHeight / maxValue);
        return p;
    }

    public Point getTextPos(Point pointPos, Paint paint, String str) {
        Point p = new Point();
        int textW = (int) paint.measureText(str);
        p.x = pointPos.x - textW / 2;
        p.y = pointPos.y - 2 * mPointRadius;
        return p;
    }


    public static String getMeasureStr() {
        return mMeasureStr;
    }

    public double getMaxValue(List<CompareBean> list) {
        double retDouble = 0;
        for (int i = 0; i < list.size(); i++) {
            double value = Double.parseDouble(list.get(i).value);
            if (value > retDouble) {
                retDouble = value;
            }
        }
        return retDouble;
    }


    public View getView() {
        return mView;
    }

    public void setView(View mView) {
        this.mView = mView;
    }

    public int getBottomVerticalLineHeight() {
        return mBottomVerticalLineHeight;
    }

    public void setBottomVerticalLineHeight(int mBottomVerticalLineHeight) {
        this.mBottomVerticalLineHeight = mBottomVerticalLineHeight;
    }

    public int getBottomTextMarginTop() {
        return mBottomTextMarginTop;
    }

    public void setBottomTextMarginTop(int mBottomTextMarginTop) {
        this.mBottomTextMarginTop = mBottomTextMarginTop;
    }

    public int getBottomTextMarginLeft() {
        return mBottomTextMarginLeft;
    }

    public void setBottomTextMarginLeft(int mBottomTextMarginLeft) {
        this.mBottomTextMarginLeft = mBottomTextMarginLeft;
    }

    public int getViewTotalHeight() {
        return mViewTotalHeight;
    }

    public void setViewTotalHeight(int mViewTotalHeight) {
        this.mViewTotalHeight = mViewTotalHeight;
    }

    public int getTopViewHeight() {
        return mTopViewHeight;
    }

    public void setTopViewHeight(int mTopViewHeight) {
        this.mTopViewHeight = mTopViewHeight;
    }

    public int getBottomTextHeight() {
        return mBottomTextHeight;
    }

    public void setBottomTextHeight(int mBottomTextHeight) {
        this.mBottomTextHeight = mBottomTextHeight;
    }

    public int getBottomTextWidth() {
        return mBottomTextWidth;
    }

    public void setBottomTextWidth(int mBottomTextWidth) {
        this.mBottomTextWidth = mBottomTextWidth;
    }

    public int getItemWidth() {
        return mItemWidth;
    }

    public void setItemWidth(int mItemWidth) {
        this.mItemWidth = mItemWidth;
    }

    public int getBottomTextY() {
        return mBottomTextY;
    }

    public void setBottomTextY(int mBottomTextY) {
        this.mBottomTextY = mBottomTextY;
    }

    public int getBottomVerticalLineTopY() {
        return mBottomVerticalLineTopY;
    }

    public void setBottomVerticalLineTopY(int mBottomVerticalLineTopY) {
        this.mBottomVerticalLineTopY = mBottomVerticalLineTopY;
    }

    public int getBottomVerticalLineBottomY() {
        return mBottomVerticalLineBottomY;
    }

    public void setBottomVerticalLineBottomY(int mBottomVerticalLineBottomY) {
        this.mBottomVerticalLineBottomY = mBottomVerticalLineBottomY;
    }

    public int getViewTotalWidth() {
        return mViewTotalWidth;
    }

    public void setViewTotalWidth(int mViewTotalWidth) {
        this.mViewTotalWidth = mViewTotalWidth;
    }

    public int getPointRadius() {
        return mPointRadius;
    }

//    public static CompareBean getCompareViewData() {
//        List
//        CompareBean bean = new CompareBean();
//        bean.name = "07-19 09:09:09";
//        bean.list = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            CompareBean itemBean = new CompareBean();
//            itemBean.date = "07-19 09:09:09";
//            bean.list.add(itemBean);
//        }
//        return bean;
//    }


}
