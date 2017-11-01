package com.example.navigationmenu;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;
    //Meetodi algus:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() { // Ei tea
            @Override
            public void run() { //Loob handler-i
                Intent mainIntent = new Intent(SplashActivity.this,//Loob intent-i ja läheb 'MainActivity' ekraanile (Avab activity_main.xml layout-i)
                        MainActivity.class);
                startActivity(mainIntent);
                finish();//Lõpetab handleri töö
            }
        }, SPLASH_TIME_OUT);//Alustab SPLASH_TIME_OUT-i
    }
}
