package com.quyunshuo.tencentbuglytinker

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.view.View
import android.widget.Toast
import com.permissionx.guolindev.PermissionX
import com.quyunshuo.tencentbuglytinker.databinding.ActivityMainBinding
import com.tencent.bugly.beta.Beta

/**
 * @Author: QuYunShuo
 * @Time: 2020/7/27
 * @Class: MainActivity
 * @Remark:
 */
class MainActivity : BaseActivity() {

    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun getContentView(): View = mBinding.root

    override fun initView() {
        mBinding.mTitleTv.text =
            resources.getString(R.string.app_title_version, BuildConfig.VERSION_CODE)
        mBinding.mUpdateBtn.setOnClickListener {
            Beta.checkUpgrade()
        }
        getPermission()
    }

    /**
     * 获取所需权限
     */
    @SuppressLint("InlinedApi")
    private fun getPermission() {
        PermissionX
            .init(this)
            .permissions(
                Manifest.permission.REQUEST_INSTALL_PACKAGES,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_LOGS
            )
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    Toast.makeText(this, "已全部授予权限", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        this,
                        "已授予权限: $grantedList 未授予权限: $deniedList",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // 在 Android 8 以后，未知应用安装权限的开关被除掉，取而代之的是未知来源应用的管理列表，需要在里面打开每个应用的未知来源的安装权限
                // 由于 Android 8 以后 Manifest.permission.REQUEST_INSTALL_PACKAGES 不再是运行时权限 所以不能使用常规的检查权限方式
                // 要使用 packageManager.canRequestPackageInstalls() 来判断有无此权限 没有此权限就要引导用户去手动开启此权限
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val canRequestPackageInstalls: Boolean =
                        packageManager.canRequestPackageInstalls()
                    if (!canRequestPackageInstalls) {
                        val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES)
                        startActivityForResult(intent, 10086)
                    }
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 处理手动开启权限的情况
        if (requestCode == 10086 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val canRequestPackageInstalls: Boolean =
                packageManager.canRequestPackageInstalls()
            if (canRequestPackageInstalls) {
                Toast.makeText(this, "已授予权限", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "未授予权限", Toast.LENGTH_SHORT).show()
            }
        }
    }
}