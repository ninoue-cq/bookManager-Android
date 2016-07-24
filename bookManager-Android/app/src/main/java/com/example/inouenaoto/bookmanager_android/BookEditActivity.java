package com.example.inouenaoto.bookmanager_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class BookEditActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_edit);

    }




/*
        Intent intent = getIntent();
        String selectedTitle = intent.getStringExtra("titleText");
        String selectedPrice = intent.getStringExtra("priceText");
    //    String selectedDate = intent.getStringExtra("titleText");
        int selectedImage = intent.getIntExtra("imageView", 0);

        EditText bookTitle = (EditText) findViewById(R.id.editBookTitle);
        bookTitle.setText(selectedTitle);

        EditText bookPrice = (EditText) findViewById(R.id.editBookPrice);
        bookPrice.setText(selectedPrice);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(selectedImage);


    //    EditText bookdate = (EditText) findViewById(R.id.editBookTDate);
    //    booktitle.setText(selectedTitle);
*/

}
