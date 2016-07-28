package com.example.inouenaoto.bookmanager_android;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by inouenaoto on 2016/07/28.
 */
public class BookDataAdd extends AsyncTask<String, Integer, String> {
    StringBuffer buffer;
    private APIListener mAPIListener;

    public String mTitle;
    public String mPrice;
    public String mDate;

    @Override
    protected String doInBackground(String... params) {
        String mTitle = params[0];
        String mPrice = params[1];
        String mDate = params[2];

        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://app.com/book/regist");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(1000);
            conn.setDoOutput(true);

            String postText = String.format("image_url=aaa&name=%s&price=%s&purchase_date=%s", mTitle, mPrice, mDate);

            String postData = postText;
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(postData.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream inputStream = conn.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            buffer = new StringBuffer();
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                buffer.append(temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

        return buffer.toString();
    }
}