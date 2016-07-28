package com.example.inouenaoto.bookmanager_android;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


public class BookListFragment extends Fragment implements APIListener {

    private ListView mListView;
    private BookListFragment mThisFragment;
    private int micons = R.mipmap.ic_launcher;
    public BookListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v= inflater.inflate(R.layout.fragment_book_list, container, false);
        mListView = (ListView) v.findViewById(R.id.my_book_listView);

        mThisFragment = this;

        //書籍一覧のデータの取得
        BookListGet getBookData = new BookListGet();
        getBookData.setAPIListener(mThisFragment);
        getBookData.execute();

        // セルのクリックで編集フラグメントへデータを送る
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
               FragmentManager fragmentManager = getFragmentManager();
               FragmentTransaction transaction = fragmentManager.beginTransaction();
               Fragment bookEditFragment = new BookEditFragment();

               ListView listView = (ListView) parent;
               CustomData customData = (CustomData) listView.getItemAtPosition(position);

               Bundle bundle = new Bundle();
              // bundle.putString("titleText",titles[position]);
               bundle.putString("titleText",customData.getTitle());
               bundle.putString("priceText",customData.getPrice());
               bundle.putString("dateText",customData.getDate());
               //int selectedImage = customData.micons[position];
               bundle.putInt("image",micons);
             //  bundle.putExtra("selectedImage",customData.getIcon());

               //値を書き込む
               bookEditFragment.setArguments(bundle);
               transaction.replace(R.id.container, bookEditFragment).commit();

    }
         });
        return v;
    }

    //アクションバーの設定
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle("書籍一覧");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.list_menu, menu);
    }
    //アクションバーのボタンイベントのハンドリング
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.to_add_button:
                Intent intent = new Intent();
                intent.setClassName("com.example.inouenaoto.bookmanager_android",
                        "com.example.inouenaoto.bookmanager_android.BookAddActivity");
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void didConnection(StringBuffer result) {
       ArrayList<CustomData> objects = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(String.valueOf(result));
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                CustomData item = new CustomData();

                //購入日の書式変更
                SimpleDateFormat beforeDate = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss ZZZZ");
                SimpleDateFormat afterDate =  new SimpleDateFormat("yyyy/MM/dd");

                String title = jsonObject.getString("name");
                String price = jsonObject.getString("price");
                Date  date = beforeDate.parse(jsonObject.getString("purchase_date"));

                String formatedDate = "";
                formatedDate = afterDate.format(date);

                item.setTitle(title);
                item.setPrice(price);
                item.setDate(formatedDate);
                item.setIcon(BitmapFactory.decodeResource(
                        getResources(),
                        micons
                ));
                objects.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ListAdapter customAdapter = new ListAdapter(getActivity(),0,objects);
        mListView.setAdapter(customAdapter);
    }
}