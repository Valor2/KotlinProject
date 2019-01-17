package com.kotlin.app.lbl.ui.fragment.viewpage


import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.kotlin.app.lbl.R
import com.kotlin.app.lbl.base.BaseMainFragmet
import kotlinx.android.synthetic.main.fragment_circular_view.*
import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 *
 */
class ChartFragment : BaseMainFragmet() {

    var tf : Typeface? = null

    companion object {
        fun newInstance(): ChartFragment {
            val args = Bundle()
            val fragment = ChartFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun getLayoutResources(): Int {
        return   R.layout.fragment_chart
    }

    override fun initView() {
        chartView()
    }

    override fun initData() {
        setData(4,4f)
        for (set in chart1.getData().getDataSets())
            set.setDrawValues(false)
        chart1.invalidate()
    }

    fun chartView(){
        chart1.setUsePercentValues(true)
        chart1.description.isEnabled = false
        chart1.setExtraOffsets(5F, 10F, 5F, 5F)
        chart1.dragDecelerationFrictionCoef = 0.95f

//        chart1.centerText = "圆形图标"
        tf = Typeface.createFromAsset(_mActivity.assets, "OpenSans-Regular.ttf")

        chart1.setCenterTextTypeface(Typeface.createFromAsset(_mActivity.assets, "OpenSans-Light.ttf"))
        chart1.centerText = generateCenterSpannableText2() // 设置圆形图内部文字
        chart1 .setCenterTextColor(Color.WHITE)
        chart1.setExtraOffsets(20f, 0f, 20f, 0f)

        chart1.isDrawHoleEnabled = true
        chart1.setHoleColor(R.color.chart_bg_color)


        chart1.setTransparentCircleColor(Color.WHITE)
        chart1.setTransparentCircleAlpha(90)

        chart1.setHoleRadius(75f) // 设置内部圆的半径
        chart1.setTransparentCircleRadius(55f) // 设置圆的半径

        chart1.setDrawCenterText(true)
//        chart1.setDrawEntryLabels(false)

        chart1.setRotationAngle(0f)
        // enable rotation of the chart by touch
        chart1.setRotationEnabled(true)
        chart1.setHighlightPerTapEnabled(true)


        val l = chart1.getLegend()
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP)
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT)
        l.setOrientation(Legend.LegendOrientation.VERTICAL)
        l.setDrawInside(false)
        l.setEnabled(false)
    }

    private fun setData(count: Int, range: Float) {

        val entries = ArrayList<PieEntry>()  //显示数据
        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        var parties : Array<String> = arrayOf("10%","30%","40%","20%","Party E")

        for (i in 0 until count) {
            entries.add(PieEntry((Math.random() * range).toFloat() + range / 5, parties[i % parties.size]))
        }

        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f

        // add a lot of colors

        val colors = ArrayList<Int>()
        colors.add(resources.getColor(R.color.bg_blue))
        colors.add(resources.getColor(R.color.bg_receipt))
        colors.add(resources.getColor(R.color.bg_cancel))
        colors.add(resources.getColor(R.color.bg_yellow))
//        for (c in ColorTemplate.VORDIPLOM_COLORS)
////            colors.add(resources.getColor(R.color.bg_blue))
//            colors.add(c)
//
//        for (c in ColorTemplate.JOYFUL_COLORS)
////            colors.add(resources.getColor(R.color.bg_receipt))
//            colors.add(c)
//
//        for (c in ColorTemplate.COLORFUL_COLORS)
////            colors.add(resources.getColor(R.color.bg_cancel))
//            colors.add(c)
//
//
//        for (c in ColorTemplate.LIBERTY_COLORS)
////            colors.add(resources.getColor(R.color.bg_yellow))
//            colors.add(c)
//
//        for (c in ColorTemplate.PASTEL_COLORS)
//            colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())

        dataSet.colors = colors
        //dataSet.setSelectionShift(0f);


        dataSet.valueLinePart1OffsetPercentage = 80f
        dataSet.valueLinePart1Length = 0.2f
        dataSet.valueLinePart2Length = 0.4f
        //dataSet.setUsingSliceColorAsValueLineColor(true);

        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE

        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.BLACK)
        data.setValueTypeface(tf)
        chart1.setData(data)

        // undo all highlights
        chart1.highlightValues(null)

        chart1.invalidate()
    }

    private fun generateCenterSpannableText2(): SpannableString {
        val s = SpannableString("下单量\n234")
        s.setSpan(RelativeSizeSpan(1.0f), 0, 3, 0)
        s.setSpan(StyleSpan(Typeface.NORMAL), 3, s.length - 4, 0)
        s.setSpan(ForegroundColorSpan(resources.getColor(R.color.white)), 3, s.length - 4, 0)
//        s.setSpan(StyleSpan(Typeface.ITALIC), s.length - 3, s.length, 0)
        s.setSpan(RelativeSizeSpan(2f), s.length - 3,  s.length, 0)
        s.setSpan(ForegroundColorSpan(resources.getColor(R.color.white)), s.length - 4, s.length, 0)
        return s
    }

    private fun generateCenterSpannableText(): SpannableString {
        val s = SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda")
        s.setSpan(RelativeSizeSpan(1.5f), 0, 14, 0)
        s.setSpan(StyleSpan(Typeface.NORMAL), 14, s.length - 15, 0)
        s.setSpan(ForegroundColorSpan(Color.GRAY), 14, s.length - 15, 0)
        s.setSpan(RelativeSizeSpan(.65f), 14, s.length - 15, 0)
        s.setSpan(StyleSpan(Typeface.ITALIC), s.length - 14, s.length, 0)
        s.setSpan(ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length - 14, s.length, 0)
        return s
    }


}
