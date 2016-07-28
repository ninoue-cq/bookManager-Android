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
import android.util.Log;
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

    public String mBookId;
    private  EditText mEditBookTitle;
    private  EditText mEditBookPrice;
    private  EditText mEditBookDate;


    private static final int mREQUEST_GALLERY = 0;
    private ImageView mBookImageView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_book_edit, container, false);

        EditText setDateText = (EditText) v.findViewById(R.id.edit_book_date);
        setDateText.setOnClickListener(new SetDateTextAction());

        //一覧画面から受け取った値をそれぞれのエデットテキストに反映
        String title = getArguments().getString("titleText");
        String price = getArguments().getString("priceText");
        String date = getArguments().getString("dateText");

        mBookId=getArguments().getString("bookId");

        int image = getArguments().getInt("image");
        Bitmap imaged=BitmapFactory.decodeResource(
                getResources(),
                image);

        mEditBookTitle = (EditText) v.findViewById(R.id.edit_book_title);
        mEditBookPrice = (EditText) v.findViewById(R.id.edit_book_price);
        mEditBookDate= (EditText) v.findViewById(R.id.edit_book_date);
        ImageView editImage = (ImageView)  v.findViewById(R.id.book_image);

        mEditBookTitle.setText(title);
        mEditBookPrice.setText(price);
        mEditBookDate.setText(date);
        editImage.setImageBitmap(imaged);

        //画像添付ボタンの処理
        v.findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBookImageView=(ImageView) v.findViewById(R.id.book_image);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, mREQUEST_GALLERY);
            }
        });
        return v;
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
                    EditText setDateText = (EditText) v.findViewById(R.id.edit_book_date);
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
                //編集データをサーバーに送る処理 長いのでメソッドにすべきかもしれない
                final String titleText =mEditBookTitle.getText().toString();
                final String priceText = mEditBookPrice.getText().toString();
                final String dateText = mEditBookDate.getText().toString();

                if (titleText.length() == 0 || priceText.length() == 0 || dateText.length() == 0) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setMessage("未入力項目があります");
                    alertDialog.setPositiveButton("確認",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    alertDialog.show();
                } else {
                    new BookDataEdit(getActivity()).execute(titleText, priceText, dateText,mBookId);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setMessage("編集完了しました");
                    alertDialog.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface daialg, int which) {
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                    alertDialog.show();
                }
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
