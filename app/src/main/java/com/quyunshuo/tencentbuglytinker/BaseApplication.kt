package com.quyunshuo.tencentbuglytinker

import android.app.Application
import com.tencent.bugly.Bugly

/**
 * @Author: QuYunShuo
 * @Time: 2020/7/27
 * @Class: BaseApplication
 * @Remark:
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initBugly()
    }

    /**
     * 初始化腾讯 Bugly 热更新
     */
    private fun initBugly() {
        Bugly.init(applicationContext, "0b6134d7ba", true)
    }
}