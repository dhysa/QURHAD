package com.android.qurhad.quranSurat;

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
 * Created by Ayyu Andhysa on 8/17/2016.
 */
public class QuranSurat_Adapter extends ArrayAdapter<QuranSurat_Item> {
    private Activity activity;
    int id;
    ArrayList<QuranSurat_Item> items;

    public QuranSurat_Adapter(Activity context, int resource, ArrayList<QuranSurat_Item> objects) {
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

        // Load item in order to match position in layout listView
        QuranSurat_Item item = items.get(position);
        TextView nomor_surat = (TextView) convertView.findViewById(R.id.nomor_surat);
        TextView nama_surat = (TextView) convertView.findViewById(R.id.nama_surat);
        nomor_surat.setText(item.getId_nama_surat());
        nama_surat.setText(item.getNama());

        //load fonts
        final Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/CFJackStory.ttf");
        nama_surat.setTypeface(typeface);
        nomor_surat.setTypeface(typeface);


        return convertView;
    }


}
