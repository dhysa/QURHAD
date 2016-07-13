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


}