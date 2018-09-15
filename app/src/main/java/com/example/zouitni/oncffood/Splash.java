package com.example.zouitni.oncffood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                startActivity(new Intent("com.example.zouitni.STARTINGPOINT"));
            }
        };
        timer.start();
    }
    public void onBackPressed() {
        Toast.makeText(this,"Appuyer une nouvelle fois pour quitter",Toast.LENGTH_SHORT).show();
        /*onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);*/
    }
}
