package com.kotlin.app.lbl

import android.app.Activity
import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import java.util.*

class BaseApplication : MultiDexApplication(){

        companion object {

            private var activityStack: Stack<Activity>? = null
            private var mContext: Context? = null
            private var application: BaseApplication? = null

            fun getInstance(): BaseApplication {
                if (application == null) {
                    application = BaseApplication()
                }
                return application!!
            }

        }

        override fun attachBaseContext(base: Context?) {
            super.attachBaseContext(base)
            MultiDex.install(this)
        }


        override fun onCreate() {
            val processName = packageName
            if (processName != null) {
                val defaultProcess = processName == "com.kotlin.app.lbl"
                if (defaultProcess) {
                    initApp()  //初始化
                }
            }
            super.onCreate()
        }

        fun initApp() {
            mContext = this
        }

        fun getContext() = mContext!!.applicationContext

        fun AppExit() {
            finisAllActivity()
        }

        fun finisAllActivity() {
            var i = 0
            val size = activityStack!!.size
            while (i < size) {
                if (null != activityStack!!.get(i)) {
                    activityStack!!.get(i).finish()
                }
                i++
            }
            activityStack!!.clear()
        }

        fun AddActivity(activity: Activity) {
            if (activityStack == null) {
                activityStack = Stack()
            }
            activityStack!!.add(activity)
        }

        fun finishActivity(activity: Activity) {
            if (activity != null) {
                activityStack!!.remove(activity)
                activity.finish()
            }
        }

}