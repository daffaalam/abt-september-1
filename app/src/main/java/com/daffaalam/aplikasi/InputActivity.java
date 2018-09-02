package com.daffaalam.aplikasi;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class InputActivity extends AppCompatActivity {

    private EditText et_name, et_ttl, et_address, et_phone, et_email, et_website, et_facebook, et_twitter, et_line, et_telegram, et_instagram, et_whatsapp;
    private RadioGroup rg_gender, rg_blood;
    private Spinner spin_religion;
    private TextView tv_shoes;

    private Calendar calendar;
    private String error, s_name, s_ttl, s_gender, s_address, s_phone, s_email, s_religion, s_blood, s_website, s_shoes, s_facebook, s_twitter, s_line, s_telegram, s_instagram, s_whatsapp;
    private Integer shoes;

    private Pattern pathFacebook, pathTwitter, pathLine, pathTelegram, pathInstagram, pathWhatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        et_name = findViewById(R.id.et_name);
        et_ttl = findViewById(R.id.et_ttl);
        et_address = findViewById(R.id.et_address);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);
        et_website = findViewById(R.id.et_website);
        et_facebook = findViewById(R.id.et_facebook);
        et_twitter = findViewById(R.id.et_twitter);
        et_line = findViewById(R.id.et_line);
        et_telegram = findViewById(R.id.et_telegram);
        et_instagram = findViewById(R.id.et_instagram);
        et_whatsapp = findViewById(R.id.et_whatsapp);
        rg_gender = findViewById(R.id.rg_gender);
        rg_blood = findViewById(R.id.rg_blood);
        spin_religion = findViewById(R.id.spin_religion);
        tv_shoes = findViewById(R.id.tv_shoes);

        calendar = Calendar.getInstance();
        error = "harap isi bidang ini";

        et_ttl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
            }
        });

        tv_shoes.setText(String.valueOf(40));

        pathFacebook = Pattern.compile("(http|https)://(facebook.com|fb.me)/.*");
        pathTwitter = Pattern.compile("(http|https)://(twitter.com)/.*");
        pathLine = Pattern.compile("(http|https)://(line.me/ti/p/~).*");
        pathTelegram = Pattern.compile("(http|https)://(t.me|telegram.me)/.*");
        pathInstagram = Pattern.compile("(http|https)://(instagram.com)/.*");
        pathWhatsapp = Pattern.compile("(http|https)://(wa.me)/.*");
    }

    private void getDate() {
        new DatePickerDialog(InputActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                et_ttl.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.US).format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void genderChecker() {
        switch (rg_gender.getCheckedRadioButtonId()) {
            case -1:
                s_gender = "";
                break;
            case R.id.rb_gender_man:
                s_gender = "Laki-Laki";
                break;
            case R.id.rb_gender_woman:
                s_gender = "Perempuan";
                break;
        }
    }

    public void decreaseShoes(View v) {
        shoes = Integer.valueOf(tv_shoes.getText().toString());
        if (shoes <= 36) {
            Toast.makeText(this, "ukuran terlalu kecil", Toast.LENGTH_SHORT).show();
        } else {
            tv_shoes.setText(String.valueOf(shoes - 1));
        }
    }

    public void increaseShoes(View v) {
        shoes = Integer.valueOf(tv_shoes.getText().toString());
        if (shoes >= 46) {
            Toast.makeText(this, "ukuran terlalu besar", Toast.LENGTH_SHORT).show();
        } else {
            tv_shoes.setText(String.valueOf(shoes + 1));
        }
    }

    private void bloodChecker() {
        switch (rg_blood.getCheckedRadioButtonId()) {
            case -1:
                s_blood = "";
                break;
            case R.id.rb_blood_a:
                s_blood = "A";
                break;
            case R.id.rb_blood_b:
                s_blood = "B";
                break;
            case R.id.rb_blood_ab:
                s_blood = "AB";
                break;
            case R.id.rb_blood_o:
                s_blood = "O";
                break;
        }
    }

    public void validator(View v) {

        s_name = et_name.getText().toString();
        s_ttl = et_ttl.getText().toString();
        s_address = et_address.getText().toString();
        s_phone = et_phone.getText().toString();
        s_email = et_email.getText().toString();
        s_religion = spin_religion.getSelectedItem().toString();
        s_website = et_website.getText().toString();
        s_shoes = tv_shoes.getText().toString();
        s_facebook = et_facebook.getText().toString();
        s_twitter = et_twitter.getText().toString();
        s_line = et_line.getText().toString();
        s_telegram = et_telegram.getText().toString();
        s_instagram = et_instagram.getText().toString();
        s_whatsapp = et_whatsapp.getText().toString();

        genderChecker();
        bloodChecker();

        if (s_name.isEmpty()) {
            et_name.setError(error);
            et_name.requestFocus();
        } else if (s_ttl.isEmpty()) {
            Toast.makeText(this, "harap pilih tanggal lahir Anda", Toast.LENGTH_SHORT).show();
            getDate();
        } else if (rg_gender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "harap pilih jenis kelamin Anda", Toast.LENGTH_SHORT).show();
        } else if (s_address.isEmpty()) {
            et_address.setError(error);
            et_address.requestFocus();
        } else if (s_phone.isEmpty()) {
            et_phone.setError(error);
            et_phone.requestFocus();
        } else if (!Patterns.PHONE.matcher(s_phone).matches()) {
            et_phone.setError("nomor tidak valid");
            et_phone.requestFocus();
        } else if (s_email.isEmpty()) {
            et_email.setError(error);
            et_email.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(s_email).matches()) {
            et_email.setError("email tidak valid");
            et_email.requestFocus();
        } else if (s_religion.isEmpty()) {
            Toast.makeText(this, "harap pilih agama Anda", Toast.LENGTH_SHORT).show();
        } else if (rg_blood.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "harap pilih golongan darah Anda", Toast.LENGTH_SHORT).show();
        } else if (s_website.isEmpty()) {
            et_website.setError(error);
            et_website.requestFocus();
        } else if (!Patterns.WEB_URL.matcher(s_website).matches()) {
            et_website.setError("website tidak valid");
            et_website.requestFocus();
        } else if (s_facebook.isEmpty()) {
            et_facebook.setError(error);
            et_facebook.requestFocus();
        } else if (!pathFacebook.matcher(s_facebook).matches()) {
            et_facebook.setText(getResources().getString(R.string.text_facebook) + s_facebook);
            et_facebook.requestFocus();
        } else if (s_twitter.isEmpty()) {
            et_twitter.setError(error);
            et_twitter.requestFocus();
        } else if (!pathTwitter.matcher(s_twitter).matches()) {
            et_twitter.setText(getResources().getString(R.string.text_twitter) + s_twitter);
            et_twitter.requestFocus();
        } else if (s_line.isEmpty()) {
            et_line.setError(error);
            et_line.requestFocus();
        } else if (!pathLine.matcher(s_line).matches()) {
            et_line.setText(getResources().getString(R.string.text_line) + s_line);
            et_line.requestFocus();
        } else if (s_telegram.isEmpty()) {
            et_telegram.setError(error);
            et_telegram.requestFocus();
        } else if (!pathTelegram.matcher(s_telegram).matches()) {
            et_telegram.setText(getResources().getString(R.string.text_telegram) + s_telegram);
            et_telegram.requestFocus();
        } else if (s_instagram.isEmpty()) {
            et_instagram.setError(error);
            et_instagram.requestFocus();
        } else if (!pathInstagram.matcher(s_instagram).matches()) {
            et_instagram.setText(getResources().getString(R.string.text_instagram) + s_instagram);
            et_instagram.requestFocus();
        } else if (s_whatsapp.isEmpty()) {
            et_whatsapp.setError(error);
            et_whatsapp.requestFocus();
        } else if (!pathWhatsapp.matcher(s_whatsapp).matches()) {
            et_whatsapp.setText(getResources().getString(R.string.text_whatsapp) + s_whatsapp);
            et_whatsapp.requestFocus();
        } else {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_launcher)
                    .setTitle("Data Anda")
                    .setMessage("" +
                            s_name + "\n" +
                            s_ttl + "\n" +
                            s_gender + "\n" +
                            s_address + "\n" +
                            s_phone + "\n" +
                            s_email + "\n" +
                            s_religion + "\n" +
                            s_blood + "\n" +
                            s_website + "\n" +
                            s_shoes + "\n" +
                            s_facebook + "\n" +
                            s_twitter + "\n" +
                            s_line + "\n" +
                            s_telegram + "\n" +
                            s_instagram + "\n" +
                            s_whatsapp
                    )
                    .setNegativeButton("EDIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(InputActivity.this, OutputActivity.class)
                                    .putExtra("NAMA", s_name)
                                    .putExtra("TTL", s_ttl)
                                    .putExtra("KELAMIN", s_gender)
                                    .putExtra("ALAMAT", s_address)
                                    .putExtra("HP", s_phone)
                                    .putExtra("EMAIL", s_email)
                                    .putExtra("AGAMA", s_religion)
                                    .putExtra("GOLDAR", s_blood)
                                    .putExtra("WEB", s_website)
                                    .putExtra("SEPATU", s_shoes)
                                    .putExtra("FB", s_facebook)
                                    .putExtra("TWIT", s_twitter)
                                    .putExtra("LINE", s_line)
                                    .putExtra("TG", s_telegram)
                                    .putExtra("IG", s_instagram)
                                    .putExtra("WA", s_whatsapp)
                            );
                        }
                    })
                    .show();
        }
    }

    public void clearAll(View v) {
        et_name.setText("");
        et_ttl.setText("");
        rg_gender.clearCheck();
        et_address.setText("");
        et_phone.setText("");
        et_email.setText("");
        spin_religion.setSelection(0);
        rg_blood.clearCheck();
        et_website.setText("");
        tv_shoes.setText(String.valueOf(40));
        et_facebook.setText("");
        et_twitter.setText("");
        et_line.setText("");
        et_telegram.setText("");
        et_instagram.setText("");
        et_whatsapp.setText("");
    }
}
