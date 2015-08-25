package com.example.test4xposed;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Main implements IXposedHookLoadPackage {

    /**
     * 包加载时候的回调
     */
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        // 将包名不是 com.example.test4xposed 的应用剔除掉
        if (!lpparam.packageName.equals("com.example.test4xposed"))
            return;
        XposedBridge.log("Loaded app: " + lpparam.packageName);

        findAndHookMethod(
                "com.example.test4xposed.MainActivity", lpparam.classLoader, "testFunction", String.class, String.class,
                new XC_MethodHook() {

                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("beforeHookedMethod");
                        XposedBridge.log("参数1 = " + param.args[0]);
                        XposedBridge.log("参数2 = " + param.args[1]);
                        // 若注释掉下面这行，并用adb logcat -s test查看，会看到log
                        param.setResult("try prevent the origin call");
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("afterHookedMethod");
                        XposedBridge.log("参数1 = " + param.args[0]);
                        XposedBridge.log("参数2 = " + param.args[1]);

                    }

                    @Override
                    protected void call(Param param) throws Throwable {
                        super.call(param);
                        XposedBridge.log("call~");
                    }

                });
    }
}
