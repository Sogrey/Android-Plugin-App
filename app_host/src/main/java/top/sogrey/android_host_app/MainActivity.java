package top.sogrey.android_host_app;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import top.sogrey.bundle_library.PluginManager;
import top.sogrey.bundle_library.ProxyActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 申请权限
     *
     * @param view
     */
    public void applicationAuthority(View view) {
        //运行时权限
        ActivityCompat.requestPermissions(
                this,
                new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                }, 100);
    }

    /**
     * 跳转第三方apk
     *
     * @param view
     */
    public void jumpApk(View view) {
        PluginManager pluginManager = PluginManager.getInstance();
        //设置上下文
        pluginManager.setContext(getApplicationContext());
        //加载第三方apk
        pluginManager.loadPath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/app_plugin-debug.apk");
        //不管要跳转哪个apk,都需要先跳转到代理activity中去
        Intent intent = new Intent(this, ProxyActivity.class);
        //获取插件apk的第一个activity的name
        String apkMainActivity = pluginManager.getPackageInfo().activities[0].name;
        intent.putExtra("className",apkMainActivity);
        startActivity(intent);
    }
}
