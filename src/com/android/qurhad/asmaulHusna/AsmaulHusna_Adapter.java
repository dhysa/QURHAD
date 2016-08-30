package com.android.qurhad.asmaulHusna;

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
 * Created by Ayyu Andhysa on 8/30/2016.
 */
public class AsmaulHusna_Adapter extends ArrayAdapter<AsmaulHusna_Item> {
    private Activity activity;
    int id;
    ArrayList<AsmaulHusna_Item> items;

    public AsmaulHusna_Adapter(Activity contexts, int resources, ArrayList<AsmaulHusna_Item> object){
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
        AsmaulHusna_Item item = items.get(position);

        TextView nomor_asmaulHusna  = (TextView) convertView.findViewById(R.id.nomor_asmaulHusna);
        TextView nama_asmaulHusna = (TextView) convertView.findViewById(R.id.nama_asmaulHusna);
        TextView arti_asmaulHusna = (TextView) convertView.findViewById(R.id.arti_asmaulHusna);
        TextView arab_asmaulHusna = (TextView) convertView.findViewById(R.id.arab_asmaulHusna);

        nomor_asmaulHusna.setText(item.getId_asmaulHusna());
        nama_asmaulHusna.setText(item.getNama_asmaulHusna());
        arti_asmaulHusna.setText(item.getArti_asmaulHusna());
        arab_asmaulHusna.setText(item.getArab_asmaulHusna());

        //load fonts
        final Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/CFJackStory.ttf");
        nomor_asmaulHusna.setTypeface(typeface);
        arti_asmaulHusna.setTypeface(typeface);
        nama_asmaulHusna.setTypeface(typeface);

        return convertView;
    }
}
