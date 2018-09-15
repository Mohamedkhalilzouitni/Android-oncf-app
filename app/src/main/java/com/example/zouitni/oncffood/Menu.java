package com.example.zouitni.oncffood;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Menu extends AppCompatActivity {

    static String[][] alertes = new String[10][10];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        alertes[0] = new String[]{"a","Item Name", "Item Description","Item Price", "a", "a", "a", "a", "a", "a"};
        for (int i = 1; i<10;i++) {
            alertes[i] = alertes[0];
        }
        ListAdapter buckysAdapter = new com.example.zouitni.oncffood.MenuAdapter(this, alertes);
        ListView buckysListView = findViewById(R.id.Menu);

        buckysListView.setAdapter(buckysAdapter);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}
