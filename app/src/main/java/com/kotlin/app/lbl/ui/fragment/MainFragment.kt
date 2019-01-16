package com.kotlin.app.lbl.ui.fragment

import android.app.Service
import android.os.Bundle
import android.os.Vibrator
import com.kotlin.app.lbl.R
import com.kotlin.app.lbl.base.BaseMainFragmet
import com.kotlin.app.lbl.ui.view.BottomBar
import com.kotlin.app.lbl.ui.view.BottomBarTab
import kotlinx.android.synthetic.main.wechat_fragment_main.*
import me.yokeyword.fragmentation.SupportFragment

class MainFragment : BaseMainFragmet() {
    override fun initData() {
    }

    var mFragmet: Array<SupportFragment>? = null
    var vibrator: Vibrator? = null

    val FIRST  = 0
    val SECOND = 1
    val THIRD  = 2
    val FOURTH = 3

    override fun getLayoutResources(): Int {
       return R.layout.wechat_fragment_main
    }



    /**
     * kotlin静态方法用companion 包着，返回对象是: MainFragment
     */
    companion object {
        fun newInstance(): MainFragment {
            val args = Bundle()
            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override  fun initView() {
        mFragmet = Array(4, { SupportFragment() })
        vibrator = _mActivity.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator

        bottomBar.addItem(BottomBarTab(_mActivity, R.mipmap.icon_home, getString(R.string.home)))
                 .addItem(BottomBarTab(_mActivity, R.mipmap.icon_interaction, getString(R.string.Interaction)))
                 .addItem(BottomBarTab(_mActivity, R.mipmap.icon_wallet, getString(R.string.wallet)))
                 .addItem(BottomBarTab(_mActivity, R.mipmap.icon_account_circle, getString(R.string.wallet)))
        bottomBar.setOnTabSelectedListener(object : BottomBar.OnTabSelectedListener{
            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int, prePosition: Int) {
                showHideFragment(mFragmet!![position],mFragmet!![prePosition])
               var tab :BottomBarTab? = bottomBar.getItem(SECOND)
                if (position == SECOND) {
                     tab!!.setUnreadCount(0)
                } else {
                    tab!!.setUnreadCount(tab.getUnreadCount() + 1);
                }
            }

            override fun onTabReselected(position: Int) {
            }
        })
        initPage()
    }


    fun initPage(){
        var firstFragment:SupportFragment? = findChildFragment(HomeFragment::class.java)
        if(firstFragment == null){
            mFragmet!![FIRST] = HomeFragment.newInstance()
            mFragmet!![SECOND] = CircularViewFragment.newInstance()
            mFragmet!![THIRD] = HistogramViewFragment.newInstance()
            mFragmet!![FOURTH] = SummaryFragment.newInstance()

            loadMultipleRootFragment(R.id.fl_tab_container,FIRST
                ,mFragmet!![FIRST]
                ,mFragmet!![SECOND]
                ,mFragmet!![THIRD]
                ,mFragmet!![FOURTH])
        }else{
            mFragmet!![FIRST] = findChildFragment(HomeFragment().javaClass)
            mFragmet!![SECOND] = findChildFragment(CircularViewFragment().javaClass)
            mFragmet!![THIRD] = findChildFragment(HistogramViewFragment().javaClass)
            mFragmet!![FOURTH] = findChildFragment(SummaryFragment().javaClass)
        }
    }


}