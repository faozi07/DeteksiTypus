package com.android.DeteksiTypus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    MediaPlayer mpSound1 = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mpSound1 = MediaPlayer.create(SplashScreenActivity.this, R.raw.splashscreen);
        mpSound1.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences spLogin = getSharedPreferences(StaticVars.SP_LOGIN, MODE_PRIVATE);
                if (spLogin.getString(StaticVars.SP_LOGIN_USERNAME,"").equals("")) {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                }
            }
        }, 8000);

    }
}
