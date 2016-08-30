package com.android.qurhad.quranAyat;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.android.qurhad.R;
import com.android.qurhad.database.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ayyu Andhysa on 8/27/2016.
 */
public class QuranAyat extends Activity {
    private DatabaseHelper dbHelper;
    private ListView listView;
    private QuranAyat_Adapter adapter;
    //  private TextView fetchIDtext;

    ArrayList<QuranAyat_Item> arrayList = new ArrayList<QuranAyat_Item>();


    @Override // load layout activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quran_ayat);
        int plus = 1;

        Intent fetchID = getIntent();
        int id = fetchID.getIntExtra("id", 0);

//        fetchIDtext = (TextView) findViewById(R.id.fetchID);
//        fetchIDtext.setText("ID" + String.valueOf(id));

        dbHelper = new DatabaseHelper(this);


        try {
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        } catch (SQLException e) {

        }

        try {

            Cursor cursor = dbHelper.QueryData("select aya,text_quran, text_indo from  quran_terjemahan where id_surat =" + (id+1) );
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        QuranAyat_Item item = new QuranAyat_Item();
                        item.setAya(cursor.getString(0));
                        item.setText_quran(cursor.getString(1));
                        item.setText_indo(cursor.getString(2));
                        arrayList.add(item);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLException e) {

        }

        adapter = new QuranAyat_Adapter(this, R.layout.quran_ayat_item_list, arrayList);
        listView = (ListView) findViewById(R.id.list_ayat);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

}

