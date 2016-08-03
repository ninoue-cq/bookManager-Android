package com.example.inouenaoto.bookmanager_android;

import android.app.ActionBar;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ListFragment;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getActionBar();
        setContentView(R.layout.activity_main);
        //final ActionBar actionBar = getActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab()
                .setText("書籍一覧")
                .setTabListener(new TabListener<BookListFragment>(
                        this, "tag1", BookListFragment.class)));
        actionBar.addTab(actionBar.newTab()
                .setText("設定")
                .setTabListener(new TabListener<SettingFragment>(
                        this, "tag2", SettingFragment.class)));
    }
}

