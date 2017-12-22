package github.tornaco.xposedmoduletest.xposed.submodules;

import android.app.AndroidAppHelper;
import android.util.Log;

import java.util.Set;

import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import github.tornaco.xposedmoduletest.xposed.app.XAshmanManager;
import github.tornaco.xposedmoduletest.xposed.util.XposedLog;

/**
 * Created by guohao4 on 2017/10/31.
 * Email: Tornaco@163.com
 */

// Hook hookUnCaughtErr settings.
class RuntimeInitSubModule extends IntentFirewallAndroidSubModule {

    @Override
    public void initZygote(IXposedHookZygoteInit.StartupParam startupParam) {
        super.initZygote(startupParam);
        hookUnCaughtErr();
    }

    private void hookUnCaughtErr() {
        XposedLog.verbose("hookUnCaughtErr...");
        try {
            final Class c = XposedHelpers.findClass("com.android.internal.os.RuntimeInit$UncaughtHandler",
                    null);
            Set unHooks = XposedBridge.hookAllMethods(c, "uncaughtException",
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            // Wrap err log to xp log.
                            Thread t = (Thread) param.args[0];
                            Throwable e = (Throwable) param.args[1];
                            String currentPackage = AndroidAppHelper.currentPackageName();
                            String trace = Log.getStackTraceString(e);
                            XposedLog.verbose("uncaughtException on currentPackage@%s, thread@%s, throwable@%s", currentPackage, t, e);
                            XposedLog.verbose("***** FATAL EXCEPTION TRACE DUMP APM-S*****\n%s", trace);

                            // Now report to ash man.
                            XAshmanManager xAshmanManager = XAshmanManager.get();
                            if (xAshmanManager.isServiceAvailable()) {
                                xAshmanManager.onApplicationUncaughtException(currentPackage, t.getName(), e.toString(), trace);
                            }
                        }
                    });
            XposedLog.verbose("hookUnCaughtErr OK:" + unHooks);
            setStatus(unhooksToStatus(unHooks));
        } catch (Exception e) {
            XposedLog.verbose("Fail hookUnCaughtErr: " + Log.getStackTraceString(e));
            setStatus(SubModuleStatus.ERROR);
            setErrorMessage(Log.getStackTraceString(e));
        }
    }
}
