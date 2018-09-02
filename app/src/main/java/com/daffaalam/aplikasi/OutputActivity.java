package com.daffaalam.aplikasi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class OutputActivity extends AppCompatActivity {

    private TextView tv_name_out, tv_birth_out, tv_gender_out, tv_address_out, tv_phone_out, tv_email_out, tv_religion_out, tv_blood_out, tv_website_out, tv_shoes_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        tv_name_out = findViewById(R.id.tv_name_out);
        tv_birth_out = findViewById(R.id.tv_birth_out);
        tv_gender_out = findViewById(R.id.tv_gender_out);
        tv_address_out = findViewById(R.id.tv_address_out);
        tv_phone_out = findViewById(R.id.tv_phone_out);
        tv_email_out = findViewById(R.id.tv_email_out);
        tv_religion_out = findViewById(R.id.tv_religion_out);
        tv_blood_out = findViewById(R.id.tv_blood_out);
        tv_website_out = findViewById(R.id.tv_website_out);
        tv_shoes_out = findViewById(R.id.tv_shoes_out);

        tv_name_out.setText(getIntent().getStringExtra("NAMA"));
        tv_birth_out.setText(getIntent().getStringExtra("TTL"));
        tv_gender_out.setText(getIntent().getStringExtra("KELAMIN"));
        tv_address_out.setText(getIntent().getStringExtra("ALAMAT"));
        tv_phone_out.setText(getIntent().getStringExtra("HP"));
        tv_email_out.setText(getIntent().getStringExtra("EMAIL"));
        tv_religion_out.setText(getIntent().getStringExtra("AGAMA"));
        tv_blood_out.setText(getIntent().getStringExtra("GOLDAR"));
        tv_website_out.setText(getIntent().getStringExtra("WEB"));
        tv_shoes_out.setText(getIntent().getStringExtra("SEPATU"));
    }

    public void sms(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW).setType("vnd.android-dir/mms-sms").putExtra("address", getIntent().getStringExtra("HP")));
    }

    public void call(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + getIntent().getStringExtra("HP"))));
    }

    public void facebook(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("FB"))));
    }

    public void twitter(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("TWIT"))));
    }

    public void line(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("LINE"))));
    }

    public void telegram(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("TG"))));
    }

    public void instagram(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("IG"))));
    }

    public void whatsapp(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("WA"))));
    }
}