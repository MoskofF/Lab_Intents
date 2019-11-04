package com.loom.lab_intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ExplicitActivity extends AppCompatActivity {

    private Button okey;
    private Button cancel;

    private EditText vnesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);

        initViews();
        initListeners();
    }

    private void initViews() {
        okey = findViewById(R.id.okey);
        cancel = findViewById(R.id.cancel);

        vnesi = findViewById(R.id.vnesi);
    }

    private void initListeners() {
        okey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sendMsg() {
        String s = vnesi.getText().toString();

        Intent msg = new Intent();

        msg.putExtra("$Weed Papi", s);
        setResult(1, msg);
        finish();
    }
}
