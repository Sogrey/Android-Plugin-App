package top.sogrey.bundle_library;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 代理activity，加载到第三方apk的activity
 */
public class ProxyActivity extends Activity {
    PluginInterface pluginInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Set<String> ketSet = bundle.keySet();
        if(!ketSet.contains("className")){
            try {
                throw new NonStandardPlugInException("非标准插件异常:宿主与插件之间通讯，确保Intent中传入了`className`,即插件中Activity");
            } catch (NonStandardPlugInException e) {
                e.printStackTrace();
            }
            finish();
            return;
        }
//        //取出除`className`外的其他参数
//        Map<String,Object> ketValue = new HashMap<>();
//        for (String key : ketSet) {
//            if(!"className".equals(key)){
//                Object value = bundle.get(key);
//                ketValue.put(key,value);
//            }
//        }

        //拿到第三方apk中的activity的name
        String className = bundle.getString("className");
        try {
            //获取到第三方activity的类对象
            Class<?> aClass = PluginManager.getInstance().getDexClassLoader().loadClass(className);
            //第三方activity的类对象实例化
            Object newInstance = aClass.newInstance();
            //判断是否符合插件标准
            if (newInstance instanceof PluginInterface) {
                pluginInterface = (PluginInterface) newInstance;
                //将代理activity的上下文注入到插件activity里
                pluginInterface.attach(this);
                //去执行插件apk中的activity的onCreate
                pluginInterface.onCreate(bundle);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getStringExtra("className");
        Intent intent1 = new Intent(this, ProxyActivity.class);
        intent1.putExtra("className", className);
        super.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pluginInterface.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        pluginInterface.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pluginInterface.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pluginInterface.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pluginInterface.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        pluginInterface.onSaveInstanceState(outState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        pluginInterface.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        pluginInterface.onBackPressed();
    }

    /**
     * 非标准插件异常
     */
    static class NonStandardPlugInException extends Exception {   // 创建自定义异常类
        String message; // 定义 String 类型变量

        public  NonStandardPlugInException(String ErrorMessager){  // 父类方法
            message = ErrorMessager;
        }

        public String getMessage(){ // 覆盖 getMessage() 方法
            return message;
        }

    }
}
