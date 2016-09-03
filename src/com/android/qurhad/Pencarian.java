package com.android.qurhad;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.android.qurhad.database.DatabaseHelper;
import com.android.qurhad.main.MainMenu;

/**
 * Created by Ayyu Andhysa on 7/12/2016.
 */
public class Pencarian extends MainMenu {
    DatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pencarian);
        final TextView pencarian = (TextView) findViewById(R.id.pencarianText);
        final TextView pencarianH = (TextView) findViewById(R.id.pencarianHadis);
        final EditText cari = (EditText) findViewById(R.id.cariField);
        ImageButton tombolCari = (ImageButton) findViewById(R.id.cariButton);

        dbHelper = new DatabaseHelper(this);

        // TabHost
        final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec spec;
        spec = tabHost.newTabSpec("Al-Quran").setIndicator("Al-Quran").setContent(R.id.AlQuran);
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("Hadist").setIndicator("Hadist").setContent(R.id.Hadist);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);

        // Background color and text color tab
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#ffffcc"));
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
            tv.setTextColor(Color.parseColor("#763F32"));
            tv.setTextAppearance(Pencarian.this, android.R.style.TextAppearance_DeviceDefault_Medium);
            if (i == tabHost.getCurrentTab()) {
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#763F32"));
                tv.setTextColor(Color.parseColor("#ffd9b3"));
            } else {
                tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#d9afa6"));
                tv.setTextColor(Color.parseColor("#763F32"));
            }

            // Set Height Tab Title
            final View view = tabHost.getTabWidget().getChildTabViewAt(i);
            if (view != null) {
                // reduce height of the tab
                view.getLayoutParams().height *= 0.7;

                //  get title text view
                final View textView = view.findViewById(android.R.id.title);
                if (textView instanceof TextView) {
                    // just in case check the type

                    // center text
                    ((TextView) textView).setGravity(Gravity.CENTER_VERTICAL);
                    // wrap text
                    ((TextView) textView).setSingleLine(false);

                    // explicitly set layout parameters
                    textView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    textView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                }
            }
        }

        // Tab OnChangeListener
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String arg0) {
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#ffffcc"));
                    TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                    tv.setTextColor(Color.parseColor("#763F32"));
                    if (i == tabHost.getCurrentTab()) {
                        tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundColor(Color.parseColor("#763F32"));
                        tv.setTextColor(Color.parseColor("#ffd9b3"));
                    } else {
                        tabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#FFF1B5"));
                        tv.setTextColor(Color.parseColor("#763F32"));
                    }
                }
            }
        });

        //Show keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(cari, InputMethodManager.SHOW_FORCED);

        tombolCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dbHelper.checkAndCopyDatabase();
                    dbHelper.openDatabase();
                    Cursor cursorQuran = dbHelper.QueryData("select nama_surat.nama, quran_terjemahan.text_indo, quran_terjemahan.aya from quran_terjemahan " +
                            "left outer join nama_surat on quran_terjemahan.id_surat = nama_surat.id_nama_surat");
                    Cursor cursorHadist = dbHelper.QueryData("select tema_hadist.tema, hadist.isi_hadist, hadist.nama_periwayat from hadist" +
                            " left outer join tema_hadist on hadist.id_hadist_tema = tema_hadist.id_tema");
                    cursorQuran.moveToFirst();
                    cursorHadist.moveToFirst();

                    String p = cari.getText().toString().toLowerCase();
                    pencarian.setText("");
                    pencarianH.setText("");

                    // Check if Value Empty
                    if (TextUtils.isEmpty(p)) {
                        // Show Toast
                        Toast.makeText(Pencarian.this, "Kolom Belum Terisi", Toast.LENGTH_SHORT).show();
                    } else {

                        //Hide keyboard
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(cari.getWindowToken(), 0);

                        if (!cursorQuran.isAfterLast()) {
                            do {
                                String t = cursorQuran.getString(cursorQuran.getColumnIndex("text_indo")).toLowerCase();


                                char[] text = t.toCharArray();
                                char[] pattern = p.toCharArray();

                                int pos = BoyerMoore.indexOf(text, pattern);
                                String surat = cursorQuran.getString(cursorQuran.getColumnIndex("nama"));
                                String ayat = cursorQuran.getString(cursorQuran.getColumnIndex("aya"));
                                String terjemahan = cursorQuran.getString(cursorQuran.getColumnIndex("text_indo"));

                                if (pos >= 0) {

                                    //pencarian.append("index ke " + pos + "\n" + " surat " + surat + " , dan ayat ke " + ayat + "\n");
                                    pencarian.append("Surat " + surat + "\n" +
                                            "ayat ke " + ayat + ", artinya: " + terjemahan + "\n" + "\n");
//                                    pencarianH.append("Tema Hadist: " + tema + "\n" +
//                                            "H.R Berbunyi: " + isi_hadist + "Diriwayatkan oleh: "+ periwayat+ "\n" + "\n" );


                                }

                            } while (cursorQuran.moveToNext());
                        }

                        if (!cursorHadist.isAfterLast()) {
                            do {
                                String tHadist = cursorHadist.getString(cursorHadist.getColumnIndex("isi_hadist")).toLowerCase();

                                char[] textHadist = tHadist.toCharArray();
                                char[] patternHadist = p.toCharArray();

                                int posHadist = BoyerMoore.indexOf(textHadist, patternHadist);
                                String tema = cursorHadist.getString(cursorHadist.getColumnIndex("tema"));
                                String periwayat = cursorHadist.getString(cursorHadist.getColumnIndex("nama_periwayat"));
                                String isi_hadist = cursorHadist.getString(cursorHadist.getColumnIndex("isi_hadist"));


                                if (posHadist >= 0) {

                                    //pencarian.append("index ke " + pos + "\n" + " surat " + surat + " , dan ayat ke " + ayat + "\n");
                                    pencarianH.append("Tema Hadist: " + tema + "\n" +
                                            "H.R Berbunyi: " + isi_hadist + "Diriwayatkan oleh: " + periwayat + "\n" + "\n");

                                }

                            } while (cursorHadist.moveToNext());

                        }

                        if(pencarian.getText().length() == 0){
                            pencarian.setText("Kata tidak ditemukan");
                        }
                        if(pencarianH.getText().length() == 0){
                            pencarianH.setText("Kata tidak ditemukan");
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }
}
