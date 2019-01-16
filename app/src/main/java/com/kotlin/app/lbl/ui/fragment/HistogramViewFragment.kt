package com.kotlin.app.lbl.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.model.GradientColor

import com.kotlin.app.lbl.R
import com.kotlin.app.lbl.base.BaseMainFragmet
import com.kotlin.app.lbl.custom.DayAxisValueFormatter
import com.kotlin.app.lbl.custom.MyValueFormatter
import com.kotlin.app.lbl.custom.XYMarkerView
import kotlinx.android.synthetic.main.fragment_histogram_view.*
import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 *
 */
class HistogramViewFragment : BaseMainFragmet(),SeekBar.OnSeekBarChangeListener {


    companion object {
        fun newInstance(): HistogramViewFragment {
            val args = Bundle()
            val fragment = HistogramViewFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_histogram_view
    }

    override fun initView() {
        initcharView()

    }

    override fun initData() {
        setData(10,15f)
        bar_chart.invalidate()
        histogram_button.setOnClickListener {
            for (set in bar_chart.getData().getDataSets())
                set.setDrawValues(!set.isDrawValuesEnabled())

            bar_chart.invalidate()
        }
        histogram_button1.setOnClickListener {
            bar_chart.animateX(2000)
        }
        histogram_button2.setOnClickListener {
            bar_chart.animateY(2000)
        }
        histogram_button3.setOnClickListener {
            bar_chart.animateXY(2000, 2000)
        }
    }

    private fun initcharView(){
        bar_chart.setDrawBarShadow(false)
        bar_chart.setDrawValueAboveBar(true)

        bar_chart.getDescription().setEnabled(false)
        bar_chart.setMaxVisibleValueCount(60) // 条目超过60条不会显示数字
        // scaling can now only be done on x- and y-axis separately
        bar_chart.setPinchZoom(false)
        bar_chart.setPinchZoom(false)

        val xAxisFormatter :ValueFormatter  = DayAxisValueFormatter(bar_chart) // 底部数字显示
        val xAxis :XAxis = bar_chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
//        xAxis.typeface =
        xAxis.setDrawGridLines(false)
        xAxis.granularity = 1f // only intervals of 1 day
        xAxis.labelCount = 7
        xAxis.valueFormatter = xAxisFormatter

        val custom = MyValueFormatter("$") // 左右两边数字显示

        val leftAxis = bar_chart.getAxisLeft()
//        leftAxis.setTypeface(tfLight)
        leftAxis.setLabelCount(8, false)
        leftAxis.setValueFormatter(custom)
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
        leftAxis.setSpaceTop(15f)
        leftAxis.setAxisMinimum(0f) // this replaces setStartAtZero(true)

        val rightAxis = bar_chart.getAxisRight()
        rightAxis.setDrawGridLines(false)
//        rightAxis.setTypeface(tfLight)
        rightAxis.setLabelCount(8, false)
        rightAxis.setValueFormatter(custom)
        rightAxis.setSpaceTop(15f)
        rightAxis.setAxisMinimum(0f) // this replaces setStartAtZero(true)

        val l = bar_chart.getLegend()
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM)
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT)
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL)
        l.setDrawInside(false)
        l.setForm(Legend.LegendForm.SQUARE)
        l.setFormSize(9f)
        l.setTextSize(11f)
        l.setXEntrySpace(4f)

        val mv = XYMarkerView(_mActivity, xAxisFormatter)
        mv.setChartView(bar_chart) // For bounds control
        bar_chart.setMarker(mv) // Set the marker to the chart
    }


    private fun setData(count: Int, range: Float) {

        val start = 1f

        val values = ArrayList<BarEntry>()

        var i = start.toInt()
        while (i < start + count) {
            val `val` = (Math.random() * (range + 1)).toFloat()

            if (Math.random() * 100 < 25) {
//                values.add(BarEntry(i, `val`, resources.getDrawable(R.drawable.star)))
            } else {
                values.add(BarEntry(i.toFloat(), `val`))
            }
            i++
        }

        val set1: BarDataSet

        if (bar_chart.getData() != null && bar_chart.getData().getDataSetCount() > 0) {
            set1 = bar_chart.data.getDataSetByIndex(0) as BarDataSet
//            set1 = bar_chart.getData().getDataSetByIndex(0)
            set1.values = values
            bar_chart.getData().notifyDataChanged()
            bar_chart.notifyDataSetChanged()

        } else {
            set1 = BarDataSet(values, "The year 2017")

            set1.setDrawIcons(false)

            //            set1.setColors(ColorTemplate.MATERIAL_COLORS);

            /*int startColor = ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            int endColor = ContextCompat.getColor(this, android.R.color.holo_blue_bright);
            set1.setGradientColor(startColor, endColor);*/

            val startColor1 = ContextCompat.getColor(_mActivity,  R.color.histogram_bottom)
//            val startColor2 = ContextCompat.getColor(_mActivity, android.R.color.holo_blue_light)
//            val startColor3 = ContextCompat.getColor(_mActivity, android.R.color.holo_orange_light)
//            val startColor4 = ContextCompat.getColor(_mActivity, android.R.color.holo_green_light)
//            val startColor5 = ContextCompat.getColor(_mActivity, android.R.color.holo_red_light)
            val endColor1 = ContextCompat.getColor(_mActivity, R.color.histogram_top)
//            val endColor2 = ContextCompat.getColor(_mActivity, android.R.color.holo_purple)
//            val endColor3 = ContextCompat.getColor(_mActivity, android.R.color.holo_green_dark)
//            val endColor4 = ContextCompat.getColor(_mActivity, android.R.color.holo_red_dark)
//            val endColor5 = ContextCompat.getColor(_mActivity, android.R.color.holo_orange_dark)

            val gradientColors = ArrayList<GradientColor>()
            gradientColors.add(GradientColor(startColor1, endColor1))
//            gradientColors.add(GradientColor(startColor2, endColor2))
//            gradientColors.add(GradientColor(startColor3, endColor3))
//            gradientColors.add(GradientColor(startColor4, endColor4))
//            gradientColors.add(GradientColor(startColor5, endColor5))

            set1.gradientColors = gradientColors

            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)

            val data = BarData(dataSets)
            data.setValueTextSize(10f)
//            data.setValueTypeface(tfLight)
            data.barWidth = 0.9f

            bar_chart.setData(data)
        }
    }


    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        setData(10,15f)
        bar_chart.invalidate()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

}
