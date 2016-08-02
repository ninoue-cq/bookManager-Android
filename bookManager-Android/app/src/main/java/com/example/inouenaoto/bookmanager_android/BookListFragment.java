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
import java.util.ArrayList;
import android.widget.AdapterView;
import android.widget.Toast;


public class BookListFragment extends Fragment  {

    public BookListFragment() {
        // Required empty public constructor
    }

    public static final int[] icons = {
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
            R.mipmap.ic_launcher
    };

    public static final String[] titles = {
            "book1",
            "book2",
            "book3"
    };

    public static final String[] prices = {
            "1200",
            "1500",
            "1600"
    };

    public static final String[] dates = {
            "2014年 7月20日 ",
            "2014年 8月20日 ",
            "2014年 9月20日 "
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View listFragment= inflater.inflate(R.layout.fragment_book_list, container, false);
        ListView listView = (ListView) listFragment.findViewById(R.id.my_book_listView);

        // データを準備
        ArrayList<CustomData> users = new ArrayList<>();

        for (int i = 0; i < icons.length; i++) {
            CustomData customData = new CustomData();
            customData.setIcon(BitmapFactory.decodeResource(
                    getResources(),
                    icons[i]
            ));
            customData.setTitle(titles[i]);
            customData.setPrice(prices[i]);
            customData.setDate(dates[i]);
            users.add(customData);
        }

        // Adapter - ArrayAdapter - UserAdapter
        ListAdapter adapter = new ListAdapter(getActivity(), 0, users);

        // ListViewに表示
        listView.setAdapter(adapter);

        // セルのクリックで編集フラグメントへデータを送る
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
               int selectedImage = icons[position];

               //値を書き込む
               bookEditFragment.setArguments(bundle);
               transaction.replace(R.id.container, bookEditFragment).commit();
           }
         });
        return listFragment;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
