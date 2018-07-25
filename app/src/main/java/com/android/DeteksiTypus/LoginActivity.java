package com.android.DeteksiTypus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnRegister;
    EditText editUsername, editPassword;
    public static boolean isTerdaftar = false;
    public static String username = "", nama = "", umur = "", jenkel = "";
    UserDB userDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userDB = new UserDB(this);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        editUsername = findViewById(R.id.username);
        editPassword = findViewById(R.id.textPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
                if (editUsername.getText().toString().equals("") || editPassword.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Isi data dengan lengkap", Toast.LENGTH_LONG).show();
                } else {
                    userDB.login(editUsername.getText().toString(), editPassword.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setMessage("Login ...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog.dismiss();
                            cekDataUser();
                        }
                    }, 3000);
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void cekDataUser() {
        if (isTerdaftar) {
            finish();
            SharedPreferences spLogin = getSharedPreferences(StaticVars.SP_LOGIN, MODE_PRIVATE);
            SharedPreferences.Editor loginEditor = spLogin.edit();
            loginEditor.putString(StaticVars.SP_LOGIN_USERNAME, username);
            loginEditor.putString(StaticVars.SP_LOGIN_NAMA, nama);
            loginEditor.putString(StaticVars.SP_LOGIN_UMUR, umur);
            loginEditor.putString(StaticVars.SP_LOGIN_JENIS_KELAMIN, jenkel);
            loginEditor.putString(StaticVars.SP_LOGIN_SOLUSI_DIAGNOSA, "");
            loginEditor.apply();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } else {
            Toast.makeText(LoginActivity.this, "Login gagal, silahkan coba lagi", Toast.LENGTH_LONG).show();
        }
    }
}
