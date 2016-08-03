package com.example.inouenaoto.bookmanager_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by inouenaoto on 2016/07/27.
 */
public class ListAdapter extends ArrayAdapter<CustomData> {

    private LayoutInflater layoutInflater;

    public ListAdapter(Context c, int id, ArrayList<CustomData> users) {
        super(c, id, users);
        this.layoutInflater = (LayoutInflater) c.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
        );
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(
                    R.layout.listitem,
                    parent,
                    false
            );
        }
        CustomData user = (CustomData) getItem(pos);
        ((ImageView) convertView.findViewById(R.id.icon))
                .setImageBitmap(user.getIcon());
        ((TextView) convertView.findViewById(R.id.title))
                .setText(user.getTitle());

        ((TextView) convertView.findViewById(R.id.price))
                .setText(user.getPrice()+ "円+税");
        ((TextView) convertView.findViewById(R.id.date))
                .setText(user.getDate());
        return convertView;
    }
}