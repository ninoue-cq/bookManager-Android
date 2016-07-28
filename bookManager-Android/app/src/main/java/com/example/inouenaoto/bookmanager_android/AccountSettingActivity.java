package com.example.inouenaoto.bookmanager_android;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
//import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class AccountSettingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        setTitle("アカウント設定");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.account_setting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {

            case R.id.close_button:
                finish();
                break;
            case R.id.save_button:
                //アカウントの登録処理 長いからメソッドにしたほうがいいかもしれない
                EditText mail = (EditText) findViewById(R.id.mail_adress);
                final String mailText = mail.getText().toString();

                EditText password = (EditText) findViewById(R.id.password);
                final String passwordText = password.getText().toString();

                EditText confirm = (EditText) findViewById(R.id.pass_conf);
                final String confPassText = confirm.getText().toString();


                if (!passwordText.equals(confPassText)) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(AccountSettingActivity.this);
                    alertDialog.setMessage("パスワードが一致しません");
                    alertDialog.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    alertDialog.show();
                }
                else if (mailText.length() == 0 || passwordText.length() == 0 || confPassText.length() == 0) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(AccountSettingActivity.this);
                    alertDialog.setMessage("未入力項目があります");
                    alertDialog.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    alertDialog.show();
                } else {
                    new AccountRegister().execute(mailText, passwordText, confPassText);
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(AccountSettingActivity.this);
                    new AlertDialog.Builder(AccountSettingActivity.this);
                    alertDialog.setMessage("登録が完了しました");
                    alertDialog.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface daialg, int which) {
                                    finish();}
                            });
                    alertDialog.show();

                }
                break;
        }
        return true;
    }
}

