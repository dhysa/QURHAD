package com.android.qurhad.bookmark;

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
public class Bookmark extends MainMenu {
    private ListView listView;
    private DatabaseHelper dbHelper;
    private Bookmark_Adapter adapter;
    ArrayList<Bookmark_Item> arrayList = new ArrayList<Bookmark_Item>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookmark);

        dbHelper = new DatabaseHelper(this);

        try {
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        } catch (SQLException e) {

        }

        try {
            Cursor cursor = dbHelper.QueryData("select aya, text_quran, text_indo from quran_terjemahan where bookmarks =1");
            if (cursor != null ) {
                if (cursor.moveToFirst()) {
                    do {
                        Bookmark_Item item = new Bookmark_Item();
                        item.setAya(cursor.getString(0));
                        item.setText_quran(cursor.getString(1));
                        item.setText_indo(cursor.getString(2));
                        arrayList.add(item);
                    } while (cursor.moveToNext());
                }
            }
        } catch (SQLException e) {

        }

        adapter = new Bookmark_Adapter(this, R.layout.bookmark_item_list, arrayList);
        listView = (ListView) findViewById(R.id.list_Bookmark);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
