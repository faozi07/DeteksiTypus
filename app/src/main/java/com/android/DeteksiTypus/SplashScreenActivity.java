package com.android.DeteksiTypus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SoundPool sp;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sp = new SoundPool.Builder().setMaxStreams(5).build();
        } else {
            sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        int sound = sp.load(SplashScreenActivity.this, R.raw.splashscreen, 1);
        sp.play(sound, 1.0f, 1.0f, 0, 0, 1.0f);

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
