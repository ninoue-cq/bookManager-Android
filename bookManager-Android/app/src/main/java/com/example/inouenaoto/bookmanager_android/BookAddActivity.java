package com.example.inouenaoto.bookmanager_android;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.InputStream;

public class BookAddActivity extends Activity  {

    // 日付設定ダイアログのインスタンスを格納する変数
    private DatePickerDialog.OnDateSetListener DateSetListener;
    private static final int mREQUEST_GALLERY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_add);
        setTitle("書籍追加");

        EditText setDateText = (EditText) findViewById(R.id.addBookDate);
        setDateText.setOnClickListener(new SetDateTextAction());

        //画像添付ボタンの処理
        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, mREQUEST_GALLERY);
            }
        });
    }

    //ピッカーのデータを取得しエディットテキストに反映させるためのクラス
    public class SetDateTextAction implements View.OnClickListener {
        @Override
        public void onClick(final View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(BookAddActivity.this);
            final DatePicker datePicker = new DatePicker(BookAddActivity.this);
            builder.setView(datePicker);
            builder.setTitle("日付選択");
            builder.setPositiveButton("決定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText setDateText = (EditText) v.findViewById(R.id.addBookDate);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account_setting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.closeButton:
                finish();
                break;
            case R.id.saveButton:
                //書籍追加の処理をここに書く
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      //  super.onActivityResult(requestCode, resultCode, data);
        // TODO Auto-generated method stub
        ImageView bookImageView = (ImageView) findViewById(R.id.bookImage);
        if(requestCode == mREQUEST_GALLERY && resultCode == RESULT_OK) {
            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                // 選択した画像を表示
                bookImageView.setImageBitmap(img);
                } catch (Exception e) {

                }
            }
        }
}


