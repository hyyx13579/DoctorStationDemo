package com.founder.ihospital_bdrm_doctor_station_offline.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.founder.ihospital_bdrm_doctor_station_offline.R;
import com.founder.ihospital_bdrm_doctor_station_offline.Utils.Url;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.util.ArrayList;

/**
 * Created by 呼延 on 2016/3/25.
 * 病区概况的GridView点击后的详细数据及图表信息界面
 */
public class WardGeneralizationFragment_Frag_two extends BaseFragment implements View.OnClickListener {

    private ImageView imgBtm;
    public static final String TAG = WardGeneralizationFragment_Frag_two.class.getSimpleName();
    private TextView title;
    private int[] lArray;
    private int[] colors = {Color.rgb(236, 134, 147),
            Color.rgb(243, 153, 119), Color.rgb(248, 206, 132),
            Color.rgb(136, 200, 150)};
    private ArrayList<Integer> careLevelAndStateCount;
    private String SPECIAL = "特级护理";
    private String ONELEVEL = "一级护理";
    private String TWOLEVEL = "二级护理";
    private String THREELEVEL = "三级护理";
    private LinearLayout linear;
    private GraphicalView pieChartView;
    private int sum;
    private TextView parentCount;
    private TextView myParentCount;
    private String patientInHos;
    private String myPatientInHos;
    private String userName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.main_frame_frag_two, container, false);

        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();

    }

    private void initData() {
        Bundle arguments = getArguments();
        String name = arguments.getString("name");
        userName = arguments.getString("userName");
        title.setText(name);
        patientInHos = arguments.getString("patientInHos");
        parentCount.setText(patientInHos + "人");
        myPatientInHos = arguments.getString("myPatientInHos");
        myParentCount.setText(myPatientInHos + "人");
        careLevelAndStateCount = arguments.getIntegerArrayList("careLevelAndStateCount");
        lArray = new int[careLevelAndStateCount.size()];
        for (int i = 0; i < careLevelAndStateCount.size(); i++) {
            lArray[i] = careLevelAndStateCount.get(i);
            sum += lArray[i];

        }
        if (sum != 0) {
            DefaultRenderer renderer = buildCategoryRenderer(colors);
            CategorySeries dataset = buildCategoryDataset("统计数据", lArray);
            pieChartView = ChartFactory.getPieChartView(getContext(), dataset, renderer);
            linear.addView(pieChartView);
            //linear.setPadding(0, 40, 0, 0);
        } else {
            Url.isToast(getContext(), "数据全为零");
        }


    }

    private void initView() {
        imgBtm = ((ImageView) layout.findViewById(R.id.imgbtn));
        imgBtm.setOnClickListener(this);
        title = ((TextView) layout.findViewById(R.id.title_frag_two));
        linear = ((LinearLayout) layout.findViewById(R.id.linearlayout_frag_two));
        parentCount = ((TextView) layout.findViewById(R.id.parent_frag_two));
        myParentCount = ((TextView) layout.findViewById(R.id.myparent_frag_two));


    }

    protected CategorySeries buildCategoryDataset(String title, int[] values) {
        CategorySeries series = new CategorySeries(title);
        series.add("特级", values[0]);
        series.add("一级", values[1]);
        series.add("二级", values[2]);
        series.add("三级", values[3]);
        return series;
    }


    protected DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();

        // renderer.setLegendTextSize()// 设置左下角表注的文字大小
       // renderer.setZoomButtonsVisible(false);//设置显示放大缩小按钮
        renderer.setZoomEnabled(false);// 设置不允许放大缩小.
        renderer.setChartTitle("统计结果");// 设置图表的标题 默认是居中顶部显示
        renderer.setChartTitleTextSize(30);
        renderer.setLabelsTextSize(20);// 饼图上标记文字的字体大小
        renderer.setLabelsColor(Color.BLACK);//饼图上标记文字的颜色
        renderer.setPanEnabled(false);// 设置是否可以平移
        renderer.setDisplayValues(true);//是否显示值
        renderer.setFitLegend(true);//设置图例字号自适应.
        renderer.setClickEnabled(false);// 设置是否可以被点击
        renderer.setShowLabels(true);
        renderer.setStartAngle(0);
        renderer.setShowLegend(false);

        renderer.setMargins(new int[]{0, 0, 0, 0});//设置图标距离父View的距离(上下左右)
        // margins - an array containing the margin size values, in this order:
        // top, left, bottom, right
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgbtn:
                switchFragment();
                break;
        }
    }

    private void switchFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("id", userName);
        WardGeneralizationFragment_Frag_one wardGeneralizationFragment_frag_one = new WardGeneralizationFragment_Frag_one();
        wardGeneralizationFragment_frag_one.setArguments(bundle);
        fragmentTransaction.replace(R.id.main_fragment_frame, wardGeneralizationFragment_frag_one)
                .commit();

    }


}
