package top.sogrey.bundle_library;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * 所有外置apk中的页面必须要实现的接口类，面向接口开发，定义插件apk activity标准。
 */
public interface PluginInterface {
    public void attach(Activity hostActivity);
    public void onCreate(Bundle savedInstanceState);
    public void onStart();
    public void onResume();
    public void onPause();
    public void onStop();
    public void onDestroy();
    public void onSaveInstanceState(Bundle outState);
    public boolean onTouchEvent(MotionEvent event);
    public void onBackPressed();
}
