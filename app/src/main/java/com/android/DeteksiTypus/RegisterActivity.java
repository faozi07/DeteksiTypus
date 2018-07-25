package com.android.DeteksiTypus;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    Spinner spinUmur,spinJk;
    EditText editUsername, editPassword, editNama;
    ArrayAdapter<String> adapterUmur,adapterJk;
    List<String> itemUmur,itemJk;
    Button btnRegister;
    public static boolean isRegistered = false, registerSukses = false;
    UserDB userDB = null;

    @Override
    protected void onResume() {
        super.onResume();
        isRegistered = false;
        registerSukses = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userDB = new UserDB(this);
        final SQLiteDatabase sqlDb = userDB.getWritableDatabase();
        userDB.onCreate(sqlDb);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Daftar");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        itemUmur = new ArrayList<>();
        for (int i=0;i<=100;i++) {
            itemUmur.add(String.valueOf(i) +" tahun");
        }
        itemJk = new ArrayList<>();
        itemJk.add("Laki-laki");
        itemJk.add("Perempuan");
        spinUmur = findViewById(R.id.spinUmur);
        spinJk = findViewById(R.id.spinJk);
        adapterUmur = new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, itemUmur);
        adapterUmur.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapterJk = new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, itemJk);
        adapterJk.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinUmur.setAdapter(adapterUmur);
        spinJk.setAdapter(adapterJk);
        editNama = findViewById(R.id.nama);
        editPassword = findViewById(R.id.textPassword);
        editUsername = findViewById(R.id.username);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }

                if (editNama.getText().toString().equals("") || editPassword.getText().toString().equals("") ||
                        editUsername.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Isi data dengan lengkap", Toast.LENGTH_LONG).show();
                } else {
                    userDB.cekUser(editUsername.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                    progressDialog.setMessage("Mendaftar ...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (isRegistered) {
                                Toast.makeText(RegisterActivity.this, "User sudah terdaftar, gunakan data user lain", Toast.LENGTH_LONG).show();
                            } else {
                                userDB.insertUser(editUsername.getText().toString(), editPassword.getText().toString(), editNama.getText().toString(),
                                        spinUmur.getSelectedItem().toString(), spinJk.getSelectedItem().toString());
                            }
                        }
                    }, 2000);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            if (registerSukses) {
                                finish();
                                Toast.makeText(RegisterActivity.this, "Berhasil Daftar, silahkan login", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Gagal Daftar, silahkan coba lagi", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, 4000);
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}
