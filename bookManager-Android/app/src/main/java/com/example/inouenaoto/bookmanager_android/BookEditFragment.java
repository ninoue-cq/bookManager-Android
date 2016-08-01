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
import android.preference.EditTextPreference;
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


public class BookEditFragment extends Fragment {

    public BookEditFragment() {}

    private static final int mREQUEST_GALLERY = 0;
    private ImageView mBookImageView;

    public View view;

    public EditText setDateText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    //    final View view = inflater.inflate(R.layout.fragment_book_edit, container, false);

        view = inflater.inflate(R.layout.fragment_book_edit, container, false);
        setDateText = (EditText) view.findViewById(R.id.edit_book_date);

        EditText editDate = (EditText) view.findViewById(R.id.edit_book_date);
        editDate.setOnClickListener(new SetDateTextAction());

        //一覧画面から受け取った値をそれぞれのエデットテキストに反映
        String title = getArguments().getString("titleText");
        String price = getArguments().getString("priceText");
        String date = getArguments().getString("dateText");

        EditText editTitle = (EditText) view.findViewById(R.id.edit_book_title);
        EditText editPrice = (EditText) view.findViewById(R.id.edit_book_price);
    //    EditText editDate = (EditText) v.findViewById(R.id.edit_book_date);

        editTitle.setText(title);
        editPrice.setText(price);
        editDate.setText(date);

        mBookImageView=(ImageView) view.findViewById(R.id.book_image);

        //画像添付ボタンの処理
        view.findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, mREQUEST_GALLERY);
            }
        });
        return view;
    }


    PicckerSetting picckerSetting = new PicckerSetting();
    public class SetDateTextAction implements View.OnClickListener {
        @Override
        public void onClick(final View v) {
            picckerSetting.pickerAppear(getActivity(),setDateText);
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

        inflater.inflate(R.menu.edit_menu, menu);
    }
    //アクションバーのボタンイベントのハンドリング
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {

            case R.id.close_button:
               // Toast.makeText(getActivity(), R.string.add, Toast.LENGTH_SHORT).show();
                BookListFragment bookListFragment = new BookListFragment();
                FragmentManager manager = this.getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.container, bookListFragment)
                        .addToBackStack("transaction")
                        .commit();
                break;
            case R.id.save_button:
                //編集データをサーバーに送る処理を書く
                break;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //  super.onActivityResult(requestCode, resultCode, data);
        // TODO Auto-generated method stub
        if(requestCode == mREQUEST_GALLERY && resultCode == Activity.RESULT_OK) {
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                // 選択した画像を表示
                mBookImageView.setImageBitmap(img);
            } catch (Exception e) {

            }
        }
    }
}
