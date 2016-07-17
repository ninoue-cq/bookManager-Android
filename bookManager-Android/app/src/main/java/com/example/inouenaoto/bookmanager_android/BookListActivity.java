package com.example.inouenaoto.bookmanager_android;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.inouenaoto.bookmanager_android.R;

import java.util.ArrayList;


public class BookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        ListView myListView = (ListView) findViewById(R.id.myListView);

        // データを準備
        ArrayList<User> users = new ArrayList<>();

        int[] icons = {
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher
        };

        String[] titles = {
                "book1",
                "book2",
                "book3"
        };

        String[] prices = {
                "1200",
                "1500",
                "1600"
        };

        String[] dates = {
                "2014年 7月20日 ",
                "2014年 8月20日 ",
                "2014年 9月20日 "
        };
        for (int i = 0; i < icons.length; i++) {
            User user = new User();
            user.setIcon(BitmapFactory.decodeResource(
                    getResources(),
                    icons[i]
            ));
            user.setTitle(titles[i]);
            user.setPrice(prices[i]);
            user.setDate(dates[i]);
            users.add(user);
        }

        // Adapter - ArrayAdapter - UserAdapter
        UserAdapter adapter = new UserAdapter(this, 0, users);

        // ListViewに表示
        myListView.setAdapter(adapter);
    }

    public class UserAdapter extends ArrayAdapter<User> {

        private LayoutInflater layoutInflater;

        public UserAdapter(Context c, int id, ArrayList<User> users) {
            super(c, id, users);
            this.layoutInflater = (LayoutInflater) c.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
            );
        }


        @Override
        public View getView(int pos, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(
                        R.layout.listitem,
                        parent,
                        false
                );
            }

            User user = (User) getItem(pos);

            ((ImageView) convertView.findViewById(R.id.icon))
                    .setImageBitmap(user.getIcon());
            ((TextView) convertView.findViewById(R.id.title))
                    .setText(user.getTitle());
            ((TextView) convertView.findViewById(R.id.price))
                    .setText(user.getPrice());
            ((TextView) convertView.findViewById(R.id.date))
                    .setText(user.getDate());
            return convertView;
        }
    }

    public class User {
        private Bitmap icon;
        private String title;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
        public Bitmap getIcon() {
            return icon;
        }

        public void setIcon(Bitmap icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }

        private String price;
        private String date;
    }

}