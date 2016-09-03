package com.android.qurhad.hadistRiwayat;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import com.android.qurhad.R;
import com.android.qurhad.TextViewPlus;
import com.android.qurhad.database.DatabaseHelper;
import com.android.qurhad.hadistTema.HadistTema;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ayyu Andhysa on 8/30/2016.
 */
public class HadistRiwayat extends HadistTema {
    private DatabaseHelper dbHelper;
    private ListView listHadist;
    private HadistRiwayat_Adapter adapter;
    ArrayList<HadistRiwayat_Item> arrayList = new ArrayList<HadistRiwayat_Item>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hadist_riwayat);
        listHadist = (ListView) findViewById(R.id.list_hadistRiwayat);
        TextViewPlus nama_temaRiwayat = (TextViewPlus) findViewById(R.id.nama_temaRiwayat);

        Intent fetchID = getIntent();
        int id = fetchID.getIntExtra("id", 0);

        int jumlah = (id+1);

        dbHelper = new DatabaseHelper(this);

        try {
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        } catch (SQLException e) {

        }

        try {
            Cursor cursorNamaHadist = dbHelper.QueryData("select tema from tema_hadist where id_tema = " + jumlah);
            Cursor cursor = dbHelper.QueryData("select hadist_nomor, isi_hadist, nama_periwayat from hadist where  id_hadist_tema=" + jumlah);
            if (cursor != null && cursorNamaHadist != null) {
                if (cursor.moveToFirst() & cursorNamaHadist.moveToFirst()) {
                    do {
                        nama_temaRiwayat.setText(cursorNamaHadist.getString(cursorNamaHadist.getColumnIndex("tema")));
                        HadistRiwayat_Item item = new HadistRiwayat_Item();
                        item.setHadist_nomor(cursor.getString(0));
                        item.setIsi_hadist(cursor.getString(1));
                        item.setNama_periwayat(cursor.getString(2));
                        arrayList.add(item);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLException e) {

        }

        adapter = new HadistRiwayat_Adapter(this, R.layout.hadist_riwayat_item, arrayList);
        listHadist = (ListView) findViewById(R.id.list_hadistRiwayat);
        listHadist.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}