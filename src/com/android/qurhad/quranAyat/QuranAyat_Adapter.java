package com.android.qurhad.quranAyat;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.android.qurhad.R;

import java.util.ArrayList;

/**
 * Created by Ayyu Andhysa on 8/26/2016.
 */
public class QuranAyat_Adapter extends ArrayAdapter<QuranAyat_Item> {
    private Activity activity;
    int id;
    ArrayList<QuranAyat_Item> items;


    public QuranAyat_Adapter(Activity context, int resource, ArrayList<QuranAyat_Item> objects) {
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
        QuranAyat_Item item = items.get(position);
        TextView id_quran = (TextView) convertView.findViewById(R.id.id_quran);
        TextView nomor_ayat = (TextView) convertView.findViewById(R.id.nomor_ayat);
        TextView text_arab = (TextView) convertView.findViewById(R.id.text_arab);
        TextView tex_indo = (TextView) convertView.findViewById(R.id.text_indo);

        id_quran.setText(item.getId_quran());
        nomor_ayat.setText(item.getAya());
        text_arab.setText(item.getText_quran());
        tex_indo.setText(item.getText_indo());

        return convertView;
    }
}
