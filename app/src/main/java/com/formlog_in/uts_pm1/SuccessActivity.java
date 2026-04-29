package com.formlog_in.uts_pm1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        // 1. Inisialisasi TextView dari XML kamu
        TextView tvName = findViewById(R.id.tvResultName);
        TextView tvEmail = findViewById(R.id.tvResultEmail);
        TextView tvPhone = findViewById(R.id.tvResultPhone);
        TextView tvGender = findViewById(R.id.tvResultGender);
        TextView tvSeminar = findViewById(R.id.tvResultSeminar);

        Button btnBack = findViewById(R.id.btnBackHome);
        ImageView ivHome = findViewById(R.id.ivHomeSuccess);

        // 2. Ambil data dari Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Set teks sesuai data yang dikirim
            tvName.setText(extras.getString("USER_NAME"));
            tvEmail.setText(extras.getString("USER_EMAIL"));
            tvPhone.setText(extras.getString("USER_PHONE"));
            tvGender.setText(extras.getString("USER_GENDER"));

            // Format tampilan nama seminar agar lebih manis
            String seminar = extras.getString("SEMINAR_NAME");
            tvSeminar.setText("Seminar \"" + seminar + "\"");
        }

        // 3. Logika kembali ke Dashboard
        View.OnClickListener goHome = v -> {
            Intent intent = new Intent(SuccessActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        };

        btnBack.setOnClickListener(goHome);
        ivHome.setOnClickListener(goHome);
    }
}