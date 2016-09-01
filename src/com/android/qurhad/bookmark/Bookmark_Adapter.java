package com.android.qurhad.bookmark;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.android.qurhad.R;


import java.util.ArrayList;

/**
 * Created by Ayyu Andhysa on 9/1/2016.
 */
public class Bookmark_Adapter extends ArrayAdapter<Bookmark_Item> {
    private Activity activity;
    int id;
    ArrayList<Bookmark_Item> items;


    public Bookmark_Adapter(Activity context, int resource, ArrayList<Bookmark_Item> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.id = resource;
        this.items = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(id, null);
        }
        Bookmark_Item item = items.get(position);
        TextView aya = (TextView) convertView.findViewById(R.id.nomor_bookmark);
        TextView quran_indo = (TextView) convertView.findViewById(R.id.indo_bookmark);
        TextView quran_arab = (TextView) convertView.findViewById(R.id.arab_bookmark);

        aya.setText(item.getAya());
        quran_indo.setText(item.getText_indo());
        quran_arab.setText(item.getText_quran());

        return convertView;
    }

}
