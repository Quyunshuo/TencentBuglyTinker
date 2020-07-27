package com.quyunshuo.tencentbuglytinker

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @Author: QuYunShuo
 * @Time: 2020/7/27
 * @Class:BaseActivity
 * @Remark:
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentView())
        initView()
    }

    protected abstract fun getContentView(): View

    protected abstract fun initView()
}