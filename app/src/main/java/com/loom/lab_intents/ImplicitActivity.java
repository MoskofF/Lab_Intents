package com.loom.lab_intents;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ImplicitActivity extends AppCompatActivity {

    private TextView lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        initViews();

        listaPopuni();
    }

    private void initViews() {
        lista = findViewById(R.id.lista);
    }

    private void listaPopuni() {
        Intent filter = new Intent("android.intent.action.MAIN", null);
        filter.addCategory("android.intent.category.LAUNCHER");

        PackageManager packMng = this.getPackageManager();

        List<ResolveInfo> inf = packMng.queryIntentActivities(filter, 0);

        //lista.setText(inf.get(0).loadLabel(packMng));

        for (ResolveInfo loom : inf) {
            lista.append(loom.loadLabel(packMng) + "\n");
        }
    }
}
