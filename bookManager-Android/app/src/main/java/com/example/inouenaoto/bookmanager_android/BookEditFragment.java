package com.example.inouenaoto.bookmanager_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookEditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BookEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookEditFragment extends Fragment {

    public BookEditFragment() {}
    private static final int REQUEST_GALLERY =0;
    private ImageView imgView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_book_edit, container, false);

        EditText setDateText = (EditText) view.findViewById(R.id.editBookDate);
        setDateText.setOnClickListener(new SetDateTextAction());

        //一覧画面から受け取った値をそれぞれのエデットテキストに反映
        String title = getArguments().getString("titleText");
        String price = getArguments().getString("priceText");
        String date = getArguments().getString("dateText");

        EditText edittitle = (EditText) view.findViewById(R.id.editBookTitle);
        EditText editprice = (EditText) view.findViewById(R.id.editBookPrice);
        EditText editdate = (EditText) view.findViewById(R.id.editBookDate);

        edittitle.setText(title);
        editprice.setText(price);
        editdate.setText(date);

        //画像添付ボタンの処理
        view.findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgView=(ImageView) view.findViewById(R.id.bookImage);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_GALLERY);
            }
        });
        return view;
    }

    //ピッカーのデータを取得しエディットテキストに反映させるためのクラス
    public class SetDateTextAction implements View.OnClickListener {
        @Override
        public void onClick(final View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final DatePicker datePicker = new DatePicker(getActivity());
            builder.setView(datePicker);
            builder.setTitle("日付選択");
            builder.setPositiveButton("決定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText setDateText = (EditText) v.findViewById(R.id.editBookDate);
                    int year = datePicker.getYear();
                    int month = datePicker.getMonth();
                    int day = datePicker.getDayOfMonth();
                    setDateText.setText(year + "/" + month + "/" + day);
                }
            });
            builder.setNegativeButton("キャンセル", null);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    //アクションバーの設定
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        getActivity().setTitle("書籍編集");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.edie_menu, menu);
    }
    //アクションバーのボタンイベントのハンドリング
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {

            case R.id.closeButton:
               // Toast.makeText(getActivity(), R.string.add, Toast.LENGTH_SHORT).show();
                BookListFragment bookListfragment = new BookListFragment();
                FragmentManager manager = this.getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.container, bookListfragment)
                        .addToBackStack("transaction")
                        .commit();
                break;
            case R.id.saveButton:
                //編集データをサーバーに送る処理を書く
                break;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  super.onActivityResult(requestCode, resultCode, data);
        // TODO Auto-generated method stub
       // ImageView imgView=(ImageView) findViewById(R.id.bookImage);
        if(requestCode == REQUEST_GALLERY && resultCode == Activity.RESULT_OK) {
            try {
                InputStream in = getActivity().getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(in);
                in.close();
                // 選択した画像を表示
                imgView.setImageBitmap(img);
            } catch (Exception e) {

            }
        }
    }

/*
    public static BookEditFragment newInstance(int position) {
        BookEditFragment editFragment = new BookEditFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        editFragment.setArguments(args);
        return editFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_edit, container, false);
        ((EditText) view.findViewById(R.id.editBookTitle))
                .setText(BookListFragment.titles[getArguments().getInt("position")]);
        return view;
    }
*/

/*    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_edit, container, false);
        String title = getArguments().getString("titleText");
        EditText titleText = (EditText) v.findViewById(R.id.editBookTitle);
        return v;

    }
*/
    /*
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_book_edit, container, false);

        String title = getArguments().getString("title");
        EditText editText = (EditText) v.findViewById(R.id.editBookTitle);
        editText.setText(title);

        return v;
    }

*/
    /*何番目のセルがタップされたかを返すメソッド
    public static BookEditFragment newInstance(int position) {
        BookEditFragment editFragment = new BookEditFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        BookEditFragment.setArguments(args);
        return editFragment;
    }
    */

    /*
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View editView = inflater.inflate(R.layout.fragment_book_edit, container, false);
        ((EditText) editView.findViewById(R.id.editBookTitle))
                .setText(News.Details[getArguments().getInt("position")]);
        return editView;
    }
    */
}
