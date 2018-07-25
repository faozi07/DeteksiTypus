package com.android.DeteksiTypus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class HasilActivity extends AppCompatActivity {

    TextView teksNama, teksUsername, teksUmur, teksHasilDemam, teksHasilSakitKepala, teksNafsuMakan, teksPencernaan, teksBatukKering,
            teksBercakMerah, teksPerutBengkak, teksHasil, teksSolusi;
    ImageView imgProfil;
    String[] hasil = {};
    File avatarFile;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Hasil Diagnosa");
        }
        SharedPreferences spLogin = getSharedPreferences(StaticVars.SP_LOGIN, Context.MODE_PRIVATE);

        teksNama = findViewById(R.id.teksNama);
        teksUmur = findViewById(R.id.teksUmur);
        teksHasilDemam = findViewById(R.id.hasilDemam);
        teksHasilSakitKepala = findViewById(R.id.hasilSakitKepala);
        teksNafsuMakan = findViewById(R.id.hasilNafsuMakan);
        teksPencernaan = findViewById(R.id.hasilPencernaan);
        teksBatukKering = findViewById(R.id.hasilBatukKering);
        teksBercakMerah = findViewById(R.id.hasilBercakMerah);
        teksPerutBengkak = findViewById(R.id.hasilPerutBengkak);
        teksHasil = findViewById(R.id.teksHasil);
        teksSolusi = findViewById(R.id.solusi);
        teksUsername = findViewById(R.id.teksUsername);
        imgProfil = findViewById(R.id.imgProfil);

        teksSolusi.setText(spLogin.getString(StaticVars.SP_LOGIN_SOLUSI_DIAGNOSA, ""));
        hasil = getResources().getStringArray(R.array.hasilTipes);
        teksHasil.setText(spLogin.getString(StaticVars.SP_LOGIN_HASIL_DIAGNOSA, ""));
        if (spLogin.getBoolean(StaticVars.isDemam, false)) {
            teksHasilDemam.setVisibility(View.VISIBLE);
            teksHasilDemam.setText("- "+hasil[0]);
        } else {
            teksHasilDemam.setVisibility(View.GONE);
        }

        if (spLogin.getBoolean(StaticVars.isSakitKepala, false)) {
            teksHasilSakitKepala.setVisibility(View.VISIBLE);
            teksHasilSakitKepala.setText("- " + hasil[1]);
        } else {
            teksHasilSakitKepala.setVisibility(View.GONE);
        }

        if (spLogin.getBoolean(StaticVars.isNafsuMakan, false)) {
            teksNafsuMakan.setVisibility(View.VISIBLE);
            teksNafsuMakan.setText("- " + hasil[2]);
        } else {
            teksNafsuMakan.setVisibility(View.GONE);
        }

        if (spLogin.getBoolean(StaticVars.isGangguan, false)) {
            teksPencernaan.setVisibility(View.VISIBLE);
            teksPencernaan.setText("- " + hasil[3]);
        } else {
            teksPencernaan.setVisibility(View.GONE);
        }

        if (spLogin.getBoolean(StaticVars.isMuncul, false)) {
            teksBatukKering.setVisibility(View.VISIBLE);
            teksBatukKering.setText("- " + hasil[4]);
        } else {
            teksBatukKering.setVisibility(View.GONE);
        }

        if (spLogin.getBoolean(StaticVars.isTimbul, false)) {
            teksBercakMerah.setVisibility(View.VISIBLE);
            teksBercakMerah.setText("- " + hasil[5]);
        } else {
            teksBercakMerah.setVisibility(View.GONE);
        }

        if (spLogin.getBoolean(StaticVars.isPerut, false)) {
            teksPerutBengkak.setVisibility(View.VISIBLE);
            teksPerutBengkak.setText("- " + hasil[6]);
        } else {
            teksPerutBengkak.setVisibility(View.GONE);
        }

        avatarFile = new File(spLogin.getString(StaticVars.SP_LOGIN_AVATAR, ""));
        if (!spLogin.getString(StaticVars.SP_LOGIN_AVATAR, "").equals("")) {
            imgProfil.setImageURI(Uri.fromFile(avatarFile));
        }
        teksUsername.setText(spLogin.getString(StaticVars.SP_LOGIN_USERNAME, ""));
        teksNama.setText(spLogin.getString(StaticVars.SP_LOGIN_NAMA, ""));
        teksUmur.setText(spLogin.getString(StaticVars.SP_LOGIN_UMUR, ""));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
