package com.android.DeteksiTypus;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SoalDiagnosisActivity extends AppCompatActivity {

    TextView teksPertanyaan, tNomor;
    RadioButton rbYa, rbNo;
    RadioGroup rbGrup;
    Button btnNext, btnPlay;
    ImageView imgMusic;
    String[] pertanyaan = {}, keterangan = {};
    int noSoal = 0, nomor = 0;
    boolean isYes = false, isPilih = false;
    public static boolean is1 = false, is2 = false, is3 = false, is4 = false, is5 = false, is6 = false, is7 = false, is8 = false, is9 = false, is10 = false,
            is11 = false, is12 = false, is13 = false, is14 = false, is15 = false, is16 = false, is17 = false;

    private int ss1, ss2, ss3, ss4, ss5, ss6, ss7, ss8, ss9, ss10;
    private int ss11, ss12, ss13, ss14, ss15, ss16, ss17, ss18, ss19, ss20;
    private int ss21, ss22, ss23, ss24, ss25, ss26, ss27, ss28, ss29, ss30;
    private int ss31, ss32, ss33, ss34, ss35, ss36, ss37, ss38, ss39, ss40;
    private int ss41, ss42, ss43, ss44, ss45, ss46, ss47, ss48, ss49, ss50;
    SoundPool sp;

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
        keterangan = getResources().getStringArray(R.array.hasilTipes);
        teksPertanyaan = findViewById(R.id.pertanyaan);
        tNomor = findViewById(R.id.tno);
        btnNext = findViewById(R.id.btnNext);
        rbYa = findViewById(R.id.rbYa);
        rbNo = findViewById(R.id.rbTidak);
        rbGrup = findViewById(R.id.rbGrup);
        imgMusic = findViewById(R.id.imgMusic);
        btnPlay = findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();

                Animation animation = AnimationUtils.loadAnimation(SoalDiagnosisActivity.this, R.anim.zoomin);
                imgMusic.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        imgMusic.startAnimation(AnimationUtils.loadAnimation(SoalDiagnosisActivity.this, R.anim.zoomin));
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

        rbYa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isYes = true;
                isPilih = true;
                if (noSoal == 0) {
                    is1 = true;
                } else if (noSoal == 1) {
                    is2 = true;
                } else if (noSoal == 2) {
                    is3 = true;
                } else if (noSoal == 3) {
                    is4 = true;
                } else if (noSoal == 4) {
                    is5 = true;
                } else if (noSoal == 5) {
                    is6 = true;
                } else if (noSoal == 6) {
                    is7 = true;
                } else if (noSoal == 7) {
                    is8 = true;
                } else if (noSoal == 8) {
                    is9 = true;
                } else if (noSoal == 9) {
                    is10 = true;
                } else if (noSoal == 10) {
                    is11 = true;
                } else if (noSoal == 11) {
                    is12 = true;
                } else if (noSoal == 12) {
                    is13 = true;
                } else if (noSoal == 13) {
                    is14 = true;
                } else if (noSoal == 14) {
                    is15 = true;
                } else if (noSoal == 15) {
                    is16 = true;
                } else if (noSoal == 16) {
                    is17 = true;
                }
            }
        });
        rbNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isYes = false;
                isPilih = true;
                if (noSoal == 0) {
                    is1 = false;
                } else if (noSoal == 1) {
                    is2 = false;
                } else if (noSoal == 2) {
                    is3 = false;
                } else if (noSoal == 3) {
                    is4 = false;
                } else if (noSoal == 4) {
                    is5 = false;
                } else if (noSoal == 5) {
                    is6 = false;
                } else if (noSoal == 6) {
                    is7 = false;
                } else if (noSoal == 7) {
                    is8 = false;
                } else if (noSoal == 8) {
                    is9 = false;
                } else if (noSoal == 9) {
                    is10 = false;
                } else if (noSoal == 10) {
                    is11 = false;
                } else if (noSoal == 11) {
                    is12 = false;
                } else if (noSoal == 12) {
                    is13 = false;
                } else if (noSoal == 13) {
                    is14 = false;
                } else if (noSoal == 14) {
                    is15 = false;
                } else if (noSoal == 15) {
                    is16 = false;
                } else if (noSoal == 16) {
                    is17 = false;
                }
            }
        });
        tNomor.setText(String.valueOf(noSoal + 1) + ".");
        teksPertanyaan.setText(pertanyaan[noSoal]);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbGrup.clearCheck();
                if (isPilih) {
                    isPilih = false;
                    if (isYes) {
                        nomor = nomor+1;
                        StaticVars.arrayDiagnosa.add(String.valueOf(nomor)+". "+keterangan[noSoal]);
                    }
                    noSoal = noSoal + 1;
                    if (noSoal < pertanyaan.length) {
                        teksPertanyaan.setText(pertanyaan[noSoal]);
                        tNomor.setText(String.valueOf(noSoal + 1) + ".");
                    }
                    if (noSoal >= pertanyaan.length - 1) {
                        btnNext.setText("Selesai");
                        btnNext.setTextColor(Color.RED);
                    }

                    if (noSoal < pertanyaan.length) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                play();
                            }
                        }, 300);
                    }

                    if (noSoal >= pertanyaan.length) {
                        if (is1 && is2 && is3 && is4) {
                            if (is5 && is6 && is7 && is8 && is9 && is10 && is11 && is12) {
                                if (is13) {
                                    if (is14) {
                                        if (is15 && is16) {
                                            hasilDiagnosa("Anda Teridentifikasi Tipes", StaticVars.solusiGejala1,StaticVars.arrayDiagnosa);
                                        } else {
                                            hasilDiagnosa("Anda Teridentifikasi Gejala Tipes Minggu Keempat", StaticVars.solusiGejala1, StaticVars.arrayDiagnosa);
                                        }
                                    } else {
                                        hasilDiagnosa("Anda Teridentifikasi Gejala Tipes Minggu Ketiga", StaticVars.solusiGejala1, StaticVars.arrayDiagnosa);
                                    }
                                } else {
                                    hasilDiagnosa("Anda Teridentifikasi Gejala Tipes Minggu Kedua", StaticVars.solusiGejala1, StaticVars.arrayDiagnosa);
                                }
                            } else {
                                hasilDiagnosa("Anda Teridentifikasi Gejala Tipes Minggu Pertama", StaticVars.solusiGejala2, StaticVars.arrayDiagnosa);
                            }
                        } else {
                            hasilDiagnosa("Anda Tidak Teridentifikasi Tipes", StaticVars.solusiTidakTipes, StaticVars.arrayDiagnosa);
                        }
                        finish();
                    }
                } else {
                    isPilih = false;
                    Toast.makeText(SoalDiagnosisActivity.this, "Pilih jawaban Anda terlebih dahulu", Toast.LENGTH_LONG).show();
                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sp = new SoundPool.Builder().setMaxStreams(5).build();
        } else {
            sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        setMusic();
        startAnimationPlay();
        play();
    }

    private void setMusic() {
        ss1 = sp.load(SoalDiagnosisActivity.this, R.raw.sp1, 1);
        ss2 = sp.load(SoalDiagnosisActivity.this, R.raw.sp2, 1);
        ss3 = sp.load(SoalDiagnosisActivity.this, R.raw.sp3, 1);
        ss4 = sp.load(SoalDiagnosisActivity.this, R.raw.sp4, 1);
        ss5 = sp.load(SoalDiagnosisActivity.this, R.raw.sp5, 1);
        ss6 = sp.load(SoalDiagnosisActivity.this, R.raw.sp6, 1);
        ss7 = sp.load(SoalDiagnosisActivity.this, R.raw.sp7, 1);
        ss8 = sp.load(SoalDiagnosisActivity.this, R.raw.sp8, 1);
        ss9 = sp.load(SoalDiagnosisActivity.this, R.raw.sp9, 1);
        ss10 = sp.load(SoalDiagnosisActivity.this, R.raw.sp10, 1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ss11 = sp.load(SoalDiagnosisActivity.this, R.raw.sp11, 1);
                ss12 = sp.load(SoalDiagnosisActivity.this, R.raw.sp12, 1);
                ss13 = sp.load(SoalDiagnosisActivity.this, R.raw.sp13, 1);
                ss14 = sp.load(SoalDiagnosisActivity.this, R.raw.sp14, 1);
                ss15 = sp.load(SoalDiagnosisActivity.this, R.raw.sp15, 1);
                ss16 = sp.load(SoalDiagnosisActivity.this, R.raw.sp16, 1);
                ss17 = sp.load(SoalDiagnosisActivity.this, R.raw.sp17, 1);
                ss18 = sp.load(SoalDiagnosisActivity.this, R.raw.sp18, 1);
                ss19 = sp.load(SoalDiagnosisActivity.this, R.raw.sp19, 1);
                ss20 = sp.load(SoalDiagnosisActivity.this, R.raw.sp20, 1);
            }
        }, 200);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ss21 = sp.load(SoalDiagnosisActivity.this, R.raw.sp21, 1);
                ss22 = sp.load(SoalDiagnosisActivity.this, R.raw.sp22, 1);
                ss23 = sp.load(SoalDiagnosisActivity.this, R.raw.sp23, 1);
                ss24 = sp.load(SoalDiagnosisActivity.this, R.raw.sp24, 1);
                ss25 = sp.load(SoalDiagnosisActivity.this, R.raw.sp25, 1);
                ss26 = sp.load(SoalDiagnosisActivity.this, R.raw.sp26, 1);
                ss27 = sp.load(SoalDiagnosisActivity.this, R.raw.sp27, 1);
                ss28 = sp.load(SoalDiagnosisActivity.this, R.raw.sp28, 1);
                ss29 = sp.load(SoalDiagnosisActivity.this, R.raw.sp29, 1);
                ss30 = sp.load(SoalDiagnosisActivity.this, R.raw.sp30, 1);
            }
        }, 400);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ss31 = sp.load(SoalDiagnosisActivity.this, R.raw.sp31, 1);
                ss32 = sp.load(SoalDiagnosisActivity.this, R.raw.sp32, 1);
                ss33 = sp.load(SoalDiagnosisActivity.this, R.raw.sp33, 1);
                ss34 = sp.load(SoalDiagnosisActivity.this, R.raw.sp34, 1);
                ss35 = sp.load(SoalDiagnosisActivity.this, R.raw.sp35, 1);
                ss36 = sp.load(SoalDiagnosisActivity.this, R.raw.sp36, 1);
                ss37 = sp.load(SoalDiagnosisActivity.this, R.raw.sp37, 1);
                ss38 = sp.load(SoalDiagnosisActivity.this, R.raw.sp38, 1);
                ss39 = sp.load(SoalDiagnosisActivity.this, R.raw.sp39, 1);
                ss40 = sp.load(SoalDiagnosisActivity.this, R.raw.sp40, 1);
            }
        }, 600);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ss41 = sp.load(SoalDiagnosisActivity.this, R.raw.sp41, 1);
                ss42 = sp.load(SoalDiagnosisActivity.this, R.raw.sp42, 1);
                ss43 = sp.load(SoalDiagnosisActivity.this, R.raw.sp43, 1);
                ss44 = sp.load(SoalDiagnosisActivity.this, R.raw.sp44, 1);
                ss45 = sp.load(SoalDiagnosisActivity.this, R.raw.sp45, 1);
                ss46 = sp.load(SoalDiagnosisActivity.this, R.raw.sp46, 1);
                ss47 = sp.load(SoalDiagnosisActivity.this, R.raw.sp47, 1);
                ss48 = sp.load(SoalDiagnosisActivity.this, R.raw.sp48, 1);
                ss49 = sp.load(SoalDiagnosisActivity.this, R.raw.sp49, 1);
                ss50 = sp.load(SoalDiagnosisActivity.this, R.raw.sp50, 1);
            }
        }, 800);
    }

    private void play() {
        switch (noSoal) {
            case 0:
                sp.play(ss1, 1.0f, 1.0f, 0, 0, 1.0f); // ======================== 0 ========================
                break;
            case 1:
                sp.pause(ss1);
                sp.play(ss2, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 2:
                sp.pause(ss2);
                sp.play(ss3, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 3:
                sp.pause(ss3);
                sp.play(ss4, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 4:
                sp.pause(ss4);
                sp.play(ss5, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 5:
                sp.pause(ss5);
                sp.play(ss6, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 6:
                sp.pause(ss6);
                sp.play(ss7, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 7:
                sp.pause(ss7);
                sp.play(ss8, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 8:
                sp.pause(ss8);
                sp.play(ss9, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 9:
                sp.pause(ss9);
                sp.play(ss10, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 10:
                sp.pause(ss10);
                sp.play(ss11, 1.0f, 1.0f, 0, 0, 1.0f); // ========================= 11 ===================
                break;
            case 11:
                sp.pause(ss11);
                sp.play(ss12, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 12:
                sp.pause(ss12);
                sp.play(ss13, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 13:
                sp.pause(ss13);
                sp.play(ss14, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 14:
                sp.pause(ss14);
                sp.play(ss15, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 15:
                sp.pause(ss15);
                sp.play(ss16, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 16:
                sp.pause(ss16);
                sp.play(ss17, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 17:
                sp.pause(ss17);
                sp.play(ss18, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 18:
                sp.pause(ss18);
                sp.play(ss19, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 19:
                sp.pause(ss19);
                sp.play(ss20, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 20:
                sp.pause(ss20);
                sp.play(ss21, 1.0f, 1.0f, 0, 0, 1.0f); // =========================== 21 ==========================
                break;
            case 21:
                sp.pause(ss21);
                sp.play(ss22, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 22:
                sp.pause(ss22);
                sp.play(ss23, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 23:
                sp.pause(ss23);
                sp.play(ss24, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 24:
                sp.pause(ss24);
                sp.play(ss25, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 25:
                sp.pause(ss25);
                sp.play(ss26, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 26:
                sp.pause(ss26);
                sp.play(ss27, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 27:
                sp.pause(ss27);
                sp.play(ss28, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 28:
                sp.pause(ss28);
                sp.play(ss29, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 29:
                sp.pause(ss29);
                sp.play(ss30, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 30:
                sp.pause(ss30);
                sp.play(ss31, 1.0f, 1.0f, 0, 0, 1.0f); // ========================== 31 ==========================
                break;
            case 31:
                sp.pause(ss31);
                sp.play(ss32, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 32:
                sp.pause(ss32);
                sp.play(ss33, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 33:
                sp.pause(ss33);
                sp.play(ss34, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 34:
                sp.pause(ss34);
                sp.play(ss35, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 35:
                sp.pause(ss35);
                sp.play(ss36, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 36:
                sp.pause(ss36);
                sp.play(ss37, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 37:
                sp.pause(ss37);
                sp.play(ss38, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 38:
                sp.pause(ss38);
                sp.play(ss39, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 39:
                sp.pause(ss39);
                sp.play(ss40, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 40:
                sp.pause(ss40);
                sp.play(ss41, 1.0f, 1.0f, 0, 0, 1.0f); // ============================== 41 =======================
                break;
            case 41:
                sp.pause(ss41);
                sp.play(ss42, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 42:
                sp.pause(ss42);
                sp.play(ss43, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 43:
                sp.pause(ss43);
                sp.play(ss44, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 44:
                sp.pause(ss44);
                sp.play(ss45, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 45:
                sp.pause(ss45);
                sp.play(ss46, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 46:
                sp.pause(ss46);
                sp.play(ss47, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 47:
                sp.pause(ss47);
                sp.play(ss48, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 48:
                sp.pause(ss48);
                sp.play(ss49, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            case 49:
                sp.pause(ss49);
                sp.play(ss50, 1.0f, 1.0f, 0, 0, 1.0f);
                break;
            default:
                sp.play(ss1, 1.0f, 1.0f, 0, 0, 1.0f);
                break;

        }
    }

    private void startAnimationPlay() {
        final AnimatorSet animatorSet1 = new AnimatorSet();
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imgMusic, "scaleY", 0.6f);
        scaleY.setDuration(200);
        ObjectAnimator scaleYBack = ObjectAnimator.ofFloat(imgMusic, "scaleY", 1f);
        scaleYBack.setDuration(500);
        scaleYBack.setInterpolator(new BounceInterpolator());
        animatorSet1.setStartDelay(600);
        animatorSet1.playSequentially(scaleY, scaleYBack);
        animatorSet1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet1.setStartDelay(500);
                animatorSet1.start();
            }
        });
        imgMusic.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        animatorSet1.start();
    }

    private void hasilDiagnosa(String hasil, String solusi, ArrayList<String> arrayDiagnosa) {
        SharedPreferences spLogin = getSharedPreferences(StaticVars.SP_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spLogin.edit();
        editor.putString(StaticVars.SP_LOGIN_HASIL_DIAGNOSA, hasil);
        editor.putString(StaticVars.SP_LOGIN_SOLUSI_DIAGNOSA, solusi);
        Set<String> set = new HashSet<>(arrayDiagnosa);
        editor.putStringSet(StaticVars.SP_LOGIN_ARRAY_DIAGNOSA, set);
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
