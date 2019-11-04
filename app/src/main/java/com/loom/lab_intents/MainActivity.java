package com.loom.lab_intents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private static final int getResult = 1;
    private static final String textche = "$Weed Papi";

    private Logger loomLog;

    private Button startExplicit;
    private Button startImplicit;
    private Button sent;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == getResult) {
            if (resultCode == 1) {
                loomLog.info("Tua sam");
                String s = data.getStringExtra(textche);

                result.setText(s);
            }
        }
    }
}
