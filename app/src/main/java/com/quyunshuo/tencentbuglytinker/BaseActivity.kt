package com.quyunshuo.tencentbuglytinker

import android.os.Bundle
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
        setContentView(getLayoutId())
        initView()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initView()
}