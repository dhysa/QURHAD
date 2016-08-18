package com.android.qurhad;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

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
        Intent intent = new Intent(MainMenu.this, QuranSurat.class);
        startActivity(intent);
    }

    public void hadistButton(View view)
    {
        Intent intent = new Intent(MainMenu.this, HadistRiwayat.class);
        startActivity(intent);
    }

    public void cariButton(View view)
    {
        Intent intent = new Intent(MainMenu.this, Pencarian.class);
        startActivity(intent);
    }

    public void catatanButton(View view)
    {
        Intent intent = new Intent(MainMenu.this, Catatan.class);
        startActivity(intent);
    }

    public void bookmarkButton(View view)
    {
        Intent intent = new Intent(MainMenu.this, Bookmark.class);
        startActivity(intent);
    }

    public void tentangButton(View view)
    {
        Intent intent = new Intent(MainMenu.this, TentangQurhad.class);
        startActivity(intent);
    }

    public void pengaturanButton(View view)
    {
        Intent intent = new Intent(MainMenu.this, Pengaturan.class);
        startActivity(intent);

    }


}