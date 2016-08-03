package com.example.inouenaoto.bookmanager_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //タブの設定
        TextView textView = new TextView(this);
        textView.setText("Tab2");
        setContentView(textView);

    }
}
