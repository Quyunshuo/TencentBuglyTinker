#需要注意的地方:
1. 权限问题
 根据Android版本进行动态权限申请的适配
 Manifest.permission.REQUEST_INSTALL_PACKAGES 权限在 Android 8 以后,未知应用安装权限的开关被除掉
 取而代之的是未知来源应用的管理列表,需要在里面打开每个应用的未知来源的安装权限
 由于 Android 8 以后 Manifest.permission.REQUEST_INSTALL_PACKAGES 不再是运行时权限 所以不能使用常规的检查权限方式
 要使用 packageManager.canRequestPackageInstalls() 来判断有无此权限 没有此权限就要引导用户去手动开启此权限
 保证必需的权限都授予
2. Android 8 限制HTTP明文请求的问题
 Bugly 的全量更新升级策略是使用 HTTP 进行数据传输,所以要使用 HTTP 明文请求,对 Android 8 以上版本要进行适配,热更新采用的是HTTPS
3.热更新混淆问题
 开启混淆打补丁包会在混淆环节编译失败,原因尚不明,问题未解决,所以我先练习的时候把混淆关闭了