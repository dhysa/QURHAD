package com.android.qurhad.catatan;

import android.app.Activity;
import android.graphics.Typeface;
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
public class Catatan_Adapter extends ArrayAdapter<Catatan_Item>{
    private Activity activity;
    int id;
    ArrayList<Catatan_Item> items;


    public Catatan_Adapter(Activity context, int resource, ArrayList<Catatan_Item> objects) {
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
        Catatan_Item item = items.get(position);
        TextView judul_notes = (TextView) convertView.findViewById(R.id.judul_notesList);
        TextView isi_notes = (TextView) convertView.findViewById(R.id.isi_notesList);

        judul_notes.setText(item.getJudul_notes());
        isi_notes.setText(item.getIsi_notes());

        final Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/CFJackStory.ttf");
        judul_notes.setTypeface(typeface);

        return convertView;
    }
}
