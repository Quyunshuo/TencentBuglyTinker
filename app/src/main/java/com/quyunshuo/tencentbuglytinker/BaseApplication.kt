package com.quyunshuo.tencentbuglytinker

import com.tencent.tinker.loader.app.TinkerApplication
import com.tencent.tinker.loader.shareutil.ShareConstants

/**
 * @Author: QuYunShuo
 * @Time: 2020/7/27
 * @Class: BaseApplication
 * @Remark:
 */
class BaseApplication() : TinkerApplication(
    ShareConstants.TINKER_ENABLE_ALL,
    "com.quyunshuo.tencentbuglytinker.BaseApplicationLike",
    "com.tencent.tinker.loader.TinkerLoader",
    false
)