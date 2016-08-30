package com.android.qurhad.hadistTema;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.qurhad.database.DatabaseHelper;
import com.android.qurhad.R;
import com.android.qurhad.hadistRiwayat.HadistRiwayat;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ayyu Andhysa on 6/28/2016.
 */
public class HadistTema extends Activity {
    private DatabaseHelper dbHelper;
    private ListView listTema;
    private HadistTema_Adapter adapter;
    ArrayList<HadistTema_Item> arrayList = new ArrayList<HadistTema_Item>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hadist_tema);

        dbHelper = new DatabaseHelper(this);

        try {
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
               } catch (SQLException e) {

        }

        try {
            Cursor cursor = dbHelper.QueryData("select * from tema_hadist");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        HadistTema_Item item = new HadistTema_Item();
                        item.setId_tema(cursor.getString(0));
                        item.setTema(cursor.getString(1));
                        arrayList.add(item);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLException e) {

        }

        adapter = new HadistTema_Adapter(this, R.layout.hadist_tema_item_list, arrayList);
        listTema = (ListView) findViewById(R.id.list_hadistTema);
        listTema.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listTema.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent halaman_hadistRiwayat = new Intent(getApplicationContext(), HadistRiwayat.class);
                halaman_hadistRiwayat.putExtra("id", (int)id);
                startActivity(halaman_hadistRiwayat);
            }
        });
    }

}
