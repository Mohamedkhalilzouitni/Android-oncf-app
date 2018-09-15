package com.example.zouitni.oncffood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class secondSplash extends AppCompatActivity {

    static String[][] restoData = new String[10][10]; ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_splash);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                startActivity(new Intent("com.example.zouitni.SECONDPOINT"));
            }
        };
        timer.start();


        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            int size = bundle.getInt("size");
            restoData=new String[size][10];
            Object[] objectArray = (Object[]) bundle.getSerializable("restoData");
            if(objectArray!=null){
                restoData = new String[objectArray.length][];
                for(int i=0;i<objectArray.length;i++){
                    restoData[i]=(String[]) objectArray[i];
                }
            }
        } else {
            Toast.makeText(this,"Veuillez attendez SVP...",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
