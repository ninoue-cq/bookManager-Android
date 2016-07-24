package com.example.inouenaoto.bookmanager_android;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_edit, container, false);

        String title = getArguments().getString("titleText");
        String price = getArguments().getString("priceText");


        EditText edittitle = (EditText) view.findViewById(R.id.editBookTitle);
        EditText editprice = (EditText) view.findViewById(R.id.editBookPrice);

        edittitle.setText(title);
        editprice.setText(price);
        return view;
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
