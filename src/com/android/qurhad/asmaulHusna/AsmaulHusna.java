package com.android.qurhad.asmaulHusna;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import com.android.qurhad.R;
import com.android.qurhad.database.DatabaseHelper;


import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ayyu Andhysa on 8/30/2016.
 */
public class AsmaulHusna extends Activity {
    private DatabaseHelper dbHelper;
    private ListView listAsmaulHusna;
    private AsmaulHusna_Adapter adapter;
    ArrayList<AsmaulHusna_Item> arrayList = new ArrayList<AsmaulHusna_Item>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asmaul_husna);

        dbHelper = new DatabaseHelper(this);

        try {
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        } catch (SQLException e) {

        }


        try {
            Cursor cursor = dbHelper.QueryData("select * from  asmaul_husna");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        AsmaulHusna_Item item = new AsmaulHusna_Item();
                        item.setId_asmaulHusna(cursor.getString(0));
                        item.setNama_asmaulHusna(cursor.getString(1));
                        item.setArti_asmaulHusna(cursor.getString(2));
                        item.setArab_asmaulHusna(cursor.getString(3));
                        arrayList.add(item);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLException e) {

        }

        listAsmaulHusna = (ListView) findViewById(R.id.list_asmaulHusna);
        adapter = new AsmaulHusna_Adapter(this, R.layout.asmaul_husna_item_list, arrayList);
        listAsmaulHusna.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}

