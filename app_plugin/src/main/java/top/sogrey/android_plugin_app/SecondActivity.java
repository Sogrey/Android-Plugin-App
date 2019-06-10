package top.sogrey.android_plugin_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import top.sogrey.bundle_library.BasePluginActivity;

public class SecondActivity extends BasePluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        that.setContentView(R.layout.activity_second);
    }

    public void jumpMainActivity(View v){
        that.startActivity(new Intent(that,MainActivity.class));
    }
}
