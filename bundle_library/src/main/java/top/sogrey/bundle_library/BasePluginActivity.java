package top.sogrey.bundle_library;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Set;

public class BasePluginActivity extends Activity implements PluginInterface {
    //主APP的引用对象 - 代理activity
    protected Activity that;

    @Override
    public void attach(Activity hostActivity) {
        this.that = hostActivity;
    }

    /**
     * 获取上下文
     *
     * @return
     */
    public Context getContext() {
        return that == null ? this : that;
    }

    @Override
    public void setContentView(View view) {
        if (that == null) {
            super.setContentView(view);
        } else {
            that.setContentView(view);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if (that == null) {
            super.setContentView(layoutResID);
        } else {
            that.setContentView(layoutResID);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (that == null) {
            return super.findViewById(id);
        } else {
            return that.findViewById(id);
        }
    }

    @Override
    public Intent getIntent() {
        if (that == null) {
            return super.getIntent();
        } else {
            return that.getIntent();
        }
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        if (that == null) {
            return super.getLayoutInflater();
        } else {
            return that.getLayoutInflater();
        }
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        if (that == null) {
            return super.getApplicationInfo();
        } else {
            return that.getApplicationInfo();
        }
    }

    @Override
    public Window getWindow() {
        if (that == null) {
            return super.getWindow();
        } else {
            return that.getWindow();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if (that == null) {
            return super.getWindowManager();
        } else {
            return that.getWindowManager();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        if (that == null) {
            super.startActivity(intent);
        } else {
            Bundle bundle = intent.getExtras();
            Set<String> ketSet = bundle.keySet();

            //偷梁换柱
            Intent newIntent = new Intent(that, ProxyActivity.class);
            newIntent.putExtras(bundle);
            newIntent.putExtra("className", intent.getComponent().getClassName());
            that.startActivity(newIntent);
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (that == null) {
            super.startActivityForResult(intent, requestCode);
        } else {
            Bundle bundle = intent.getExtras();

            //偷梁换柱
            Intent newIntent = new Intent(that, ProxyActivity.class);
            newIntent.putExtras(bundle);
            newIntent.putExtra("className", intent.getComponent().getClassName());
            that.startActivityForResult(intent, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        if (that == null) {
            super.startActivityForResult(intent, requestCode, options);
        } else {
            Bundle bundle = intent.getExtras();

            //偷梁换柱
            Intent newIntent = new Intent(that, ProxyActivity.class);
            newIntent.putExtras(bundle);
            newIntent.putExtra("className", intent.getComponent().getClassName());
            that.startActivityForResult(intent, requestCode, options);
        }
    }

    public void setResult_Plugin(int resultCode) {
        if (that == null) {
            super.setResult(resultCode);
        } else {
            that.setResult(resultCode);
        }
    }

    public void setResult_Plugin(int resultCode, Intent data) {
        if (that == null) {
            super.setResult(resultCode, data);
        } else {
            that.setResult(resultCode, data);
        }
    }

    @Override
    public void finish() {
        if (that == null) {
            super.finish();
        } else {
            that.finish();
        }
    }

    @Override
    public void finishActivity(int requestCode) {
        if (that == null) {
            super.finishActivity(requestCode);
        } else {
            that.finishActivity(requestCode);
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStart() {
//        super.onStart();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {
//        super.onResume();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onPause() {
//        super.onPause();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStop() {
//        super.onStop();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroy() {
//        super.onDestroy();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
