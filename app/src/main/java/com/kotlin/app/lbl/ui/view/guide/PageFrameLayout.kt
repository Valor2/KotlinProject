package com.shenyun.volumecostprofit.guide

import android.content.Context
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.kotlin.app.lbl.BaseApplication
import com.kotlin.app.lbl.PageActivity
import com.kotlin.app.lbl.R

class PageFrameLayout : FrameLayout,ViewPager.OnPageChangeListener{
    private val fragments: MutableList<PageFragment> = mutableListOf()

    private var iv_vp : MutableList<ImageView>? = null
    private var dot_ll: LinearLayout? = null
    private var dot_on: Int = 0
    private var dot_off:Int = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)



    /**
     * dpתpx
     */
    fun dip2px(ctx: Context, dpValue: Float): Int {
        val scale = ctx.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 设置资源文件并初始化控件
     *
     * @param layoutIds
     */
    fun setUpViews(layoutIds: IntArray, dot_on: Int, dot_off: Int) {
        this.dot_on = dot_on
        this.dot_off = dot_off
        iv_vp = mutableListOf()

        dot_ll = LinearLayout(BaseApplication.getInstance().getContext())
        val params = FrameLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            dip2px(BaseApplication.getInstance().getContext(), 35f)
        )
        dot_ll!!.gravity = Gravity.CENTER
        params.gravity = Gravity.BOTTOM
        dot_ll!!.layoutParams = params
        dot_ll!!.orientation = LinearLayout.HORIZONTAL

        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lp.setMargins(8, 0, 8, 0)

        for (i in layoutIds.indices) {
            val pageFragment = PageFragment()
            val bundle = Bundle()
            bundle.putInt("index", i)
            bundle.putInt("count", layoutIds.size)
            bundle.putInt("layoutId", layoutIds[i])

            pageFragment.arguments = bundle
            fragments!!.add(pageFragment)
            val view = ImageView(context)
            view.setImageResource(dot_on)
            view.layoutParams = lp
            iv_vp?.add(view)
            dot_ll?.addView(iv_vp!![i])
        }

        setSelectVp(0)
        val activity = context as PageActivity
        val viewPager = ViewPager(BaseApplication.getInstance().getContext())
        viewPager.id = R.id.id_page
        viewPager.adapter = PageFragmentAdapter(activity!!.supportFragmentManager, this?.fragments)
        viewPager.addOnPageChangeListener(this)

        addView(viewPager)
        addView(dot_ll)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (fragments != null) {
            if (position == fragments.size - 1) {
                dot_ll!!.alpha = 0f
            } else {
                dot_ll!!.alpha = 1f
            }
        }

    }

    override fun onPageSelected(position: Int) {
        setSelectVp(position)
    }

    override fun onPageScrollStateChanged(state: Int) {}

    /**
     * 切换轮播图点
     *
     * @param index
     */
    fun setSelectVp(index: Int) {
        for (i in iv_vp!!.indices) {
            if (i == index) {
                iv_vp!![i].setImageResource(dot_on)
            } else {
                iv_vp!![i].setImageResource(dot_off)
            }
        }
    }
}