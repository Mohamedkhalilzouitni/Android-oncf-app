package com.example.zouitni.oncffood;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Categories extends AppCompatActivity {
    static String[][] categories = new String[10][10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
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

        categories[0] = new String[]{"Category 1","Item Name", "Item Description","Item Price", "a", "a", "a", "a", "a", "a"};
        for (int i = 1; i<10;i++) {
            categories[i] = categories[0];
        }
        ListAdapter buckysAdapter = new CategoryAdapter(this, categories);
        ListView cats = (ListView) findViewById(R.id.Categories);

        cats.setAdapter(buckysAdapter);
    }
}
