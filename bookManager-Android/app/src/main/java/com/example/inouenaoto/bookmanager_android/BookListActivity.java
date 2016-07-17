package com.example.inouenaoto.bookmanager_android;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
public class BookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

    ListView myListView = (ListView) findViewById(R.id.myListView);

        // データを準備
    ArrayList<String> items = new ArrayList<>();
    for (int i = 0; i < 30; i++) {
        items.add("BookNo-" + i);
    }
    // Adapter - ArrayAdapter
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
            this,
            R.layout.listitem,
            items
    );
    // ListViewに表示
    myListView.setAdapter(adapter);
    }

}
