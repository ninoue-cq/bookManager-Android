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

public class AccountRegister extends AsyncTask<String, Integer, String> {
    StringBuffer buffer;

    @Override
    protected String doInBackground(String... params) {
        String mailAddress = params[0];
        String password = params[1];

        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://app.com/account/register");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(1000);
            connection.setDoOutput(true);

            String postText = String.format("mail_address=%s&password=%s", mailAddress, password);
            String postData = postText;
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(postData.getBytes());
            outputStream.flush();
            outputStream.close();

            InputStream inputStream = connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            buffer = new StringBuffer();
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null) {
                buffer.append(temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return buffer.toString();
    }
}