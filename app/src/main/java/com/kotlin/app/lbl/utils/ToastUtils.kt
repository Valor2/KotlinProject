package com.kotlin.app.lbl.utils

import android.content.Context
import android.widget.Toast

/**
 *
 *
 * toast工具类封装
 */
object ToastUtils {
    private var mToast: Toast? = null

    /**
     * 显示一个toast提示
     */
    fun shwoToast(context: Context, string: String) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }
}
/**
 * 显示一个toast提示
 *
 * @param text toast字符串
 */
