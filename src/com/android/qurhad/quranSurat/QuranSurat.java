package com.android.qurhad.quranSurat;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.qurhad.database.DatabaseHelper;
import com.android.qurhad.R;
import com.android.qurhad.quranAyat.QuranAyat;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ayyu Andhysa on 6/28/2016.
 */
public class QuranSurat extends Activity {

    private DatabaseHelper dbHelper;
    private ListView listQuran;
    private QuranSurat_Adapter adapter;
    ArrayList<QuranSurat_Item> arrayList = new ArrayList<QuranSurat_Item>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quran_surat);
        //Load database
        dbHelper = new DatabaseHelper(this);
        listQuran = (ListView) findViewById(R.id.list_quran);
        adapter = new QuranSurat_Adapter(this, R.layout.quran_surat_item_list, arrayList);


        try {
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        } catch (SQLException e) {

        }
        try {
            Cursor cursor = dbHelper.QueryData("select * from nama_surat");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        QuranSurat_Item item = new QuranSurat_Item();
                        item.setId_nama_surat(cursor.getString(0));
                        item.setNama(cursor.getString(1));
                        arrayList.add(item);
                    } while (cursor.moveToNext());
                }

            }
        } catch (SQLException e) {
        }

        listQuran.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listQuran.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent halaman_ayat = new Intent(QuranSurat.this, QuranAyat.class);
                halaman_ayat.putExtra("id", (int) id);
                startActivity(halaman_ayat);

            }
        });

   }

}
