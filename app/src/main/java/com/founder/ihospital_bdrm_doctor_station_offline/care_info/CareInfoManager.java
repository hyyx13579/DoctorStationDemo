package com.founder.ihospital_bdrm_doctor_station_offline.care_info;

import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.ViewGroup;


import com.founder.ihospital_bdrm_doctor_station_offline.Model.DCNursingRecordOfPatientHR.ValuesBean.NurseingRecordListBean.ItemListBean;
import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.care_info.bean.DrawStringBean;
import com.founder.ihospital_bdrm_doctor_station_offline.care_info.util.TimeUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by bin_li on 16/5/4.
 * 体征单数据处理Manager
 */
public class CareInfoManager {
    private static final int TITLE_ITEM_COUNT = 4;
    private static final int BOTTOM_ITEM_COUNT = 8;
    private static final int UNIT_VERTICAL_COUNT = 48;//5*9+3

    private final String[] mTopTitleName = {"日期", "住院天数", "手术后天数", "时间"};
    private final String[] mPulseStrs = {"180", "160", "140", "120", "100", "80", "60", "40", "20"};
    private final String[] mTemperatureStr = {"42", "41", "40", "39", "38", "37", "36", "35", "34"};
    private final String[] mBottomTitleName = {"呼吸", "血压", "大便次数", "尿量", "出水量", "入水量", "体重"};
    private final String[] mTopTimeDrawString = {"02", "06", "10", "14", "18", "22"};

    public static final String mPulse = "脉搏";
    public static final String mTemperature = "体温";
    private final String mUnitPulse = "次/分";
    private final String mUnitTem = "℃";


    private ViewGroup mContext;
    private int mUnitWidth;
    private int mUnitHeight;
    private int mTitleWidth;
    private int mItemHeight;
    private int mTitleHeight;
    private int mDetilsHeight;
    private int mDetilsWidth;
    private int mBottomHeight;
    private int mDetilsItemWidth;
    private int mDetilsItemHeight;
    private int mMaxWidth;
    private int mMaxHeight;

    public CareInfoManager() {
    }

    public CareInfoManager(ViewGroup context) {
        this.mContext = context;
    }

    public void initDimen(int daySum) {
        this.mUnitWidth = mContext.getResources().getDimensionPixelOffset(R.dimen.care_info_unit_width);
        this.mUnitHeight = mContext.getResources().getDimensionPixelOffset(R.dimen.care_info_unit_height);
        this.mItemHeight = mContext.getResources().getDimensionPixelOffset(R.dimen.care_info_item_hight);
        this.mTitleWidth = mContext.getResources().getDimensionPixelOffset(R.dimen.care_info_title_width);
        this.mTitleHeight = mItemHeight * TITLE_ITEM_COUNT;
        this.mBottomHeight = mItemHeight * BOTTOM_ITEM_COUNT;
        this.mDetilsItemWidth = mUnitWidth * 6;
        this.mDetilsItemHeight = mUnitHeight * 5;
        this.mDetilsWidth = mDetilsItemWidth * daySum;
        this.mDetilsHeight = mUnitHeight * UNIT_VERTICAL_COUNT;
        this.mMaxWidth = mTitleWidth + mDetilsWidth;
        this.mMaxHeight = mTitleHeight + mDetilsHeight + mBottomHeight;
    }

    /**
     * 计算(日期,住院天数,手术后天数,时间)的y轴坐标
     */
    public List<Integer> getTitleHorzontalLineY() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                list.add(0);
                continue;
            } else if (i == 4) {
                list.add(i * mContext.getResources().getDimensionPixelOffset(R.dimen.care_info_item_hight) - 1);
            }

            list.add(i * mContext.getResources().getDimensionPixelOffset(R.dimen.care_info_item_hight));
        }
        return list;
    }


    /**
     * 计算日期行,每次移动的X轴坐标
     */
    public List<Integer> getTopTitleVerticalX() {

        List<Integer> list = new ArrayList<>();
        list.add(mContext.getScrollX());
        list.add(mContext.getScrollX() + getTitleWidth() - 1);
        return list;
    }


    /**
     * 计算除(日期,住院天数,手术后天数,时间)栏的天数x轴坐标
     */
    public List<Integer> getTopVerticalX() {
        List<Integer> list = new ArrayList<Integer>();
        int count = mDetilsWidth / mTitleWidth;
        for (int i = 1; i < count + 1; i++) {
            list.add(mTitleWidth + i * mDetilsItemWidth);
        }
        list.add(mMaxWidth);
        return list;
    }

    /**
     * 计算每个5*6小格,6个点在x轴的位置
     */

    public List<Integer> getUnitVerticalLine() {
        List<Integer> list = new ArrayList<Integer>();
        int scrollWidth = mMaxWidth - mTitleWidth;
        int unitCount = scrollWidth / mUnitWidth;
        for (int i = 1; i < unitCount; i++) {
            if (i % 6 == 0)
                continue;
            list.add(i * mUnitWidth + mTitleWidth);
        }
        return list;
    }

    /**
     * 计算每个5*6小格,5个点在y轴的位置
     */
    public List<Integer> getDetailHorizontalLines() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < UNIT_VERTICAL_COUNT; i++) {
            list.add(mTitleHeight + i * mUnitHeight);
        }
        return list;
    }


    /**
     * 计算每个5*6小格,整体在x轴坐标
     */
    public List<Integer> getDetailVerticalLines() {
        List<Integer> list = new ArrayList<Integer>();
        int count = mDetilsWidth / mUnitWidth;
        for (int i = 0; i < count; i++) {
            list.add(mTitleWidth + i * mUnitWidth);
        }
        return list;
    }

    /**
     *
     * */
    public List<Integer> getDetailTitleLine() {
        List<Integer> list = new ArrayList<>();
        int firstLine = mContext.getScrollX();
        list.add(firstLine);
        list.add(firstLine + mTitleWidth / 2);
        list.add(firstLine + mTitleWidth - 1);
        return list;
    }


    public List<Integer> getBottomTitleHorizontalLine() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(mTitleHeight + mDetilsHeight + i * mItemHeight);
        }
        return list;
    }

    public List<Integer> getBottomTitleVerticalLine() {
        return getTopTitleVerticalX();
    }

    public List<Integer> getBottomHorizontalLine() {
        return getBottomTitleHorizontalLine();
    }

    private List<Integer> mBottomVerticalLine;

    public List<Integer> getBottomVertocalLine() {
        if (mBottomVerticalLine != null)
            return mBottomVerticalLine;
        mBottomVerticalLine = new ArrayList<Integer>();
        int count = mDetilsWidth / mTitleWidth * 2;
        for (int i = 0; i < count; i++) {
            mBottomVerticalLine.add(mTitleWidth + i * (mDetilsItemWidth / 2));
        }
        mBottomVerticalLine.add(mMaxWidth);
        return mBottomVerticalLine;
    }

    public List<DrawStringBean> getTopTitleDrawString(Paint p) {
        List<DrawStringBean> list = new ArrayList<>();
        for (int i = 0; i < mTopTitleName.length; i++) {
            DrawStringBean bean = new DrawStringBean();
            bean.mString = mTopTitleName[i];
            bean.mPoint = new Point();
            Point rP = getRelativePoint(bean.mString, mTitleWidth, mItemHeight, p);
            bean.mPoint.x = mContext.getScrollX() + rP.x;
            bean.mPoint.y = mContext.getScrollY() + i * mItemHeight + rP.y;
            list.add(bean);
        }
        return list;
    }


    public List<DrawStringBean> getTopDateDrawString(Paint p, List<String> datas) {
        List<DrawStringBean> list = new ArrayList<>();
        Point rP = getRelativePoint(datas.get(0), mDetilsItemWidth, mItemHeight, p);
        for (int i = 0; i < datas.size(); i++) {
            DrawStringBean bean = new DrawStringBean();
            bean.mString = datas.get(i);
            bean.mPoint = new Point();
            bean.mPoint.x = mTitleWidth + i * mDetilsItemWidth + rP.x;
            bean.mPoint.y = mContext.getScrollY() + rP.y;
            list.add(bean);
        }
        return list;
    }

    public List<DrawStringBean> getTopTimeDrawString(Paint p, int dayNum) {
        List<DrawStringBean> list = new ArrayList<>();
        Point rP = getRelativePoint(mTopTimeDrawString[0], mUnitWidth, mItemHeight, p);
        for (int i = 0; i < dayNum * 6; i++) {
            DrawStringBean bean = new DrawStringBean();
            bean.mString = mTopTimeDrawString[(i % mTopTimeDrawString.length)];
            bean.mPoint = new Point();
            bean.mPoint.x = mTitleWidth + i * mUnitWidth + rP.x;
            bean.mPoint.y = mContext.getScrollY() + mItemHeight * 3 + rP.y;
            list.add(bean);
        }
        return list;
    }

    public List<DrawStringBean> getDetailsTitleDrawString(Paint p) {
        List<DrawStringBean> list = new ArrayList<>();
        //maibo
        //"脉搏"
        DrawStringBean bean = new DrawStringBean();
        bean.mString = mPulse;
        bean.mPoint = new Point();
        Point rP = getRelativePoint(bean.mString, mTitleWidth / 2, -1, p);
        bean.mPoint.x = mContext.getScrollX() + rP.x;
        bean.mPoint.y = mTitleHeight + rP.y;
        list.add(bean);
        //"次/分"
        DrawStringBean pulseUnit = new DrawStringBean();
        pulseUnit.mString = mUnitPulse;
        pulseUnit.mPoint = new Point();
        Point rP2 = getRelativePoint(pulseUnit.mString, mTitleWidth / 2, -1, p);
        pulseUnit.mPoint.x = mContext.getScrollX() + rP2.x;
        // title + “脉搏”的高度+自己的高度+5px的间隔
        pulseUnit.mPoint.y = mTitleHeight + rP.y + rP2.y + 5;
        list.add(pulseUnit);
        //脉搏 数值
        for (int i = 0; i < mPulseStrs.length; i++) {
            DrawStringBean pulseCount = new DrawStringBean();
            pulseCount.mString = mPulseStrs[i];
            pulseCount.mPoint = new Point();
            Point countP = getRelativePoint(pulseCount.mString, mTitleWidth / 2, -1, p);
            pulseCount.mPoint.x = mContext.getScrollX() + countP.x;
            pulseCount.mPoint.y = mTitleHeight + mUnitHeight * 3 + i * mDetilsItemHeight + countP.y / 2;
            list.add(pulseCount);
        }
        //"体温"
        DrawStringBean temBean = new DrawStringBean();
        temBean.mString = mTemperature;
        temBean.mPoint = new Point();
        Point temRP = getRelativePoint(temBean.mString, mTitleWidth / 2, -1, p);
        temBean.mPoint.x = mContext.getScrollX() + mTitleWidth / 2 + temRP.x;
        temBean.mPoint.y = mTitleHeight + temRP.y;
        list.add(temBean);
        //"℃"
        DrawStringBean temUnit = new DrawStringBean();
        temUnit.mString = mUnitTem;
        temUnit.mPoint = new Point();
        Point temRP2 = getRelativePoint(temUnit.mString, mTitleWidth / 2, -1, p);
        temUnit.mPoint.x = mContext.getScrollX() + mTitleWidth / 2 + temRP2.x;
        // title + “体温”的高度+自己的高度+5px的间隔
        temUnit.mPoint.y = mTitleHeight + temRP.y + temRP2.y + 5;
        list.add(temUnit);
        for (int i = 0; i < mTemperatureStr.length; i++) {
            DrawStringBean temCount = new DrawStringBean();
            temCount.mString = mTemperatureStr[i];
            temCount.mPoint = new Point();
            Point countP = getRelativePoint(temCount.mString, mTitleWidth / 2, -1, p);
            temCount.mPoint.x = mContext.getScrollX() + mTitleWidth / 2 + countP.x;
            temCount.mPoint.y = mTitleHeight + mUnitHeight * 3 + i * mDetilsItemHeight + countP.y / 2;
            list.add(temCount);
        }
        return list;
    }


    public List<DrawStringBean> getBottomTitleName(Paint p) {
        List<DrawStringBean> list = new ArrayList<>();
        for (int i = 0; i < mBottomTitleName.length; i++) {
            DrawStringBean bean = new DrawStringBean();
            bean.mString = mBottomTitleName[i];
            bean.mPoint = new Point();
            Point rP = getRelativePoint(bean.mString, mTitleWidth, mItemHeight, p);
            bean.mPoint.x = mContext.getScrollX() + rP.x;
            bean.mPoint.y = mTitleHeight + mDetilsHeight + i * mItemHeight + rP.y;
            list.add(bean);
        }
        return list;
    }

    public List<DrawStringBean> getEnterDaysPoint(String inday, List<String> strs, Paint p) {
        List<DrawStringBean> list = new ArrayList<>();

        if (TextUtils.isEmpty(inday)) {
            return list;
        }
        long inDay = TimeUtils.parseTimeString(inday, "yyyy-MM-dd");
        for (int i = 0; i < strs.size(); i++) {
            DrawStringBean bean = new DrawStringBean();
            bean.mString = (int) ((TimeUtils.parseTimeString(strs.get(i), "yyyy-MM-dd") - inDay) / (24 * 60 * 60 * 1000)) + 1 + "";
            bean.mPoint = new Point();
            Point rP = getRelativePoint(bean.mString, mDetilsItemWidth, mItemHeight, p);
            bean.mPoint.x = mTitleWidth + i * mDetilsItemWidth + rP.x;
            bean.mPoint.y = mContext.getScrollY() + mItemHeight + rP.y;
            list.add(bean);
        }

        return list;
    }

    public List<DrawStringBean> getBreathePos(Paint p, Map<String, List<ItemListBean>> map, String firstTime) {

        List<DrawStringBean> list = new ArrayList<>();
        long startTime = TimeUtils.parseTimeString(firstTime, "yyyy-MM-dd");
        List<ItemListBean> tempList = map.get(mBottomTitleName[0]);
        if (tempList == null || tempList.size() <= 0)
            return list;
        List<ItemListBean> showList = getShowList(startTime, tempList);
        for (int i = 0; i < showList.size(); i++) {
            long targetTime = showList.get(i).getTimeInMillis();
            int days = ((int) (targetTime - startTime)) / (24 * 60 * 60 * 1000);
            int hours = (((int) (targetTime - startTime)) % (24 * 60 * 60 * 1000)) / (4 * 60 * 60 * 1000);
            boolean am = (((int) (targetTime - startTime)) % (4 * 60 * 60 * 1000)) == (((int) (targetTime - startTime)) % (2 * 60 * 60 * 1000));
            DrawStringBean bean = new DrawStringBean();
            bean.mPoint = new Point();
            bean.mString = (int) Double.parseDouble(showList.get(i).getValue()) + "";
            Point rP = getRelativePoint(bean.mString, mUnitWidth, mItemHeight / 2, p);
            bean.mPoint.x = mTitleWidth + days * mDetilsItemWidth + hours * mUnitWidth + rP.x;
            if (am) {
                bean.mPoint.y = mTitleHeight + mDetilsHeight + rP.y;
            } else {
                bean.mPoint.y = mTitleHeight + mDetilsHeight + 2 * rP.y;
            }

            list.add(bean);
        }
        return list;
    }

    public List<DrawStringBean> getBottomPos(Paint p, Map<String, List<ItemListBean>> map, String firstTime) {
        List<DrawStringBean> list = new ArrayList<>();
        long tempTime = TimeUtils.parseTimeString(firstTime, "yyyy-MM-dd");
        for (int i = 1; i < mBottomTitleName.length; i++) {
            List<ItemListBean> tempList = map.get(mBottomTitleName[i]);
            if (tempList == null || tempList.size() <= 0) {
                continue;
            }
            List<ItemListBean> showList = getShowList(tempTime, tempList);
            for (int j = 0; j < showList.size(); j++) {
                DrawStringBean bean = new DrawStringBean();
                bean.mString = showList.get(j).getValue();
                bean.mPoint = new Point();
                Point rP = getRelativePoint(bean.mString, mDetilsItemWidth / 2, mItemHeight, p);
                long targetTime = showList.get(j).getTimeInMillis();
                int day = (int) (targetTime - tempTime) / (24 * 60 * 60 * 1000);
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(targetTime);
                if (calendar.get(Calendar.AM_PM) == Calendar.AM)
                    bean.mPoint.x = mTitleWidth + day * mDetilsItemWidth + rP.x;
                else if (calendar.get(Calendar.AM_PM) == Calendar.PM)
                    bean.mPoint.x = mTitleWidth + day * mDetilsItemWidth + rP.x + mDetilsItemWidth / 2;
                bean.mPoint.y = mTitleHeight + mDetilsHeight + i * mItemHeight + rP.y;
                list.add(bean);
            }
        }
        return list;
    }

    public List<Point> getPulsePoint(Map<String, List<ItemListBean>> map, String firstTime) {
        List<Point> list = new ArrayList<>();
        List<ItemListBean> pulseList = map.get(mPulse);
        if (pulseList == null)
            return list;
        long tempTime = TimeUtils.parseTimeString(firstTime, "yyyy-MM-dd");
        List<ItemListBean> showList = getShowList(tempTime, pulseList);
        for (int i = 0; i < showList.size(); i++) {
            Point p = new Point();
            p.x = (int) (mTitleWidth + (showList.get(i).getTimeInMillis() - tempTime) * mDetilsItemWidth / (24 * 60 * 60 * 1000));
            p.y = (mDetilsHeight + mTitleHeight) - (int) (Double.parseDouble(showList.get(i).getValue()) * mDetilsHeight / 192);
            list.add(p);
        }
        return list;
    }

    public List<Point> getTemPoint(Map<String, List<ItemListBean>> map, String firstTime) {
        List<Point> list = new ArrayList<>();
        List<ItemListBean> temList = map.get(mTemperature);

        if (temList == null)
            return list;
        long tempTime = TimeUtils.parseTimeString(firstTime, "yyyy-MM-dd");
        List<ItemListBean> showList = getShowList(tempTime, temList);
        for (int i = 0; i < showList.size(); i++) {
            Point p = new Point();
            p.x = (int) (mTitleWidth + (showList.get(i).getTimeInMillis() - tempTime) * mDetilsItemWidth / (24 * 60 * 60 * 1000));
            p.y = mTitleHeight + (int) ((42.5 - Double.parseDouble(showList.get(i).getValue())) * mDetilsHeight / (42.5 - 33));
            list.add(p);
        }
        return list;
    }

    private List<ItemListBean> getShowList(final long time, List<ItemListBean> list) {
        List<ItemListBean> retList = new ArrayList<>();
        float startX = mContext.getScrollX();
        float endX = startX + mContext.getWidth();
        int startDay = (int) (startX / getDetilsItemWidth()) - 1;
        int endDay = (int) (endX / getDetilsItemWidth()) + 1;
        long startTime = time + startDay * 24 * 60 * 60 * 1000;
        long endTime = time + endDay * 24 * 60 * 60 * 1000;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTimeInMillis() > startTime && list.get(i).getTimeInMillis() < endTime) {
                retList.add(list.get(i));
            }
        }
        return retList;
    }

    private Point getRelativePoint(String str, int totalWidth, int totalHeight, Paint p) {
        Point point = new Point();
        Rect rect = getTextRect(p, str);
        point.x = (totalWidth - rect.width()) / 2;
        if (totalHeight < 0)
            point.y = rect.height();
        else
            point.y = totalHeight - (totalHeight - rect.height()) / 2;
        return point;
    }

    public Rect getTextRect(Paint p, String str) {
        Rect rect = new Rect();
        p.getTextBounds(str, 0, str.length(), rect);
        return rect;
    }

    private int getTextX(int totalWidth, String s, Paint paint) {
        int textWidth = (int) paint.measureText(s);
        return (totalWidth - textWidth) / 2;
    }


    //##########  get()/set() ##########//

    public int getUnitHeight() {
        return mUnitHeight;
    }

    public void setUnitHeight(int mUnitHeight) {
        this.mUnitHeight = mUnitHeight;
    }

    public int getUnitWidth() {
        return mUnitWidth;
    }

    public void setUnitWidth(int mUnitWidth) {
        this.mUnitWidth = mUnitWidth;
    }

    public int getTitleWidth() {
        return mTitleWidth;
    }

    public void setTitleWidth(int mTitleWidth) {
        this.mTitleWidth = mTitleWidth;
    }

    public int getItemHeight() {
        return mItemHeight;
    }

    public void setItemHeight(int mItemHeight) {
        this.mItemHeight = mItemHeight;
    }

    public int getTitleHeight() {
        return mTitleHeight;
    }

    public void setTitleHeight(int mTitleHeight) {
        this.mTitleHeight = mTitleHeight;
    }

    public int getDetilsHeight() {
        return mDetilsHeight;
    }

    public void setDetilsHeight(int mDetilsHeight) {
        this.mDetilsHeight = mDetilsHeight;
    }

    public int getBottomHeight() {
        return mBottomHeight;
    }

    public void setBottomHeight(int mBottomHeight) {
        this.mBottomHeight = mBottomHeight;
    }

    public int getDetilsItemWidth() {
        return mDetilsItemWidth;
    }

    public void setDetilsItemWidth(int mDetilsItemWidth) {
        this.mDetilsItemWidth = mDetilsItemWidth;
    }

    public int getDetilsItemHeight() {
        return mDetilsItemHeight;
    }

    public void setDetilsItemHeight(int mDetilsItemHeight) {
        this.mDetilsItemHeight = mDetilsItemHeight;
    }

    public int getMaxWidth() {
        return mMaxWidth;
    }

    public void setMaxWidth(int mMaxWidth) {
        this.mMaxWidth = mMaxWidth;
    }

    public int getMaxHeight() {
        return mMaxHeight;
    }

    public void setMaxHeight(int mMaxHeight) {
        this.mMaxHeight = mMaxHeight;
    }

    public int getDetilsWidth() {
        return mDetilsWidth;
    }

    public void setDetilsWidth(int mDetilsWidth) {
        this.mDetilsWidth = mDetilsWidth;
    }
}
