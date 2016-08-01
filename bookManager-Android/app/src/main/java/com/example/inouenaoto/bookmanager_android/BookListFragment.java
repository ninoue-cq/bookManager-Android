package com.example.inouenaoto.bookmanager_android;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.widget.AdapterView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


public class BookListFragment extends Fragment implements APIListener{

    private ListView mListView;
    private int micons = R.mipmap.ic_launcher;

    private ArrayList<CustomData> mObjects;
    private int mTotal = 0;

    private int mReadCount = 1;//読み込み回数のカウント
    private int mDisplayCount = 0;//表示件数

    private JSONArray mJsonArray;

    public BookListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_book_list, container, false);
        mListView = (ListView) v.findViewById(R.id.my_book_listView);

        View myFooter = getActivity().getLayoutInflater().inflate(R.layout.list_footer,null,false);
        mListView.addFooterView(myFooter);
        mObjects=new ArrayList<>();

        final BookListFragment thisFragment = this;

        //書籍一覧のデータの取得
        BookListGet bookListGet = new BookListGet();
        bookListGet.setAPIListener(this);
        bookListGet.execute();

        // フッターのボタンを取り出す
        Button footerButton = (Button)myFooter.findViewById(R.id.read_more_button);
        footerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("mReadCount * 1=",Integer.toString(mReadCount * 1));
                Log.d("読み込み回数",Integer.toString(mReadCount));
                //書籍一覧のデータの取得
                Log.d("表示件数",Integer.toString(mDisplayCount));
                readCountJudge();
                BookListGet bookListGet = new BookListGet();
                bookListGet.setAPIListener(thisFragment);
                bookListGet.execute();
            }
        });

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
               bundle.putString("titleText", customData.getTitle());
               bundle.putString("priceText", customData.getPrice());
               bundle.putString("dateText", customData.getDate());
               bundle.putString("bookId", customData.getId());
               bundle.putInt("image", micons);

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

        try {
            JSONObject jsonObject = new JSONObject(String.valueOf(result));
            mJsonArray = jsonObject.getJSONArray("result");

          //  for (int i = 0; i < jsonArray.length(); i++) {
            for (int i = 0 + mDisplayCount; i < mDisplayCount + 5; i++) {
                jsonObject = mJsonArray.getJSONObject(i);
                CustomData item = new CustomData();

                //購入日の書式変更
                SimpleDateFormat beforeDate = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss ZZZZ");
                SimpleDateFormat afterDate =  new SimpleDateFormat("yyyy/MM/dd");

                String title = jsonObject.getString("name");
                String price = jsonObject.getString("price");
                String id =jsonObject.getString("id");
                Date  date = beforeDate.parse(jsonObject.getString("purchase_date"));

                String formatedDate = "";
                formatedDate = afterDate.format(date);

                item.setId(id);
                item.setTitle(title);
                item.setPrice(price);
                item.setDate(formatedDate);
                item.setIcon(BitmapFactory.decodeResource(
                        getResources(),
                        micons
                ));
                mObjects.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ListAdapter customAdapter = new ListAdapter(getActivity(),0,mObjects);
        mListView.setAdapter(customAdapter);
        mTotal=mObjects.size();
    }

    //さらに読みこむボタンが押された時の判定
    public void readCountJudge(){
        if(mJsonArray.length() >= mReadCount * 5){
            mReadCount += 1;
            mDisplayCount += 5;
        }
        else if(mJsonArray.length()>=mReadCount*5 && mJsonArray.length() <= (mReadCount+1)*5){
            mDisplayCount = mJsonArray.length();
        }
        else{
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setMessage("これ以上はデータがありません");
            alertDialog.setPositiveButton("確認",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("alertDialog", "確認ボタンクリック");
                        }
                    });
            alertDialog.show();
        }
    }
}

