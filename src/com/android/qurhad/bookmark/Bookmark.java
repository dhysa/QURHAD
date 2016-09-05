package com.android.qurhad.bookmark;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.android.qurhad.R;
import com.android.qurhad.database.DatabaseHelper;
import com.android.qurhad.main.MainMenu;
import com.android.qurhad.quranAyat.QuranAyat;
import com.android.qurhad.quranSurat.QuranSurat;

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
            Cursor cursor = dbHelper.QueryData("select aya, text_quran, text_indo, id_quran from quran_terjemahan where bookmarks =1");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Bookmark_Item item = new Bookmark_Item();
                        item.setAya(cursor.getString(0));
                        item.setText_quran(cursor.getString(1));
                        item.setText_indo(cursor.getString(2));
                        item.setId_quran(cursor.getString(3));
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, long id) {

                final CharSequence menu[] = new CharSequence[]{"Hapus", "Rubah"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Bookmark.this);
                builder.setTitle("Pilihan Menu");
                builder.setItems(menu, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // the user clicked on menu[which]
                                switch (which) {
                                    case 0:
//                                        Toast.makeText(getApplicationContext(), adapter.getItem(position).getId_quran() , Toast.LENGTH_LONG).show();
                                        try {

                                            dbHelper.openDatabase();
                                            dbHelper.ExeSQLData("UPDATE quran_terjemahan SET bookmarks = null WHERE id_quran = " + adapter.getItem(position).getId_quran());
                                            Intent bookmark = new Intent(Bookmark.this,Bookmark.class);
                                            bookmark.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            bookmark.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(bookmark);
                                            Toast toast = Toast.makeText(Bookmark.this, "Bookmark telah dihapus", Toast.LENGTH_SHORT);
                                            toast.show();

                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }

                                        break;
                                    case 1:
                                        // get notes_input.xml view
                                        try {
                                            dbHelper.openDatabase();
                                            dbHelper.ExeSQLData("UPDATE quran_terjemahan SET bookmarks = null WHERE id_quran = " + adapter.getItem(position).getId_quran());
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        Intent intent = new Intent(Bookmark.this, QuranSurat.class);
                                        startActivity(intent);
                                        break;
                                }
                            }
                        }

                );
                builder.show();
            }

        });

    }

    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}