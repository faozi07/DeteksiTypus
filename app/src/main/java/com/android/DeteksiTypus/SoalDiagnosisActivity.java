package com.android.DeteksiTypus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SoalDiagnosisActivity extends AppCompatActivity {

    TextView teksPertanyaan, tNomor;
    RadioButton rbYa, rbNo;
    RadioGroup rbGrup;
    Button btnNext;
    String[] pertanyaan = {};
    int noSoal = 0;
    boolean isYes = false, isPilih = false;
    public static boolean isTipes = false, isDemam = false, isSakitKepala = false, isNafsuMakan = false, isGangguan = false, isMuncul = false,
            isTimbul = false, isPerut = false;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_diagnosis);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Diagnosis");
        }
        pertanyaan = getResources().getStringArray(R.array.pertanyaanTipes);
        teksPertanyaan = findViewById(R.id.pertanyaan);
        tNomor = findViewById(R.id.tno);
        btnNext = findViewById(R.id.btnNext);
        rbYa = findViewById(R.id.rbYa);
        rbNo = findViewById(R.id.rbTidak);
        rbGrup = findViewById(R.id.rbGrup);
        rbYa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isYes = true;
                isPilih = true;
                if (noSoal == 0) {
                    isDemam = true;
                } else if (noSoal == 1) {
                    isSakitKepala = true;
                } else if (noSoal == 2) {
                    isNafsuMakan = true;
                } else if (noSoal == 3) {
                    isGangguan = true;
                } else if (noSoal == 4) {
                    isMuncul = true;
                } else if (noSoal == 5) {
                    isTimbul = true;
                } else if (noSoal == 6) {
                    isPerut = true;
                }
            }
        });
        rbNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isYes = false;
                isPilih = true;
                if (noSoal == 0) {
                    isDemam = false;
                } else if (noSoal == 1) {
                    isSakitKepala = false;
                } else if (noSoal == 2) {
                    isNafsuMakan = false;
                } else if (noSoal == 3) {
                    isGangguan = false;
                } else if (noSoal == 4) {
                    isMuncul = false;
                } else if (noSoal == 5) {
                    isTimbul = false;
                } else if (noSoal == 6) {
                    isPerut = false;
                }
            }
        });
        tNomor.setText(String.valueOf(noSoal + 1)+".");
        teksPertanyaan.setText(pertanyaan[noSoal]);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbGrup.clearCheck();
                if (isPilih) {
                    isPilih = false;
                    noSoal = noSoal + 1;
                    if (noSoal < pertanyaan.length) {
                        teksPertanyaan.setText(pertanyaan[noSoal]);
                        tNomor.setText(String.valueOf(noSoal + 1)+".");
                    }
                    if (noSoal >= pertanyaan.length - 1) {
                        btnNext.setText("Selesai");
                        btnNext.setTextColor(Color.RED);
                    }

                    if (noSoal >= pertanyaan.length) {
                        if (isGangguan && isMuncul && isTimbul && isPerut) {
                            hasilDiagnosa("Anda terindikasi penyakit Tipes", StaticVars.solusiTipes, isDemam, isSakitKepala, isNafsuMakan, true, true,
                                    true, true);
                        } else if (isDemam && isSakitKepala && isNafsuMakan && !isGangguan || !isMuncul || !isTimbul || !isPerut){
                            hasilDiagnosa("Anda terindikasi Gejala Tipes", StaticVars.solusiGejala, isDemam, isSakitKepala, isNafsuMakan, isGangguan, isMuncul,
                                    isTimbul, isPerut);
                        } else {
                            hasilDiagnosa("Anda tidak terindikasi penyakit Tipes", StaticVars.solusiTidakTipes, isDemam, isSakitKepala, isNafsuMakan, isGangguan, isMuncul,
                                    isTimbul, isPerut);
                        }
                        finish();
                    }
                } else {
                    isPilih = false;
                    Toast.makeText(SoalDiagnosisActivity.this, "Pilih jawaban Anda terlebih dahulu", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void hasilDiagnosa(String hasil, String solusi, boolean isDemam, boolean isSakitKepala, boolean isNafsuMakan, boolean isGangguan,
                               boolean isMuncul, boolean isTimbul, boolean isPerut) {
        SharedPreferences spLogin = getSharedPreferences(StaticVars.SP_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spLogin.edit();
        editor.putString(StaticVars.SP_LOGIN_HASIL_DIAGNOSA,hasil);
        editor.putString(StaticVars.SP_LOGIN_SOLUSI_DIAGNOSA,solusi);
        editor.putBoolean(StaticVars.isDemam,isDemam);
        editor.putBoolean(StaticVars.isSakitKepala,isSakitKepala);
        editor.putBoolean(StaticVars.isNafsuMakan,isNafsuMakan);
        editor.putBoolean(StaticVars.isGangguan,isGangguan);
        editor.putBoolean(StaticVars.isMuncul,isMuncul);
        editor.putBoolean(StaticVars.isTimbul,isTimbul);
        editor.putBoolean(StaticVars.isPerut,isPerut);
        editor.apply();
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
