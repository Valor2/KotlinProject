package com.shenyun.volumecostprofit.guide

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class PageFragmentAdapter(
    var fm:FragmentManager,
    var array: MutableList<PageFragment>?) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return array!!.get(position)
    }

    override fun getCount(): Int {
        return array!!.size
     }
}