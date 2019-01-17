package com.kotlin.app.lbl.ui.fragment.viewpage


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.Fragment
import android.text.method.Touch
import android.view.*
import android.widget.AdapterView
import android.widget.PopupWindow
import android.widget.TextView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.kotlin.app.lbl.R
import com.kotlin.app.lbl.base.BaseMainFragmet
import com.kotlin.app.lbl.mvp.contract.MessagePopWindowListener
import com.kotlin.app.lbl.utils.PopupWindowUtil
import kotlinx.android.synthetic.main.fragment_chart_bottom.*
import kotlinx.android.synthetic.main.popup_content_layout.view.*

/**
 * A simple [Fragment] subclass.
 *
 */
class ChartBottomFragment : BaseMainFragmet() ,MessagePopWindowListener{


    private val day_8: List<Float> = listOf(230f, 250f, 270f, 180f, 230f, 200f, 240f, 270f)
    private val week_8: List<Float> = listOf(1400f, 1600f, 2000f, 960f, 1300f, 1200f, 1700f, 2400f)
    private val mount_8: List<Float> = listOf(23000f, 25000f, 27000f, 18000f, 23000f, 20000f, 24000f, 27000f)
    private var mPopupWindow: PopupWindow? = null
    private var RawX: Int = 0
    private var RawY: Int = 0

    override fun getLayoutResources(): Int {
        return R.layout.fragment_chart_bottom
    }

    override fun initView() {
        initChartView()
    }

    override fun initData() {
        setData(7,3)

        linearlayout2.setOnClickListener {
//            RawX = tv_view.x.toInt()
//            RawY = tv_view.y.toInt()
            showPopupWindow(linearlayout2,this)

        }



    }

    fun initChartView(){

        bar_chart!!.description.isEnabled = false
        bar_chart!!.setMaxVisibleValueCount(60)
        bar_chart!!.setPinchZoom(false)
        bar_chart!!.setDrawBarShadow(false)
        bar_chart!!.setDrawGridBackground(false)

        var xAxis: XAxis = bar_chart!!.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        bar_chart!!.axisLeft.setDrawGridLines(false)
        bar_chart!!.animateY(1000)
        bar_chart!!.legend.isEnabled = false
        setData(7,3)
    }

    private fun setData(count: Int, type: Int = 1) {
        var yVals1: MutableList<BarEntry> = mutableListOf()
        var i: Int = 1
        while (i <= count) {
            when (type) {
                1 -> yVals1.add(BarEntry(i.toFloat(), day_8[i - 1]))
                2 -> yVals1.add(BarEntry(i.toFloat(), week_8[i - 1]))
                3 -> yVals1.add(BarEntry(i.toFloat(), mount_8[i - 1]))
            }
            i++
        }
        var set1: BarDataSet? = null
        if (bar_chart?.data != null && bar_chart!!.data.dataSetCount > 0) {
            set1 = bar_chart!!.data.getDataSetByIndex(0) as BarDataSet?
            set1!!.values = yVals1
            bar_chart!!.data.notifyDataChanged()
            bar_chart!!.notifyDataSetChanged()
        } else {
            set1 = BarDataSet(yVals1, "下单量")
            set1.color = Color.rgb(15, 116, 240)
            set1.setDrawValues(false)
            var dataSets: MutableList<IBarDataSet> = mutableListOf()
            dataSets.add(set1)
            var data: BarData = BarData(dataSets)
            bar_chart!!.data = data
            bar_chart!!.setFitBars(true)

        }
        for (set in bar_chart?.data?.dataSets!!) {
            set.setDrawValues(true)
        }
        bar_chart!!.invalidate()
    }

    override fun PopWindowDismiss(type: Int) {
        if(type == 1){
            setData(7,1)
        }else if(type == 2){
            setData(7,2)
        }else if(type == 3){
            setData(7,3)
        }
        mPopupWindow!!.dismiss()
    }

    override fun SelectPopWindowDismiss() {
        if (mPopupWindow != null) {
            mPopupWindow!!.dismiss()
        }

    }

    private fun showPopupWindow(anchorView: View, listener: MessagePopWindowListener) {
        val contentView = getPopupWindowContentView( listener)
        mPopupWindow = PopupWindow(
            contentView,
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true
        )
        // 如果不设置PopupWindow的背景，有些版本就会出现一个问题：无论是点击外部区域还是Back键都无法dismiss弹框
        mPopupWindow!!.setBackgroundDrawable(ColorDrawable())
        // 设置好参数之后再show
        RawX =  anchorView.rotationX.toInt()
        RawY =  anchorView.rotationY.toInt()
        val windowPos = PopupWindowUtil.calculatePopWindowPos(anchorView, contentView, RawX, RawY)
        mPopupWindow!!.showAtLocation(anchorView, Gravity.TOP or Gravity.START, windowPos[0], windowPos[1])
    }


    private fun getPopupWindowContentView( listener: MessagePopWindowListener): View {
        // 一个自定义的布局，作为显示的内容
        val layoutId = R.layout.popup_content_layout   // 布局ID
        val contentView = LayoutInflater.from(_mActivity!!).inflate(layoutId, null)

        val menuItemOnClickListener = View.OnClickListener { v ->
            if ((v as TextView).text == "天") {
                listener.PopWindowDismiss(1)
            } else if (v.text == "周") {
                listener.PopWindowDismiss(2)
            } else if (v.text == "月") {
                listener.PopWindowDismiss(3)
            }
            listener.SelectPopWindowDismiss()
        }
        contentView.menu_item1.setOnClickListener(menuItemOnClickListener)
        contentView.menu_item2.setOnClickListener(menuItemOnClickListener)
        contentView.menu_item3.setOnClickListener(menuItemOnClickListener)
        return contentView
    }


}
