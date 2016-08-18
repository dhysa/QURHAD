package com.android.qurhad;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Ayyu Andhysa on 6/28/2016.
 */
public class QuranSurat extends Activity {
    private DatabaseHelper dbHelper;
    ListView listView;
    QuranSurat_Adapter adapter;
    ArrayList<QuranSurat_Item> arrayList = new ArrayList<QuranSurat_Item>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quran_surat);

        dbHelper = new DatabaseHelper(this);

        try {
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        } catch (SQLException e){

        }
        try {
            Cursor cursor = dbHelper.QueryData("select * from nama_surat");
            if ( cursor != null){
                if (cursor.moveToFirst()){
                    do{
                        QuranSurat_Item item = new QuranSurat_Item();
                        item.setId_nama_surat(cursor.getString(0));
                        item.setNama(cursor.getString(1));
                        arrayList.add(item);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLException e){}

        adapter = new QuranSurat_Adapter(this, R.layout.quran__surat_item_list,arrayList);
        listView = (ListView) findViewById(R.id.list_quran);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }
}
