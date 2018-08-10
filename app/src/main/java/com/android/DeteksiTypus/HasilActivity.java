package com.android.DeteksiTypus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class HasilActivity extends AppCompatActivity {

    TextView teksNama, teksUsername, teksUmur, teksHasil, teksSolusi;
    ImageView imgProfil;
    String[] hasil = {};
    File avatarFile;
    ListView listView;
    Button btnHasilDiagnosa;
    ArrayAdapter<String> arrayAdapter;

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
        teksHasil = findViewById(R.id.teksHasil);
        teksSolusi = findViewById(R.id.solusi);
        teksUsername = findViewById(R.id.teksUsername);
        imgProfil = findViewById(R.id.imgProfil);
        listView = findViewById(R.id.listView);
        btnHasilDiagnosa = findViewById(R.id.btnLihatHasil);

        btnHasilDiagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInfoDiagnosa();
            }
        });

        if (StaticVars.arrayDiagnosa.isEmpty()) {
            Set<String> set = new HashSet<>();
            StaticVars.arrayDiagnosa.addAll(spLogin.getStringSet(StaticVars.SP_LOGIN_ARRAY_DIAGNOSA, set));
        }

        teksSolusi.setText(spLogin.getString(StaticVars.SP_LOGIN_SOLUSI_DIAGNOSA, ""));
        hasil = getResources().getStringArray(R.array.hasilTipes);
        teksHasil.setText(spLogin.getString(StaticVars.SP_LOGIN_HASIL_DIAGNOSA, ""));

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

    @SuppressLint({"SetTextI18n", "InflateParams"})
    private void dialogInfoDiagnosa() {
        LayoutInflater inflater;
        View dialog_layout;
        AlertDialog.Builder dialogInfo;
        final AlertDialog theDialog;

        inflater = LayoutInflater.from(this);
        dialog_layout = inflater.inflate(R.layout.dialog_hasil_diagnosa, null);

        dialogInfo = new AlertDialog.Builder(this);
        dialogInfo.setView(dialog_layout);
        dialogInfo.setCancelable(true);
        theDialog = dialogInfo.create();
        listView = dialog_layout.findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, StaticVars.arrayDiagnosa);
        listView.setAdapter(arrayAdapter);

        if (!HasilActivity.this.isFinishing()) {
            theDialog.show();
        }
    }

}
