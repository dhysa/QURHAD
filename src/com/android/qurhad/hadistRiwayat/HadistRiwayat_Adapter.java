package com.android.qurhad.hadistRiwayat;

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
public class HadistRiwayat_Adapter extends ArrayAdapter<HadistRiwayat_Item>{
    private Activity activity;
    int id;
    ArrayList<HadistRiwayat_Item> items;

    public HadistRiwayat_Adapter(Activity contexts, int resources, ArrayList<HadistRiwayat_Item> object){
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
        HadistRiwayat_Item item = items.get(position);

        TextView nomor_hadistRiwayat  = (TextView) convertView.findViewById(R.id.nomor_hadistRiwayat);
        TextView isi_hadist = (TextView) convertView.findViewById(R.id.isi_hadist);
        TextView nama_periwayat = (TextView) convertView.findViewById(R.id.nama_periwayat);

        nomor_hadistRiwayat.setText(item.getHadist_nomor());
        isi_hadist.setText(item.getIsi_hadist());
        nama_periwayat.setText(item.getNama_periwayat());

        //load fonts
        final Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/CFJackStory.ttf");
        nama_periwayat.setTypeface(typeface);

        return convertView;
    }

}
