package com.kotlin.app.lbl.adatpter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import cn.youngkaaa.yviewpager.YFragmentPagerAdapter

class YPagerAdatpter(fm:FragmentManager,list: ArrayList<Fragment>) :YFragmentPagerAdapter(fm) {

    var mFm : FragmentManager = fm!!
    var list : ArrayList<Fragment> = list
    override fun getItem(position: Int): Fragment {
        return  list.get(position)
    }

    override fun getCount(): Int {
       return list.size
    }
}