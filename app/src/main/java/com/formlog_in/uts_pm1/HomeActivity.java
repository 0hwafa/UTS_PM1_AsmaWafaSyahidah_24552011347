package com.formlog_in.uts_pm1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView tvHello = findViewById(R.id.tvHelloUser);
        AppCompatButton btnDaftar = findViewById(R.id.btnGoToRegister);

        // Ambil nama dari intent (dari Login/Register)
        String nama = getIntent().getStringExtra("KEY_NAMA");
        if (nama != null && !nama.isEmpty()) {
            tvHello.setText("Hello, " + nama + "!");
        }

        // Pindah ke Form Pendaftaran Seminar
        btnDaftar.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, FormSeminarActivity.class);
            startActivity(intent);
        });
    }
}