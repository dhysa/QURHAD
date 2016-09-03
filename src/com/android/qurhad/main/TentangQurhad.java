package com.android.qurhad.main;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.android.qurhad.R;

/**
 * Created by Ayyu Andhysa on 7/12/2016.
 */
public class TentangQurhad extends MainMenu{

    // Load Layout Tentang QurHad
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tentang_qurhad);

        // Button to Send Feedback
        Button sendFeedback = (Button) findViewById(R.id.buttonFeedback);
        sendFeedback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO
                        , Uri.fromParts("mailto", "ayyu.andhysa@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback Aplikasi QurHad");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "[Feedback Anda]...");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

    }

}
