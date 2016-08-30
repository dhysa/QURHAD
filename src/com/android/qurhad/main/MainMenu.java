package com.android.qurhad.main;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.android.qurhad.*;
import com.android.qurhad.asmaulHusna.AsmaulHusna;
import com.android.qurhad.hadistTema.HadistTema;
import com.android.qurhad.quranSurat.QuranSurat;

public class MainMenu extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    public void quranButton(View view)
    {
        Intent halaman_quranSurat = new Intent(MainMenu.this, QuranSurat.class);
        startActivity(halaman_quranSurat);
    }

    public void hadistButton(View view)
    {
        Intent halaman_hadistTema = new Intent(MainMenu.this, HadistTema.class);
        startActivity(halaman_hadistTema);
    }

    public void catatanButton(View view)
    {
        Intent halaman_catatan = new Intent(MainMenu.this, Catatan.class);
        startActivity(halaman_catatan);
    }

    public void bookmarkButton(View view)
    {
        Intent halaman_bookmark = new Intent(MainMenu.this, Bookmark.class);
        startActivity(halaman_bookmark);
    }

    public void tentangButton(View view)
    {
        Intent halaman_tentangQurHad = new Intent(MainMenu.this, TentangQurhad.class);
        startActivity(halaman_tentangQurHad);
    }

    public void asmaulHusnaButton(View view)
    {
        Intent halaman_asmaulHusna = new Intent(MainMenu.this, AsmaulHusna.class);
        startActivity(halaman_asmaulHusna);
    }

}