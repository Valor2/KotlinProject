package com.kotlin.app.lbl.ui.fragment


import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.widget.SeekBar
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.kotlin.app.lbl.R
import com.kotlin.app.lbl.base.BaseMainFragmet
import kotlinx.android.synthetic.main.fragment_circular_view.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 *
 */
class CircularViewFragment : BaseMainFragmet(),SeekBar.OnSeekBarChangeListener {

    var tf : Typeface? = null

    companion object {
        fun newInstance(): CircularViewFragment {
            val args = Bundle()
            val fragment = CircularViewFragment()
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_circular_view
    }

    override fun initView() {
        seekBar1.setOnSeekBarChangeListener(this)
        seekBar2.setOnSeekBarChangeListener(this)
        chart1.setUsePercentValues(true)
        chart1.description.isEnabled = false
        chart1.setExtraOffsets(5F, 10F, 5F, 5F)
        chart1.dragDecelerationFrictionCoef = 0.95f

        chart1.centerText = "圆形图标"
        tf = Typeface.createFromAsset(_mActivity.assets, "OpenSans-Regular.ttf")

        chart1.setCenterTextTypeface(Typeface.createFromAsset(_mActivity.assets, "OpenSans-Light.ttf"))
        chart1.centerText = generateCenterSpannableText() // 设置圆形图内部文字

        chart1.setExtraOffsets(20f, 0f, 20f, 0f)

        chart1.isDrawHoleEnabled = true
        chart1.setHoleColor(Color.WHITE)

        chart1.setTransparentCircleColor(Color.WHITE)
        chart1.setTransparentCircleAlpha(110)

        chart1.setHoleRadius(58f)
        chart1.setTransparentCircleRadius(61f)

        chart1.setDrawCenterText(true)

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

    override fun initData() {
        setData(5,5f)
        button.setOnClickListener {
            // false圆形饼图 true 甜甜圈园图
            if (chart1.isDrawHoleEnabled())
                chart1.setDrawHoleEnabled(false)
            else
                chart1.setDrawHoleEnabled(true)
            chart1.invalidate()  // 重新加饼图
        }
        button1.setOnClickListener {
            // fasle不显示文字，true显示文字
            if (chart1.isDrawCenterTextEnabled())
                chart1.setDrawCenterText(false)
            else
                chart1.setDrawCenterText(true)
            chart1.invalidate()  // 重新加饼图
        }
        button2.setOnClickListener {
            //隐藏饼图上的文字
            chart1.setDrawEntryLabels(!chart1.isDrawEntryLabelsEnabled())
            chart1.invalidate()  // 重新加饼图
        }
        button3.setOnClickListener {
            //改变圆形图显示
            val toSet = !chart1.isDrawRoundedSlicesEnabled() || !chart1.isDrawHoleEnabled()
            chart1.setDrawRoundedSlices(toSet)
            if (toSet && !chart1.isDrawHoleEnabled()) {
                chart1.setDrawHoleEnabled(true)
            }
            if (toSet && chart1.isDrawSlicesUnderHoleEnabled()) {
                chart1.setDrawSlicesUnderHole(false)
            }
            chart1.invalidate()
        }
        button4.setOnClickListener {
            //隐藏虚线指引文字
            for (set in chart1.getData().getDataSets())
                set.setDrawValues(!set.isDrawValuesEnabled())
            chart1.invalidate()
        }

    }

    override fun onProgressChanged(seekBar : SeekBar?, progress: Int, formUser : Boolean) {
        setData(5,5f)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

    private fun setData(count: Int, range: Float) {

        val entries = ArrayList<PieEntry>()

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        var parties : Array<String> = arrayOf("Party A","Party B","Party C","Party D","Party E")
        for (i in 0 until count) {
            entries.add(PieEntry((Math.random() * range).toFloat() + range / 5, parties[i % parties.size]))
        }

        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.sliceSpace = 3f
        dataSet.selectionShift = 5f

        // add a lot of colors

        val colors = ArrayList<Int>()

        for (c in ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS)
            colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS)
            colors.add(c)

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
