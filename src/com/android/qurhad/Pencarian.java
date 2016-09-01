
package com.android.qurhad;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.android.qurhad.database.DatabaseHelper;

/**
 * Created by Ayyu Andhysa on 7/12/2016.
 */
public class Pencarian extends Activity {
    DatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pencarian);
        final TextView pencarian = (TextView) findViewById(R.id.pencarianText);
        final EditText cari = (EditText) findViewById(R.id.cariField);
        ImageButton tombolCari = (ImageButton) findViewById(R.id.cariButton);

        dbHelper = new DatabaseHelper(this);

        tombolCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p = cari.getText().toString();

                // Check if Value Empty
                if (TextUtils.isEmpty(p)) {
                    // Show Toast
                    Toast.makeText(Pencarian.this, "Kolom Belum Terisi", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        dbHelper.checkAndCopyDatabase();
                        dbHelper.openDatabase();
                        Cursor cursor = dbHelper.QueryData("select id_surat, text_indo from quran_terjemahan");

                        cursor.moveToFirst();
                        if (!cursor.isAfterLast()) {
                            do {
                                String t = cursor.getString(cursor.getColumnIndex("text_indo"));

                                char[] text = t.toCharArray();
                                char[] pattern = p.toCharArray();

                                int pos = BoyerMoore.indexOf(text, pattern);

                                if(pos >= 0){
                                    pencarian.append("index ke " + pos + "\n");
                                }

                            } while (cursor.moveToNext());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
