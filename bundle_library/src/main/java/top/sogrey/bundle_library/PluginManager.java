package top.sogrey.bundle_library;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * 插件管理类，用来加在第三方插件apk资源对象以及类对象
 */
public class PluginManager {
    //单例
    private static PluginManager pluginManager = new PluginManager();
    //上下文
    private Context context;
    //插件apk的资源对象
    private Resources resources;
    //插件apk的类对象
    private DexClassLoader dexClassLoader;
    //插件apk的包信息
    private PackageInfo packageInfo;

    private PluginManager() {
    }

    public static PluginManager getInstance() {
        return pluginManager;
    }

    /**
     * 接收上下文
     *
     * @param context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * 用于加载第三方apk
     *
     * @param apkPath
     */
    public void loadPath(String apkPath) {
        //获取当前应用的私有存储路径
        File dexOutFile = context.getDir("dex", Context.MODE_PRIVATE);
        //初始化类加载器,用来加载第三方apk中的activity类
        dexClassLoader = new DexClassLoader(apkPath, dexOutFile.getAbsolutePath(), null, context.getClassLoader());
        //获取包管理器
        PackageManager packageManager = context.getPackageManager();
        //通过包管理器去获取第三方apk中的包管理信息类
        packageInfo = packageManager.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);//??

        //获取第三方插件中的资源
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            //addAssetPath 方法用来指明 AssetManager 对象要管理的资源对象的dex文件路径
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, apkPath);
            resources = new Resources(assetManager,/*计量标准*/context.getResources().getDisplayMetrics(),/*基本配置*/context.getResources().getConfiguration());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public Resources getResources() {
        return resources;
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }
}
