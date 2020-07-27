package com.quyunshuo.tencentbuglytinker

import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.content.Intent
import androidx.multidex.MultiDex
import com.tencent.bugly.Bugly
import com.tencent.bugly.beta.Beta
import com.tencent.tinker.entry.DefaultApplicationLike


/**
 * @Author: QuYunShuo
 * @Time: 2020/7/27
 * @Class: BaseApplicationLike
 * @Remark: Application代理类
 */
class BaseApplicationLike(
    application: Application,
    tinkerFlags: Int,
    tinkerLoadVerifyFlag: Boolean,
    applicationStartElapsedTime: Long,
    applicationStartMillisTime: Long,
    tinkerResultIntent: Intent
) : DefaultApplicationLike(
    application,
    tinkerFlags,
    tinkerLoadVerifyFlag,
    applicationStartElapsedTime,
    applicationStartMillisTime,
    tinkerResultIntent
) {
    override fun onCreate() {
        super.onCreate()
        initBugly()
    }

    /**
     * 初始化腾讯 Bugly 热更新
     */
    private fun initBugly() {
        Bugly.init(application, "0b6134d7ba", true)
    }

    override fun onBaseContextAttached(base: Context?) {
        super.onBaseContextAttached(base)
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base)
        // 安装tinker
        Beta.installTinker(this)
    }

    public fun registerActivityLifecycleCallback(callbacks: ActivityLifecycleCallbacks) {
        application.registerActivityLifecycleCallbacks(callbacks)
    }
}