package top.sogrey.android_plugin_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import top.sogrey.bundle_library.BasePluginActivity;

public class FristActivity extends BasePluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String param1 = "没有参数";

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                param1 = bundle.getString("param1");
            }
        }

        Toast.makeText(getContext(), param1, Toast.LENGTH_SHORT).show();

        findViewById(R.id.btn_jumpActivity1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SecondActivity.class);
                intent.putExtra("param1", "由FristActivity跳转至SecondActivity");
                startActivity(intent);
            }
        });
    }
}
