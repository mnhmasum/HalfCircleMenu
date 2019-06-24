package com.nhasan.circlemenu;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

public class MainActivity extends AppCompatActivity implements OnChartValueSelectedListener, SeekBar.OnSeekBarChangeListener {
    private PieChart chart;
    private PieChart chart2;
    private PieChart chart3;

    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;


    public static final int[] MATERIAL_COLORS1 = {
            rgb("#999999"), rgb("#999999"), rgb("#999999"), rgb("#999999")
    };

    public static final int[] MATERIAL_COLORS2 = {
            rgb("#2478BE"), rgb("#2478BE"), rgb("#2478BE"), rgb("#999999")
    };

    public static final int[] MATERIAL_COLORS_T_ONE = {
            rgb("#2478BE"), 0, 0
    };

    public static final int[] MATERIAL_COLORS_T_TWO = {
            0, rgb("#2478BE"), 0, 0
    };

    public static final int[] MATERIAL_COLORS_T_THREE = {
            0, 0, rgb("#2478BE"), rgb("#999999")
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setTitle("Menu");

        tvX = findViewById(R.id.tvXMax);
        seekBarX = findViewById(R.id.seekBar1);
        seekBarX.setOnSeekBarChangeListener(this);
        //seekBarX.setProgress(20);

        chartOne();
        chartTwo((float) seekBarX.getProgress());
        chartThree();

        tvX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PieActivity.class));
            }
        });

    }

    private void chartThree() {
        //Chart3 ##############
        chart3 = findViewById(R.id.chart3);
        chart3.setBackgroundColor(Color.TRANSPARENT);

        moveOffScreen3();

        chart3.setUsePercentValues(false);
        chart3.setDrawEntryLabels(false);
        chart3.getDescription().setEnabled(false);

        //chart3.setCenterTextTypeface(tfLight);
        chart3.setDrawCenterText(false);
        chart3.setDrawEntryLabels(true);
        //chart3.setCenterText(generateCenterSpannableText());

        chart3.setDrawHoleEnabled(false);
        chart3.setHoleColor(Color.WHITE);

        chart3.setTransparentCircleColor(Color.WHITE);
        chart3.setTransparentCircleAlpha(110);

        chart3.setHoleRadius(0f);
        chart3.setTransparentCircleRadius(61f);

        chart3.setDrawCenterText(true);

        chart3.setRotationEnabled(false);
        chart3.setHighlightPerTapEnabled(false);


        chart3.setMaxAngle(180f); // HALF CHART
        chart3.setRotationAngle(180f);
        chart3.setCenterTextOffset(0, -20);

        chart3.setOnChartValueSelectedListener(this);
        chart3.setHighlightPerTapEnabled(true);

        setData3(3, 100);

        chart3.animateY(1400, Easing.EaseInOutQuad);

        // entry label styling
        chart3.setEntryLabelColor(Color.WHITE);
        //chart3.setEntryLabelTypeface(tfRegular);
        chart3.setEntryLabelTextSize(12f);
    }

    private void chartTwo(float progress) {
        //Chart2  ##############
        chart2 = findViewById(R.id.chart2);
        chart2.setBackgroundColor(Color.TRANSPARENT);

        moveOffScreen2();

        chart2.setUsePercentValues(true);
        chart2.getDescription().setEnabled(false);

        //chart2.setCenterTextTypeface(tfLight);
        chart2.setDrawCenterText(false);
        chart2.setDrawEntryLabels(false);
        //chart2.setCenterText(generateCenterSpannableText());

        chart2.setDrawHoleEnabled(true);
        chart2.setHoleColor(Color.WHITE);

        chart2.setTransparentCircleColor(Color.WHITE);
        chart2.setTransparentCircleAlpha(110);

        chart2.setHoleRadius(0f);
        chart2.setTransparentCircleRadius(progress);

        chart2.setDrawCenterText(true);

        chart2.setRotationEnabled(false);
        chart2.setHighlightPerTapEnabled(false);

        chart2.setMaxAngle(180f); // HALF CHART
        chart2.setRotationAngle(180f);
        chart2.setCenterTextOffset(0, -20);

        setData2(0f);

        chart2.animateY(1400, Easing.EaseInOutQuad);

        // entry label styling
        chart2.setEntryLabelColor(Color.WHITE);
        //chart2.setEntryLabelTypeface(tfRegular);
        chart2.setEntryLabelTextSize(12f);
    }

    private void chartOne() {
        chart = findViewById(R.id.chart1);
        chart.setBackgroundColor(Color.WHITE);

        moveOffScreen();

        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);

        //chart.setCenterTextTypeface(tfLight);
        chart.setDrawCenterText(false);
        chart.setDrawEntryLabels(false);
        //chart.setCenterText(generateCenterSpannableText());

        chart.setDrawHoleEnabled(false);
        chart.setHoleColor(Color.WHITE);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);

        chart.setDrawCenterText(true);

        chart.setRotationEnabled(false);
        chart.setHighlightPerTapEnabled(false);

        chart.setMaxAngle(180f); // HALF CHART
        chart.setRotationAngle(180f);
        chart.setCenterTextOffset(0, -20);

        setData(3, 100);

        chart.animateY(1400, Easing.EaseInOutQuad);

        // entry label styling
        chart.setEntryLabelColor(Color.WHITE);
        //chart.setEntryLabelTypeface(tfRegular);
        chart.setEntryLabelTextSize(12f);
    }

    private void setData(int count, float range) {
        ArrayList<PieEntry> values = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            values.add(new PieEntry(3.0f));
        }

        PieDataSet dataSet = new PieDataSet(values, "");
        dataSet.setUsingSliceColorAsValueLineColor(false);
        dataSet.setSliceSpace(1f);

        dataSet.setColors(MATERIAL_COLORS1);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(0x999999);

        chart.setData(data);
        chart.invalidate();

    }

    private void setData2(float count) {
        ArrayList<PieEntry> values = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            values.add(new PieEntry(4.4f, "test"));
        }

        PieDataSet dataSet = new PieDataSet(values, "");
        dataSet.setUsingSliceColorAsValueLineColor(false);
        dataSet.setSliceSpace(1f);
        //dataSet.setSelectionShift(190f);

        dataSet.setColors(MATERIAL_COLORS2);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(0x2478BE);

        chart2.setData(data);
        chart2.invalidate();

    }

    private void setData3(int count, float range) {
        ArrayList<PieEntry> values = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            values.add(new PieEntry(1f, "Light " + i));

        }

        PieDataSet dataSet = new PieDataSet(values, "");
        dataSet.setUsingSliceColorAsValueLineColor(true);
        dataSet.setSliceSpace(1f);
        //dataSet.setSelectionShift(-100f);

        if (range == 100) {
            dataSet.setColor(Color.TRANSPARENT);
        } else {
            if (count == 0) {

                dataSet.setColors(MATERIAL_COLORS_T_ONE);
            } else if (count == 1) {
                dataSet.setColors(MATERIAL_COLORS_T_TWO);
            } else if (count == 2) {
                dataSet.setColors(MATERIAL_COLORS_T_THREE);
            }
        }

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);

        chart3.setData(data);

        chart3.invalidate();

        for (IDataSet<?> set : chart3.getData().getDataSets()) {
            set.setDrawValues(!set.isDrawValuesEnabled());
            set.setHighlightEnabled(false);
        }
    }

    private void moveOffScreen() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;

        int offset = (int) (height * 0.65);

        RelativeLayout.LayoutParams rlParams =
                (RelativeLayout.LayoutParams) chart.getLayoutParams();
        rlParams.setMargins(0, 0, 0, -offset);
        chart.setLayoutParams(rlParams);
    }

    private void moveOffScreen2() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;

        int offset = (int) (height * 0.65);

        RelativeLayout.LayoutParams rlParams =
                (RelativeLayout.LayoutParams) chart2.getLayoutParams();
        rlParams.setMargins(0, 0, 0, -offset);
        chart2.setLayoutParams(rlParams);
    }

    private void moveOffScreen3() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;

        int offset = (int) (height * 0.65);

        RelativeLayout.LayoutParams rlParams =
                (RelativeLayout.LayoutParams) chart3.getLayoutParams();
        rlParams.setMargins(0, 0, 0, -offset);
        chart3.setLayoutParams(rlParams);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.only_github, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.viewGithub: {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://github.com/PhilJay/MPAndroidChart/blob/master/MPChartExample/src/com/xxmassdeveloper/mpchartexample/HalfPieChartActivity.java"));
                startActivity(i);
                break;
            }
        }

        return true;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;

        Log.d("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());

        for (IDataSet<?> set : chart3.getData().getDataSets()) {
            set.setDrawValues(!set.isDrawValuesEnabled());
            set.setHighlightEnabled(false);
        }

        setData3((int) h.getX(), 20);


    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tvX.setText(String.valueOf(seekBarX.getProgress()));

        Log.d("Progress", "onProgressChanged: " + progress);
        Log.d("Progress", "onProgressChanged: Seekbar " + seekBarX.getProgress());

        chart2.setTransparentCircleRadius((float) progress);
        chart2.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}



