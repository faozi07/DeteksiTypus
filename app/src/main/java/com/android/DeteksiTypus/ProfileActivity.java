package com.android.DeteksiTypus;

import android.*;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kosalgeek.android.photoutil.GalleryPhoto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfileActivity extends AppCompatActivity {

    TextView teksNama, teksUsername, teksUmur, teksJenkel, teksHasilDiagnosa;
    ImageView imgProfil;
    GalleryPhoto galleryPhoto;
    String photoPath = "", strMyImagePath = "";
    int rotate = 0;
    File avatarFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        galleryPhoto = new GalleryPhoto(getApplicationContext());
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Profil");
        }
        teksNama = findViewById(R.id.teksNama);
        teksUmur = findViewById(R.id.teksUmur);
        teksHasilDiagnosa = findViewById(R.id.hasilDiagnosa);
        teksUsername = findViewById(R.id.teksUsername);
        teksJenkel = findViewById(R.id.teksJenkel);
        imgProfil = findViewById(R.id.imgProfil);
        SharedPreferences spLogin = getSharedPreferences(StaticVars.SP_LOGIN, Context.MODE_PRIVATE);
        avatarFile = new File(spLogin.getString(StaticVars.SP_LOGIN_AVATAR,""));
        if (!spLogin.getString(StaticVars.SP_LOGIN_AVATAR, "").equals("")) {
            imgProfil.setImageURI(Uri.fromFile(avatarFile));
        }
        teksUsername.setText(spLogin.getString(StaticVars.SP_LOGIN_USERNAME,""));
        teksNama.setText(spLogin.getString(StaticVars.SP_LOGIN_NAMA,""));
        teksUmur.setText(spLogin.getString(StaticVars.SP_LOGIN_UMUR,""));
        teksJenkel.setText(spLogin.getString(StaticVars.SP_LOGIN_JENIS_KELAMIN,""));
        teksHasilDiagnosa.setText(spLogin.getString(StaticVars.SP_LOGIN_HASIL_DIAGNOSA,""));
        imgProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                            checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        startActivityForResult(galleryPhoto.openGalleryIntent(), 22131);
                    } else {
                        ActivityCompat.requestPermissions(ProfileActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                } else {
                    startActivityForResult(galleryPhoto.openGalleryIntent(), 22131);
                }
            }
        });
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 22131: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivityForResult(galleryPhoto.openGalleryIntent(), 22131);
                    Log.d("permission granted  ", "permission granted");
                } else {
                    Log.d("permission denied  ", "permission denied");
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 22131) {
                Uri avatarUriB = null;
                if (data.getData() == null) {
                    avatarUriB.toString();
                } else {
                    avatarUriB = data.getData();
                }
                galleryPhoto.setPhotoUri(avatarUriB);

                photoPath = galleryPhoto.getPath();
                getCameraPhotoOrientation(ProfileActivity.this, avatarUriB, photoPath);
                decodeFile(photoPath);
                SharedPreferences spLogin = getSharedPreferences(StaticVars.SP_LOGIN, MODE_PRIVATE);
                SharedPreferences.Editor loginEditor = spLogin.edit();
                loginEditor.putString(StaticVars.SP_LOGIN_AVATAR, strMyImagePath);
                loginEditor.apply();
                avatarFile = new File(strMyImagePath);
                imgProfil.setImageURI(Uri.fromFile(avatarFile));
            }
        }
    }

    public void getCameraPhotoOrientation(Context context, Uri imageUri, String imagePath) {
        try {
            context.getContentResolver().notifyChange(imageUri, null);

            ExifInterface exif = new ExifInterface(imagePath);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
                case ExifInterface.ORIENTATION_NORMAL:
                    rotate = 0;
                    break;
                case ExifInterface.ORIENTATION_UNDEFINED:
                    rotate = 0;
                    break;
            }

            Log.i("RotateImage", "Exif orientation: " + orientation);
            Log.i("RotateImage", "Rotate value: " + rotate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String dateToString(Date date) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        return df.format(date);
    }

    private void decodeFile(String path) {
        Bitmap scaledBitmap;
        try {
            Bitmap unscaledBitmap = ScalingUtilities.decodeFile(path, 500, 500, ScalingUtilities.ScalingLogic.FIT);
            scaledBitmap = ScalingUtilities.createScaledBitmap(unscaledBitmap, 500, 500, ScalingUtilities.ScalingLogic.FIT);

            Matrix matrix = new Matrix();
            if (rotate == 90) {
                matrix.setRotate(90);
            } else if (rotate == 270) {
                matrix.setRotate(270);
            } else if (rotate == 180) {
                matrix.setRotate(180);
            }
            Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);

            // Store to tmp file

            String storage = Environment.getExternalStorageDirectory().toString();
            File mFolder = new File(storage + "/DeteksiTipus");
            if (!mFolder.exists()) {
                if (mFolder.mkdir()) {
                    System.out.println("mFolder created");
                } else {
                    System.out.println("mFolder failed to create");
                }
            } else {
                if (mFolder.delete()) {
                    if (mFolder.mkdir()) {
                        System.out.println("mFolder created");
                    } else {
                        System.out.println("mFolder failed to create");
                    }
                } else {
                    System.out.println("mFolder failed to delete");
                }
            }

            String s = dateToString(new Date())+".png";

            File f = new File(mFolder.getAbsolutePath(), s);

            strMyImagePath = f.getAbsolutePath();
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(f);
                rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, 75, fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            } catch (Exception e) {

                e.printStackTrace();
            }

            rotatedBitmap.recycle();
        } catch (Throwable e) {
            Log.e("LoadImage Error", String.valueOf(e));
        }

        if (strMyImagePath == null) {
            strMyImagePath = path;
        }
    }
}
