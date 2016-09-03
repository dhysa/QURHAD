package com.android.qurhad.catatan;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import com.android.qurhad.R;
import com.android.qurhad.database.DatabaseHelper;
import com.android.qurhad.main.MainMenu;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ayyu Andhysa on 6/28/2016.
 */
public class Catatan extends MainMenu {
    private ListView listView;
    private DatabaseHelper dbHelper;
    private Catatan_Adapter adapter;
    ArrayList<Catatan_Item> arrayList = new ArrayList<Catatan_Item>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catatan);

        dbHelper = new DatabaseHelper(this);

        try {
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        } catch (SQLException e) {

        }

        try {
            Cursor cursor = dbHelper.QueryData("select judul_notes, isi_notes from notes");
            if (cursor != null ) {
                if (cursor.moveToFirst()) {
                    do {
                        Catatan_Item item = new Catatan_Item();
                        item.setJudul_notes(cursor.getString(0));
                        item.setIsi_notes(cursor.getString(1));
                        arrayList.add(item);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLException e) {

        }

        adapter = new Catatan_Adapter(this, R.layout.catatan_item_list, arrayList);
        listView = (ListView) findViewById(R.id.list_Notes);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
