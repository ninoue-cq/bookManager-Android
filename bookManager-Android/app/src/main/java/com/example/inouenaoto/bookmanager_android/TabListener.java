package com.example.inouenaoto.bookmanager_android;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ListFragment;


public class TabListener<T extends Fragment> implements ActionBar.TabListener {
    private Fragment mFragment;
    private final Activity mActivity;
    private final String mTag;

    //コンストラクタ
    public TabListener(Activity activity, String tag, Class<T> clz) {
        mActivity = activity;
        mTag = tag;
        mFragment = mActivity.getFragmentManager().findFragmentByTag(mTag);
    }

    //タブが選択されたとき
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

        if (mTag == "tag1") {
            Fragment listFragment = new BookListFragment();
            FragmentManager manager = mActivity.getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.container, listFragment);
            transaction.commit();
        } else if (mTag == "tag2") {
            Fragment settingFragment = new SettingFragment();
            FragmentManager manager = mActivity.getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.container, settingFragment);
            transaction.commit();
        }
    }

    //タブの選択が解除されたとき
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (mFragment != null) {
            FragmentManager fm = mActivity.getFragmentManager();
            fm.beginTransaction().detach(mFragment).commit();
        }
    }

    //選択されているタブが選択されたとき
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }
}