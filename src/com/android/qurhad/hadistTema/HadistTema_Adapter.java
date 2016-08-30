package com.android.qurhad.hadistTema;

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
 * Created by Ayyu Andhysa on 8/18/2016.
 */
public class HadistTema_Adapter extends ArrayAdapter<HadistTema_Item> {
    private Activity activity;
    int id;
    ArrayList<HadistTema_Item> items;

    public HadistTema_Adapter(Activity contexts, int resources, ArrayList<HadistTema_Item> object){
        super(contexts, resources, object);
        this.activity = contexts;
        this.id = resources;
        this.items = object;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null){
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(id,null);
        }

        // Load item in order to match position in layout listView
        HadistTema_Item item = items.get(position);
        TextView nomor_tema = (TextView) convertView.findViewById(R.id.nomor_tema);
        TextView nama_tema = (TextView) convertView.findViewById(R.id.nama_tema);
        nomor_tema.setText(item.getId_tema());
        nama_tema.setText(item.getTema());

        //load fonts
        final Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/CFJackStory.ttf");
        nomor_tema.setTypeface(typeface);
        nama_tema.setTypeface(typeface);

        return convertView;
    }
}
