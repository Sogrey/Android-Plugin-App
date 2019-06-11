package top.sogrey.android_plugin_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import top.sogrey.bundle_library.BasePluginActivity;

public class SecondActivity extends BasePluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.btn_jumpMainActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(that==null?SecondActivity.this:that, FristActivity.class));
            }
        });
    }
}
