package com.example.zouitni.oncffood;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class Test_activity extends AppCompatActivity {

    EditText idTicket;
    static String Ticket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_activity);



        Button enter = (Button) findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idTicket = findViewById(R.id.idTicketPrompt);
                Ticket = idTicket.getText().toString();
                WebService ws = null;
                try {
                    ws = new WebService(Test_activity.this);
                } catch (Exception E) {
                    E.printStackTrace();
                }
                ws.execute(Ticket,"","Debut");
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
