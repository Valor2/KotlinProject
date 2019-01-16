package com.kotlin.app.lbl.ui.fragment.viewpage


import android.os.Bundle
import android.support.v4.app.Fragment
import com.kotlin.app.lbl.R
import com.kotlin.app.lbl.base.BaseMainFragmet
import com.kotlin.app.lbl.ui.fragment.CircularViewFragment


/**
 * A simple [Fragment] subclass.
 *
 */
class ChartFragment : BaseMainFragmet() {

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
    }

    override fun initData() {
    }


}
