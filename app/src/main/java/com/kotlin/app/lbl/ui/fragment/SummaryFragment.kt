package com.kotlin.app.lbl.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import cn.youngkaaa.yviewpager.YViewPager
import com.kotlin.app.lbl.R
import com.kotlin.app.lbl.adatpter.ViewPagerAdatpter
import com.kotlin.app.lbl.adatpter.YPagerAdatpter
import com.kotlin.app.lbl.base.BaseMainFragmet
import com.kotlin.app.lbl.ui.fragment.viewpage.*
import kotlinx.android.synthetic.main.fragment_summary.*


/**
 * A simple [Fragment] subclass.
 *
 */
class SummaryFragment : BaseMainFragmet() {

    lateinit var mFragments: ArrayList<Fragment>
    val STRATEGY = arrayOf("weekly", "monthly", "historical")
    var mTabs = listOf<String>("周排行","月排行","年排行").toMutableList()
    lateinit var mYFragments: ArrayList<Fragment>


    companion object {
        fun newInstance(): SummaryFragment {
            val args = Bundle()
            val fragment = SummaryFragment()
            fragment.setArguments(args)
            return fragment
        }
    }


    override fun getLayoutResources(): Int {
        return R.layout.fragment_summary
    }

    override fun initView() {
        var weekFragment: ChartFragment = ChartFragment()
        var weekBundle = Bundle()
        weekBundle.putString("strategy", STRATEGY[0])
        weekFragment.arguments = weekBundle

        var char1Fragment: Chart1Fragment = Chart1Fragment()
//        var char1Fragment: ChartFragment = ChartFragment()
        var char1bundle = Bundle()
        char1bundle.putString("strategy", STRATEGY[1])
        char1Fragment.arguments = char1bundle

        var char2Fragment: Chart2Fragment = Chart2Fragment()
//        var char2Fragment: ChartFragment = ChartFragment()
        var char2bundle = Bundle()
        char2bundle.putString("strategy", STRATEGY[2])
        char2Fragment.arguments = char2bundle

        mFragments = ArrayList()
        mFragments.add(weekFragment as Fragment)
        mFragments.add(char1Fragment as Fragment)
        mFragments.add(char2Fragment as Fragment)
        vp_content.adapter = ViewPagerAdatpter(fragmentManager!!, mFragments, mTabs)
        indicator.setViewPager(vp_content)
        setVViewPager()
    }

    fun setVViewPager(){
        mYFragments = ArrayList()
        var charBottomFragment: ChartBottomFragment = ChartBottomFragment()
        var charBottomTwoFragment: CharBottomTwoFragment = CharBottomTwoFragment()


        mYFragments.add(charBottomFragment as Fragment)
        mYFragments.add(charBottomTwoFragment as Fragment)

        viewpager.adapter = YPagerAdatpter(fragmentManager!!,mYFragments)
        viewpager.setDirection( YViewPager.VISIBLE)
    }

    override fun initData() {
    }



}
