package com.android.DeteksiTypus;

/*
 * Created by faozi on 14/03/18.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

public class UserDB extends SQLiteOpenHelper {

    // ===================================== TAMBAH KOMODITAS LAHAN ================================
    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    // ================================================ LAHAN ======================================
    private static final String TABLE_NAME_USER = "user";
    private static final String ID = "ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String NAME = "NAME";
    private static final String UMUR = "UMUR";
    private static final String PASSWORD = "PASSWORD";
    private static final String JENKEL = "JENKEL";

    public UserDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USER + " (" + NAME + " TEXT null, " + USER_NAME +
                    " TEXT null, "+PASSWORD+" TEXT null, "+UMUR+" TEXT null, "+JENKEL+" TEXT null);";
            Log.d("DataKomoditi ", "onCreate: " + sql);
            db.execSQL(sql);
//
//            SharedPreferences spLogin = context.getSharedPreferences(StaticVars.SP_LOGIN, MODE_PRIVATE);
//            SharedPreferences.Editor loginEditor = spLogin.edit();
//            loginEditor.putString(StaticVars.SP_PATH_DB, db.getPath());
//            loginEditor.apply();
        } catch (Exception exp) {
//            FragmentHome.errorDB = String.valueOf(exp);
            exp.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
        onCreate(db);
    }

    public void dropTable() {
        try {
            SQLiteDatabase database = getWritableDatabase();
            String updateQuery = "DROP TABLE IF EXISTS " + TABLE_NAME_USER;
            database.execSQL(updateQuery);
            database.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertUser(String username, String password, String nama, String umur, String jenkel) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            String sql = "INSERT INTO " + TABLE_NAME_USER + " (" + USER_NAME + ", " + PASSWORD + ", "+NAME+", "+UMUR+", "+JENKEL+") VALUES ('"
                    + username + "', '" + password + "', '" + nama + "', '"+umur+"', '"+jenkel+"');";
            db.execSQL(sql);
            RegisterActivity.registerSukses = true;
        } catch (Exception exp) {
            exp.printStackTrace();
            RegisterActivity.registerSukses = false;
        }
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public void cekUser(String username) {
        SQLiteDatabase db = getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("SELECT " + USER_NAME +" FROM " + TABLE_NAME_USER +
                " WHERE " + USER_NAME + "='" + username + "'", null);
        if (cursor.moveToFirst()) {
            do {
                RegisterActivity.isRegistered = cursor.getString(0) != null && username.equals(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        db.close();
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public void login(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_USER +
                " WHERE " + USER_NAME + "='" + username + "' AND "+PASSWORD+" = '"+password+"'", null);
        if (cursor.moveToFirst()) {
            do {
                LoginActivity.isTerdaftar = cursor.getString(1) != null && username.equals(cursor.getString(1));
                LoginActivity.username = cursor.getString(1);
                LoginActivity.nama = cursor.getString(0);
                LoginActivity.umur = cursor.getString(3);
                LoginActivity.jenkel = cursor.getString(4);
            } while (cursor.moveToNext());
        }
        db.close();
    }

}
