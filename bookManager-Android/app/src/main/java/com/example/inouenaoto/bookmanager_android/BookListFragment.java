package com.example.inouenaoto.bookmanager_android;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import android.widget.AdapterView;
import android.widget.Toast;


public class BookListFragment extends Fragment  {

    public BookListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_book_list, container, false);
        ListView myListView = (ListView) v.findViewById(R.id.myBookListView);

      //  myListView.setOnItemClickListener(this);


        // データを準備
        ArrayList<User> users = new ArrayList<>();

        int[] icons = {
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher
        };

        String[] titles = {
                "book1",
                "book2",
                "book3"
        };

        String[] prices = {
                "1200",
                "1500",
                "1600"
        };

        String[] dates = {
                "2014年 7月20日 ",
                "2014年 8月20日 ",
                "2014年 9月20日 "
        };
        for (int i = 0; i < icons.length; i++) {
            User user = new User();
            user.setIcon(BitmapFactory.decodeResource(
                    getResources(),
                    icons[i]
            ));
            user.setTitle(titles[i]);
            user.setPrice(prices[i]);
            user.setDate(dates[i]);
            users.add(user);
        }

        // Adapter - ArrayAdapter - UserAdapter
        UserAdapter adapter = new UserAdapter(getActivity(), 0, users);

        // ListViewに表示
        myListView.setAdapter(adapter);

        // Event
         myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
             Intent intent = new Intent(getActivity(), BookEditActivity.class);
   /*       // clickされたpositionのtextとphotoのID
              String selectedText = scenes[position];
             int selectedPhoto = photos[position];
            // インテントにセット
              intent.putExtra("Text", selectedText);
              intent.putExtra("Photo", selectedPhoto);
              // Activity をスイッチする  */
             startActivity(intent);
    }
         });

        return v;

    }



    public class UserAdapter extends ArrayAdapter<User> {

        private LayoutInflater layoutInflater;

        public UserAdapter(Context c, int id, ArrayList<User> users) {
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

            User user = (User) getItem(pos);

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

    public class User {
        private Bitmap icon;
        private String title;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
        public Bitmap getIcon() {
            return icon;
        }

        public void setIcon(Bitmap icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }

        private String price;
        private String date;
    }







/*

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Intent intent = new Intent(this.getApplicationContext(), BookEditActivity.class);
        // clickされたpositionのtextとphotoのID
        String selectedTitle =title[position];
        // インテントにセット
        intent.putExtra("Text", selectedTitle);
        intent.putExtra("Photo", selectedPhoto);       // Activity をスイッチする
        startActivity(intent);
    }


*/
    //編集画面への遷移と受け渡し


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
