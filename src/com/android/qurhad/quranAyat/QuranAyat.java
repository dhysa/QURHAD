package com.android.qurhad.quranAyat;

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
import com.android.qurhad.TextViewPlus;
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
        TextViewPlus nama_suratAyat = (TextViewPlus) findViewById(R.id.nama_suratAyat);

        final Intent fetchID = getIntent();
        int id = fetchID.getIntExtra("id", 0);
        int jumlah = (id + 1);
        dbHelper = new DatabaseHelper(this);

        //openDatabase
        try {
            dbHelper.checkAndCopyDatabase();
            dbHelper.openDatabase();
        } catch (SQLException e) {

        }

        // ambil data dengan query dan memasukan data ke item list
        try {
            Cursor cursorNamaSurat = dbHelper.QueryData("select nama from  nama_surat where id_nama_surat =" + jumlah);
            Cursor cursor = dbHelper.QueryData("select aya,text_quran, text_indo from  quran_terjemahan where id_surat =" + jumlah);
            if (cursor != null && cursorNamaSurat != null) {
                if (cursor.moveToFirst() && cursorNamaSurat.moveToFirst()) {
                    do {
                        nama_suratAyat.setText(cursorNamaSurat.getString(cursorNamaSurat.getColumnIndex("nama")));
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

        // masukin item ke dalem adapter di tampilkan ke ListView
        adapter = new QuranAyat_Adapter(this, R.layout.quran_ayat_item_list, arrayList);
        listView = (ListView) findViewById(R.id.list_ayat);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final CharSequence menu[] = new CharSequence[]{"Bookmark", "Notes"};
                AlertDialog.Builder builder = new AlertDialog.Builder(QuranAyat.this);
                builder.setTitle("Pilihan Menu");
                builder.setItems(menu, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // the user clicked on menu[which]
                                switch (which) {
                                    case 0:
                                        try {
                                            dbHelper.openDatabase();
                                            dbHelper.ExeSQLData("UPDATE quran_terjemahan SET bookmarks= 1 WHERE id_quran=" + 5);
                                            Toast toast = Toast.makeText(QuranAyat.this, "Bookmark telah ditambahka", Toast.LENGTH_SHORT);
                                            toast.show();

                                        } catch (SQLException e) {

                                        }
                                        break;
                                    case 1:
                                        // get notes_input.xml view
                                        LayoutInflater layoutInflater = LayoutInflater.from(QuranAyat.this);
                                        View promptView = layoutInflater.inflate(R.layout.notes_input, null);
                                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(QuranAyat.this);
                                        alertDialogBuilder.setView(promptView);


                                        final EditText judul_note = (EditText) promptView.findViewById(R.id.judul_notes);
                                        final EditText isi_notes = (EditText) promptView.findViewById(R.id.isi_notes);
                                        // setup a dialog window
                                        alertDialogBuilder.setCancelable(false)
                                                .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        final String judul = judul_note.getText().toString();
                                                        final String isi = isi_notes.getText().toString();

                                                        try {
                                                            dbHelper.openDatabase();
                                                            dbHelper.ExeSQLData("insert  into notes (judul_notes , isi_notes)  values('" + judul + "', '" + isi + "')");
                                                            Toast toast = Toast.makeText(QuranAyat.this, "Notes telah ditambahkan", Toast.LENGTH_SHORT);
                                                            toast.show();

                                                        } catch (SQLException e) {

                                                        }

                                                    }
                                                })
                                                .setNegativeButton("Batal",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {
                                                                dialog.cancel();
                                                            }
                                                        });

                                        // create an alert dialog
                                        AlertDialog alert = alertDialogBuilder.create();
                                        alert.show();
                                        break;
                                }
                            }
                        }

                );
                builder.show();
            }
        });

    }

}

