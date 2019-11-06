package com.loom.lab_intents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URI;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private static final int getResult = 1;
    private static final int slikaPrilika = 2;
    private static final String textche = "$Weed Papi";

    private Logger loomLog;

    private Button startExplicit;
    private Button startImplicit;
    private Button sent;
    private Button slika;

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loomLog = Logger.getLogger("MainActivity");

        initViews();
        initListeners();
    }

    private void initViews() {
        startExplicit = findViewById(R.id.startExplicit);
        startImplicit = findViewById(R.id.startImplicit);
        sent = findViewById(R.id.send);
        slika = findViewById(R.id.img);

        result = findViewById(R.id.txtview);
    }

    private void initListeners() {
        startExplicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startExplicit();
            }
        });
        startImplicit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImplicit();
            }
        });
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentMsg();
            }
        });
        slika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otvoriSlika();
            }
        });
    }

    private void startExplicit() {
        Intent intent = new Intent(this, ExplicitActivity.class);

        startActivityForResult(intent, getResult);
    }

    private void startImplicit() {
        Intent intent = new Intent();

        intent.setAction("com.loom.lab.IMPLICIT_ACTION");
        intent.addCategory("android.intent.category.DEFAULT");

        startActivity(intent);
    }

    private void sentMsg() {
        Intent intent = new Intent();

        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_SUBJECT, "MPiP Send Title");
        intent.putExtra(Intent.EXTRA_TEXT, "Content send from MainActivity");
        intent.setType("text/plain");

        startActivity(intent);
    }

    private void otvoriSlika() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String [] types = {"image/jpg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, types);
        startActivityForResult(intent, slikaPrilika);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == getResult) {
            if (resultCode == 1) {
                loomLog.info("Tua sam");
                String s = data.getStringExtra(textche);

                result.setText(s);
            }
        } else if (requestCode == slikaPrilika) {
            if (resultCode == RESULT_OK){
                //startActivity(new Intent(Intent.ACTION_VIEW, data.getData()));

                Uri slika = data.getData();

                vidiaSlikata(slika);
            }
        }
    }

    private void vidiaSlikata(Uri slika) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(slika, "image/*");

        Intent chooser = Intent.createChooser(intent, "Choose your app!");

        if (chooser.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
    }
}
